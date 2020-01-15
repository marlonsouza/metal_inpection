package com.marlon.desafiopro.spot;

import java.math.BigDecimal;

public class SpotResponse {

	private Integer totalArea;
	private Integer numberSpots;
	private BigDecimal averageArea;
	private BigDecimal biggestArea;
	
	public SpotResponse() {
	}
	
	public static SpotResponse create() {
		SpotResponse instance = new SpotResponse();
		
		instance.numberSpots = 0;
		instance.totalArea = 0;
		instance.averageArea = BigDecimal.valueOf(0);
		instance.biggestArea = BigDecimal.valueOf(0);
		
		return instance;
	
	}
	
	public void setTotalArea(Integer totalArea) {
		this.totalArea = totalArea;
	}

	public void setNumberSpots(Integer numberSpots) {
		this.numberSpots = numberSpots;
	}

	public Integer getTotalArea() {
		return totalArea;
	}
	
	public Integer getNumberSpots() {
		return numberSpots;
	}
	
	public BigDecimal getAverageArea() {
		return averageArea;
	}
	
	public void setAverageArea(BigDecimal averageArea) {
		this.averageArea = averageArea;
	}
	
	public BigDecimal getBiggestArea() {
		return biggestArea;
	}
	
	public void setBiggestArea(BigDecimal biggestArea) {
		this.biggestArea = biggestArea;
	}
	
	public void addTotalArea() {
		this.totalArea++;
	}
	
	public void addNumberSpots() {
		this.numberSpots++;
	}
	
}
