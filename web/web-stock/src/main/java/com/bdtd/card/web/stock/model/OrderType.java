package com.bdtd.card.web.stock.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.card.common.util.MapUtil;

public enum OrderType {

	ASC(1, "升序"), 
	DESC(2, "降序"), 
	;

	private OrderType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private final static Map<Integer, OrderType> CACHE = new HashMap<>();
	private final static List<Map<String, Object>> ITEM_LIST = new ArrayList<>();

	static {
		for (OrderType type : OrderType.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = MapUtil.createMap("id", type.getType(), "name", type.getDesc());
			ITEM_LIST.add(map);
		}
	}

	private int type;
	private String desc;

	public static OrderType valueOf(Integer type) {
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
