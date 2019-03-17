package com.bdtd.card.data.stock.model.query;

public class CurrentIncreaseQuery extends BaseQuery {

	private Integer stockType;
	private Float min;
	private Float max;

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

	@Override
	public String toString() {
		return "CurrentIncreaseQuery [stockType=" + stockType + ", min=" + min + ", max=" + max + ", offset=" + offset
				+ ", limit=" + limit + ", page=" + page + ", sortField=" + sortField + ", asc=" + asc + ", begin="
				+ begin + ", end=" + end + "]";
	}

}
