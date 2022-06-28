package com.hacademy.ap.entity;

import java.util.List;

import com.hacademy.ap.util.NoValueException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ItemData {
	private String version;
	private List<Item> items;
	
	public int getLevelByName(String itemName) {
		itemName = itemName.toLowerCase().replace(" ", "");
		for(Item item : items) {
			if(itemName.equals(item.getReplaceName())) {
				return item.getLevel();
			}
		}
		throw new NoValueException("not found item");
	}
	
	public String getGradeByName(String itemName) {
		itemName = itemName.toLowerCase().replace(" ", "");
		for(Item item : items) {
			if(itemName.equals(item.getReplaceName())) {
				return item.getGrade();
			}
		}
		throw new NoValueException("not found item");
	}
	
	public Item getItemByName(String itemName) {
		itemName = itemName.toLowerCase().replace(" ", "");
		for(Item item : items) {
			if(itemName.equals(item.getReplaceName())) {
				return item;
			}
		}
		throw new NoValueException("not found item");
	}
}
