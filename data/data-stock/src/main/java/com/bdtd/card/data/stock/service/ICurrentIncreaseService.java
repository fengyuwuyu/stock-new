package com.bdtd.card.data.stock.service;

import java.time.LocalDate;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;

/**
 * <p>
 * 最近最大涨幅分析 服务类
 * </p>
 *
 * @author lilei
 * @since 2019-03-07
 */
public interface ICurrentIncreaseService extends IService<CurrentIncrease> {

	IPage<CurrentIncrease> findByQuery(CurrentIncreaseQuery query);
	
	IPage<CurrentIncrease> initAnalysis(CurrentIncreaseQuery query, List<StockMain> stockMainList) ;

	IPage<CurrentIncrease> findBySql(CurrentIncreaseQuery query);

	LocalDate findMaxMsaDay();

}
