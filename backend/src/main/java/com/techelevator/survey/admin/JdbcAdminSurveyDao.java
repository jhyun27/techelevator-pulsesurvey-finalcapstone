package com.techelevator.survey.admin;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAdminSurveyDao implements AdminSurveyDao {
	
	private JdbcTemplate template;
	
	public JdbcAdminSurveyDao(DataSource datasource) {
		this.template = new JdbcTemplate(datasource);
	}

	@Override
	public void deactivateLocation(String city, String state) {
		String select = "SELECT location_id FROM location WHERE city = ? AND state = ?";
		SqlRowSet row = template.queryForRowSet(select, city, state);
		row.next();
		int locationId = row.getInt("location_id");
		String update = "UPDATE location SET isActive = false WHERE location_id = ?";
		template.update(update, locationId);
	}
	
	@Override
	public void deactivateInstructor(String firstName, String lastName) {
		String select = "SELECT instructor_id FROM instructor WHERE first_name = ? AND last_name = ?";
		SqlRowSet row = template.queryForRowSet(select, firstName, lastName);
		row.next();
		int instructorId = row.getInt("instructor_id");
		String update = "UPDATE instructor SET isActive = false WHERE instructor_id = ?";
		template.update(update, instructorId);
	}

	@Override
	public void deactivateSection(String sectionName) {
		String select = "SELECT section_id FROM cohort WHERE section_name = ?";
		SqlRowSet row = template.queryForRowSet(select, sectionName);
		row.next();
		int sectionId = row.getInt("section_id");
		String update = "UPDATE section SET isActive = false WHERE section_id = ?";
		template.update(update, sectionId);
	}

	@Override
	public void deactivateTopic(String topicName) {
		String select = "SELECT topic_id FROM cohort WHERE topic_name = ?";
		SqlRowSet row = template.queryForRowSet(select, topicName);
		row.next();
		int topicId = row.getInt("topic_id");
		String update = "UPDATE topic SET isActive = false WHERE topic_id = ?";
		template.update(update, topicId);
	}


	@Override
	public void activateCohort(int cohortNumber) {
		String select = "SELECT cohort_id FROM cohort WHERE cohort_number = ?";
		SqlRowSet row = template.queryForRowSet(select, cohortNumber);
		row.next();
		int cohortId = row.getInt("cohort_id");
		String update = "UPDATE cohort SET isActive = true WHERE cohort_id = ?";
		template.update(update, cohortId);
	}

	@Override
	public void activateLocation(String city, String state) {
		String select = "SELECT location_id FROM location WHERE city = ? AND state = ?";
		SqlRowSet row = template.queryForRowSet(select, city, state);
		row.next();
		int locationId = row.getInt("location_id");
		String update = "UPDATE location SET isActive = true WHERE location_id = ?";
		template.update(update, locationId);
	}

	@Override
	public void activateInstructor(String firstName, String lastName) {
		String select = "SELECT instructor_id FROM instructor WHERE first_name = ? AND last_name = ?";
		SqlRowSet row = template.queryForRowSet(select, firstName, lastName);
		row.next();
		int instructorId = row.getInt("instructor_id");
		String update = "UPDATE instructor SET isActive = true WHERE instructor_id = ?";
		template.update(update, instructorId);
	}

	@Override
	public void activateSection(String sectionName) {
		String select = "SELECT section_id FROM cohort WHERE section_name = ?";
		SqlRowSet row = template.queryForRowSet(select, sectionName);
		row.next();
		int sectionId = row.getInt("section_id");
		String update = "UPDATE section SET isActive = true WHERE section_id = ?";
		template.update(update, sectionId);
	}

	@Override
	public void activateTopic(String topicName) {
		String select = "SELECT topic_id FROM cohort WHERE topic_name = ?";
		SqlRowSet row = template.queryForRowSet(select, topicName);
		row.next();
		int topicId = row.getInt("topic_id");
		String update = "UPDATE topic SET isActive = true WHERE topic_id = ?";
		template.update(update, topicId);
	}

	
	@Override
	public void addNewCohort(int cohortNumber) {
		String insert = "INSERT INTO cohort (cohort_id, cohort_number, isActive) VALUES (DEFAULT, ?, true)";
		template.update(insert, cohortNumber);
	}

	@Override
	public void addNewLocation(String city, String state) {
		String insert = "INSERT INTO location (location_id, city, state, isActive) VALUES (DEFAULT, ?, ?, true)";
		template.update(insert, city, state);
	}

	@Override
	public void addNewInstructor(String firstName, String lastName) {
		String insert = "INSERT INTO instructor (instructor_id, first_name, last_name, isActive) VALUES (DEFAULT, ?, ?, true)";
		template.update(insert, firstName, lastName);
	}

	@Override
	public void addNewSection(String sectionName, int instructorId, int locationId, int cohortId) {
		String insert = "INSERT INTO section (section_id, section_name, instructor_id, location_id, cohort_id, isActive) VALUES (DEFAULT, ?, ?, ?, ?, true)";
		template.update(insert, sectionName, instructorId, locationId, cohortId);
	}

	@Override
	public void addNewTopic(String topicName) {
		String insert = "INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, ?, true)";
		template.update(insert, topicName);
	}
	
	@Override
	public void updateLocation(int locationId, String city, String state) {
		String update = "UPDATE location SET city = ?, state = ? WHERE location_id = ?";
		template.update(update, city, state, locationId);
	}
	
	@Override
	public void updateInstructor(int instructorId, String firstName, String lastName) {
		String update = "UPDATE instructor SET first_name = ?, last_name = ? WHERE instructor_id = ?";
		template.update(update, firstName, lastName, instructorId);
	}
	
	@Override
	public void updateSection(int sectionId, String sectionName) {
		String update = "UPDATE section SET section_name = ? WHERE section_id = ?";
		template.update(update, sectionName, sectionId);
	}
	
	@Override
	public void updateTopic(int topicId, String topicName) {
		String update = "UPDATE topic SET topic_name = ? WHERE topic_id = ?";
		template.update(update, topicName, topicId);
	}

	@Override
	public void deactivateCohort(int cohortNumber) {
		String select = "SELECT cohort_id FROM cohort WHERE cohort_number = ?";
		SqlRowSet row = template.queryForRowSet(select, cohortNumber);
		row.next();
		int cohortId = row.getInt("cohort_id");
		String update = "UPDATE cohort SET isActive = false WHERE cohort_id = ?";
		template.update(update, cohortId);
	}

}
