package com.qa.ims.persistence.domain;


public class Items {
	private Long ID;//add the fields i need from the ERD
	private String Name;
	private float Cost;
	
	public Items(String Name, float Cost) {//generate Item Constructor
		this.setCost(Cost);
		this.setName(Name);
		
	}
	
	public Items(Long ID, String Name, float Cost) {//generate Item Constructor with ID
		this(Name, Cost);
		this.setID(ID);
	}
	
	public Items(Long ID) {//get the item from id constructor
		this.setID(ID);
		//get the rest 
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d The Item Name: %s Cost: %s", this.getID(), this.getName(), String.format("%.02f",this.getCost()));//2dp
	}
	
	//getters and setters below
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public float getCost() {
		return Cost;
	}
	public void setCost(float cost) {
		Cost = cost;
	}
	
	
}
