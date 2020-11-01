package com.revature.controller;

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
@RequestMapping(path = "user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)
	public List<User> getFriends(HttpSession session) {
		UserService us = new UserService();
		List<User> friends = us.getFriends((int) session.getAttribute("user"));
		
		return friends;
	}
}
