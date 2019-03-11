package com.bdtd.card.data.stock.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdtd.card.common.util.DateUtil;
import com.bdtd.card.data.stock.dao.CurrentIncreaseMapper;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.data.stock.util.StockUtils;

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

	private int historyDay = 10;
	private int futureDay = 10;

	@Autowired
	private StockMainMapper stockMainMapper;

	@Override
	public IPage<CurrentIncrease> findByQuery(CurrentIncreaseQuery query) {
		QueryWrapper<CurrentIncrease> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderBy(true, query.getAsc(), query.getSortField());
		IPage<CurrentIncrease> page = this.page(new Page<>(query.getOffset(), query.getLimit()), queryWrapper);
		if (page.getTotal() == 0) {
			return initAnalysis(query);
		}
		if (DateUtil.localDate2Long(page.getRecords().get(0).getMsaDay()) != query.getEnd().getTime()) {
			this.baseMapper.delete(null);
			return initAnalysis(query);
		}
		return new Page<>(query.getOffset(), query.getLimit(), 0L);
	}
	
	private IPage<CurrentIncrease> initAnalysis(CurrentIncreaseQuery query) {
		List<StockMain> stockMainList = this.stockMainMapper.findByQuery(query);
		Map<String, List<StockMain>> stockMainMap = stockMainList.stream()
				.collect(Collectors.groupingBy(StockMain::getSymbol));
		List<CurrentIncrease> result = new ArrayList<CurrentIncrease>(stockMainMap.size());
		for (List<StockMain> stockMains : stockMainMap.values()) {
			int index = StockUtils.getIndex(stockMains, query.getEnd());
			if (index == -1) {
				continue;
			}
			StockMain curr = stockMains.get(index);
			String symbol = stockMains.get(0).getSymbol();
			Float increase = stockMains.get(index).getIncrease();
			LocalDate msaDay = DateUtil.long2LocalDate(curr.getDay().getTime());

			Float twoIncrease = StockUtils.findMaxIncrease(stockMains, index - 2, index).getMaxIncrease();
			Float thressIncrease = StockUtils.findMaxIncrease(stockMains, index - 3, index).getMaxIncrease();
			Float fourIncrease = StockUtils.findMaxIncrease(stockMains, index - 4, index).getMaxIncrease();
			Float fiveIncrease = StockUtils.findMaxIncrease(stockMains, index - 5, index).getMaxIncrease();
			Float tenIncrease = StockUtils.findMaxIncrease(stockMains, index - 10, index).getMaxIncrease();
			Float fifteenIncrease = StockUtils.findMaxIncrease(stockMains, index - 15, index).getMaxIncrease();
			Float twentyIncrease = StockUtils.findMaxIncrease(stockMains, index - 20, index).getMaxIncrease();
			Float maxIncrease = StockUtils.findMaxIncrease(stockMains, 0, index).getMaxIncrease();
			StringBuilder increases = new StringBuilder();
			StringBuilder volumes = new StringBuilder();
			Float futureFiveDayIncrease = StockUtils.findMaxIncrease(stockMains, index - 1, index + 4).getMaxIncrease();
			Float futureTenDayIncrease = StockUtils.findMaxIncrease(stockMains, index - 1, index + 10).getMaxIncrease();
			Float futureFifteenDayIncrease = StockUtils.findMaxIncrease(stockMains, index - 1, index + 10)
					.getMaxIncrease();
			Float futureTwentyDayIncrease = StockUtils.findMaxIncrease(stockMains, index - 1, index + 10)
					.getMaxIncrease();
			StringBuilder futureIncreases = new StringBuilder();
			StringBuilder futureVolumes = new StringBuilder();
			Float dayVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 1);
			Float twoVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 2);
			Float threeVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 3);
			Float fourVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 4);
			Float fiveVolumeAvg = StockUtils.getVolumeAvgCompare(stockMains, index, 5);

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
					futureVolumes.append(
							CommonsUtil.formatDecimal(main.getVolume().floatValue() / curr.getVolume().floatValue())
									+ ", ");
				}
			}

			CurrentIncrease currentIncrease = new CurrentIncrease(symbol, increase, twoIncrease, thressIncrease,
					fourIncrease, fiveIncrease, tenIncrease, fifteenIncrease, twentyIncrease, maxIncrease,
					increases.toString(), volumes.toString(), futureFiveDayIncrease, futureTenDayIncrease,
					futureFifteenDayIncrease, futureTwentyDayIncrease, futureIncreases.toString(),
					futureVolumes.toString(), dayVolumeAvg, twoVolumeAvg, threeVolumeAvg, fourVolumeAvg, fiveVolumeAvg,
					msaDay);
			result.add(currentIncrease);
		}
		
		this.baseMapper.insertAll(result);
		
		Page<CurrentIncrease> page = new Page<>(query.getOffset(), query.getLimit());
		page.setTotal(result.size());
		page.setRecords(CommonsUtil.subList(result, query.getOffset(), query.getLimit()));
		return page;
	}
	
	public void insertAll(List<CurrentIncrease> list) {
		
		this.baseMapper.insertAll(list);
	}

}
