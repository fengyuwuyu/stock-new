package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class SerialLowVolumeStrategy extends BaseAnalysisStrategy {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex,
			Date begin, float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		
		Float tmp = (curr.getClose() - curr.getMin()) / curr.getClose() * 100;
		int lowerShadow = tmp.intValue();
//		log.info("lowerShadow = {}", lowerShadow);
		
		if (lowerShadow >= 3) {
			try {
				StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, 0, index);
				ResultDetail analysisResult = createResultDetail(curr, entity.getMaxIncrease(), maxIndex, stockMains);
				analysisResult.setLowerShadow(lowerShadow);
				result.add(analysisResult);
			} catch (Exception e) {
			}
		}
	}

}
