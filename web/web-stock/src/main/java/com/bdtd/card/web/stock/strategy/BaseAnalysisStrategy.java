package com.bdtd.card.web.stock.strategy;

import java.util.List;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.util.StockUtils;

public abstract class BaseAnalysisStrategy implements IAnalysisStrategy {
	
	private int count = 10;
	public static int CHECK_DAY = 20;
	public static int INCREASE_DAY = 6;
	public static int futureDay = 5;
	public static float MIN_INCREASE = -8F;
	public static float INCREASE = 20F;
	protected int computeDay = 10;
	
	public ResultDetail createResultDetail(StockMain curr, Float maxIncrease, int index, List<StockMain> stockMains) {
		StringBuilder increases = new StringBuilder();
		StringBuilder volumes = new StringBuilder();
		StringBuilder futureIncreases = new StringBuilder();
		StringBuilder futureVolumes = new StringBuilder();
		StringBuilder closes = new StringBuilder();
		int tmp = index - count;
		tmp = tmp >= 0 ? tmp : 0;
		for (int i = tmp ; i <= index; i++) {
			StockMain main = stockMains.get(i);
			increases.append(main.getIncrease() + ", ");
			volumes.append(CommonsUtil.formatDecimal(main.getVolume().doubleValue() / curr.getVolume().doubleValue()) + ", ");
			closes.append(main.getClose() + ", ");
		}

		if (index + 1 < stockMains.size()) {
			int a = index + futureDay >= stockMains.size() ? stockMains.size() - 1 : index + futureDay;
			for (int i = index + 1; i <= a; i++) {
				StockMain main = stockMains.get(i);
				futureIncreases.append(main.getIncrease() + ", ");
				futureVolumes.append(CommonsUtil.formatDecimal(main.getVolume().floatValue() / curr.getVolume().floatValue()) + ", ");
			}
		}
		
		Float volumeRatio = stockMains.get(index).getVolume().floatValue() / stockMains.get(index - 1).getVolume().floatValue();
		
		float max = 0;
		Float futureIncrease = 0F;
		if (index != stockMains.size() - 1) {
			int futureIndex = (index + futureDay) > stockMains.size() ? stockMains.size() : index + futureDay;
			for (int i = index + 1; i < futureIndex; i++) {
				if (stockMains.get(i).getClose() > max) {
					max = stockMains.get(i).getClose();
				}
			}
			futureIncrease = (max - curr.getClose()) * 100 / curr.getClose();
		}

		StockMiddleEntity entity = StockUtils.findMaxIncrease(stockMains, index - CHECK_DAY, index);
		float hasIncrease = entity.getMaxIncrease();
		
//		if (max > curr.getClose()) {
//		}
		return new ResultDetail(curr, hasIncrease, increases.toString(),
				volumes.toString(), closes.toString(), volumeRatio, futureIncrease, hasIncrease, futureIncreases.toString(), futureVolumes.toString());
	}
}
