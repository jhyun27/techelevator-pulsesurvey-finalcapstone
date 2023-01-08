package com.techelevator.survey;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.survey.identifyinginfo.Cohort;
import com.techelevator.survey.identifyinginfo.Instructor;
import com.techelevator.survey.identifyinginfo.Location;
import com.techelevator.survey.identifyinginfo.Section;
import com.techelevator.survey.identifyinginfo.Topic;
import com.techelevator.user.User;

@Component
public class JdbcSurveyDao implements SurveyDao {
	
	private JdbcTemplate template;
	
	public JdbcSurveyDao(DataSource datasource) {
		this.template = new JdbcTemplate(datasource);
	}
	
	@Override
	public SurveyVersion getSurveyById(int surveyId) {
		String select = "SELECT sv.version_id, sv.survey_id, users.id, sv.location_id, sv.cohort_id, sv.instructor_id, timestamp, sect.section_id, t.topic_id " + 
				"FROM survey_version sv " + 
				"JOIN section sect ON sect.section_id = sv.section_id " + 
				"JOIN topic t ON sv.topic_id = t.topic_id " + 
				"JOIN users ON sv.user_id = users.id " +
				"WHERE sv.survey_id = ? " + 
				"ORDER BY version_id " + 
				"DESC LIMIT 1";
		SqlRowSet rows = template.queryForRowSet(select, surveyId);
		rows.next();
		SurveyVersion survey = mapRowToSurvey(rows);
		return survey;
	}
	
	//Gets list of most current surveys
	@Override
	public List<SurveyVersion> getSurveys() {
		List<SurveyVersion> surveys = new ArrayList<SurveyVersion>();
		String select = "SELECT sv.version_id, sv.survey_id, users.id, sv.location_id, sv.cohort_id, sv.instructor_id, timestamp, sect.section_id, t.topic_id " + 
				"FROM survey_version sv " + 
				"JOIN section sect ON sect.section_id = sv.section_id " + 
				"JOIN topic t ON sv.topic_id = t.topic_id " +
				"JOIN users ON sv.user_id = users.id " +
				"WHERE sv.version_id = (SELECT MAX(version_id) FROM survey_version WHERE survey_id = sv.survey_id) " +
				"ORDER BY sv.survey_id DESC";
		SqlRowSet rows = template.queryForRowSet(select);
		while(rows.next()) {
			SurveyVersion survey = mapRowToSurvey(rows);
			surveys.add(survey);
		}
		return surveys;
	}

	@Override
	public List<SurveyVersion> getAllSurveyVersions(int surveyId) {
		List <SurveyVersion> surveys = new ArrayList<SurveyVersion>();
		String select = "SELECT sv.version_id, sv.survey_id, users.id, sv.location_id, sv.cohort_id, sv.instructor_id, timestamp, sect.section_id, t.topic_id " + 
				"FROM survey_version sv " + 
				"JOIN section sect ON sect.section_id = sv.section_id " + 
				"JOIN topic t ON sv.topic_id = t.topic_id " + 
				"JOIN users ON sv.user_id = users.id " +
				"WHERE sv.survey_id = ? " + 
				"ORDER BY sv.version_id DESC";
		SqlRowSet rows = template.queryForRowSet(select, surveyId);
		while(rows.next()) {
			SurveyVersion versions = mapRowToSurvey(rows);
			surveys.add(versions);
		}
		return surveys;
	}
	
	@Override
	public SurveyVersion addNewSurvey(SurveyVersion survey) {
		String insertSurvey = "INSERT INTO survey (survey_id) VALUES (DEFAULT) RETURNING survey_id";
		SqlRowSet rowsSurvey = template.queryForRowSet(insertSurvey);
		rowsSurvey.next();
		int surveyId = rowsSurvey.getInt("survey_id");
		survey.setSurveyId(surveyId);
		int versionId = addVersion(survey);
		
		for(SurveyResponse response : survey.getSurveyResponses()) {
			int responseId = addResponse(response, versionId);
			response.setResponseId(responseId);
		}
		
		return survey;
	}
	
