package com.marlon.desafiopro.spot;

public class Position {
	private Integer x;
	private Integer y;
	private Integer sizeY;
	private Integer sizeX;
	
	private static final Position create(Integer x, Integer y) {
		Position instance = new Position();
		
		instance.x = x;
		instance.y = y;
		
		return instance;
	}
	
	public Integer getSizeY() {
		return sizeY;
	}
	
	public void setSizeY(Integer sizeY) {
		this.sizeY = sizeY;
	}
	
	public Integer getSizeX() {
		return sizeX;
	}
	
	public void setSizeX(Integer sizeX) {
		this.sizeX = sizeX;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
	public Position top() {
		if(this.x - 1<0) return null;
		
		return Position.create(this.x - 1, this.y);
	}
	
	public Position bottom() {
		if(this.sizeX <= this.x + 1) return null;
		
		return Position.create(this.x + 1, this.y);
	}
	
	public Position next() {
		if(this.sizeY <= this.y + 1) return null;
		
		return Position.create(this.x, this.y + 1);
	}
	
	public Position last() {
		if(this.y - 1 < 0) return null;
		
		return Position.create(this.x, this.y - 1);
	}
}
