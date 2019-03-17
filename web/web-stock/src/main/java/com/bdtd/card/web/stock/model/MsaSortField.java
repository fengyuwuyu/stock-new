package com.bdtd.card.web.stock.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdtd.card.common.util.MapUtil;

public enum MsaSortField {

	FIRST_LEVEL_INCREASE("first_level_increase", "firstLevelIncrease"), 
	SECOND_LEVEL_INCREASE("second_level_increase", "secondLevelIncrease"), 
	THIRD_LEVEL_INCREASE("third_level_increase", "third_level_increase"), 
	INCREASE("increase", "increase"), 
	TWO_INCREASE("two_increase", "twoIncrease"), 
	THREE_INCREASE("thress_increase", "thressIncrease"), 
	FOUR_INCREASE("four_increase", "fourIncrease"), 
	FIVE_INCREASE("five_increase", "fiveIncrease"), 
	TEM_INCREASE("ten_increase", "tenIncrease"), 
	FIFTEEN_INCREASE("fifteen_increase", "fifteenIncrease"), 
	TWENTY_INCREASE("twenty_increase", "twentyIncrease"), 
	MAX_INCREASE("max_increase", "maxIncrease"), 
	FUTURE_FIVE_DAY_INCREASE("future_five_day_increase", "futureFiveDayIncrease"), 
	FUTURE_TEN_DAY_INCREASE("future_ten_day_increase", "futureTenDayIncrease"), 
	FUTURE_FIFTEEN_DAY_INCREASE("future_fifteen_day_increase", "futureFifteenDayIncrease"), 
	FUTURE_TWENTY_DAY_INCREASE("future_twenty_day_increase", "futureTwentyDayIncrease"), 
	DAY_VOLUME_AVY("day_volume_avg", "dayVolumeAvg"), 
	TWO_VOLUME_AVY("two_volume_avg", "twoVolumeAvg"), 
	THREE_VOLUME_AVY("three_volume_avg", "threeVolumeAvg"), 
	FOUR_VOLUME_AVY("four_volume_avg", "fourVolumeAvg"), 
	FIVE_VOLUME_AVY("five_volume_avg", "fiveVolumeAvg"), 
	;

	private MsaSortField(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private final static Map<String, MsaSortField> CACHE = new HashMap<>();
	private final static List<Map<String, Object>> ITEM_LIST = new ArrayList<>();

	static {
		for (MsaSortField type : MsaSortField.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = MapUtil.createMap("id", type.getType(), "name", type.getDesc());
			ITEM_LIST.add(map);
		}
	}

	private String type;
	private String desc;

	public static MsaSortField fromType(String type) {
		return CACHE.get(type);
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
	public static List<Map<String, Object>> select() {
		return ITEM_LIST;
	}

}
