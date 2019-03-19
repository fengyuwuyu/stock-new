package com.bdtd.card.data.stock.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdtd.card.base.consts.StockConsts;
import com.bdtd.card.common.model.OriginMask;
import com.bdtd.card.common.util.DateUtil;
import com.bdtd.card.data.stock.base.MidStockLevel;
import com.bdtd.card.data.stock.dao.CurrentIncreaseMapper;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.data.stock.util.model.CurrentStockData;

/**
 * <p>
 * </p>
 *
 * @author lilei
 * @since 2019-03-07
 */
@Service
public class CurrentIncreaseServiceImpl extends ServiceImpl<CurrentIncreaseMapper, CurrentIncrease>
		implements ICurrentIncreaseService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private int historyDay = 10;
	private int futureDay = 10;

	@Autowired
	private StockMainMapper stockMainMapper;

	@Override
	public IPage<CurrentIncrease> findByQuery(CurrentIncreaseQuery query) {
		IPage<CurrentIncrease> page = findDb(query);
//		if (page.getTotal() == 0 || DateUtil.localDate2Long(page.getRecords().get(0).getMsaDay()) != query.getEnd().getTime()) {
//			List<StockMain> stockMainList = this.stockMainMapper.findByQuery(query);
//			initAnalysis(query, stockMainList);
//			return findDb(query);
//		}
		return page;
	}
	
	private IPage<CurrentIncrease> findDb(CurrentIncreaseQuery query) {
		if (query.getGenerate() == OriginMask.YES.getType()) {
			QueryWrapper<CurrentIncrease> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("msa_day", query.getEnd());
			this.remove(queryWrapper );
			List<StockMain> stockMainList = this.stockMainMapper.findByQuery(query);
			initAnalysis(query, stockMainList);
		}
		long total = this.baseMapper.countByQuery(query);
		IPage<CurrentIncrease> page = null;
		if (total == 0) {
			page = new Page<>(query.getPage(), query.getLimit(), total);
			page.setRecords(Collections.emptyList());
			return page;
		}
		List<CurrentIncrease> list = this.baseMapper.findByQuery(query);
		getCurrentData(list);
		page = new Page<>(query.getPage(), query.getLimit(), total);
		getCurrentData(list);
		page.setRecords(list);
		return page;
		
	}
	
