package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;
import com.bdtd.card.web.stock.util.StockUtils;

@Service
public class MakeMoneyStrategy extends BaseAnalysisStrategy {
	
	public static int CHECK_DAY = 8;
	public static int INCREASE_DAY = 6;
	public static float MIN_INCREASE = -3F;
	public static float INCREASE = 10F;
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result,
			int maxIndex, Date begin, float limit) throws Exception {
		if (INCREASE_DAY > CHECK_DAY) {
			return ;
		}
		
		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		
		// 1.
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - CHECK_DAY, index);
		if (entity.getMaxIncrease() < INCREASE) {
			return ;
		}
		
		// 2.
		int increaseCount = 0;
		for (int i = index - CHECK_DAY + 1; i <= index; i++) {
			if (stockMains.get(i).getIncrease() > 0) {
				increaseCount++;
			} else if (stockMains.get(i).getIncrease() < MIN_INCREASE) {
				return ;
			}
		}
		
		if (increaseCount < INCREASE_DAY) {
			return;
		}
		
		ResultDetail analysisResult = createResultDetail(curr, maxIncrease, index, stockMains);
		result.add(analysisResult);
	}

}
