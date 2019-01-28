package com.bdtd.card.data.stock.model;

/**
 * 分页参数实体类
 * @date 2016-03-21
 * */
public class PageModel {
	
	private Integer page;
	private Integer rows;
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getRows() {
		return rows;
	}
	
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getStart() {
		return (this.page - 1) * this.rows;
	}
	
}
