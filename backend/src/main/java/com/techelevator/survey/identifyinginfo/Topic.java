package com.techelevator.survey.identifyinginfo;

public class Topic {
	
	private int topicId;
	private String topicName;
	private boolean active;
	
	public Topic() {
		
	}
	
	public Topic(String topicName) {
		this.topicName = topicName;
	}
	
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}
