package com.hacademy.ap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterTotalStatusInformation {
	//체력
	private int hp, mp;

	//왼손
	private int physicalLeft, accuracyLeft, criticalLeft;
	
	//오른손
	private int physicalRight, accuracyRight, criticalRight;
	
	//마법
	private int magicalBoost, magicalAccuracy, magicalCriticalRight;
	
	//방패방어, 무기방어
	private int block, parry;
	
	//마저
	private int magicResist;
	
	//속성저항
	private int airResist, fireResist, earthResist, waterResist;
	
	//회피
	private int dodge;
	
	//물리방어
	private int physicalDefend;
	
	//물치저, 마치저
	private int phyCriticalReduceRate, magCriticalReduceRate;
}
