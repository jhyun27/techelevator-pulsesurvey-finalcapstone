package com.techelevator.survey.identifyinginfo;

public class Section {
	
	private int sectionId;
	private String sectionName;
	private boolean active;
	
	public Section() {
		
	}
	
	public Section(String sectionName) {
		this.sectionName = sectionName;
	}
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
