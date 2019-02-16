package com.bdtd.card.data.stock.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * <p>
 * 股票监控
 * </p>
 *
 * @author lilei
 * @since 2019-01-29
 */
@TableName("stock_monitor")
public class Monitor extends Model<Monitor> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 股票代码
	 */
	@TableField("symbol")
	private String symbol;

	/**
	 * 开始时间
	 */
	@TableField("begin_date")
	private LocalDate beginDate;

	/**
	 * 结束时间
	 */
	@TableField("end_date")
	private LocalDate endDate;

	/**
	 * 买入价格
	 */
	@TableField("buy_price")
	private BigDecimal buyPrice;

	/**
	 * 最高出售价格
	 */
	@TableField("sell_price_high")
	private BigDecimal sellPriceHigh;

	/**
	 * 最低出售价格
	 */
	@TableField("sell_price_low")
	private BigDecimal sellPriceLow;

	/**
	 * 创建时间
	 */
	@TableField("create_date")
	private LocalDateTime createDate;

	/**
	 * 更新时间
	 */
	@TableField("update_date")
	private LocalDateTime updateDate;

	@TableField("monitor_type")
	private Integer monitorType;

	@TableField(exist = false)
	private Integer type;

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellPriceHigh() {
		return sellPriceHigh;
	}

	public void setSellPriceHigh(BigDecimal sellPriceHigh) {
		this.sellPriceHigh = sellPriceHigh;
	}

	public BigDecimal getSellPriceLow() {
		return sellPriceLow;
	}

	public void setSellPriceLow(BigDecimal sellPriceLow) {
		this.sellPriceLow = sellPriceLow;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Monitor [id=" + id + ", symbol=" + symbol + ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", buyPrice=" + buyPrice + ", sellPriceHigh=" + sellPriceHigh + ", sellPriceLow=" + sellPriceLow
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", monitorType=" + monitorType
				+ ", type=" + type + "]";
	}
}
