package com.bdtd.card.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MonitorStatus {

	CREATED(1, "新增"),
	NOTIFIED(2, "已通知"),
	DELETED(3, "已删除"),
	;
	
	private int type;
	private String desc;
	private MonitorStatus(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public String getDesc() {
		return desc;
	}
	
	private static final Map<Integer, MonitorStatus> CACHE = new HashMap<>(MonitorStatus.values().length);
	private static final List<Map<String, Object>> ITEM_LIST = new ArrayList<>(MonitorStatus.values().length);
	
	static {
		for (MonitorStatus type : MonitorStatus.values()) {
			CACHE.put(type.getType(), type);
			Map<String, Object> map = new HashMap<>(2);
			map.put("id", type.getType());
			map.put("name", type.getDesc());
			ITEM_LIST.add(map);
		}
		
		
	}
	
	public static MonitorStatus typeOf(int type) {
		return CACHE.get(type);
	}
	
	public static List<Map<String, Object>> select() {
		return ITEM_LIST;
	}
}
