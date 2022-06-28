package com.hacademy.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CharacterStigmaInformation {
	private int enchantCount;
	private String image;
	private int itemId;
	private String name;
	private String quality;
	private int stigmaSlotNumber;
	private String stigmaSlotType;
}
