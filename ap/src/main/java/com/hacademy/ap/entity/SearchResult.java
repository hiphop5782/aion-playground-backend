package com.hacademy.ap.entity;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SearchResult {
	private String collectionId;
	private int totalCount;
	private int resultCount;
	private List<SearchUnit> documents;
	
	public SearchResult filterAndSort() {
		this.documents = documents.stream().filter(unit->{
			switch(unit.getServerName()) {
			case "가디언":
			case "아칸":
				return false;
			}
			return true;
		}).sorted((a, b)->{
			return b.getLevel() - a.getLevel();
		}).collect(Collectors.toList());
		return this;
	}
}
