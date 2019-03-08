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
public class DecreaseAndSerialLowVolumeStrategy extends BaseAnalysisStrategy {
	
	protected int decreaseDay = 3;
	private int checkSize = 4;
	private float percentage = 0.6f;
	private float min_increase = -0.5f;
	private float max_increase = 0.5f;
	private int minMatchDay = 1;
	
	@Override
	public void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex,
			Date begin, float limit) throws Exception {
		int start = index - checkSize;
		if (start <= 0) {
			return;
		}
		StockMain baseCompare = stockMains.get(start - 1);
		int matchDay = 0;
		for (int i = start; i <= index; i++) {
			StockMain tmp = stockMains.get(start);
			if (tmp.getIncrease() > min_increase && tmp.getIncrease() <= max_increase && tmp.getVolume() < baseCompare.getVolume() * percentage) {
				matchDay++;
			}
		}
		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - computeDay, index);
		
		StockMain curr = stockMains.get(index);
		Float maxIncrease = entity.getMaxIncrease();
		
		if (matchDay >= minMatchDay) {
			ResultDetail analysisResult = createResultDetail(curr , maxIncrease , index, stockMains);
			if (analysisResult.getMaxIncrease() >= 20F)
				result.add(analysisResult);
		}
	}

}
