package com.hacademy.ap.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CharacterDetailInformation {
	private List<CharacterEquipmentsInformation> character_equipments;
	private CharacterAbyssInformation character_abyss;
	private CharacterStatusInformation character_stats;
	private List<CharacterStigmaInformation> character_stigma;
	
	public CharacterDetailInformation sort() {
		character_equipments.sort((a, b)->{
			ItemType aType = ItemType.valueOf(a.getCategory1().getAlias());
			ItemType bType = ItemType.valueOf(b.getCategory1().getAlias());
			if(aType == bType) {
				aType = ItemType.valueOf(a.getCategory2().getAlias());
				bType = ItemType.valueOf(b.getCategory2().getAlias());
				if(aType == bType) {
					aType = ItemType.valueOf(a.getCategory3().getAlias());
					bType = ItemType.valueOf(b.getCategory3().getAlias());
				}
			}
			return aType.ordinal() - bType.ordinal();
		});
		character_stigma.sort((a, b)->{
			StigmaType aType = StigmaType.valueOf(a.getQuality().toUpperCase());
			StigmaType bType = StigmaType.valueOf(b.getQuality().toUpperCase());
			if(aType == bType) {
				return b.getStigmaSlotNumber() - a.getStigmaSlotNumber();
			}
			return bType.ordinal() - aType.ordinal();
		});
		return this;
	}
}



