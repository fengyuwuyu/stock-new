package com.bdtd.card.web.stock.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.data.stock.dao.ResultCompareMapper;
import com.bdtd.card.data.stock.dao.ResultDetailMapper;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.ResultCompare;
import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.util.DateUtil;
import com.bdtd.card.web.stock.strategy.impl.MakeMoneyStrategy;
import com.bdtd.card.web.stock.util.StockUtils;

@RequestMapping("/statistics")
@Controller
public class StaticticsController {
	
	private static final int MAX_INSERT_SIZE = 10000;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	private int[] checkDays = {7};
	private int[] increaseDays = {5, 6, 7};
	private float[] minIncreases = {-5F, -4F, -3F};
	private float[] increases = {10F, 11F, 12F, 13F};
	
	@Autowired
	private StockMainMapper stockMainMapper;
	@Autowired
	private ResultCompareMapper resultCompareMapper;
	@Autowired
	private ResultDetailMapper resultDetailMapper;
	@Autowired
	MakeMoneyStrategy makeMoneyStrategy;

	@RequestMapping("/create")
	@ResponseBody
	public Map<String, Object> create() {
		float limit = 10F;
		Date begin = new Date(DateUtil.getDate(2018, 0, 1).getTime());
		
		List<StockMain> stockMainList = stockMainMapper.findAll(begin);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));

		int maxIndex = 0;

		List<ResultCompare> list = new ArrayList<>(MAX_INSERT_SIZE);
		for (int i = 0; i < checkDays.length; i++) {
			MakeMoneyStrategy.CHECK_DAY = checkDays[i];
			for (int j = 0; j < increaseDays.length; j++) {
				MakeMoneyStrategy.INCREASE_DAY = increaseDays[j];
				for (int j2 = 0; j2 < minIncreases.length; j2++) {
					MakeMoneyStrategy.MIN_INCREASE = minIncreases[j2];
					for (int k = 0; k < increases.length; k++) {
						MakeMoneyStrategy.INCREASE = increases[k];
						if (this.resultCompareMapper.statisticsCount(MapUtil.createMap("checkDay", MakeMoneyStrategy.CHECK_DAY, "increaseDay", 
								MakeMoneyStrategy.INCREASE_DAY, "minIncrease", MakeMoneyStrategy.MIN_INCREASE, "minTotalIncrease", MakeMoneyStrategy.INCREASE)) > 0) {
							continue;
						}

						List<Date>  days = stockMainMap.get("300555").stream().map(StockMain::getDay).collect(Collectors.toList());
						for (int l = 10; l < days.size() - 1; l++) {
							Date date = days.get(l);
							List<ResultDetail> result = new ArrayList<>(100);
							for (List<StockMain> stockMains : stockMainMap.values()) {
								int index = StockUtils.getIndex(stockMains, date);
								if (index == -1) {
									continue;
								}
								try {
									makeMoneyStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
								} catch (Exception e) {
									log.warn(String.format("symbol = %s, maxIndex = %d, maxLen = %d, error = %s",  stockMains.get(0).getSymbol(), maxIndex, stockMains.size(), e.getMessage()));
								}
							}
							ResultCompare compare = StockUtils.createResultCompare(result, date);
							if (compare != null) {
								list.add(compare);
							}
						}
					}
				}
			}
		}

		int size = list.size();
		if (size > 0) {
			if (size <= MAX_INSERT_SIZE) {
				this.resultCompareMapper.insertList(list);
			} else {
				int fromIndex = 0;
				int toIndex = MAX_INSERT_SIZE;
				List<ResultCompare> insertList = null;
				while (toIndex <= size) {
					insertList = list.subList(fromIndex, toIndex);
					this.resultCompareMapper.insertList(insertList);
					if (toIndex == size) {
						break;
					}
					fromIndex += MAX_INSERT_SIZE;
					toIndex += MAX_INSERT_SIZE;
					toIndex = toIndex > size ? size : toIndex;
				} 
				
			}
		}
		return MapUtil.createSuccessMap();
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public Map<String, Object> detail() {
		float limit = 10F;
		Date begin = new Date(DateUtil.getDate(2018, 0, 1).getTime());
		
		List<StockMain> stockMainList = stockMainMapper.findAll(begin);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));

		int maxIndex = 0;
		float minFutureIncrease = 15F;
		float maxFutureIncrease = -10F;
		
		List<Date>  days = stockMainMap.get("300555").stream().map(StockMain::getDay).collect(Collectors.toList());
		List<ResultDetail> list = new ArrayList<>(MAX_INSERT_SIZE);
		List<ResultDetail> list1 = new ArrayList<>(MAX_INSERT_SIZE);
		for (int l = 10; l < days.size() - 5; l++) {
			Date date = days.get(l);
			List<ResultDetail> result = new ArrayList<>(100);
			for (List<StockMain> stockMains : stockMainMap.values()) {
				int index = StockUtils.getIndex(stockMains, date);
				if (index == -1) {
					continue;
				}
				try {
					makeMoneyStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
				} catch (Exception e) {
					log.warn(String.format("symbol = %s, maxIndex = %d, maxLen = %d, error = %s",  stockMains.get(0).getSymbol(), maxIndex, stockMains.size(), e.getMessage()));
				}
			}
			result.forEach((item) -> {
				if (item.getFutureIncrease() >= minFutureIncrease) {
					list.add(item);	
				} else if (item.getFutureIncrease() <= maxFutureIncrease) {
					list1.add(item);
				}
			});
		}
		
		int size = list.size();
		if (size > 0) {
			if (size <= MAX_INSERT_SIZE) {
				this.resultDetailMapper.insertList(list);
			} else {
				int fromIndex = 0;
				int toIndex = MAX_INSERT_SIZE;
				List<ResultDetail> insertList = null;
				while (toIndex <= size) {
					insertList = list.subList(fromIndex, toIndex);
					this.resultDetailMapper.insertList(insertList);
					if (toIndex == size) {
						break;
					}
					fromIndex += MAX_INSERT_SIZE;
					toIndex += MAX_INSERT_SIZE;
					toIndex = toIndex > size ? size : toIndex;
				} 
			}
		}
		
		size = list1.size();
		if (size > 0) {
			if (size <= MAX_INSERT_SIZE) {
				this.resultDetailMapper.insertList1(list1);
			} else {
				int fromIndex = 0;
				int toIndex = MAX_INSERT_SIZE;
				List<ResultDetail> insertList = null;
				while (toIndex <= size) {
					insertList = list1.subList(fromIndex, toIndex);
					this.resultDetailMapper.insertList1(insertList);
					if (toIndex == size) {
						break;
					}
					fromIndex += MAX_INSERT_SIZE;
					toIndex += MAX_INSERT_SIZE;
					toIndex = toIndex > size ? size : toIndex;
				} 
			}
		}
		return MapUtil.createSuccessMap();
	}
	
}
