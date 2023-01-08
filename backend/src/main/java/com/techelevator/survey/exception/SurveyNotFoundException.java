package com.techelevator.survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SurveyNotFoundException extends RuntimeException {

	//A serializable class can declare its own serialVersionUID explicitly
	//by declaring a field named “serialVersionUID” that must be static, final,
	//and of type long.
	private static final long serialVersionUID = 4L; //
	
	private int surveyId;
	
	public SurveyNotFoundException(int id, String message) {
		super(message);
		setSurveyId(id);
	}
	
	public int getSurveyId() {
		return surveyId;

	}
	
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

}
