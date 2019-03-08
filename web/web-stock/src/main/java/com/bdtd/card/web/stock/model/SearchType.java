package com.bdtd.card.web.stock.model;

import java.util.HashMap;
import java.util.Map;

public enum SearchType {

	MAKE_MONEY(-1, "必赚"), 
	MAKE_MONEY1(-2, "必赚1"), 
	MAX_INCREASE(1, "最大涨幅"), 
	SERIAL_LOW_VOLUME(2, "连续低成交量"), 
	DECREASE_AND_SERIAL_LOW_VOLUME(4, "下跌且低成交量"), 
	SERIAL_INCREASE(8, "连续上涨"), 
	SERIAL_INCREASE_AND_LOW_VOLUME(16, "上涨且低成交量"), 
	NEARLY_TEN_DAY(32, "近十天"), 
	HISTORY_INCREASE(33, "历史涨幅"),
	PRICE_LOW_SHOCK(34, "股价最小波动"),
	MAX_RECENT_INCREASE(35, "近几天最大涨幅"),
	;

	private SearchType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	private final static Map<Integer, SearchType> CACHE = new HashMap<>();

	static {
		for (SearchType type : SearchType.values()) {
			CACHE.put(type.getType(), type);
		}
	}

	private int type;
	private String desc;

	public static SearchType valueOf(Integer type) {
		return CACHE.get(type);
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

}
