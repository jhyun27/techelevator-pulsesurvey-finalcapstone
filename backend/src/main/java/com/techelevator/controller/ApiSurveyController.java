package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.survey.SurveyVersion;
import com.techelevator.survey.SurveyDao;
import com.techelevator.survey.exception.SurveyNotFoundException;
import com.techelevator.survey.identifyinginfo.Cohort;
import com.techelevator.survey.identifyinginfo.Instructor;
import com.techelevator.survey.identifyinginfo.Location;
import com.techelevator.survey.identifyinginfo.Section;
import com.techelevator.survey.identifyinginfo.Topic;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin
public class ApiSurveyController {

	@Autowired
	private SurveyDao surveyDao;
	
	@GetMapping
	public List <SurveyVersion> list() {
		return surveyDao.getSurveys();
	}
	
	@GetMapping("/cohorts")
	public List <Cohort> listCohorts() {
		return surveyDao.getAllCohorts();
	}
	
	@GetMapping("/locations")
	public List <Location> listLocations() {
		return surveyDao.getAllLocations();
	}
	
	@GetMapping("/instructors")
	public List <Instructor> listInstructors() {
		return surveyDao.getAllInstructors();
	}
	
	@GetMapping("/sections")
	public List <Section> listSections() {
		return surveyDao.getAllSections();
	}
	
	@GetMapping("/topics")
	public List <Topic> listTopics() {
		return surveyDao.getAllTopics();
	}
	
	@GetMapping("/{surveyId}")
	public SurveyVersion get(@PathVariable int surveyId) {
		SurveyVersion survey = surveyDao.getSurveyById(surveyId);
		if(survey != null) {
			return survey;
		} else {
			throw new SurveyNotFoundException(surveyId, "Survey Not Found!");
		}
	}
	
	@GetMapping("/{surveyId}/version")
	public List <SurveyVersion> getVersion(@PathVariable int surveyId) {
		return surveyDao.getAllSurveyVersions(surveyId);
	}
	
	//Adds a survey item to the list
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SurveyVersion create(@RequestBody SurveyVersion survey) {
		return surveyDao.addNewSurvey(survey);
	}
	
	//Update existing survey using the id in the path
	@PutMapping("/{surveyId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public SurveyVersion update(@RequestBody SurveyVersion survey, @PathVariable int surveyId) {
		SurveyVersion requestedSurvey = surveyDao.getSurveyById(surveyId);
		if ( requestedSurvey != null ) {
			return surveyDao.updateSurvey(survey);
		} else {
			throw new SurveyNotFoundException(surveyId, "Item Not Found!");
		}
	}
	
}
