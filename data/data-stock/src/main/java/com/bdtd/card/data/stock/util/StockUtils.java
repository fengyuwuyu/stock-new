package com.bdtd.card.data.stock.util;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bdtd.card.common.util.HttpUtils;
import com.bdtd.card.common.util.StringUtil;
import com.bdtd.card.data.stock.base.MidStockLevel;
import com.bdtd.card.data.stock.base.StockType;
import com.bdtd.card.data.stock.model.ResultCompare;
import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.StockMiddleEntity;
import com.bdtd.card.data.stock.util.model.CurrentStockData;

public class StockUtils {
	
	public static Map<String, CurrentStockData> getCurrentStockData(String stockCurrDataUrl, List<String> symbols, List<Integer> types) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < symbols.size(); i++) {
			if (types.get(i) == 0) {
				sb.append("sh");
			} else {
				sb.append("sz");
			}
			sb.append(symbols.get(i));
			sb.append(",");
		}
		String result = HttpUtils.sendGet(stockCurrDataUrl + sb.substring(0, sb.length() - 1), null, "GB2312");
		return dealResult(result);
	}

	@SuppressWarnings("unused")
	public static Map<String, CurrentStockData> dealResult(String result) {
		if (StringUtil.isNullEmpty(result)) {
			return Collections.emptyMap();
		}
		
		Map<String, CurrentStockData> map = new HashMap<>();
		String[] arr = result.split(";");
		if (arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				String str = arr[i];
				str = str.replace("\"", "");
				try {
					String[] contents = str.split(",");
					if (contents != null && contents.length > 0) {
						String symbol = contents[0].split("=")[0];
						symbol = symbol.substring(symbol.length() - 6);
						
						float open = Float.valueOf(contents[1]);
						float close = Float.valueOf(contents[2]);
						float current = Float.valueOf(contents[3]);
						float max = Float.valueOf(contents[4]);
						float min = Float.valueOf(contents[5]);
						long volume = Long.valueOf(contents[8]);
						
						float buy1 = Float.valueOf(contents[11]);
						long buyVolume1 = Long.valueOf(contents[10]);
						float buy2 = Float.valueOf(contents[13]);
						long buyVolume2 = Long.valueOf(contents[12]);
						float buy3 = Float.valueOf(contents[15]);
						long buyVolume3 = Long.valueOf(contents[14]);
						float buy4 = Float.valueOf(contents[17]);
						long buyVolume4 = Long.valueOf(contents[16]);
						float buy5 = Float.valueOf(contents[19]);
						long buyVolume5 = Long.valueOf(contents[18]);
						long totalBuyVolume = 0L;
						totalBuyVolume += buyVolume1;
						totalBuyVolume += buyVolume2;
						totalBuyVolume += buyVolume3;
						totalBuyVolume += buyVolume4;
						totalBuyVolume += buyVolume5;

						long sellVolume1 = Long.valueOf(contents[20]);
						float sell1 = Float.valueOf(contents[21]);
						long sellVolume2 = Long.valueOf(contents[22]);
						float sell2 = Float.valueOf(contents[23]);
						long sellVolume3 = Long.valueOf(contents[24]);
						float sell3 = Float.valueOf(contents[25]);
						long sellVolume4 = Long.valueOf(contents[26]);
						float sell4 = Float.valueOf(contents[27]);
						long sellVolume5 = Long.valueOf(contents[28]);
						float sell5 = Float.valueOf(contents[29]);
						long totalSellVolume = 0L;
						totalSellVolume += sellVolume1;
						totalSellVolume += sellVolume2;
						totalSellVolume += sellVolume3;
						totalSellVolume += sellVolume4;
						totalSellVolume += sellVolume5;
						CurrentStockData currentStockData = new CurrentStockData(symbol, current, volume, totalSellVolume, totalBuyVolume);
						map.put(symbol, currentStockData);
					}
				} catch (Exception e) {
				}
			}
		}
		return map;
	}
	
	public static int getMinIndex(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end >= stockMains.size() ? stockMains.size() - 1 : end;
		
		float min = stockMains.get(begin).getClose();
		int minIndex = begin;
		
		for (int i = begin; i <= end; i++) {
			float close = stockMains.get(i).getClose();
			if (close < min) {
				min = close;
				minIndex = i;
			}
		}
		
		return minIndex;
	}
	
	public static int getMaxIndex(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end >= stockMains.size() ? stockMains.size() - 1 : end;
		
		float max = stockMains.get(begin).getClose();
		int maxIndex = begin;
		
		for (int i = begin; i <= end; i++) {
			float close = stockMains.get(i).getClose();
			if (close > max) {
				max = close;
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
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
		if (begin == end) {
			return new StockMiddleEntity(begin, begin, begin, begin, 0F);
		}
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
		int minIndex = begin + 1;
		int maxIndex = begin + 1;
		float min = stockMains.get(begin + 1).getClose();
		float max = stockMains.get(begin + 1).getClose();
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
		
		if (minIndex < maxIndex) {
			maxIncrease = (max - min) * 100 / min;
		}
		
		return new StockMiddleEntity(minIndex, maxIndex, max, min, maxIncrease);
	}

	public static StockMiddleEntity findCurrentMaxIncrease(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end > stockMains.size() ? stockMains.size() - 1 : end;
		int minIndex = begin + 1;
		int maxIndex = begin + 1;
		float min = stockMains.get(begin + 1).getClose();
		float max = stockMains.get(begin + 1).getClose();
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
		
		if (maxIndex != end) {
			min = Float.MAX_VALUE;
			for (int i = maxIndex + 1; i <= end; i++) {
				close = stockMains.get(i).getClose();
				if (close < min) {
					min = close;
					minIndex = i;
				}
			}
		}
		if (minIndex > maxIndex) {
			maxIncrease = (min - max) * 100 / min;
		} else {
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
			}
		}
		return -1;
	}

	public static float getVolumeAvgCompare(List<StockMain> stockMains, int index, int day) {
		double total = 0f;
		int begin = index - day;
		begin = begin < 0 ? 0 : begin;
		for (int j = begin; j < index; j++) {
			total += stockMains.get(j).getVolume().doubleValue();
		}
		try {
			return Float.valueOf(CommonsUtil.formatDecimal(stockMains.get(index).getVolume().doubleValue() / (total / day)));
		} catch (Exception e) {
			return 1000F;
		}
	}

	public static StockMiddleEntity findRecentMaxIncrease(List<StockMain> stockMains, int begin, int end) {
		begin = begin < 0 ? 0 : begin;
		end = end >= stockMains.size() ? stockMains.size() - 1 : end;
		int minIndex = begin;
		int maxIndex = begin;
		float base = stockMains.get(begin).getClose();
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		float maxIncrease = 0;
		
		float close = 0;
		for (int i = begin + 1; i <= end; i++) {
			close = stockMains.get(i).getClose();
			if (close > max) {
				max = close;
				maxIndex = i;
			} else if (close < min) {
				min = close;
				minIndex = i;
			}
		}
		
		if (max >= base) {
			maxIncrease = (max - base) * 100 / base;
		} else {
			maxIncrease = (min - base) * 100 / base;
		}
		return new StockMiddleEntity(minIndex, maxIndex, max, base, maxIncrease);
	}

	public static MidStockLevel getStockLevel(List<StockMain> stockMains, int begin, int end) {
		Integer firstLevelDay = null;
		Float firstLevelIncrease = null;
		Integer secondLevelDay = null;
		Float secondLevelIncrease = null;
		Integer thirdLevelDay = null;
		Float thirdLevelIncrease = null;
		Integer stockType = null;
		
		
		int maxIndex = getMaxIndex(stockMains, begin, end);
		int minIndex = getMinIndex(stockMains, begin, maxIndex);
		// 2.若顶部是当天
		firstLevelDay = maxIndex - minIndex;
		firstLevelIncrease = (stockMains.get(maxIndex).getClose() - stockMains.get(minIndex).getClose()) * 100 / stockMains.get(minIndex).getClose();
		stockType = StockType.INCREASE.getType();
		if (maxIndex == end) {
			return new MidStockLevel(firstLevelDay, firstLevelIncrease, stockType);
		}
		
		int secondMinIndex = getMinIndex(stockMains, maxIndex + 1, end);
		// 3.若第二底部是当天
		secondLevelDay = secondMinIndex - maxIndex;
		secondLevelIncrease = (stockMains.get(secondMinIndex).getClose() - stockMains.get(maxIndex).getClose()) * 100 / stockMains.get(maxIndex).getClose();
		stockType = StockType.DECREASE.getType();
		if (secondMinIndex == end) {
			return new MidStockLevel(firstLevelDay, firstLevelIncrease, secondLevelDay, secondLevelIncrease, stockType);
		}
		
		int thirdMaxIndex = getMaxIndex(stockMains, secondMinIndex + 1, end);
		thirdLevelDay = thirdMaxIndex - secondMinIndex;
		thirdLevelIncrease = (stockMains.get(thirdMaxIndex).getClose() - stockMains.get(secondMinIndex).getClose()) * 100 / stockMains.get(secondMinIndex).getClose();
		stockType = StockType.REBOUND.getType();
		return new MidStockLevel(firstLevelDay, firstLevelIncrease, secondLevelDay, secondLevelIncrease, thirdLevelDay, thirdLevelIncrease, stockType);
	}
}
