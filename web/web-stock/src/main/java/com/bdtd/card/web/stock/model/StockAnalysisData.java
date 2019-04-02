package com.bdtd.card.web.stock.model;

import java.time.LocalDateTime;

import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.util.model.CurrentStockData;

public class StockAnalysisData {

	private CurrentIncrease currentIncrease;
	private CurrentStockData currentStockData;
	private CurrentStockData openStockData;
	private CurrentStockData maxStockData;
	private CurrentStockData minStockData;
	private Integer type;
	private LocalDateTime maxTime;
	private LocalDateTime minTime;
	private LocalDateTime openTime;
	private LocalDateTime currTime;
	private Float raiseIncrease;
	private Float decreaseIncrease;

	public CurrentIncrease getCurrentIncrease() {
		return currentIncrease;
	}

	public void setCurrentIncrease(CurrentIncrease currentIncrease) {
		this.currentIncrease = currentIncrease;
	}

	public CurrentStockData getCurrentStockData() {
		return currentStockData;
	}

	public void setCurrentStockData(CurrentStockData currentStockData) {
		this.currentStockData = currentStockData;
	}

	public CurrentStockData getOpenStockData() {
		return openStockData;
	}

	public void setOpenStockData(CurrentStockData openStockData) {
		this.openStockData = openStockData;
	}

	public CurrentStockData getMaxStockData() {
		return maxStockData;
	}

	public void setMaxStockData(CurrentStockData maxStockData) {
		this.maxStockData = maxStockData;
	}

	public CurrentStockData getMinStockData() {
		return minStockData;
	}

	public void setMinStockData(CurrentStockData minStockData) {
		this.minStockData = minStockData;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public LocalDateTime getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(LocalDateTime maxTime) {
		this.maxTime = maxTime;
	}

	public LocalDateTime getMinTime() {
		return minTime;
	}

	public void setMinTime(LocalDateTime minTime) {
		this.minTime = minTime;
	}

	public LocalDateTime getOpenTime() {
		return openTime;
	}

	public void setOpenTime(LocalDateTime openTime) {
		this.openTime = openTime;
	}

	public LocalDateTime getCurrTime() {
		return currTime;
	}

	public void setCurrTime(LocalDateTime currTime) {
		this.currTime = currTime;
	}

	@Override
	public String toString() {
		return "StockAnalysisData [currentIncrease=" + currentIncrease + ", currentStockData=" + currentStockData
				+ ", openStockData=" + openStockData + ", maxStockData=" + maxStockData + ", minStockData="
				+ minStockData + ", type=" + type + ", maxTime=" + maxTime + ", minTime=" + minTime + ", openTime="
				+ openTime + ", currTime=" + currTime + "]";
	}

}
