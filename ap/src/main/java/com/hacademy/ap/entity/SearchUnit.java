package com.hacademy.ap.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SearchUnit {
	private int charId;				private String charName;
	private int serverId;			private String serverName;
	private int classId;			private String className;
	private int legionId;			private String legionName;
	private int gender;
	private int level;
	private int raceId;				private String raceName;
	private String weapon;
	private String weaponInfo;
	private String armor;
	private String armorInfo;
	private String accessary;
	private String accessaryInfo;
	private String stigma;
	private String stigmaInfo;
	private String equipment;
	private String profileImg;
	private String wearing;
	private String wearingInfo;
}
