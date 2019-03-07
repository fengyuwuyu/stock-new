package com.bdtd.card.data.stock.model.query;

public class CurrentIncreaseQuery extends BaseQuery {

	@Override
	public String toString() {
		return "RecentIncreaseQuery [begin=" + begin + ", end=" + end + ", limit=" + limit + ", offset=" + offset
				+ ", sortField=" + sortField + ", desc=" + desc + "]";
	}

}
