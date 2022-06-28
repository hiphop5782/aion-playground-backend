package com.hacademy.ap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterEquipmentsInformation {
	private String attackType;
	private int authorizeCount;
	private int enchantCount;
	private int equipSlotType;
	private int itemId;
	private int level;
	private String quality;
	private String image;
	private String name;
	private EquipmentCategory category1;
	private EquipmentCategory category2;
	private EquipmentCategory category3;
	
	public boolean isHighEnchant() {
		return enchantCount >= 10;
	}
}
