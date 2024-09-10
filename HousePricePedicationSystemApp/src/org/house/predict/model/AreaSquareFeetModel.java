package org.house.predict.model;

public class AreaSquareFeetModel {

	private int id;
	private int squareFeet;
	
	public AreaSquareFeetModel() {
		
	}
	public AreaSquareFeetModel(int squareFeet) {
		this.squareFeet=squareFeet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(int squareFeet) {
		this.squareFeet = squareFeet;
	}
}
