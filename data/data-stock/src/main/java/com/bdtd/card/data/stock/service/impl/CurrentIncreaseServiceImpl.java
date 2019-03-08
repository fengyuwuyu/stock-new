package com.bdtd.card.data.stock.service.impl;

import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;
import com.bdtd.card.data.stock.dao.CurrentIncreaseMapper;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 最近最大涨幅分析 服务实现类
 * </p>
 *
 * @author lilei
 * @since 2019-03-07
 */
@Service
public class CurrentIncreaseServiceImpl extends ServiceImpl<CurrentIncreaseMapper, CurrentIncrease> implements ICurrentIncreaseService {

	@Autowired
	private StockMainMapper stockMainMapper;

	@Override
	public Page<CurrentIncrease> findByQuery(CurrentIncreaseQuery query) {
		List<StockMain> stockMainList = this.stockMainMapper.findByQuery(query);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));
		for (List<StockMain> stockMains : stockMainMap.values()) {
			
		}
		return null;
	}

}
