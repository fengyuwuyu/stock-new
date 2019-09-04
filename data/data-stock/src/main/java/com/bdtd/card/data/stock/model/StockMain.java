package com.bdtd.card.data.stock.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StockMain implements Comparable<StockMain> {
	private Integer id;

	private String symbol;

	@JsonFormat(pattern = "yyyyMMdd", timezone = "GMT+8")
	private Date day;

	private Float open;

	private Float close;

	private Float max;

	private Float min;

	private Long volume;

	private Float increase;

	private Float maxIncrease;
	private Float fluctuate;
	private Integer lowerShadow;

	public StockMain() {
	}

	public StockMain(String symbol, Float open, Float close, Long volume, Float increase) {
		this.symbol = symbol;
		this.open = open;
		this.close = close;
		this.volume = volume;
		this.increase = increase;
	}

	public StockMain(String symbol, Date day, Float open, Float close, Float max, Float min, Long volume,
			Float increase) {
		this.symbol = symbol;
		this.day = day;
		this.open = open;
		this.close = close;
		this.max = max;
		this.min = min;
		this.volume = volume;
		this.increase = increase;
	}

	public Integer getLowerShadow() {
		return lowerShadow;
	}

	public void setLowerShadow(Integer lowerShadow) {
		this.lowerShadow = lowerShadow;
	}

	public Float getFluctuate() {
		return fluctuate;
	}

	public void setFluctuate(Float fluctuate) {
		this.fluctuate = fluctuate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol == null ? null : symbol.trim();
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Float getOpen() {
		return open;
	}

	public void setOpen(Float open) {
		this.open = open;
	}

	public Float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
	}

	public Float getMax() {
		return max;
	}

	public void setMax(Float max) {
		this.max = max;
	}

	public Float getMin() {
		return min;
	}

	public void setMin(Float min) {
		this.min = min;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Float getIncrease() {
		return increase;
	}

	public void setIncrease(Float increase) {
		this.increase = increase;
	}

	public Float getMaxIncrease() {
		return maxIncrease;
	}

	public void setMaxIncrease(Float maxIncrease) {
		this.maxIncrease = maxIncrease;
	}

	@Override
	public String toString() {
		return "StockMain [id=" + id + ", symbol=" + symbol + ", day=" + day + ", open=" + open + ", close=" + close
				+ ", max=" + max + ", min=" + min + ", volume=" + volume + ", increase=" + increase + ", maxIncrease="
				+ maxIncrease + ", fluctuate=" + fluctuate + ", lowerShadow=" + lowerShadow + "]";
	}

	public int compareTo(StockMain o) {
		return (int) (this.getIncrease() - o.getIncrease());
	}

}