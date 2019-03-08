package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class MaxIncreaseStrategy extends BaseAnalysisStrategy {
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result,
			int maxIndex, Date begin, float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - CHECK_DAY, index);
		float maxIncrease = entity.getMaxIncrease();
		if (maxIncrease >= limit) {
			ResultDetail analysisResult = createResultDetail(curr, maxIncrease, index, stockMains);
			if (analysisResult.getFutureIncrease() > limit) {
				result.add(analysisResult);
			}
		}
	}

}
