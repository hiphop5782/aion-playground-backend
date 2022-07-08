package com.hacademy.ap.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties
@Data
public class AionItemJsonResponseVO {
	private int draw;
	private int recordsTotal, recordsFiltered;
	
	private String[][] data;
	//data[0] = item code
	//data[1] = item image html
	//data[2] = item name html
	//data[3] = item 
	//data[4] = empty
	//data[5] = equip level
	//data[6] = empty (maybe type)
	//data[7] = version
	//data[8] = empty
}
