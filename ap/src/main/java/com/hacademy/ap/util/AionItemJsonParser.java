package com.hacademy.ap.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Component
public class AionItemJsonParser {
	private ObjectMapper mapper = new ObjectMapper();
	
	public String read(File target) throws FileNotFoundException, IOException {
		StringBuffer sb = new StringBuffer();
		try(
			FileReader in = new FileReader(target);
			BufferedReader buffer = new BufferedReader(in);
		) {
			while(true) {
				String line = buffer.readLine();
				if(line == null) break;
				sb.append(line.replace("\\ \"", "\\\"").trim());
			}
		}
		return sb.toString();
	}
	
	public List<AionItemJsonWrapper> parse(String json, String type, String subType) throws JsonMappingException, JsonProcessingException{
		return parse(json, type, subType, "일반");
	}
	public List<AionItemJsonWrapper> parse(String json, String type, String subType, String requiredGrade) throws JsonMappingException, JsonProcessingException{
		AionItemJsonResponseVO responseVO = mapper.readValue(json, AionItemJsonResponseVO.class);
		List<AionItemJsonWrapper> list = new ArrayList<>();
		
		for(String[] row : responseVO.getData()) {
			try {
				list.add(parseLine(row, type, subType, requiredGrade));
			}
			catch(RuntimeException e) {
				//skip
			}
		}
		return list;
	}
	
	private AionItemJsonWrapper parseLine(String[] row, String type, String subType, String requiredGrade) {
		NameAndGrade ng = parseNameAndGrade(row[2], requiredGrade);
		return AionItemJsonWrapper.builder()
				.code(row[0])
				.name(ng.name)
				.type(type)
				.subType(subType)
				.grade(ng.grade)
				.level(Integer.parseInt(row[3]))
			.build();
	}
	
	@Data @AllArgsConstructor @Builder
	static class NameAndGrade {
		String name, grade;
	}
	
	private String[] classList = new String[] {
		"col_common", "col_rare", "col_legend", "col_unique", "col_epic", "col_mythic", "col_finality"
	};
	private String[] gradeList = new String[] {
		"일반", "희귀", "전승", "유일", "영웅", "신화", "궁극"	
	};
	
	private NameAndGrade parseNameAndGrade(String origin, String requiredGrade) {
		String regex = "<span><a.*?class=\"(.*?)\".*?>(.*?)<\\/a><\\/span>";
		Matcher match = Pattern.compile(regex).matcher(origin);
		if(!match.find()) throw new RuntimeException("grade not exist");
		
		int idx = Arrays.binarySearch(gradeList, requiredGrade);
		if(idx < 0) throw new RuntimeException("invalid required grade");
		
		String grade = null;
		String classValue = match.group(1);
		for(int i = idx; i < classList.length; i++) {
			if(classValue.contains(classList[i])) {
				grade = gradeList[i];
				break;
			}
		}
		
		if(grade == null) throw new RuntimeException("not enough grade");
		
		return NameAndGrade.builder().grade(grade).name(match.group(2)).build();
	}
}
