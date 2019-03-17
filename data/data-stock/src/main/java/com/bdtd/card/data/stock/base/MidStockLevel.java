package com.bdtd.card.data.stock.base;

public class MidStockLevel {

	protected Integer firstLevelDay;
	protected Float firstLevelIncrease;
	protected Integer secondLevelDay;
	protected Float secondLevelIncrease;
	protected Integer thirdLevelDay;
	protected Float thirdLevelIncrease;
	protected Integer fourLevelDay;
	protected Float fourLevelIncrease;
	protected Integer fiveLevelDay;
	protected Float fiveLevelIncrease;
	protected Integer stockType;

	public MidStockLevel() {
		super();
	}

	public MidStockLevel(Integer firstLevelDay, Float firstLevelIncrease, Integer stockType) {
		super();
		this.firstLevelDay = firstLevelDay;
		this.firstLevelIncrease = firstLevelIncrease;
		this.stockType = stockType;
	}

	public MidStockLevel(Integer firstLevelDay, Float firstLevelIncrease, Integer secondLevelDay,
			Float secondLevelIncrease, Integer stockType) {
		super();
		this.firstLevelDay = firstLevelDay;
		this.firstLevelIncrease = firstLevelIncrease;
		this.secondLevelDay = secondLevelDay;
		this.secondLevelIncrease = secondLevelIncrease;
		this.stockType = stockType;
	}

	public MidStockLevel(Integer firstLevelDay, Float firstLevelIncrease, Integer secondLevelDay,
			Float secondLevelIncrease, Integer thirdLevelDay, Float thirdLevelIncrease, Integer stockType) {
		super();
		this.firstLevelDay = firstLevelDay;
		this.firstLevelIncrease = firstLevelIncrease;
		this.secondLevelDay = secondLevelDay;
		this.secondLevelIncrease = secondLevelIncrease;
		this.thirdLevelDay = thirdLevelDay;
		this.thirdLevelIncrease = thirdLevelIncrease;
		this.stockType = stockType;
	}

	public MidStockLevel(Integer firstLevelDay, Float firstLevelIncrease, Integer secondLevelDay,
			Float secondLevelIncrease, Integer thirdLevelDay, Float thirdLevelIncrease, Integer fourLevelDay,
			Float fourLevelIncrease, Integer fiveLevelDay, Float fiveLevelIncrease, Integer stockType) {
		super();
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

}
