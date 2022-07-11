package com.hacademy.ap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacademy.ap.util.AionItemJsonParser;
import com.hacademy.ap.util.AionItemJsonResponseVO;
import com.hacademy.ap.util.AionItemJsonWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JsonParsingTest {
	
	File target = new File("./temp/dagger.txt");
	
	String origin;
	
//	@BeforeEach
	public void before() throws FileNotFoundException {
		StringBuffer buffer = new StringBuffer();
		Scanner sc = new Scanner(target);
		while(sc.hasNextLine()) {
			buffer.append(sc.nextLine());
		}
		sc.close();
	
		origin = buffer.toString().replace("\\ \"", "\\\"");
		System.out.println("read finish");
	}
	
	
//	@Test
	public void test() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map map = mapper.readValue(origin, Map.class);
		List list = (List)map.get("data");
		System.out.println(list.size());
		System.out.println(list.get(0).getClass());
		List subList = (List)list.get(0);
		System.out.println(subList.size());
		int cnt = 0;
		for(Object o : subList) {
			String str = (String)o;
			str = str.trim();
			if(str.isEmpty()) continue;
			System.out.println(++cnt);
			System.out.println(str);
		}
	}
	
//	@Test
	public void parse() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		AionItemJsonResponseVO vo = mapper.readValue(origin, AionItemJsonResponseVO.class);
		String[][] data = vo.getData();
		
		String[] line = data[5];
		
		for(int i=0; i < line.length; i++) {
			System.out.println(i + " = " + line[i]);
		}
		
		String regex = "<span><a.*?class=\"(.*?)\".*?>(.*?)<\\/a><\\/span>";
		Matcher match = Pattern.compile(regex).matcher(line[2]);
		String name = null;
		String grade = null;
		if(match.find()) {
			System.out.println(match.group());
			System.out.println(match.group(1));
			System.out.println(match.group(2));
			name = match.group(2);
			
			String classValue = match.group(1);
			if(classValue.contains("col_common")) {
				grade = "일반";
			}
			else if(classValue.contains("col_rare")) {
				grade = "희귀";
			}
			else if(classValue.contains("col_legend")) {
				grade = "전승";
			}
			else if(classValue.contains("col_unique")) {
				grade = "유일";
			}
			else if(classValue.contains("col_epic")) {
				grade = "영웅";
			}
			else if(classValue.contains("col_mythic")) {
				grade = "신화";
			}
			else if(classValue.contains("col_finality")) {
				grade = "궁극";
			}
		}
		AionItemJsonWrapper weapon = AionItemJsonWrapper.builder()
													.code(line[0])
													.name(name)
													.type("무기")
													.subType("단검")
													.grade(grade)
													.level(Integer.parseInt(line[3]))
												.build();
		
		System.out.println(weapon);
		
	}
	
	@Autowired
	private AionItemJsonParser parser;
	
//	@Test
	public void dagger() throws IOException {
		String type = "dagger";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "무기", "단검");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
//	@Test
	public void sword() throws IOException {
		String type = "sword";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "무기", "장검");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
//	@Test
	public void mace() throws IOException {
		String type = "mace";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "무기", "전곤");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
//	@Test
	public void staff() throws IOException {
		String type = "staff";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "무기", "법봉");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
	public void load(String enType, String korType, String korSubType, String requiredGrade) throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException {
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", enType+".txt")), korType, korSubType, requiredGrade);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", enType+".json"), list);
	}
	
	@Test
	public void loadAll() throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException {
		load("dagger", "무기", "단검", "유일");
		load("sword", "무기", "장검", "유일");
		load("mace", "무기", "전곤", "유일");
		load("staff", "무기", "법봉", "유일");
		load("spellbook", "무기", "법서", "유일");
		load("orb", "무기", "보옥", "유일");
		load("greatsword", "무기", "대검", "유일");
		load("polearm", "무기", "창", "유일");
		load("bow", "무기", "활", "유일");
		load("aether_revolver", "무기", "마력총", "유일");
		load("aether_cannon", "무기", "마력포", "유일");
		load("stringed_instrument", "무기", "현악기", "유일");
		load("aether_key", "무기", "기동쇠", "유일");
		load("paint_rings", "무기", "화구", "유일");
//		load("clothes", "방어구", "의복", "유일");
		load("cloth-1", "방어구", "로브", "유일");
		load("cloth-2", "방어구", "로브", "유일");
		load("leather-1", "방어구", "가죽", "유일");
		load("leather-2", "방어구", "가죽", "유일");
		load("chain-1", "방어구", "사슬", "유일");
		load("chain-2", "방어구", "사슬", "유일");
		load("plate-1", "방어구", "판금", "유일");
		load("plate-2", "방어구", "판금", "유일");
		load("shield", "방어구", "방패", "유일");
	}
	
}
