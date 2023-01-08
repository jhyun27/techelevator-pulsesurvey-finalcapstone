package com.techelevator.survey;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.techelevator.survey.identifyinginfo.Cohort;
import com.techelevator.survey.identifyinginfo.Instructor;
import com.techelevator.survey.identifyinginfo.Location;
import com.techelevator.survey.identifyinginfo.Section;
import com.techelevator.survey.identifyinginfo.Topic;
import com.techelevator.user.User;

public class SurveyVersion {
	
	private int versionId;
	private int surveyId;
	private User user;
	private Timestamp timestamp;
	private Cohort cohort;
	private Location location;
	private Instructor instructor;
	private Section section;
	private Topic topic;
	private List<SurveyResponse> surveyResponses;
	
	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Cohort getCohort() {
		return cohort;
	}

	public void setCohort(Cohort cohort) {
		this.cohort = cohort;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public List<SurveyResponse> getSurveyResponses() {
		return surveyResponses;
	}

	public void setSurveyResponses(List<SurveyResponse> surveyResponses) {
		this.surveyResponses = surveyResponses;
	}

}