	@Override
	public SurveyVersion updateSurvey(SurveyVersion survey) {
		int versionId = addVersion(survey);
		survey.setVersionId(versionId);
		for(SurveyResponse response : survey.getSurveyResponses()) {
			int responseId = addResponse(response, versionId);
			response.setResponseId(responseId);
		}
		return survey;
	}
	
	@Override
	public List<Cohort> getAllCohorts() {
		List<Cohort> cohorts = new ArrayList<Cohort>();
		String select = "SELECT cohort_id, cohort_number, isactive FROM cohort";
		SqlRowSet rows = template.queryForRowSet(select);
		while(rows.next()) {
			Cohort cohort = new Cohort();
			cohort.setCohortId(rows.getInt("cohort_id"));
			cohort.setCohortNumber(rows.getInt("cohort_number"));
			cohort.setActive(rows.getBoolean("isActive"));
			cohorts.add(cohort);
		}
		return cohorts;
	}
	
	@Override
	public List<Location> getAllLocations() {
		List<Location> locations = new ArrayList<Location>();
		String select = "SELECT location_id, city, state, isactive FROM location";
		SqlRowSet rows = template.queryForRowSet(select);
		while(rows.next()) {
			Location location = new Location();
			location.setLocationId(rows.getInt("location_id"));
			location.setCity(rows.getString("city"));
			location.setState(rows.getString("state"));
			location.setActive(rows.getBoolean("isActive"));
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<Instructor> getAllInstructors() {
		List<Instructor> instructors = new ArrayList<Instructor>();
		String select = "SELECT instructor_id, first_name, last_name, isactive FROM instructor";
		SqlRowSet rows = template.queryForRowSet(select);
		while(rows.next()) {
			Instructor instructor = new Instructor();
			instructor.setInstructorId(rows.getInt("instructor_id"));
			instructor.setFirstName(rows.getString("first_name"));
			instructor.setLastName(rows.getString("last_name"));
			instructor.setActive(rows.getBoolean("isActive"));
			instructors.add(instructor);
		}
		return instructors;
	}
	
	@Override
	public List<Section> getAllSections() {
		List<Section> sections = new ArrayList<Section>();
		String select = "SELECT section_id, section_name, isactive FROM section";
		SqlRowSet rows = template.queryForRowSet(select);
		while(rows.next()) {
			Section section = new Section();
			section.setSectionId(rows.getInt("section_id"));
			section.setSectionName(rows.getString("section_name"));
			section.setActive(rows.getBoolean("isActive"));
			sections.add(section);
		}
		return sections;
	}
	
	@Override
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<Topic>();
		String select = "SELECT topic_id, topic_name, isactive FROM topic";
		SqlRowSet rows = template.queryForRowSet(select);
		while(rows.next()) {
			Topic topic = new Topic();
			topic.setTopicId(rows.getInt("topic_id"));
			topic.setTopicName(rows.getString("topic_name"));
			topic.setActive(rows.getBoolean("isActive"));
			topics.add(topic);
		}
		return topics;
	}
	
	private List<SurveyResponse> getSurveyResponses(int surveyId, int versionId) {
		List<SurveyResponse> responses = new ArrayList<SurveyResponse>();
		String select = "SELECT response_id, sr.version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int " + 
				"FROM survey_response sr " + 
				"JOIN survey_version sv ON sr.version_id = sv.version_id " + 
				"WHERE survey_id = ? AND sv.version_id = ?";
		SqlRowSet rows = template.queryForRowSet(select, surveyId, versionId);
		while(rows.next()) {
			SurveyResponse response = mapRowToSurveyResponse(rows);
			responses.add(response);
		}
		return responses;
	}
	
	private SurveyVersion mapRowToSurvey(SqlRowSet row) {
		SurveyVersion survey = new SurveyVersion();
		int surveyId = row.getInt("survey_id");
		int versionId = row.getInt("version_id");
		
		survey.setSurveyId(surveyId);
		survey.setVersionId(versionId);
		survey.setTimestamp(row.getTimestamp("timestamp"));
		survey.setUser(makeUserObj(row.getInt("id")));
		survey.setCohort(makeCohortObj(row.getInt("cohort_id")));
		survey.setInstructor(makeInstructorObj(row.getInt("instructor_id")));
		survey.setLocation(makeLocationObj(row.getInt("location_id")));
		survey.setSection(makeSectionObj(row.getInt("section_id")));
		survey.setTopic(makeTopicObj(row.getInt("topic_id")));
		survey.setSurveyResponses(getSurveyResponses(surveyId, versionId));
		
		return survey;
	}
	
	private SurveyResponse mapRowToSurveyResponse(SqlRowSet row) {
		SurveyResponse response = new SurveyResponse();
		response.setResponseId(row.getInt("response_id"));
		response.setVersionId(row.getInt("version_id"));
		response.setStudentName(row.getString("student_name"));
		response.setStudentId(row.getString("student_id"));
		response.setPresent(row.getBoolean("presence"));
		response.setNumberCorrect(row.getInt("number_correct"));
		response.setTotalScore(row.getInt("total_score"));
		response.setPace(row.getInt("pace"));
		response.setInterest(row.getInt("interest"));
		response.setUnderstanding(row.getInt("understanding"));
		response.setEnergyEntry(row.getString("energy_entry"));
		response.setEnergyInt(row.getInt("energy_int"));
		return response;
	}
	
	private User makeUserObj(int userId) {
		User user = new User();
		user.setId(userId);
		String select = "SELECT username, password, salt, role FROM users WHERE id = ?";
		SqlRowSet row = template.queryForRowSet(select, userId);
		row.next();
		user.setUsername(row.getString("username"));
		user.setPassword(row.getString("password"));
		user.setRole(row.getString("role"));
		return user;
	}


	private Cohort makeCohortObj(int cohortId) {
		Cohort cohort = new Cohort();
		cohort.setCohortId(cohortId);
		String select = "SELECT cohort_number, isActive FROM cohort WHERE cohort_id = ?";
		SqlRowSet row = template.queryForRowSet(select, cohortId);
		row.next();
		cohort.setActive(row.getBoolean("isActive"));
		cohort.setCohortNumber(row.getInt("cohort_number"));
		return cohort;
	}
	
	private Instructor makeInstructorObj(int instructorId) {
		Instructor instructor = new Instructor();
		instructor.setInstructorId(instructorId);
		String select = "SELECT first_name, last_name, isActive FROM instructor WHERE instructor_id = ?";
		SqlRowSet row = template.queryForRowSet(select, instructorId);
		row.next();
		instructor.setFirstName(row.getString("first_name"));
		instructor.setLastName(row.getString("last_name"));
		instructor.setActive(row.getBoolean("isActive"));
		return instructor;
	}
	
	private Location makeLocationObj(int locationId) {
		Location location = new Location();
		location.setLocationId(locationId);
		String select = "SELECT city, state, isActive FROM location WHERE location_id = ?";
		SqlRowSet row = template.queryForRowSet(select, locationId);
		row.next();
		location.setCity(row.getString("city"));
		location.setState(row.getString("state"));
		location.setActive(row.getBoolean("isActive"));
		return location;
	}
	
	private Section makeSectionObj(int sectionId) {
		Section section = new Section();
		section.setSectionId(sectionId);
		String select = "SELECT section_id, section_name, isactive FROM section WHERE section_id = ?";
		SqlRowSet row = template.queryForRowSet(select, sectionId);
		row.next();
		section.setSectionName(row.getString("section_name"));
		section.setActive(row.getBoolean("isActive"));
		return section;
	}
	
	private Topic makeTopicObj(int topicId) {
		Topic topic = new Topic();
		topic.setTopicId(topicId);
		String select = "SELECT topic_id, topic_name, isactive FROM topic WHERE topic_id = ?";
		SqlRowSet row = template.queryForRowSet(select, topicId);
		row.next();
		topic.setTopicName(row.getString("topic_name"));
		topic.setActive(row.getBoolean("isActive"));
		return topic;
	}
	
	private int addVersion(SurveyVersion survey) {
		String selectUserId = "SELECT id FROM users WHERE username = ?";
		SqlRowSet rowUserId = template.queryForRowSet(selectUserId, survey.getUser().getUsername());
		rowUserId.next();
		int userId = rowUserId.getInt("id");
		String insertVersion = "INSERT INTO survey_version (version_id, survey_id, user_id, timestamp, cohort_id, location_id, instructor_id, section_id, topic_id) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING version_id";
		SqlRowSet rowsVersion = template.queryForRowSet(insertVersion, survey.getSurveyId(), userId, Timestamp.valueOf(LocalDateTime.now()), survey.getCohort().getCohortId(), survey.getLocation().getLocationId(), survey.getInstructor().getInstructorId(), survey.getSection().getSectionId(), survey.getTopic().getTopicId());
		rowsVersion.next();
		return rowsVersion.getInt("version_id");
	}
	
	private int addResponse(SurveyResponse response, int versionId) {
		String insertResponse = "INSERT INTO survey_response (response_id, version_id, presence, student_name, student_id, total_score, number_correct, pace, interest, understanding, energy_entry, energy_int) "
				+ "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING response_id";
		SqlRowSet rowsResponses = template.queryForRowSet(insertResponse, versionId, response.isPresent(), response.getStudentName(), response.getStudentId(), response.getTotalScore(), response.getNumberCorrect(), response.getPace(), response.getInterest(), response.getUnderstanding(), response.getEnergyEntry(), response.getEnergyInt());
		rowsResponses.next();
		return rowsResponses.getInt("response_id");
	}
	
	private int getSectionId(String sectionName) {
		String sectionIdSql = "SELECT section_id FROM section WHERE section_name = ?";
		SqlRowSet sectionRow = template.queryForRowSet(sectionIdSql, sectionName);
		sectionRow.next();
		return sectionRow.getInt("section_id");
	}
//	
//	private int getTopicId(String sectionName) {
//		String sectionIdSql = "SELECT section_id FROM section WHERE section_name = ?";
//		SqlRowSet sectionRow = template.queryForRowSet(sectionIdSql, sectionName);
//		sectionRow.next();
//		return sectionRow.getInt("section_id");
//	}
//	
//	private int getSectionId(String sectionName) {
//		String sectionIdSql = "SELECT section_id FROM section WHERE section_name = ?";
//		SqlRowSet sectionRow = template.queryForRowSet(sectionIdSql, sectionName);
//		sectionRow.next();
//		return sectionRow.getInt("section_id");
//	}
	
//	private int addTopic(String topic) {
//		String insertTopic = "INSERT INTO topic (topic_id, topic_name, isActive) VALUES (DEFAULT, ?, true) RETURNING topic_id";
//		SqlRowSet rowsTopic = template.queryForRowSet(insertTopic, topic);
//		rowsTopic.next();
//		return rowsTopic.getInt("topic_id");
//	}
	
//	private int addSurvey(int sectionId, int topicId) {
//		String insertSurvey = "INSERT INTO survey (survey_id, cohort_id, location_id, instructor_id, section_id, topic_id) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING survey_id";
//		SqlRowSet rowsSurvey = template.queryForRowSet(insertSurvey, cohortId, locatinoId, instructorId, sectionId, topicId);
//		rowsSurvey.next();
//		return rowsSurvey.getInt("survey_id");
//	}
	
}
