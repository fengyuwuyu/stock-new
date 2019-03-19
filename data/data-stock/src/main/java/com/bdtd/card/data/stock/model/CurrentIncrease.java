package com.bdtd.card.data.stock.model;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 每日股票汇总
 * </p>
 *
 * @author
 * @since 2019-03-16
 */
@TableName("msa_current_increase")
public class CurrentIncrease extends Model<CurrentIncrease> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 股票代码
	 */
	@TableField("symbol")
	private String symbol;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 代码
	 */
	@TableField("code")
	private String code;

	/**
	 * 最高价
	 */
	@TableField("max")
	private Float max;

	/**
	 * 最低价
	 */
	@TableField("min")
	private Float min;
	
	@TableField("close")
	private Float close;

	/**
	 * 当天涨幅
	 */
	@TableField("increase")
	private Float increase;

	/**
	 * 当天成交量
	 */
	@TableField("volume")
	private Long volume;

	/**
	 * 2天涨幅
	 */
	@TableField("two_increase")
	private Float twoIncrease;

	/**
	 * 3天涨幅
	 */
	@TableField("thress_increase")
	private Float thressIncrease;

	/**
	 * 4天涨幅
	 */
	@TableField("four_increase")
	private Float fourIncrease;

	/**
	 * 5天涨幅
	 */
	@TableField("five_increase")
	private Float fiveIncrease;

	/**
	 * 10天涨幅
	 */
	@TableField("ten_increase")
	private Float tenIncrease;

	/**
	 * 15天涨幅
	 */
	@TableField("fifteen_increase")
	private Float fifteenIncrease;

	/**
	 * 20天涨幅
	 */
	@TableField("twenty_increase")
	private Float twentyIncrease;

	/**
	 * 截止当前涨幅
	 */
	@TableField("max_increase")
	private Float maxIncrease;

	/**
	 * 近十天涨幅
	 */
	@TableField("increases")
	private String increases;

	/**
	 * 近十天成交量
	 */
	@TableField("volumes")
	private String volumes;

	/**
	 * 未来五天涨幅
	 */
	@TableField("future_five_day_increase")
	private Float futureFiveDayIncrease;

	/**
	 * 未来十天涨幅
	 */
	@TableField("future_ten_day_increase")
	private Float futureTenDayIncrease;

	/**
	 * 未来十五天涨幅
	 */
	@TableField("future_fifteen_day_increase")
	private Float futureFifteenDayIncrease;

	/**
	 * 未来二十天涨幅
	 */
	@TableField("future_twenty_day_increase")
	private Float futureTwentyDayIncrease;

	/**
	 * 未来十天涨幅
	 */
	@TableField("future_increases")
	private String futureIncreases;

	/**
	 * 未来十天成交量
	 */
	@TableField("future_volumes")
	private String futureVolumes;

	/**
	 * 当天成交量比
	 */
	@TableField("day_volume_avg")
	private Float dayVolumeAvg;

	/**
	 * 两天成交量比
	 */
	@TableField("two_volume_avg")
	private Float twoVolumeAvg;

	/**
	 * 三天成交量比
	 */
	@TableField("three_volume_avg")
	private Float threeVolumeAvg;

	/**
	 * 四天成交量比
	 */
	@TableField("four_volume_avg")
	private Float fourVolumeAvg;

	/**
	 * 五天成交量比
	 */
	@TableField("five_volume_avg")
	private Float fiveVolumeAvg;

	/**
	 * 第一阶段天
	 */
	@TableField("first_level_day")
	private Integer firstLevelDay;

	/**
	 * 第一阶段涨幅
	 */
	@TableField("first_level_increase")
	private Float firstLevelIncrease;

	/**
	 * 第二阶段天
	 */
	@TableField("second_level_day")
	private Integer secondLevelDay;

	/**
	 * 第二阶段涨幅
	 */
	@TableField("second_level_increase")
	private Float secondLevelIncrease;

	/**
	 * 第三阶段天
	 */
	@TableField("third_level_day")
	private Integer thirdLevelDay;

	/**
	 * 第三阶段涨幅
	 */
	@TableField("third_level_increase")
	private Float thirdLevelIncrease;

	/**
	 * 第四阶段天
	 */
	@TableField("four_level_day")
	private Integer fourLevelDay;

	/**
	 * 第四阶段涨幅
	 */
	@TableField("four_level_increase")
	private Float fourLevelIncrease;

	/**
	 * 第五阶段天
	 */
	@TableField("five_level_day")
	private Integer fiveLevelDay;

	/**
	 * 第五阶段涨幅
	 */
	@TableField("five_level_increase")
	private Float fiveLevelIncrease;
	
	@TableField("pre_increase")
	private Float preIncrease;
	
	@TableField("pre_volume")
	private Long preVolume;

	/**
	 * 股票类型
	 */
	@TableField("stock_type")
	private Integer stockType;

	@TableField(exist = false)
	private Integer stockCategory;
	@TableField(exist = false)
	private Float currIncrease;
	@TableField(exist = false)
	private Long currVolume;
	@TableField(exist = false)
	private Long totalSellVolume;
	@TableField(exist = false)
	private Long totalBuyVolume;

	/**
	 * 分析日期
	 */
	@TableField("msa_day")
	private LocalDate msaDay;

	public CurrentIncrease() {
		super();
	}

	public CurrentIncrease(String symbol, String name, String code, Float max, Float min, Float increase, Long volume,
			Float twoIncrease, Float thressIncrease, Float fourIncrease, Float fiveIncrease, Float tenIncrease,
			Float fifteenIncrease, Float twentyIncrease, Float maxIncrease, String increases, String volumes,
			Float futureFiveDayIncrease, Float futureTenDayIncrease, Float futureFifteenDayIncrease,
			Float futureTwentyDayIncrease, String futureIncreases, String futureVolumes, Float dayVolumeAvg,
			Float twoVolumeAvg, Float threeVolumeAvg, Float fourVolumeAvg, Float fiveVolumeAvg, Integer firstLevelDay,
			Float firstLevelIncrease, Integer secondLevelDay, Float secondLevelIncrease, Integer thirdLevelDay,
			Float thirdLevelIncrease, Integer fourLevelDay, Float fourLevelIncrease, Integer fiveLevelDay,
			Float fiveLevelIncrease, Integer stockType, LocalDate msaDay, Float close, Float preIncrease, Long preVolume) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.code = code;
		this.max = max;
		this.min = min;
		this.increase = increase;
		this.volume = volume;
		this.twoIncrease = twoIncrease;
		this.thressIncrease = thressIncrease;
		this.fourIncrease = fourIncrease;
		this.fiveIncrease = fiveIncrease;
		this.tenIncrease = tenIncrease;
		this.fifteenIncrease = fifteenIncrease;
		this.twentyIncrease = twentyIncrease;
		this.maxIncrease = maxIncrease;
		this.increases = increases;
		this.volumes = volumes;
		this.futureFiveDayIncrease = futureFiveDayIncrease;
		this.futureTenDayIncrease = futureTenDayIncrease;
		this.futureFifteenDayIncrease = futureFifteenDayIncrease;
		this.futureTwentyDayIncrease = futureTwentyDayIncrease;
		this.futureIncreases = futureIncreases;
		this.futureVolumes = futureVolumes;
		this.dayVolumeAvg = dayVolumeAvg;
		this.twoVolumeAvg = twoVolumeAvg;
		this.threeVolumeAvg = threeVolumeAvg;
		this.fourVolumeAvg = fourVolumeAvg;
		this.fiveVolumeAvg = fiveVolumeAvg;
		this.firstLevelDay = firstLevelDay;
		this.firstLevelIncrease = firstLevelIncrease;
		this.secondLevelDay = secondLevelDay;
		this.secondLevelIncrease = secondLevelIncrease;
		this.thirdLevelDay = thirdLevelDay;
		this.thirdLevelIncrease = thirdLevelIncrease;
		this.fourLevelDay = fourLevelDay;
		this.fourLevelIncrease = fourLevelIncrease;
		this.fiveLevelDay = fiveLevelDay;
		this.fiveLevelIncrease = fiveLevelIncrease;
		this.stockType = stockType;
		this.msaDay = msaDay;
		this.close = close;
		this.preIncrease = preIncrease;
		this.preVolume = preVolume;
	}

	public Float getPreIncrease() {
		return preIncrease;
	}

	public void setPreIncrease(Float preIncrease) {
		this.preIncrease = preIncrease;
	}

	public Long getPreVolume() {
		return preVolume;
	}

	public void setPreVolume(Long preVolume) {
		this.preVolume = preVolume;
	}

	public Float getClose() {
		return close;
	}

	public void setClose(Float close) {
		this.close = close;
	}

	public Long getCurrVolume() {
		return currVolume;
	}

	public void setCurrVolume(Long currVolume) {
		this.currVolume = currVolume;
	}

	public Float getCurrIncrease() {
		return currIncrease;
	}

	public void setCurrIncrease(Float currIncrease) {
		this.currIncrease = currIncrease;
	}

	public Long getTotalSellVolume() {
		return totalSellVolume;
	}

	public void setTotalSellVolume(Long totalSellVolume) {
		this.totalSellVolume = totalSellVolume;
	}

	public Long getTotalBuyVolume() {
		return totalBuyVolume;
	}

	public void setTotalBuyVolume(Long totalBuyVolume) {
		this.totalBuyVolume = totalBuyVolume;
	}

	public Integer getStockCategory() {
		return stockCategory;
	}

	public void setStockCategory(Integer stockCategory) {
		this.stockCategory = stockCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getMax() {
		return max;
	}

	public void setMax(Float max) {
		this.max = max;
	}

	public Float getMin() {
		return min;
	}

	public void setMin(Float min) {
		this.min = min;
	}

	public Float getIncrease() {
		return increase;
	}

	public void setIncrease(Float increase) {
		this.increase = increase;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Float getTwoIncrease() {
		return twoIncrease;
	}

	public void setTwoIncrease(Float twoIncrease) {
		this.twoIncrease = twoIncrease;
	}

	public Float getThressIncrease() {
		return thressIncrease;
	}

	public void setThressIncrease(Float thressIncrease) {
		this.thressIncrease = thressIncrease;
	}

	public Float getFourIncrease() {
		return fourIncrease;
	}

	public void setFourIncrease(Float fourIncrease) {
		this.fourIncrease = fourIncrease;
	}

	public Float getFiveIncrease() {
		return fiveIncrease;
	}

	public void setFiveIncrease(Float fiveIncrease) {
		this.fiveIncrease = fiveIncrease;
	}

	public Float getTenIncrease() {
		return tenIncrease;
	}

	public void setTenIncrease(Float tenIncrease) {
		this.tenIncrease = tenIncrease;
	}

	public Float getFifteenIncrease() {
		return fifteenIncrease;
	}

	public void setFifteenIncrease(Float fifteenIncrease) {
		this.fifteenIncrease = fifteenIncrease;
	}

	public Float getTwentyIncrease() {
		return twentyIncrease;
	}

	public void setTwentyIncrease(Float twentyIncrease) {
		this.twentyIncrease = twentyIncrease;
	}

	public Float getMaxIncrease() {
		return maxIncrease;
	}

	public void setMaxIncrease(Float maxIncrease) {
		this.maxIncrease = maxIncrease;
	}

	public String getIncreases() {
		return increases;
	}

	public void setIncreases(String increases) {
		this.increases = increases;
	}

	public String getVolumes() {
		return volumes;
	}

	public void setVolumes(String volumes) {
		this.volumes = volumes;
	}

	public Float getFutureFiveDayIncrease() {
		return futureFiveDayIncrease;
	}

	public void setFutureFiveDayIncrease(Float futureFiveDayIncrease) {
		this.futureFiveDayIncrease = futureFiveDayIncrease;
	}

	public Float getFutureTenDayIncrease() {
		return futureTenDayIncrease;
	}

	public void setFutureTenDayIncrease(Float futureTenDayIncrease) {
		this.futureTenDayIncrease = futureTenDayIncrease;
	}

	public Float getFutureFifteenDayIncrease() {
		return futureFifteenDayIncrease;
	}

	public void setFutureFifteenDayIncrease(Float futureFifteenDayIncrease) {
		this.futureFifteenDayIncrease = futureFifteenDayIncrease;
	}

	public Float getFutureTwentyDayIncrease() {
		return futureTwentyDayIncrease;
	}

	public void setFutureTwentyDayIncrease(Float futureTwentyDayIncrease) {
		this.futureTwentyDayIncrease = futureTwentyDayIncrease;
	}

	public String getFutureIncreases() {
		return futureIncreases;
	}

	public void setFutureIncreases(String futureIncreases) {
		this.futureIncreases = futureIncreases;
	}

	public String getFutureVolumes() {
		return futureVolumes;
	}

	public void setFutureVolumes(String futureVolumes) {
		this.futureVolumes = futureVolumes;
	}

	public Float getDayVolumeAvg() {
		return dayVolumeAvg;
	}

	public void setDayVolumeAvg(Float dayVolumeAvg) {
		this.dayVolumeAvg = dayVolumeAvg;
	}

	public Float getTwoVolumeAvg() {
		return twoVolumeAvg;
	}

	public void setTwoVolumeAvg(Float twoVolumeAvg) {
		this.twoVolumeAvg = twoVolumeAvg;
	}

	public Float getThreeVolumeAvg() {
		return threeVolumeAvg;
	}

	public void setThreeVolumeAvg(Float threeVolumeAvg) {
		this.threeVolumeAvg = threeVolumeAvg;
	}

	public Float getFourVolumeAvg() {
		return fourVolumeAvg;
	}

	public void setFourVolumeAvg(Float fourVolumeAvg) {
		this.fourVolumeAvg = fourVolumeAvg;
	}

	public Float getFiveVolumeAvg() {
		return fiveVolumeAvg;
	}

	public void setFiveVolumeAvg(Float fiveVolumeAvg) {
		this.fiveVolumeAvg = fiveVolumeAvg;
	}

	public Integer getFirstLevelDay() {
		return firstLevelDay;
	}

	public void setFirstLevelDay(Integer firstLevelDay) {
		this.firstLevelDay = firstLevelDay;
	}

	public Float getFirstLevelIncrease() {
		return firstLevelIncrease;
	}

	public void setFirstLevelIncrease(Float firstLevelIncrease) {
		this.firstLevelIncrease = firstLevelIncrease;
	}

	public Integer getSecondLevelDay() {
		return secondLevelDay;
	}

	public void setSecondLevelDay(Integer secondLevelDay) {
		this.secondLevelDay = secondLevelDay;
	}

	public Float getSecondLevelIncrease() {
		return secondLevelIncrease;
	}

	public void setSecondLevelIncrease(Float secondLevelIncrease) {
		this.secondLevelIncrease = secondLevelIncrease;
	}

	public Integer getThirdLevelDay() {
		return thirdLevelDay;
	}

	public void setThirdLevelDay(Integer thirdLevelDay) {
		this.thirdLevelDay = thirdLevelDay;
	}

	public Float getThirdLevelIncrease() {
		return thirdLevelIncrease;
	}

	public void setThirdLevelIncrease(Float thirdLevelIncrease) {
		this.thirdLevelIncrease = thirdLevelIncrease;
	}

	public Integer getFourLevelDay() {
		return fourLevelDay;
	}

	public void setFourLevelDay(Integer fourLevelDay) {
		this.fourLevelDay = fourLevelDay;
	}

	public Float getFourLevelIncrease() {
		return fourLevelIncrease;
	}

	public void setFourLevelIncrease(Float fourLevelIncrease) {
		this.fourLevelIncrease = fourLevelIncrease;
	}

	public Integer getFiveLevelDay() {
		return fiveLevelDay;
	}

	public void setFiveLevelDay(Integer fiveLevelDay) {
		this.fiveLevelDay = fiveLevelDay;
	}

	public Float getFiveLevelIncrease() {
		return fiveLevelIncrease;
	}

	public void setFiveLevelIncrease(Float fiveLevelIncrease) {
		this.fiveLevelIncrease = fiveLevelIncrease;
	}

	public Integer getStockType() {
		return stockType;
	}

	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}

	public LocalDate getMsaDay() {
		return msaDay;
	}

	public void setMsaDay(LocalDate msaDay) {
		this.msaDay = msaDay;
	}

	@Override
	public String toString() {
		return "CurrentIncrease [id=" + id + ", symbol=" + symbol + ", name=" + name + ", code=" + code + ", max=" + max
				+ ", min=" + min + ", close=" + close + ", increase=" + increase + ", volume=" + volume
				+ ", twoIncrease=" + twoIncrease + ", thressIncrease=" + thressIncrease + ", fourIncrease="
				+ fourIncrease + ", fiveIncrease=" + fiveIncrease + ", tenIncrease=" + tenIncrease
				+ ", fifteenIncrease=" + fifteenIncrease + ", twentyIncrease=" + twentyIncrease + ", maxIncrease="
				+ maxIncrease + ", increases=" + increases + ", volumes=" + volumes + ", futureFiveDayIncrease="
				+ futureFiveDayIncrease + ", futureTenDayIncrease=" + futureTenDayIncrease
				+ ", futureFifteenDayIncrease=" + futureFifteenDayIncrease + ", futureTwentyDayIncrease="
				+ futureTwentyDayIncrease + ", futureIncreases=" + futureIncreases + ", futureVolumes=" + futureVolumes
				+ ", dayVolumeAvg=" + dayVolumeAvg + ", twoVolumeAvg=" + twoVolumeAvg + ", threeVolumeAvg="
				+ threeVolumeAvg + ", fourVolumeAvg=" + fourVolumeAvg + ", fiveVolumeAvg=" + fiveVolumeAvg
				+ ", firstLevelDay=" + firstLevelDay + ", firstLevelIncrease=" + firstLevelIncrease
				+ ", secondLevelDay=" + secondLevelDay + ", secondLevelIncrease=" + secondLevelIncrease
				+ ", thirdLevelDay=" + thirdLevelDay + ", thirdLevelIncrease=" + thirdLevelIncrease + ", fourLevelDay="
				+ fourLevelDay + ", fourLevelIncrease=" + fourLevelIncrease + ", fiveLevelDay=" + fiveLevelDay
				+ ", fiveLevelIncrease=" + fiveLevelIncrease + ", stockType=" + stockType + ", stockCategory="
				+ stockCategory + ", currIncrease=" + currIncrease + ", currVolume=" + currVolume + ", totalSellVolume="
				+ totalSellVolume + ", totalBuyVolume=" + totalBuyVolume + ", msaDay=" + msaDay + "]";
	}
}

/*
 * String symbol = null; String name = null; String code = null; Float max =
 * null; Float min = null; Float increase = null; Float twoIncrease = null;
 * Float thressIncrease = null; Float fourIncrease = null; Float fiveIncrease =
 * null; Float tenIncrease = null; Float fifteenIncrease = null; Float
 * twentyIncrease = null; Float maxIncrease = null; String increases = null;
 * String volumes = null; Float futureFiveDayIncrease = null; Float
 * futureTenDayIncrease = null; Float futureFifteenDayIncrease = null; Float
 * futureTwentyDayIncrease = null; String futureIncreases = null; String
 * futureVolumes = null; Float dayVolumeAvg = null; Float twoVolumeAvg = null;
 * Float threeVolumeAvg = null; Float fourVolumeAvg = null; Float fiveVolumeAvg
 * = null; Integer firstLevelDay = null; Float firstLevelIncrease = null;
 * Integer secondLevelDay = null; Float secondLevelIncrease = null; Integer
 * thirdLevelDay = null; Float thirdLevelIncrease = null; Integer fourLevelDay =
 * null; Float fourLevelIncrease = null; Integer fiveLevelDay = null; Float
 * fiveLevelIncrease = null; Integer stockType = null; LocalDate msaDay = null;
 */
