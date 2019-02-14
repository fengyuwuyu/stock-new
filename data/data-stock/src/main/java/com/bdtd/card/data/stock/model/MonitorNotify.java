package com.bdtd.card.data.stock.model;

public class MonitorNotify {

	private String symbol;
	private Float profit;

	public MonitorNotify() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonitorNotify(String symbol, Float profit) {
		super();
		this.symbol = symbol;
		this.profit = profit;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Float getProfit() {
		return profit;
	}

	public void setProfit(Float profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "MonitorNotify [symbol=" + symbol + ", profit=" + profit + "]";
	}

}
