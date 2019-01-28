package com.bdtd.card.web.stock.strategy.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Service
public class SerialLowVolumeStrategy extends BaseAnalysisStrategy {
	
	private int dayCount = 10;

	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex,
			Date begin, float limit) throws Exception {
		dayCount = 5;
		StockMain curr = stockMains.get(index);
		float maxIncrease = Float.valueOf(CommonsUtil.formatDecimal((stockMains.get(maxIndex).getClose() - curr.getClose()) * 100 / curr.getClose()));
		long volume = curr.getVolume();
		boolean found = true;
		Long totalVolume = 0L;
		for (int i = index - dayCount; i < index; i++) {
			if(stockMains.get(i).getVolume() > volume) {
				found = false;
				break;
			}
			totalVolume += stockMains.get(i).getVolume();
		}
		
		if (found && totalVolume / dayCount < curr.getVolume() / 3) {
			ResultDetail analysisResult = createResultDetail(curr, maxIncrease, maxIndex, stockMains);
			analysisResult.setVolumeRate(curr.getVolume().doubleValue() / (totalVolume.doubleValue() / dayCount));
			result.add(analysisResult);
		}
	}

}
