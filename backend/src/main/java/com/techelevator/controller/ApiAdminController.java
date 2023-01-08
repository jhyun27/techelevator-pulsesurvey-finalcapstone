package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.user.User;
import com.techelevator.user.UserDao;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class ApiAdminController {
	
	@Autowired
    private AuthProvider auth;
	
	@Autowired
	private UserDao userDao;
//	private AdminSurveyDao dao;	
	
	@GetMapping
	public List<User> listUsers() {
		return userDao.getAllUsers();
	}
	
	@GetMapping("/{username}")
	public User get(@PathVariable String username) {
		return userDao.getUserByUsername(username);
	}
	
//	 same as -> "/register" but need to add ONE TIME PASSWORD
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public String createUser(@Valid @RequestBody User user, BindingResult result) throws UserCreationException {
//    	System.out.println("trying to register new user");
//        if (result.hasErrors()) {
//            String errorMessages = "";
//            for (ObjectError error : result.getAllErrors()) {
//                errorMessages += error.getDefaultMessage() + "\n";
//            }
//            throw new UserCreationException(errorMessages);
//        }
//        auth.register(user.getUsername(), user.getPassword(), user.getRole(), true);
//        return "{\"success\":true}";
//    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody User user) {
		userDao.saveUser(user.getUsername(), user.getPassword(), user.getRole(), true);
	}
	
	@PutMapping("/updateRole")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateRole(@RequestBody User user) {
		userDao.updateUserRole(user.getUsername(), user.getRole());
	}
	
	@PutMapping("/changePassword")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void changePassword(@RequestBody User user) {
		userDao.changePassword(user, user.getPassword());
	}
	
	
	@PutMapping("/resetPassword")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void resetPassword(@RequestBody User user) {
		//userDao.changePassword(user, user.getPassword());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteUser(@PathVariable int id) {
		userDao.deleteUser(id);
	}
	
//	public User getValidUserWithPassword(String userName, String password)
	
	
	
	
//	@PostMapping("/add/cohort")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addCohort(@RequestBody Cohort cohort) {
//		dao.addNewCohort(cohort.getCohortNumber());
//	}
//	
//	@PostMapping("/add/location")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addLocation() {
//		
//	}
//	
//	@PostMapping("/add/instructor")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addInstructor() {
//		
//	}
//	
//	@PostMapping("/add/section")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addSection() {
//		
//	}
//	
//	@PostMapping("/add/topic")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void addTopic() {
//		
//	}

}
