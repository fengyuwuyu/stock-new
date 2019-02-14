package com.bdtd.card.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MonitorType {

	BUY(1, "买"),
	SELL(2, "卖"),
	;
	
	private int type;
	private String desc;
	private MonitorType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
	
	private static final Map<Integer, MonitorType> CACHE = new HashMap<>(MonitorType.values().length);
	private static final List<Map<String, Object>> ITEM_LIST = new ArrayList<>(MonitorType.values().length);
	
	static {
		for (MonitorType type : MonitorType.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = new HashMap<>(2);
			map.put("id", type.getType());
			map.put("name", type.getDesc());
			ITEM_LIST.add(map);
		}
		
		
	}
	
	public static MonitorType typeOf(int type) {
		return CACHE.get(type);
	}
	
	public static List<Map<String, Object>> select() {
		return ITEM_LIST;
	}
}
