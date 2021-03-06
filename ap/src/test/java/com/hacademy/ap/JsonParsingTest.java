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
				grade = "??????";
			}
			else if(classValue.contains("col_rare")) {
				grade = "??????";
			}
			else if(classValue.contains("col_legend")) {
				grade = "??????";
			}
			else if(classValue.contains("col_unique")) {
				grade = "??????";
			}
			else if(classValue.contains("col_epic")) {
				grade = "??????";
			}
			else if(classValue.contains("col_mythic")) {
				grade = "??????";
			}
			else if(classValue.contains("col_finality")) {
				grade = "??????";
			}
		}
		AionItemJsonWrapper weapon = AionItemJsonWrapper.builder()
													.code(line[0])
													.name(name)
													.type("??????")
													.subType("??????")
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
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "??????", "??????");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
//	@Test
	public void sword() throws IOException {
		String type = "sword";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "??????", "??????");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
//	@Test
	public void mace() throws IOException {
		String type = "mace";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "??????", "??????");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
//	@Test
	public void staff() throws IOException {
		String type = "staff";
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", type+".txt")), "??????", "??????");
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", type+".json"), list);
	}
	
	public void load(String enType, String korType, String korSubType, String requiredGrade) throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException {
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", enType+".txt")), korType, korSubType, requiredGrade);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", enType+".json"), list);
	}
	
	public void load(String enType, String korType, String korSubType, String requiredGrade, int minLevel, int maxLevel) throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException {
		List<AionItemJsonWrapper> list = parser.parse(parser.read(new File("temp", enType+".txt")), korType, korSubType, requiredGrade, minLevel, maxLevel);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("temp", enType+".json"), list);
	}
	
	@Test
	public void loadAll() throws JsonMappingException, JsonProcessingException, FileNotFoundException, IOException {
		load("dagger", "??????", "??????", "??????", 1, 60);
		load("sword", "??????", "??????", "??????", 1, 60);
		load("mace", "??????", "??????", "??????", 1, 60);
		load("staff", "??????", "??????", "??????", 1, 60);
		load("spellbook", "??????", "??????", "??????", 1, 60);
		load("orb", "??????", "??????", "??????", 1, 60);
		load("greatsword", "??????", "??????", "??????", 1, 60);
		load("polearm", "??????", "???", "??????", 1, 60);
		load("bow", "??????", "???", "??????", 1, 60);
		load("aether_revolver", "??????", "?????????", "??????", 1, 60);
		load("aether_cannon", "??????", "?????????", "??????", 1, 60);
		load("stringed_instrument", "??????", "?????????", "??????", 1, 60);
		load("aether_key", "??????", "?????????", "??????", 1, 60);
		load("paint_rings", "??????", "??????", "??????", 1, 60);
//		load("clothes", "?????????", "??????", "??????", 1, 60);
		load("cloth-1", "?????????", "??????", "??????", 1, 60);
		load("cloth-2", "?????????", "??????", "??????", 1, 60);
		load("leather-1", "?????????", "??????", "??????", 1, 60);
		load("leather-2", "?????????", "??????", "??????", 1, 60);
		load("chain-1", "?????????", "??????", "??????", 1, 60);
		load("chain-2", "?????????", "??????", "??????", 1, 60);
		load("plate-1", "?????????", "??????", "??????", 1, 60);
		load("plate-2", "?????????", "??????", "??????", 1, 60);
		load("shield", "?????????", "??????", "??????", 1, 60);
	}
	
}
