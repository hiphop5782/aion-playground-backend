package com.hacademy.ap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterAbyssInformation {
	private int abyssPoint;
	private int bestRankId;
	private String bestRankName;
	private int gloryPoint;
	private int lastWeekAbyssPoint;
	private int lastWeekGloryPoint;
	private int lastWeekKillCount;
	private int oldRanking;
	private int rankId;
	private String rankName;
	private int ranking;
	private int thisWeekAbyssPoint;
	private int thisWeekGloryPoint;
	private int thisWeekKillCount; 
	private int todayAbyssPoint; 
	private int todayGloryPoint; 
	private int todayKillCount; 
	private int totalKillCount; 
}
