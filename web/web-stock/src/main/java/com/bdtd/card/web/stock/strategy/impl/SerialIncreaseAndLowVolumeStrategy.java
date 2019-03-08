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
public class SerialIncreaseAndLowVolumeStrategy extends BaseAnalysisStrategy {

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex,
			Date begin, float limit) throws Exception {
		StockMain curr = stockMains.get(index);
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - CHECK_DAY, index);
		
		
		long totalVolume = 0L;
		for (int i = maxIndex - 4; i <= maxIndex; i++) {
			totalVolume += stockMains.get(i).getVolume();
		}
		long avgVolume = totalVolume / 5;
		
		boolean isLowVolume = false;
		for (int i = maxIndex + 1; i <= index; i++) {
			if ((stockMains.get(i).getIncrease() < 5 && stockMains.get(i).getIncrease() > -5) && stockMains.get(i).getVolume() < avgVolume * 0.3) {
				isLowVolume = true;
				break;
			}
		}
		
		float hasIncrease = entity.getMaxIncrease();
		int count = 0;
		boolean found = false;
		for (int i = maxIndex + 1; i < index; i++) {
			StockMain main = stockMains.get(i);
			if (main.getIncrease() > 0) {
				count++;
				if (count >= 7) {
					found = true;
					break;
				}
			} else {
				if (main.getIncrease() < -8) {
					found = false;
					break;
				}
			}
		}
		if (hasIncrease > 9 && isLowVolume ) {
			ResultDetail analysisResult = createResultDetail(curr, hasIncrease, index, stockMains);
			analysisResult.setHasIncrease(hasIncrease);
			result.add(analysisResult);
		}
	}

}
