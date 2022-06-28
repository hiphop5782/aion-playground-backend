package com.hacademy.ap.entity;

public enum ItemType implements Comparable<ItemType>{
	//category1
	WEAPON, ARMOR, ACCESSORY, WING,
	
	//category2
	SWORD, DAGGER, TWOHANDSWORD,
	POLEARM, 
	BOW,
	STAFF, 
	MACE, 
	SHIELD,
	ORB, BOOK,
	PLATE, CHAIN, LEATHER, ROBE, 
	HEAD, NECK, EAR, FINGER, WAIST,
	
	//category3
	TORSO, LEG, SHOULDER, HAND, FOOT,
	
	//etc
	NOVALUE
	;

	public boolean isEnchantEnable() {
		switch(this) {
		case ACCESSORY: case WING: case HEAD: case NECK: case EAR: case FINGER: case WAIST: case NOVALUE:
			return false;
		default:
			return true;
		}
	}
}
