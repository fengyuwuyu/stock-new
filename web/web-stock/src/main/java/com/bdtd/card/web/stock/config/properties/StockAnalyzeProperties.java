package com.bdtd.card.web.stock.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = StockAnalyzeProperties.PREFIX)
public class StockAnalyzeProperties {

	public static final String PREFIX = "stock.analyse";

	private Float maxMidIncrease = 2F;
	private Float minMidIncrease = -2F;
	private Float minIncrease = 5F;

	public Float getMaxMidIncrease() {
		return maxMidIncrease;
	}

	public void setMaxMidIncrease(Float maxMidIncrease) {
		this.maxMidIncrease = maxMidIncrease;
	}

	public Float getMinMidIncrease() {
		return minMidIncrease;
	}

	public void setMinMidIncrease(Float minMidIncrease) {
		this.minMidIncrease = minMidIncrease;
	}

	public Float getMinIncrease() {
		return minIncrease;
	}

	public void setMinIncrease(Float minIncrease) {
		this.minIncrease = minIncrease;
	}

	@Override
	public String toString() {
		return "StockAnalyzeProperties [maxMidIncrease=" + maxMidIncrease + ", minMidIncrease=" + minMidIncrease
				+ ", minIncrease=" + minIncrease + "]";
	}

}
