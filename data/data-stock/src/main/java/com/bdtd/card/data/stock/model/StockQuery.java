package com.bdtd.card.data.stock.model;

import java.sql.Date;

/**
 * @author lilei
 * 
 */
public class StockQuery extends PageModel {

	private Date begin;
	private Date end;
	private Boolean down = true;
	private String symbol;
	private Integer type;
	private float minIncrease;
	private int minVolume;
	private int remainDays;
	private int order;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Boolean getDown() {
		return down;
	}

	public void setDown(Boolean down) {
		this.down = down;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getMinIncrease() {
		return minIncrease;
	}

	public void setMinIncrease(float minIncrease) {
		this.minIncrease = minIncrease;
	}

	public int getMinVolume() {
		return minVolume;
	}

	public void setMinVolume(int minVolume) {
		this.minVolume = minVolume;
	}

	public int getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(int remainDays) {
		this.remainDays = remainDays;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "StockQuery [begin=" + begin + ", end=" + end + ", down=" + down
				+ ", symbol=" + symbol + ", type=" + type + ", minIncrease="
				+ minIncrease + ", minVolume=" + minVolume + ", remainDays="
				+ remainDays + ", order=" + order + "]";
	}

}
