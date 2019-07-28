package com.bdtd.card.data.stock.model.query;

import com.base.card.common.util.StringUtil;

public class CurrentIncreaseQuery extends BaseQuery {

	private Integer stockType;
	private Float min;
	private Float max;
	private String limitField;
	private Integer generate;
	private String symbol;
	private Integer bizLimit;

	public Integer getBizLimit() {
		return bizLimit;
	}

	public void setBizLimit(Integer bizLimit) {
		this.bizLimit = bizLimit;
	}

	public String getLimitField() {
		return limitField;
	}

	public void setLimitField(String limitField) {
		this.limitField = limitField;
	}

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
		return "CurrentIncreaseQuery [stockType=" + stockType + ", min=" + min + ", max=" + max + ", limitField="
				+ limitField + ", generate=" + generate + ", symbol=" + symbol + ", bizLimit=" + bizLimit + ", offset="
				+ offset + ", limit=" + limit + ", page=" + page + ", sortField=" + sortField + ", asc=" + asc
				+ ", begin=" + begin + ", end=" + end + "]";
	}

}
