package com.bdtd.card.data.stock.model.query;

import java.sql.Date;

public class BaseQuery {

	protected Integer offset;
	protected Integer limit;
	protected Integer page;
	protected String sortField;
	protected Boolean asc;
	protected Date begin;
	protected Date end;

	public Integer getPage() {
		if (page == null) {
			return offset == 0 ? 0 : offset / limit; 
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public Boolean getAsc() {
		return asc;
	}

	public void setAsc(Boolean desc) {
		this.asc = desc;
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

	@Override
	public String toString() {
		return "BaseQuery [offset=" + offset + ", limit=" + limit + ", sortField=" + sortField + ", asc=" + asc
				+ ", begin=" + begin + ", end=" + end + "]";
	}

}
