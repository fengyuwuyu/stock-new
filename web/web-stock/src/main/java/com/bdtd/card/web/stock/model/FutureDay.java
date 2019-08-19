package com.bdtd.card.web.stock.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdtd.card.common.util.MapUtil;

public enum FutureDay {

	ONE_WEEK(5, "一周"), 
	TWO_WEEK(10, "两周"), 
	THREE_WEEK(15, "三周"), 
	FOUR_WEEK(20, "四周"), 
	;

	private FutureDay(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private final static Map<Integer, FutureDay> CACHE = new HashMap<>();
	private final static List<Map<String, Object>> ITEM_LIST = new ArrayList<>();

	static {
		for (FutureDay type : FutureDay.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = MapUtil.createMap("id", type.getType(), "name", type.getDesc());
			ITEM_LIST.add(map);
		}
	}

	private int type;
	private String desc;

	public static FutureDay valueOf(Integer type) {
		return CACHE.get(type);
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
	public static List<Map<String, Object>> select() {
		return ITEM_LIST;
	}

}
