package com.marlon.desafiopro.spot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.json.JsonArray;

public class SpotAnalyzer {

	private SpotResponse entity;
	private JsonArray toAnalyse;
	private List<List<Integer>> transformedList;
	
	
	public static SpotAnalyzer create(JsonArray toAnalyse) {
		SpotAnalyzer mySelf = new SpotAnalyzer();
		
		mySelf.entity = SpotResponse.create();
		mySelf.toAnalyse = toAnalyse;
		mySelf.transformedList = new ArrayList<>();
		
		return mySelf;
	}
	
	private void makeZero(Position position) {
		this.transformedList.get(position.getX()).set(position.getY(), 0);
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
		
		for(Integer x = 0; x < position.getSizeX(); x++) {
			
			position.setX(x);
			position.setSizeY(toAnalyse.get(position.getX()).asJsonArray().size());
			
			for(Integer y = 0; y< position.getSizeY(); y++) {
				
				position.setY(y);
				
				if(verifyPoint(position)) {
					entity.addTotalArea();
					
					Boolean isSameSpot = 
							Arrays
								.asList(position.bottom(), position.top(), position.next(),position.last())
								.stream()
								.anyMatch(pos -> verifyPoint(pos));		
			
					if(isSameSpot) {
						makeZero(position);
						continue;
					}
					
					entity.addNumberSpots();
				}
			}
			
		}
		
		return entity;
		
		
	}
	
}
