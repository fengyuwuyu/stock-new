package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class PriceLowShockStrategy extends BaseAnalysisStrategy {

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex, Date begin,
			float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		
		float max = 0.0f;
		float min = Float.MAX_VALUE;
		int beginIdx = index - 4;
		float sum = 0F;
		beginIdx = beginIdx < 0 ? 0 : beginIdx;
		for (int i = beginIdx; i <= index; i++) {
			StockMain s = stockMains.get(i);
			if (s.getClose() > max) {
				max = s.getClose();
			}
			if (s.getClose() < min) {
				min = s.getClose();
			}
			sum += s.getClose();
		}
		
		float avg = sum / (index - beginIdx);
		curr.setFluctuate(((max - avg) / avg + (avg - min) / avg) * 100);
		if (curr.getFluctuate() < 4) {
			StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, 0, index);
			if (entity.getMaxIncrease() < INCREASE) {
				return ;
			}
			ResultDetail analysisResult = createResultDetail(curr, entity.getMaxIncrease(), index, stockMains);
			analysisResult.setFluctuate(curr.getFluctuate());
			result.add(analysisResult);
		}
	}

}
