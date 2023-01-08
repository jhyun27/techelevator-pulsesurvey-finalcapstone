package com.techelevator.user;

import java.sql.SQLException;
import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.authentication.PasswordHasher;

public class JdbcUserDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private UserDao dao;
	private JdbcTemplate template;
	private PasswordHasher passwordHasher;
	
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
		passwordHasher = new PasswordHasher();
		dao = new JdbcUserDao(dataSource, passwordHasher);
		template = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void save_user() {
		dao.saveUser("testUsername", "testPassword", "admin", false);
		User returnedUser = dao.getUserByUsername("testUsername");
		Assert.assertEquals("admin", returnedUser.getRole());
		Assert.assertEquals("testUsername", returnedUser.getUsername());
	}
	
//	@Test
//	public void delete_user() {
//		dao.saveUser("testUsername", "testPassword", "admin", false);
//		User returnedUser = dao.getUserByUsername("testUsername");
//		Assert.assertEquals("admin", returnedUser.getRole());
//		Assert.assertEquals("testUsername", returnedUser.getUsername());
//		dao.deleteUser("testUsername");
//		Assert.assertNull(dao.getUserByUsername("testUsername"));
//	}

}
