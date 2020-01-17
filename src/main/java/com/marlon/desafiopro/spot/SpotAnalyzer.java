package com.marlon.desafiopro.spot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.json.JsonArray;

public class SpotAnalyzer {

	private SpotResponse entity;
	private JsonArray toAnalyse;
	private List<List<Integer>> transformedList;
	private Integer biggestSpot;
	private Integer currentSpot;
	
	
	public static SpotAnalyzer create(JsonArray toAnalyse) {
		SpotAnalyzer mySelf = new SpotAnalyzer();
		
		mySelf.entity = SpotResponse.create();
		mySelf.toAnalyse = toAnalyse;
		mySelf.transformedList = new ArrayList<>();
		
		return mySelf;
	}
	
	private void processPoint(Position position) {
		this.transformedList.get(position.getX()).set(position.getY(), 0);
		
		this.currentSpot ++;
	}
	
	private Boolean verifyPoint(Position position) {
		if(!Optional.ofNullable(position).isPresent()) {
			return false;
		}
			
		Integer point = this.transformedList.get(position.getX()).get(position.getY());
		
		return point == 1;
	}

	private Integer getValueJson(Integer x, Integer y) {
		return toAnalyse.get(x).asJsonArray().getInt(y);
	}
	
	public SpotAnalyzer transformList() {
		
		for(Integer x = 0; x < toAnalyse.size(); x++) {
			
			transformedList.add(new ArrayList<>());
			
			for(Integer y = 0; y< toAnalyse.get(x).asJsonArray().size(); y++) {
				transformedList.get(x).add(getValueJson(x, y));
			}
			
		}
		
		return this;
	}
	
	public SpotResponse run() {
		
		Position position = new Position();
				
		position.setSizeX(toAnalyse.size());
		
		this.biggestSpot = 0;
		this.currentSpot = 0;
		
		for(Integer x = 0; x < position.getSizeX(); x++) {
			
			position.setX(x);
			position.setSizeY(toAnalyse.get(position.getX()).asJsonArray().size());
			
			for(Integer y = 0; y< position.getSizeY(); y++) {
				
				position.setY(y);
				
				if(verifyPoint(position)) {
					
					if(this.currentSpot==0) this.currentSpot++;
					
					entity.addTotalArea();
					
					Boolean isSameSpot = 
							Arrays
								.asList(position.bottom(), position.top(), position.next(),position.last())
								.stream()
								.anyMatch(pos -> verifyPoint(pos));		
			
					if(isSameSpot) {
						processPoint(position);
						continue;
					}else{
						processBiggestSpot();
					}
					
					entity.addNumberSpots();
				}
			}
			
		}
		
		this.entity.setBiggestArea(this.biggestSpot);
		
		averageCalc();

		return entity;
		
		
	}

	private void processBiggestSpot() {
		if(currentSpot>biggestSpot) {
			this.biggestSpot = currentSpot;
		}
		
		this.currentSpot = 1;
	}

	private void averageCalc() {
		if(entity.getNumberSpots()>0 && entity.getTotalArea()>0) {
			
			BigDecimal totalArea = BigDecimal.valueOf(entity.getTotalArea());
			BigDecimal numeberSpots = BigDecimal.valueOf(entity.getNumberSpots());
			BigDecimal average = totalArea.divide(numeberSpots, 5, RoundingMode.HALF_UP);
			
			entity.setAverageArea(average);
			
		}
	}
	
}
