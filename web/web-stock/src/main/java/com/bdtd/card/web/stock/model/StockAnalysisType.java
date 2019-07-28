package com.bdtd.card.web.stock.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.card.common.util.MapUtil;

public enum StockAnalysisType {

	HIGH_INCREASE(1, "高开下跌并反弹"), 
	HIGH_DECREASE_RISE(2, "高开下跌并反弹"), 
	HIGH_INCREASE_DECREASE_RISE(3, "高开上升下跌并反弹"), 
	MID_INCREASE(4, "正常开盘上升"), 
	MID_DECREASE_RISE(5, "正常开盘下跌并反弹"), 
	MID_INCREASE_DECREASE_RISE(6, "正常开盘上升下跌并反弹"), 
	LOW_INCREASE(7, "低开上升"), 
	LOW_DECREASE_RISE(8, "低开下跌并反弹"), 
	LOW_INCREASE_DECREASE_RISE(9, "低开上升下跌并反弹"), 
	;

	private StockAnalysisType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private final static Map<Integer, StockAnalysisType> CACHE = new HashMap<>();
	private final static List<Map<String, Object>> ITEM_LIST = new ArrayList<>();

	static {
		for (StockAnalysisType type : StockAnalysisType.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = MapUtil.createMap("id", type.getType(), "name", type.getDesc());
			ITEM_LIST.add(map);
		}
	}

	private int type;
	private String desc;

	public static StockAnalysisType valueOf(Integer type) {
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
