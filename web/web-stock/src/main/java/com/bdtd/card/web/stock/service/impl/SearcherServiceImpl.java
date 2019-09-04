package com.bdtd.card.web.stock.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.web.stock.model.SearchType;
import com.bdtd.card.web.stock.service.SearcherServiceI;
import com.bdtd.card.web.stock.strategy.impl.DecreaseAndSerialLowVolumeStrategy;
import com.bdtd.card.web.stock.strategy.impl.HistoryIncreaseStrategy;
import com.bdtd.card.web.stock.strategy.impl.MakeMoneyStrategy;
import com.bdtd.card.web.stock.strategy.impl.MakeMoneyStrategy2;
import com.bdtd.card.web.stock.strategy.impl.MaxIncreaseStrategy;
import com.bdtd.card.web.stock.strategy.impl.NearlyTenDayStrategy;
import com.bdtd.card.web.stock.strategy.impl.PriceLowShockStrategy;
import com.bdtd.card.web.stock.strategy.impl.SerialIncreaseAndLowVolumeStrategy;
import com.bdtd.card.web.stock.strategy.impl.SerialIncreaseStrategy;
import com.bdtd.card.web.stock.strategy.impl.SerialLowVolumeStrategy;

@Service
public class SearcherServiceImpl implements SearcherServiceI {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private StockMainMapper stockMainMapper;
	@Autowired
	MaxIncreaseStrategy maxIncreaseStrategy;
	@Autowired
	SerialLowVolumeStrategy serialLowVolumeStrategy;
	@Autowired
	SerialIncreaseStrategy serialIncreaseStrategy;
	@Autowired
	DecreaseAndSerialLowVolumeStrategy decreaseAndSerialLowVolumeStrategy;
	@Autowired
	SerialIncreaseAndLowVolumeStrategy serialIncreaseAndLowVolumeStrategy;
	@Autowired
	NearlyTenDayStrategy nearlyTenDayStrategy;
	@Autowired
	MakeMoneyStrategy makeMoneyStrategy;
	@Autowired
	MakeMoneyStrategy2 makeMoneyStrategy2;
	@Autowired
	private HistoryIncreaseStrategy historyIncreaseStrategy;
	@Autowired
	private PriceLowShockStrategy priceLowShockStrategy;

	@Override
	public Map<String, Object> findIncreaseTopn(Date begin, float limit, float maxIncrease, Integer searchType) {
		SearchType type = SearchType.valueOf(searchType);
		if (type == null) {
			return MapUtil.createFailedMap("msg", "illegal searchType [%s]", searchType);
		}
		List<StockMain> stockMainList = stockMainMapper.findAll1(begin);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));
		List<ResultDetail> result = new ArrayList<>(300);
//		List<ResultDetail> list = new ArrayList<>();

		
		Comparator<ResultDetail> comparator = new Comparator<ResultDetail>() {
			
			@Override
			public int compare(ResultDetail o1, ResultDetail o2) {
				return (int)(o1.getFluctuate()- o2.getFluctuate());
			}
		};
		for (List<StockMain> stockMains : stockMainMap.values()) {
			float max = 0;
			int maxIndex = stockMains.size() - 1;
			int index = -1;
			int count = 0;
			int maxDay = 0;
			for (StockMain stockMain : stockMains) {
				if (stockMain.getDay().after(begin)) {
					
					if (stockMain.getClose() > max) {
						max = stockMain.getClose();
						maxIndex = count;
					}
					
					maxDay++;
					if (maxDay >= 20) {
						break;
					}
					
				} else {
					index++;
				}
				count++;
			}
			
			if (index == -1) {
				continue ;
			}
			
			try {
				switch (type) {
				case MAX_INCREASE:
					maxIncreaseStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case SERIAL_LOW_VOLUME:
					serialLowVolumeStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					comparator = new Comparator<ResultDetail>() {
						
						@Override
						public int compare(ResultDetail o1, ResultDetail o2) {
							return o2.getLowerShadow() - o1.getLowerShadow();
						}
					};
					break;
				case SERIAL_INCREASE:
					serialIncreaseStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case DECREASE_AND_SERIAL_LOW_VOLUME:
					decreaseAndSerialLowVolumeStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case SERIAL_INCREASE_AND_LOW_VOLUME:
					serialIncreaseAndLowVolumeStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case NEARLY_TEN_DAY:
					nearlyTenDayStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case MAKE_MONEY:
					makeMoneyStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case MAKE_MONEY1:
					makeMoneyStrategy2.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case HISTORY_INCREASE:
					historyIncreaseStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case PRICE_LOW_SHOCK:
					priceLowShockStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				case MAX_RECENT_INCREASE:
					priceLowShockStrategy.analysis(stockMains, index, result, maxIndex, begin, limit);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				log.warn(String.format("symbol = %s, maxIndex = %d, maxLen = %d, error = %s",  stockMains.get(0).getSymbol(), maxIndex, stockMains.size(), e.getMessage()), e);;
			}
		}
		if (type == SearchType.MAKE_MONEY) {
//			StockUtils.statistics(result);
		}
		StockUtils.sort(result, comparator);
		int rightSize = result.size() > 100 ? 100 : result.size();
		result = result.subList(0, rightSize);
		return MapUtil.createSuccessMap("rows", result, "total", result.size());
	}

}
