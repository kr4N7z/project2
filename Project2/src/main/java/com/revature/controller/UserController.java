package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.service.UserService;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.models.User;
import com.revature.service.UserService;

/*
 * Using a Spring controller we can easily define endpoints and mappings!
 * its all annotation-driven!
 *
 * Note that @Controller is a spring stereotype
 *
 * First we need to define a mapping for this controller
 */
@RestController(value = "userController")
@RequestMapping(path = "/user")
public class UserController {
	UserService userService;

	UserController(){
		userService = new UserService();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void registertUser(@RequestBody User user) {
		userService.register(user);
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void login(@RequestBody User user, HttpServletRequest req) {
		userService.login(user.getEmail(), user.getPassword(), req);
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout(@RequestBody User user, HttpServletRequest req) {
		userService.logout( req);
	}

	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)
	public List<User> getFriends(HttpSession session) {
//		int userId = Integer.valueOf(session.getAttribute("user_id").toString());
//		List<User> friends = userService.getFriends(userId);
		List<User> friends = userService.getFriends(session.getAttribute("user_id"));
		return friends;
	}
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public List<User> getAllUsers(HttpSession session) {
		List<User> users = userService.getAllUsers();
		return users;
	}
}
