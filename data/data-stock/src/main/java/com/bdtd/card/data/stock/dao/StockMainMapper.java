package com.bdtd.card.data.stock.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.bdtd.card.data.stock.model.CacheItem;
import com.bdtd.card.data.stock.model.StockAnalyseResult;
import com.bdtd.card.data.stock.model.StockFilterBean;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMainAnalyse;
import com.bdtd.card.data.stock.model.StockQuery;
import com.bdtd.card.data.stock.model.StockTop100;

public interface StockMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Map<String, Object> map);

    int insertSelective(StockMain record);

    StockMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockMain record);

    int updateByPrimaryKey(StockMain record);

	List<StockMain> dataList(StockQuery query);

	int getTotal(StockQuery query);
	
	List<StockMain> dataList1(StockQuery query);

	int getTotal1(StockQuery query);

	List<StockMain> showChart(StockQuery query);

	List<String> selectSymbols();

	void insertStockAyalyseResult(StockAnalyseResult stockAnalyseResult);
	
	void updateStatus(Map<String,Object> map);

	List<String> selectAll();

	List<StockAnalyseResult> select1(String symbol);

	List<StockTop100> selectTop100(Date day);

	List<StockTop100> selectTop100Dl(Map<String, Object> createMap);

	Map<String, Date> selectDays(Date day);

	List<String> selectAllDay(int count);

	List<StockMainAnalyse> selectAnalyse(Map<String, Object> map);

	void insertStockMainAnalyse(Map<String, Object> createMap);

	void updateStockMainDays(Map<String, Object> createMap);

	List<StockMainAnalyse> findStock();

	void insertAnalyse(Map<String, Object> createMap);

	void insertStockAnaylseBase(Map<String, Object> map);

	List<String> selectAllCodes();

	void insertStockBuySell(Map<String, Object> createMap);

	void insertFBVolume(Map<String, Object> createMap);

	List<String> selectByDay(Date day);

	void createTable(Map<String, String> map);
	
	List<StockMain> selectBySymbol(StockQuery query);
	
	List<StockMain> findByDay(StockQuery query);

	List<StockMain> findBySymbolAndDay(StockQuery query);
    
    List<StockMain> findAll(Date begin);
    
    List<StockMain> findAll1(Date begin);

	int count();
	
	List<StockFilterBean> selectAnalyse1(Map<String, Object> createMap);
	
	List<CacheItem> initPrePrices();
	
}