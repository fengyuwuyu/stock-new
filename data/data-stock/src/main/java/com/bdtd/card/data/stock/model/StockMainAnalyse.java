package com.bdtd.card.data.stock.model;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.util.CommonsUtil;

/**
 * 找出连续5天涨幅大于10%的股票
 * 
 * @author lilei
 * 
 */
public class StockMainAnalyse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7325927076713573583L;

	private Logger log = LoggerFactory.getLogger(StockMainAnalyse.class);

	private String symbol;
	/** 当天日期 */
	private String now;
	/** 当天涨幅 */
	private float nowIncrease;
	/** 最高价与最低价之差 */
	private float maxMinIncrease;
	/** */
	private int maxMinIncreaseType;
	/** 最低价与当天价格之差 */
	
	private float minNowIncrease;
	/** */
	private int minNowIncreaseType;
	/** 最高价与最低价相隔天数 */
	private int maxMinDays;
	/** */
	
	private int maxMinDaysType;
	/** 最低价与当天相隔天数 */
	
	private int minNowDays;
	/** 最低价与当天相隔天数的类型 */
	
	private int minNowDaysType;
	/** 连续若干天的股票信息 */
	
	private List<DayIncrease> dayIncreases;
	/** 从now开始的若干天的持续涨幅 */
	private double lastIncrease;
	private int type;
	
	private int index = -1;
	
	private int remainDays = 5;
	
	private StringBuilder builder = new StringBuilder();
	
	private String data;
	private long volume;

	/**
	 * 判断从now开始的涨幅是否大于20，若大于20，则将其插入到数据库中
	 * 
	 * @param now
	 */
	public boolean analyse(String n, int c) {
		this.now = n;
		if(c!=0){
			this.remainDays = c;
		}
		if (dayIncreases != null && dayIncreases.size() > 0) {
			for (int i = 0; i < dayIncreases.size(); i++) {
				if (now.equals(dayIncreases.get(i).getDay())) {
					index = i;
					nowIncrease = dayIncreases.get(i).getIncrease();
					volume = dayIncreases.get(i).getVolume();
				}
				if (index != -1) {
					if (rightTypeStock()) {
						rightStock();
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 判断股票类型
	 * 
	 * @return
	 */
	private boolean rightTypeStock() {
		if (index + remainDays > dayIncreases.size()) {
			return false;
		}
		int[] temp = getMaxAndMin(dayIncreases, 0, index - 1);
		int max = temp[0];
		int min = temp[1];
		if (max < min) {
			maxMinIncrease = (this.dayIncreases.get(max).getClose() - this.dayIncreases
					.get(min).getClose())
					* 100
					/ this.dayIncreases.get(max).getClose();
			if (index == min) {
				this.type = 1;
			} else {
				this.type = 2;
			}
		} else {
			maxMinIncrease = (this.dayIncreases.get(max).getClose() - this.dayIncreases
					.get(index).getClose())
					* 100
					/ this.dayIncreases.get(max).getClose();
			if (index == max) {
				this.type = 3;
			} else {
				this.type = 4;
			}
		}
		return true;
	}

	/**
	 * 计算5天内涨幅
	 * 
	 * @return
	 */
	private boolean rightStock() {
		if(index+1>=dayIncreases.size()||index + remainDays-1>=dayIncreases.size()){
			return false;
		}
		int max = this.getMaxAndMin(dayIncreases, index + 1, index + remainDays-1)[0];
		this.lastIncrease = (dayIncreases.get(max).getClose() - dayIncreases
				.get(index).getClose())
				* 100
				/ dayIncreases.get(index).getClose();
		if (this.lastIncrease >= 10) {
			return true;
		}
		return false;
	}

	/**
	 * 判断连续的50天内波动情况
	 * 
	 * @return
	 */
	public boolean analyse1() {
		if (dayIncreases == null || dayIncreases.size() < 30) {
			return false;
		}
		this.now = dayIncreases.get(dayIncreases.size() - 1).getDay();
		this.nowIncrease = dayIncreases.get(dayIncreases.size() - 1)
				.getIncrease();
		int[] indexs = getMaxAndMin(dayIncreases, 0, dayIncreases.size() - 1);
		return charge(indexs);

	}

	private boolean charge(int[] indexs) {
		int max = indexs[0];
		int min = indexs[1];
		log.info("最高点是：   " + dayIncreases.get(max).getDay() + ", "
				+ dayIncreases.get(max).getClose() + "   最低点是：  "
				+ dayIncreases.get(min).getDay() + " , "
				+ dayIncreases.get(min).getClose());
		float maxClose = dayIncreases.get(max).getClose();
		float minClose = dayIncreases.get(min).getClose();
		float nowClose = dayIncreases.get(dayIncreases.size() - 1).getClose();
		String maxDay = dayIncreases.get(max).getDay();
		String minDay = dayIncreases.get(min).getDay();
		this.maxMinDays = CommonsUtil.getDayDiff(maxDay, minDay);
		this.minNowDays = CommonsUtil.getDayDiff(minDay, now);
		this.maxMinIncrease = (maxClose - minClose) * 100 / maxClose;
		this.minNowIncrease = (nowClose - minClose) * 100 / minClose;
		this.maxMinDaysType = this.maxMinDays / 3 + 1;
		this.minNowDaysType = this.minNowDays / 3 + 1;
		this.maxMinIncreaseType = (int) (this.maxMinIncrease / 5 + 1);
		this.minNowIncreaseType = (int) (this.minNowIncrease / 5 + 1);
		if (this.maxMinIncrease < 40 || this.minNowIncrease > 15
				|| this.nowIncrease < 1) {
			return false;
		}
		log.info(this.toString());
		return true;
	}

	private int[] getMaxAndMin(List<DayIncrease> dayIncreases, int begin,
			int end) {
		DayIncrease maxStock = null;
		DayIncrease minStock = null;
		Integer max = begin;
		Integer min = begin;
		maxStock = dayIncreases.get(begin);
		minStock = dayIncreases.get(begin);
		for (int i = begin; i < end; i++) {
			if (dayIncreases.get(i).getClose() > maxStock.getClose()) {
				maxStock = dayIncreases.get(i);
				max = i;
			}
		}
		for (int i = max; i < end; i++) {
			if (dayIncreases.get(i).getClose() < minStock.getClose()) {
				minStock = dayIncreases.get(i);
				min = i;
			}
		}
		return new int[] { max, min };
	}
	
	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public float getNowIncrease() {
		return nowIncrease;
	}

	public void setNowIncrease(float nowIncrease) {
		this.nowIncrease = nowIncrease;
	}

	public List<DayIncrease> getDayIncreases() {
		return dayIncreases;
	}

	public void setDayIncreases(List<DayIncrease> dayIncreases) {
		this.dayIncreases = dayIncreases;
	}

	public double getLastIncrease() {
		return lastIncrease;
	}

	public void setLastIncrease(double lastIncrease) {
		this.lastIncrease = lastIncrease;
	}

	/** 1：先跌后涨；2：先涨后跌；3：震荡；4：仍在上涨 */
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRemainDays() {
		return remainDays;
	}

	public void setRemainDays(int remainDays) {
		this.remainDays = remainDays;
	}

	public String getData() {
		return data;
	}

	public float getMaxMinIncrease() {
		return maxMinIncrease;
	}

	public void setMaxMinIncrease(float maxMinIncrease) {
		this.maxMinIncrease = maxMinIncrease;
	}

	public float getMinNowIncrease() {
		return minNowIncrease;
	}

	public void setMinNowIncrease(float minNowIncrease) {
		this.minNowIncrease = minNowIncrease;
	}

	public int getMaxMinDays() {
		return maxMinDays;
	}

	public void setMaxMinDays(int maxMinDays) {
		this.maxMinDays = maxMinDays;
	}

	public int getMinNowDays() {
		return minNowDays;
	}

	public void setMinNowDays(int minNowDays) {
		this.minNowDays = minNowDays;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	public int getMaxMinIncreaseType() {
		return maxMinIncreaseType;
	}

	public void setMaxMinIncreaseType(int maxMinIncreaseType) {
		this.maxMinIncreaseType = maxMinIncreaseType;
	}

	public int getMinNowIncreaseType() {
		return minNowIncreaseType;
	}

	public void setMinNowIncreaseType(int minNowIncreaseType) {
		this.minNowIncreaseType = minNowIncreaseType;
	}

	public int getMaxMinDaysType() {
		return maxMinDaysType;
	}

	public void setMaxMinDaysType(int maxMinDaysType) {
		this.maxMinDaysType = maxMinDaysType;
	}

	public int getMinNowDaysType() {
		return minNowDaysType;
	}

	public void setMinNowDaysType(int minNowDaysType) {
		this.minNowDaysType = minNowDaysType;
	}

	@Override
	public String toString() {
		return "StockMainAnalyse [log=" + log + ", symbol=" + symbol + ", now="
				+ now + ", nowIncrease=" + nowIncrease + ", maxMinIncrease="
				+ maxMinIncrease + ", maxMinIncreaseType=" + maxMinIncreaseType
				+ ", minNowIncrease=" + minNowIncrease
				+ ", minNowIncreaseType=" + minNowIncreaseType
				+ ", maxMinDays=" + maxMinDays + ", maxMinDaysType="
				+ maxMinDaysType + ", minNowDays=" + minNowDays
				+ ", minNowDaysType=" + minNowDaysType + ", dayIncreases="
				+ dayIncreases + ", lastIncrease=" + lastIncrease + ", type="
				+ type + ", index=" + index + ", remainDays=" + remainDays
				+ ", builder=" + builder + ", data=" + data + ", volume="
				+ volume + "]";
	}

	public void initAnalyse(StockMainMapper stockMainMapper) {
		this.index = 50;
		while (index < dayIncreases.size() - 5) {

		}
	}

}
