package com.revature.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
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
@CrossOrigin(origins="http://localhost:4200")
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
	public User login(@RequestBody String body, HttpServletRequest req) {
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);
		
		//return "user email "+ user.getEmail() + " user password " + user.getPassword();
		return userService.login(user.getEmail(), user.getPassword(), req);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout( HttpServletRequest req) {
		userService.logout( req);
	}

	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)
	public List<User> getFriends(HttpSession session) {
		UserService us = new UserService();
		List<User> friends = us.getFriends((User) session.getAttribute("user"));

		return friends;
	}
	
	@RequestMapping(value = "/testget", method = RequestMethod.GET)
	public String testget(@RequestParam("test") String test) {


		return test;
	}
	
	//requestparam: localhost:8080/user/whatever?user=10
	@RequestMapping(value = "/testPost", method = RequestMethod.POST)
	public String testPost(HttpSession session) {
		System.out.println(session.getAttributeNames());
		Iterator<String> iterator = session.getAttributeNames().asIterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		return "the user id is: " + (int) session.getAttribute("user_id");
	}
}
