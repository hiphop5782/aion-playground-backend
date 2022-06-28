package com.hacademy.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Item {
	private String name;
	private int level;
	private String grade;
	
	public String getReplaceName() {
		return name.toLowerCase().replace(" ", "");
	}
}
