package com.techelevator.survey;

import java.util.List;

import com.techelevator.survey.identifyinginfo.Cohort;
import com.techelevator.survey.identifyinginfo.Instructor;
import com.techelevator.survey.identifyinginfo.Location;
import com.techelevator.survey.identifyinginfo.Section;
import com.techelevator.survey.identifyinginfo.Topic;

public interface SurveyDao {
	
	List<SurveyVersion> getSurveys();
	SurveyVersion getSurveyById(int surveyId);
	SurveyVersion addNewSurvey(SurveyVersion survey);
	SurveyVersion updateSurvey(SurveyVersion survey);
	List<SurveyVersion> getAllSurveyVersions(int surveyId);
	List<Cohort> getAllCohorts();
	List<Location> getAllLocations();
	List<Instructor> getAllInstructors();
	List<Section> getAllSections();
	List<Topic> getAllTopics();

}
