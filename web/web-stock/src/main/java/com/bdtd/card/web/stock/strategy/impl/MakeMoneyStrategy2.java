package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class MakeMoneyStrategy2 extends BaseAnalysisStrategy {
	
	public static int CHECK_DAY = 8;
	public static int INCREASE_DAY = 6;
	public static float MIN_INCREASE = -3F;
	public static float INCREASE = 10F;
	
	public static float CURR_DAY_INCREASE_MIN = 9.9F;
	
	
	/**
	 * 寻找当天涨幅大于9.9
	 */
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result,
			int maxIndex, Date begin, float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		
		
		if (stockMains.get(index).getIncrease() >= CURR_DAY_INCREASE_MIN) {
			ResultDetail analysisResult = createResultDetail(curr, maxIncrease, index, stockMains);
			result.add(analysisResult);
		} 
	}

}
