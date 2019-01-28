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
public class NearlyTenDayStrategy extends BaseAnalysisStrategy {

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex,
			Date begin, float limit) throws Exception {
		if (stockMains.get(0).getSymbol().equals("000055")) {
			System.out.println();
		}
		float minValue = 10;
		float maxValue = 30;
		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - computeDay, index);
		float hasIncrease = entity.getMaxIncrease();
		
		
		if (hasIncrease >= minValue && hasIncrease <= maxValue) {
			ResultDetail analysisResult = createResultDetail(curr, maxIncrease, index, stockMains);
			analysisResult.setHasIncrease(hasIncrease);
			result.add(analysisResult);
		}
	}

}
