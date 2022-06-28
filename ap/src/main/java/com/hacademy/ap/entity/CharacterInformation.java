package com.hacademy.ap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterInformation {
	private String world;
	private int gameUid;
	private int characterId;		private String characterName;
	private int serverId;			private String serverName;
	private int jobId;				private String jobName;
	private int guildId;			private String guildName;
	private int genderId;			private String genderName;
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
	private String profileImageUrl;
	private String wearing;
	private String wearingInfo;
}
