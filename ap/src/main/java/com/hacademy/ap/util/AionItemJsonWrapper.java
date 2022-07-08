package com.hacademy.ap.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AionItemJsonWrapper {
	private String name;
	private int level;
	private String grade;
	private String type;
	private String subType;
	private String code;
	private boolean trade;
	private int enchantLevel;
}
