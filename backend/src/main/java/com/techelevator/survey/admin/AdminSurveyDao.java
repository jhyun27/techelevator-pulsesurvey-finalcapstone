package com.techelevator.survey.admin;

public interface AdminSurveyDao {
	
	void addNewCohort(int cohortNumber);
	void addNewLocation(String city, String state);
	void addNewInstructor(String firstName, String lastName);
	void addNewSection(String sectionName, int instructorId, int locationId, int cohortId);
	void addNewTopic(String topicName);
	
	void updateLocation(int locationId, String city, String state);
	void updateInstructor(int instructorId, String firstName, String lastName);
	void updateSection(int sectionId, String sectionName);
	void updateTopic(int topicId, String topicName);

	void deactivateCohort(int cohortNumber);
	void deactivateLocation(String city, String state);
	void deactivateInstructor(String firstName, String lastName);
	void deactivateSection(String sectionName);
	void deactivateTopic(String topicName);
	
	void activateCohort(int cohortNumber);
	void activateLocation(String city, String state);
	void activateInstructor(String firstName, String lastName);
	void activateSection(String sectionName);
	void activateTopic(String topicName);
	
}
