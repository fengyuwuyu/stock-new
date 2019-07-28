package com.bdtd.card.data.stock.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.card.common.util.MapUtil;

public enum StockType {

	INCREASE(1, "一路上涨"), 
	DECREASE(2, "高位下跌"), 
	REBOUND(3, "下跌后反弹"), 
	All(0, "全部"),
	;

	private StockType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private final static Map<Integer, StockType> CACHE = new HashMap<>();
	private final static List<Map<String, Object>> ITEM_LIST = new ArrayList<>();

	static {
		for (StockType type : StockType.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = MapUtil.createMap("id", type.getType(), "name", type.getDesc());
			ITEM_LIST.add(map);
		}
	}

	private int type;
	private String desc;

	public static StockType valueOf(Integer type) {
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
