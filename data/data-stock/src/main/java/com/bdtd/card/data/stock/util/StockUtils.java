package com.bdtd.card.data.stock.util;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bdtd.card.data.stock.model.ResultCompare;
import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;

public class StockUtils {
	
	public static List<ResultDetail> sortAndLimit(List<ResultDetail> result, float limit, float maxIncrease) {
		result = result.stream().filter((item) -> {return item.getHasIncrease() <= maxIncrease;}).collect(Collectors.toList());
		result.sort((a, b) -> {
			if (b.getHasIncrease() == a.getHasIncrease()) {
				return 0;
			}
			return b.getHasIncrease() > a.getHasIncrease() ? 1 : -1;
		});
		if (result == null || result.size() < 100) {
			return result;
		}
		return result.subList(0, 100);
		
	}
	
	public static StockMiddleEntity findFutureMaxIncrease(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end >= stockMains.size() ? stockMains.size() - 1 : end;
		int minIndex = 0;
		int maxIndex = 0;
		float min = stockMains.get(begin).getClose();
		float max = Float.MIN_VALUE;
		float maxIncrease = 0;
		
		float close = 0;
		for (int i = begin + 1; i <= end; i++) {
			close = stockMains.get(i).getClose();
			if (close > max) {
				max = close;
				maxIndex = i;
			}
		}
		
		maxIncrease = (max - min) * 100 / min;
		return new StockMiddleEntity(minIndex, maxIndex, max, min, maxIncrease);
	}

	/**
	 * 
	 * @param stockMains
	 * @param begin 不包含
	 * @param end  包含
	 * @return
	 */
	public static StockMiddleEntity findMaxIncrease(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end > stockMains.size() ? stockMains.size() - 1 : end;
		int minIndex = 0;
		int maxIndex = 0;
		float min = Float.MAX_VALUE;
		float max = Float.MIN_VALUE;
		float maxIncrease = 0;
		
		float close = 0;
		for (int i = begin + 1; i <= end; i++) {
			close = stockMains.get(i).getClose();
			if (close < min) {
				min = close;
				minIndex = i;
			} else if (close > max) {
				max = close;
				maxIndex = i;
			}
		}
		
		if (minIndex > maxIndex) {
			max = Float.MIN_VALUE;
			for (int i = minIndex; i <= end; i++) {
				close = stockMains.get(i).getClose();
				if (close > max) {
					max = close;
					maxIndex = i;
				}
			}
		}
		
		maxIncrease = 0;
		if (minIndex < maxIndex) {
			maxIncrease = (max - min) * 100 / min;
		}
		
		return new StockMiddleEntity(minIndex, maxIndex, max, min, maxIncrease);
	}

	public static ResultCompare createResultCompare(List<ResultDetail> result, Date day, Integer checkDay, Integer increaseDay, Integer futureDay, Float minIncrease, Float minTotalIncrease) {
		if (result == null || result.size() == 0) {
			return null;
		}
		
		Integer increaseCount = 0;
		Integer decreaseCount = 0;
		Float average = 0F;
		Float max = Float.MIN_VALUE;
		Float min = Float.MAX_VALUE;
		float total = 0;
		for (ResultDetail s : result) {
			float tmp = s.getFutureIncrease();
			if (tmp > 0) {
				increaseCount++;
			} else {
				decreaseCount++;
			}
			
			if (tmp > max) {
				max = tmp;
			} 
			if (tmp < min) {
				min = tmp;
			}
			
			total += s.getFutureIncrease();
		}
		average = total / result.size();
		return new ResultCompare(checkDay, increaseDay, futureDay, minIncrease, minTotalIncrease, increaseCount, decreaseCount, average, max, min, day);
	}

	public static int getIndex(List<StockMain> stockMains, Date date) {
		int index = -1;
		for (StockMain stockMain : stockMains) {
			index++;
			if (stockMain.getDay().getTime() == date.getTime()) {
				return index;
			} else if (stockMain.getDay().after(date)) {
				return index;
			}
		}
		return -1;
	}

	public static float getVolumeAvgCompare(List<StockMain> stockMains, int index, int day) {
		double total = 0f;
		for (int j = index - day; j < index; j++) {
			total += stockMains.get(j).getVolume().doubleValue();
		}
		return (float)(stockMains.get(index).getVolume().doubleValue() / (total / day));
	}

	public static StockMiddleEntity findRecentMaxIncrease(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end >= stockMains.size() ? stockMains.size() - 1 : end;
		int minIndex = begin;
		int maxIndex = 0;
		float min = stockMains.get(begin).getClose();
		float max = Float.MIN_VALUE;
		float maxIncrease = 0;
		
		float close = 0;
		for (int i = begin + 1; i <= end; i++) {
			close = stockMains.get(i).getClose();
			if (close > max) {
				max = close;
				maxIndex = i;
			}
		}
		
		maxIncrease = (max - min) * 100 / min;
		return new StockMiddleEntity(minIndex, maxIndex, max, min, maxIncrease);
	}
}
