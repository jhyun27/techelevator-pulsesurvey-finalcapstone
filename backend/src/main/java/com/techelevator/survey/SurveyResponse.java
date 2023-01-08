package com.techelevator.survey;

public class SurveyResponse {
	
	private int responseId;
	private int versionId;
	private String studentName;
	private String studentId;
	private boolean isPresent;
	private int totalScore;
	private int numberCorrect;
	private int pace;
	private int interest;
	private int understanding;
	private String energyEntry;
	private int energyInt;
	
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public boolean isPresent() {
		return isPresent;
	}
	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getNumberCorrect() {
		return numberCorrect;
	}
	public void setNumberCorrect(int numberCorrect) {
		this.numberCorrect = numberCorrect;
	}
	public int getPace() {
		return pace;
	}
	public void setPace(int pace) {
		this.pace = pace;
	}
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	public int getUnderstanding() {
		return understanding;
	}
	public void setUnderstanding(int understanding) {
		this.understanding = understanding;
	}
	public String getEnergyEntry() {
		return energyEntry;
	}
	public void setEnergyEntry(String energyEntry) {
		this.energyEntry = energyEntry;
	}
	public int getEnergyInt() {
		return energyInt;
	}
	public void setEnergyInt(int energyInt) {
		this.energyInt = energyInt;
	}

}
