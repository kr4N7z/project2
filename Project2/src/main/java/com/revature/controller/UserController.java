package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
@Controller(value = "userController")
@RequestMapping(path = "/user")
@JsonIgnoreProperties
@CrossOrigin(origins = "http://localhost:4200")
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
	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)
	public List<User> getFriends(HttpSession session) {
		UserService us = new UserService();

		List<User> friends = us.getFriends((User) session.getAttribute("user"))

		return friends;
	}
}