//	private IPage<CurrentIncrease> findDb(CurrentIncreaseQuery query) {
//		QueryWrapper<CurrentIncrease> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("msa_day", query.getEnd());
//		if (query.getStockType() != null && query.getStockType() != StockType.All.getType()) {
//			queryWrapper.eq("stock_type", query.getStockType());
//		}
//		if (query.getMax() != null && query.getMin() != null) {
//			queryWrapper.between(query.getSortField(), query.getMin(), query.getMax());
//		} else if (query.getMax() != null) {
//			queryWrapper.ge(query.getSortField(), query.getMax());
//		}else if (query.getMax() != null && query.getMin() != null) {
//			queryWrapper.le(query.getSortField(), query.getMin());
//		}
//		queryWrapper.orderBy(true, query.getAsc(), query.getSortField());
//		return this.page(new Page<>(query.getPage(), query.getLimit()), queryWrapper);
//	}
	
	private void getCurrentData(List<CurrentIncrease> list) {
//		if (!CommonsUtil.checkTime1(LocalDateTime.now())) {
//			return;
//		}
		
		List<String> symbols = list.stream().map(CurrentIncrease::getSymbol).collect(Collectors.toList());
		List<Integer> types = list.stream().map(CurrentIncrease::getStockCategory).collect(Collectors.toList());
		Map<String, CurrentStockData> map = StockUtils.getCurrentStockData(StockConsts.STOCK_CURR_DATA_URL, symbols, types);
		list.forEach((item) -> {
			CurrentStockData data = map.get(item.getSymbol());
			if (data != null) {
				item.setCurrIncrease(CommonsUtil.formatNumberToFloat((data.getCurrIncrease() - item.getClose()) * 100 / item.getClose()));
				item.setTotalBuyVolume(data.getTotalBuyVolume());
				item.setTotalSellVolume(data.getTotalSellVolume());
				item.setCurrVolume(data.getCurrVolume());
			}
		});
	}

	@Override
	public IPage<CurrentIncrease> initAnalysis(CurrentIncreaseQuery query, List<StockMain> stockMainList) {
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));
		List<CurrentIncrease> result = new ArrayList<CurrentIncrease>(stockMainMap.size());
		for (List<StockMain> stockMains : stockMainMap.values()) {
			if (stockMains == null || stockMains.size() == 0) {
				continue;
			}
			int index = StockUtils.getIndex(stockMains, query.getEnd());
			if (index == -1) {
				continue;
			}
			StockMain curr = stockMains.get(index);
			String symbol = stockMains.get(0).getSymbol();
			Float increase = stockMains.get(index).getIncrease();
			String name = null;
			String code = null;
			Float max = stockMains.get(index).getMax();
			Float min = stockMains.get(index).getMin();
			Long volume = stockMains.get(index).getVolume();
			Float close = stockMains.get(index).getClose();
			LocalDate msaDay = DateUtil.long2LocalDate(curr.getDay().getTime());
			
			Float preIncrease = null;
			Long preVolume = null;
			try {
				preIncrease = stockMains.get(index - 1).getIncrease();
				preVolume = stockMains.get(index - 1).getVolume();
			} catch (Exception e) {
			}

			Float twoIncrease = StockUtils.findMaxIncrease(stockMains, index - 3, index).getMaxIncrease();
			Float thressIncrease = StockUtils.findMaxIncrease(stockMains, index - 4, index).getMaxIncrease();
			Float fourIncrease = StockUtils.findMaxIncrease(stockMains, index - 5, index).getMaxIncrease();
			Float fiveIncrease = StockUtils.findMaxIncrease(stockMains, index - 6, index).getMaxIncrease();
			Float tenIncrease = StockUtils.findMaxIncrease(stockMains, index - 11, index).getMaxIncrease();
			Float fifteenIncrease = StockUtils.findMaxIncrease(stockMains, index - 16, index).getMaxIncrease();
			Float twentyIncrease = StockUtils.findMaxIncrease(stockMains, index - 21, index).getMaxIncrease();
			Float maxIncrease = StockUtils.findMaxIncrease(stockMains, 0, index).getMaxIncrease();
			StringBuilder increases = new StringBuilder();
			StringBuilder volumes = new StringBuilder();
			Float futureFiveDayIncrease = StockUtils.findFutureMaxIncrease(stockMains, index, index + 5).getMaxIncrease();
			Float futureTenDayIncrease = StockUtils.findFutureMaxIncrease(stockMains, index, index + 10).getMaxIncrease();
			Float futureFifteenDayIncrease = StockUtils.findFutureMaxIncrease(stockMains, index, index + 15)
					.getMaxIncrease();
			Float futureTwentyDayIncrease = StockUtils.findFutureMaxIncrease(stockMains, index, index + 20)
					.getMaxIncrease();
			StringBuilder futureIncreases = new StringBuilder();
			StringBuilder futureVolumes = new StringBuilder();
			float dayVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 1);
			float twoVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 2);
			float threeVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 3);
			float fourVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 4);
			float fiveVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 5);

			int tmp = index - (historyDay - 1);
			tmp = tmp >= 0 ? tmp : 0;
			for (int i = tmp; i <= index; i++) {
				StockMain main = stockMains.get(i);
				increases.append(main.getIncrease() + ", ");
				volumes.append(CommonsUtil.formatDecimal(main.getVolume().doubleValue() / curr.getVolume().doubleValue())+ ", ");
			}

			if (index + 1 < stockMains.size()) {
				int a = index + futureDay >= stockMains.size() ? stockMains.size() - 1 : index + futureDay;
				for (int i = index + 1; i <= a; i++) {
					StockMain main = stockMains.get(i);
					futureIncreases.append(main.getIncrease() + ", ");
					futureVolumes.append(CommonsUtil.formatDecimal(main.getVolume().floatValue() / curr.getVolume().floatValue()) + ", ");
				}
			}
			
			MidStockLevel midStockLevel = StockUtils.getStockLevel(stockMains, index - 30, index);
			Integer firstLevelDay = midStockLevel.getFirstLevelDay();
			Float firstLevelIncrease = midStockLevel.getFirstLevelIncrease();
			Integer secondLevelDay = midStockLevel.getSecondLevelDay();
			Float secondLevelIncrease = midStockLevel.getSecondLevelIncrease();
			Integer thirdLevelDay = midStockLevel.getThirdLevelDay();
			Float thirdLevelIncrease = midStockLevel.getThirdLevelIncrease();
			Integer fourLevelDay = midStockLevel.getFourLevelDay();
			Float fourLevelIncrease = midStockLevel.getFourLevelIncrease();
			Integer fiveLevelDay = midStockLevel.getFiveLevelDay();
			Float fiveLevelIncrease = midStockLevel.getFiveLevelIncrease();
			Integer stockType = midStockLevel.getStockType();
			try {
				CurrentIncrease currentIncrease = new CurrentIncrease(symbol, name, code, max, min, increase, volume, twoIncrease, thressIncrease, fourIncrease, fiveIncrease, tenIncrease, fifteenIncrease, twentyIncrease, maxIncrease, increases.toString(), 
						volumes.toString(), futureFiveDayIncrease, futureTenDayIncrease, futureFifteenDayIncrease, futureTwentyDayIncrease, futureIncreases.toString(), futureVolumes.toString(), dayVolumeAvg, twoVolumeAvg, threeVolumeAvg, fourVolumeAvg, fiveVolumeAvg, 
						firstLevelDay, firstLevelIncrease, secondLevelDay, secondLevelIncrease, thirdLevelDay, thirdLevelIncrease, fourLevelDay, fourLevelIncrease, fiveLevelDay, fiveLevelIncrease, stockType , msaDay, 
						close, preIncrease, preVolume);
				result.add(currentIncrease);
			} catch (Exception e) {
				log.error(String.format("创建对象失败，day = %s", query.getEnd()), e);
			}
		}
		
		try {
			if (result.size() > 0) {
				this.baseMapper.insertAll(result);
			}
		} catch (Exception e) {
			log.error(String.format("插入失败，day = %s, records = %s", query.getEnd(), result), e);
		}
		
		Page<CurrentIncrease> page = new Page<>(query.getOffset(), query.getLimit());
		page.setTotal(result.size());
		page.setRecords(CommonsUtil.subList(result, query.getOffset(), query.getLimit()));
		return page;
	}
	
	public void insertAll(List<CurrentIncrease> list) {
		
		this.baseMapper.insertAll(list);
	}

}
