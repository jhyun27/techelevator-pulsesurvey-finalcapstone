package com.techelevator.survey.identifyinginfo;

public class Instructor {
	
	private int instructorId;
	private String firstName;
	private String lastName;
	private boolean active;
	
	public Instructor() {
		
	}
	
	public Instructor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
