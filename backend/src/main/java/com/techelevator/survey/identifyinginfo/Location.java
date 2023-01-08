package com.techelevator.survey.identifyinginfo;

public class Location {
	
	private int locationId;
	private String city;
	private String state;
	private boolean active;
	
	public Location() {
		
	}
	
	public Location(String city, String state) {
		this.city = city;
		this.state = state;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
