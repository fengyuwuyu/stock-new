package com.bdtd.card.data.stock.model;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
/**
 * <p>
 * 最近最大涨幅分析
 * </p>
 *
 * @author lilei
 * @since 2019-03-08
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
     * 当天涨幅
     */
    @TableField("increase")
    private Float increase;

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
     * 未来十天涨幅
     */
    @TableField("future_increases")
    private String futureIncreases;

    /**
     * 未来十天成交量
     */
    @TableField("future_volumes")
    private String futureVolumes;

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

    public Float getIncrease() {
        return increase;
    }

    public void setIncrease(Float increase) {
        this.increase = increase;
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

    @Override
    public String toString() {
        return "CurrentIncrease{" +
        ", id=" + id +
        ", symbol=" + symbol +
        ", increase=" + increase +
        ", twoIncrease=" + twoIncrease +
        ", thressIncrease=" + thressIncrease +
        ", fourIncrease=" + fourIncrease +
        ", fiveIncrease=" + fiveIncrease +
        ", tenIncrease=" + tenIncrease +
        ", fifteenIncrease=" + fifteenIncrease +
        ", twentyIncrease=" + twentyIncrease +
        ", maxIncrease=" + maxIncrease +
        ", increases=" + increases +
        ", volumes=" + volumes +
        ", futureFiveDayIncrease=" + futureFiveDayIncrease +
        ", futureTenDayIncrease=" + futureTenDayIncrease +
        ", futureIncreases=" + futureIncreases +
        ", futureVolumes=" + futureVolumes +
        "}";
    }
}
