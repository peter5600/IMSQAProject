package com.qa.ims.persistence.domain;


public class Items {
	private Long ID;//add the fields i need from the ERD
	private String Name;
	private float Cost;
	private Long UserID;//fill out userid for the user
	
	public Items(String Name, float Cost) {//generate Item Constructor
		this.setUserID(UserID);
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
		return String.format("ID: %d Item Name: %s Cost: %s", this.getID(), this.getName(), String.format("%.02f",this.getCost()));//2dp
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
		if(cost > 0) {
		Cost = cost;
		}
	}
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID2) {
		UserID = userID2;
	}
	
}
