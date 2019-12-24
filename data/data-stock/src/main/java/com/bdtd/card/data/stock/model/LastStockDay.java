package com.bdtd.card.data.stock.model;

public class LastStockDay {

	private String symbol;
	private String lastDay;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLastDay() {
		return lastDay;
	}

	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}

	@Override
	public String toString() {
		return "LastStockDay [symbol=" + symbol + ", lastDay=" + lastDay + "]";
	}

}
