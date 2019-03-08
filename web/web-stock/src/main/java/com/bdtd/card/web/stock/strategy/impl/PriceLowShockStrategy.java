package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class PriceLowShockStrategy extends BaseAnalysisStrategy {

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex, Date begin,
			float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		if ((curr.getMax() - curr.getMin()) / curr.getClose() < 0.1 && curr.getIncrease() > 9.9F) {
			ResultDetail analysisResult = createResultDetail(curr, 0F, index, stockMains);
			result.add(analysisResult);
		}
	}

}
