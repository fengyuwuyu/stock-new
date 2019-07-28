package com.bdtd.card.web.stock.task.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.card.common.util.ThreadPool;
import com.bdtd.card.base.consts.StockConsts;
import com.bdtd.card.data.stock.dao.HolidayMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.data.stock.util.model.CurrentStockData;
import com.bdtd.card.web.stock.model.StockAnalysisData;
import com.bdtd.card.web.stock.task.ITask;

@Service
public class ChooseStockTask implements ITask {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HolidayMapper holidayMapper;
	@Autowired
	ICurrentIncreaseService currentIncreaseService;
	
	private static Map<String, StockAnalysisData> analyzeMap = new HashMap<>();
	
	private long delay = 30000;

	@Override
	public void run() {
		if (CommonsUtil.checkTime(this.holidayMapper)) {
			QueryWrapper<CurrentIncrease> queryWrapper = new QueryWrapper<>();
			LocalDate yesterday = LocalDate.now().plus(-1, ChronoUnit.DAYS);
			queryWrapper.eq("msa_day", yesterday);
			List<CurrentIncrease> list = currentIncreaseService.list(queryWrapper );
			if (list == null || list.size() == 0) {
				log.info("未查询到CurrentIncrease数据，day = {}", yesterday);
				return;
			}
			
			List<String> symbols = list.stream().map(CurrentIncrease::getSymbol).collect(Collectors.toList());
			initAnalyzeMap(symbols);
			List<Integer> types = list.stream().map(CurrentIncrease::getStockCategory).collect(Collectors.toList());
			Map<String, CurrentStockData> map = StockUtils.getCurrentStockData(StockConsts.STOCK_CURR_DATA_URL, symbols, types);
			doAnalyze(map);
		}
	}
	
	private void initAnalyzeMap(List<String> symbols) {
		symbols.forEach((symbol) -> {
			analyzeMap.put(symbol, new StockAnalysisData());
		});
	}

	private void doAnalyze(Map<String, CurrentStockData> stockMap) {
		stockMap.values().forEach((item) -> {
			String symbol = item.getSymbol();
			StockAnalysisData data = analyzeMap.get(symbol);
			
		});
	}

	@Override
	public void start() {
		log.info("ChooseStockTask启动了，每{}秒执行一次！", delay);
		ThreadPool.execute(this, 0L, delay , TimeUnit.SECONDS);
	}

}
