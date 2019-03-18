package com.bdtd.card.data.stock.model.query;

import com.bdtd.card.common.util.StringUtil;

public class CurrentIncreaseQuery extends BaseQuery {

	private Integer stockType;
	private Float min;
	private Float max;
	private Integer generate;
	private String symbol;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		if (!StringUtil.isNullEmpty(symbol)) {
			this.symbol = symbol;
		}
	}

	public Float getMin() {
		return min;
	}

	public void setMin(Float min) {
		this.min = min;
	}

	public Float getMax() {
		return max;
	}

	public void setMax(Float max) {
		this.max = max;
	}

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	public Integer getGenerate() {
		return generate;
	}

	public void setGenerate(Integer generate) {
		this.generate = generate;
	}

	@Override
	public String toString() {
		return "CurrentIncreaseQuery [stockType=" + stockType + ", min=" + min + ", max=" + max + ", generate="
				+ generate + ", symbol=" + symbol + "]";
	}

}
