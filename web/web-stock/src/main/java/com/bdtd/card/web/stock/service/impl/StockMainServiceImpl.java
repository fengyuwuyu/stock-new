package com.bdtd.card.web.stock.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.card.common.util.MapUtil;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockQuery;
import com.bdtd.card.web.stock.service.StockMainServiceI;

@Service
public class StockMainServiceImpl implements StockMainServiceI {

	@Autowired
	private StockMainMapper stockMainMapper;
	
	
	public Map<String, Object> showChart(StockQuery query) {
		List<StockMain> list = this.stockMainMapper.showChart(query);
		return MapUtil.createSuccessMap("data", list);
	}

	@Override
	public Map<String, Object> dataList(StockQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initStock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> dataList1(StockQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> initIncreaseStock() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> analyseQuery(Date day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean analyse1(int count) {
		// TODO Auto-generated method stub
		return false;
	}

}