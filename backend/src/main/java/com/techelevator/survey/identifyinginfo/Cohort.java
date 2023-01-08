package com.techelevator.survey.identifyinginfo;

public class Cohort {
	
	private int cohortId;
	private int cohortNumber;
	private boolean active;
	
	public Cohort() {
		
	}
	
	public Cohort(int cohortNumber) {
		this.cohortNumber = cohortNumber;
	}
	
	public int getCohortId() {
		return cohortId;
	}
	public void setCohortId(int cohortId) {
		this.cohortId = cohortId;
	}
	public int getCohortNumber() {
		return cohortNumber;
	}
	public void setCohortNumber(int cohortNumber) {
		this.cohortNumber = cohortNumber;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
