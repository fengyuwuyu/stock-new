package com.bdtd.card.data.stock.util.model;

/**
 * @author Administrator
 *
 */
public class CurrentStockData {

	private String symbol;
	private Float currIncrease;
	private Long currVolume;
	private Long totalSellVolume;
	private Long totalBuyVolume;

	public CurrentStockData() {
		super();
	}

	public CurrentStockData(String symbol, Float currIncrease, Long currVolume, Long totalSellVolume,
			Long totalBuyVolume) {
		super();
		this.symbol = symbol;
		this.currIncrease = currIncrease;
		this.currVolume = currVolume;
		this.totalSellVolume = totalSellVolume;
		this.totalBuyVolume = totalBuyVolume;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public Long getCurrVolume() {
		return currVolume;
	}

	public void setCurrVolume(Long currVolume) {
		this.currVolume = currVolume;
	}

	@Override
	public String toString() {
		return "CurrentStockData [symbol=" + symbol + ", currIncrease=" + currIncrease + ", currVolume=" + currVolume
				+ ", totalSellVolume=" + totalSellVolume + ", totalBuyVolume=" + totalBuyVolume + "]";
	}

}
