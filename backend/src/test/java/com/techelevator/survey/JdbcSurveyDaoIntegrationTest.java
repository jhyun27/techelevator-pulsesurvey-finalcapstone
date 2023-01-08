package com.techelevator.survey;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.survey.identifyinginfo.Cohort;
import com.techelevator.survey.identifyinginfo.Instructor;
import com.techelevator.survey.identifyinginfo.Location;
import com.techelevator.survey.identifyinginfo.Section;
import com.techelevator.survey.identifyinginfo.Topic;
import com.techelevator.user.User;

public class JdbcSurveyDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private SurveyDao dao;
	private JdbcTemplate template;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/userdb");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void destroyDataSource() {
		dataSource.destroy();
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Before
	public void setup() {
		dao = new JdbcSurveyDao(dataSource);
		template = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void get_survey_by_id_1() {
		SurveyVersion returnedSurvey = dao.getSurveyById(1);
		Assert.assertEquals(1, returnedSurvey.getSurveyId());
		Assert.assertEquals(2, returnedSurvey.getVersionId());
		Assert.assertEquals("user", returnedSurvey.getUser().getUsername());
	}
	
	@Test
	public void get_survey_by_id_2() {
		SurveyVersion returnedSurvey = dao.getSurveyById(2);
		Assert.assertEquals(2, returnedSurvey.getSurveyId());
		Assert.assertEquals(3, returnedSurvey.getVersionId());
		Assert.assertEquals("user", returnedSurvey.getUser().getUsername());
	}
	
	@Test
	public void get_surveys_size_2() {
		List<SurveyVersion> returnedSurveys = dao.getSurveys();
		Assert.assertEquals(2, returnedSurveys.size());
	}
	
	@Test
	public void add_survey() {
		SurveyVersion survey = makeNewSurvey();
		
		List<SurveyVersion> originalSurveys = dao.getSurveys();
		
		dao.addNewSurvey(survey);
		
		List<SurveyVersion> newSurveys = dao.getSurveys();
		
		Assert.assertEquals(originalSurveys.size() + 1, newSurveys.size());
		
	}
	
	@Test
	public void get_all_survey_versions() {
		List <SurveyVersion> allVersions = dao.getAllSurveyVersions(1);
		Assert.assertEquals(2, allVersions.size());
	}
	
	@Test
	public void update_survey() {
		SurveyVersion addedSurvey = dao.getSurveyById(1);
		Topic topic = new Topic();
		topic.setTopicId(3);
		topic.setTopicName("React.js");
		topic.setActive(true);
		addedSurvey.setTopic(topic);
		dao.updateSurvey(addedSurvey);
		Assert.assertEquals("React.js", addedSurvey.getTopic().getTopicName());
	}
	
	
	private SurveyVersion makeNewSurvey() {
		SurveyVersion survey = new SurveyVersion();
		User user = new User();
		user.setUsername("user");
		user.setId(1);
		survey.setUser(user);
		survey.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		Cohort cohort = new Cohort(9);
		cohort.setCohortId(1);
		survey.setCohort(cohort);
		Instructor instructor = new Instructor("Brian", "Lauvray");
		instructor.setInstructorId(1);
		survey.setInstructor(instructor);
		Location location = new Location("Columbus", "OH");
		location.setLocationId(1);
		survey.setLocation(location);
		Section section = new Section("09-Java-Blue-CBUS");
		section.setSectionId(1);
		survey.setSection(section);
		Topic topic = new Topic("Arrays");
		topic.setTopicId(1);
		survey.setTopic(topic);
		survey.setSurveyResponses(makeSurveyResponses());
		return survey;
	}
	
	private List<SurveyResponse> makeSurveyResponses() {
		List<SurveyResponse> responses = new ArrayList<SurveyResponse>();
		SurveyResponse response = new SurveyResponse();
		response.setStudentName("student_name");
		response.setStudentId("STUDENT");
		response.setPresent(true);
		response.setNumberCorrect(0);
		response.setTotalScore(0);
		response.setPace(1);
		response.setInterest(2);
		response.setUnderstanding(3);
		response.setEnergyEntry("4");
		response.setEnergyInt(4);
		return responses;
	}

}
