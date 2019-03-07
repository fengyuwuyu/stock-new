package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;
import com.bdtd.card.web.stock.util.StockUtils;

@Service
public class MaxRecentIncreaseStrategy extends BaseAnalysisStrategy {

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex, Date begin,
			float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		int computeDay = (int) limit;
		
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - CHECK_DAY, index);
		ResultDetail analysisResult = createResultDetail(curr, 0F, index, stockMains);
		result.add(analysisResult);
	}

}
