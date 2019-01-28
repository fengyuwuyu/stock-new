package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class DecreaseAndSerialLowVolumeStrategy extends BaseAnalysisStrategy {
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex,
			Date begin, float limit) throws Exception {

		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		boolean found = false;
		
		float maxClose = Float.MIN_VALUE;
		float minClose = Float.MAX_VALUE;
		int maxInd = -1;
		int minInd = -1;
		for (int i = index - 10; i < index + 1; i++) {
			float close = stockMains.get(i).getClose();
			if (close > maxClose) {
				maxClose = close;
				maxInd = i;
			}	else if (minClose > close) {
				minClose = close;
				minInd = i;
			}
		}
		
		if (maxInd > minInd && (maxClose - minClose) * 100 / minClose > 10) {
			found = true;
		} 
		
		if (found) {
			ResultDetail analysisResult = createResultDetail(curr, maxIncrease, index, stockMains);
			result.add(analysisResult);
		}
	
	}

}
