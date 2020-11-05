package com.revature.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.utility.BasicResponseWrapper;

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
@CrossOrigin(origins="*")
public class UserController {
	UserService userService;

	UserController(){
		userService = new UserService();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public BasicResponseWrapper registertUser(@RequestBody User user) {
		System.out.println(user.toString());
		return userService.register(user);
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User login(@RequestBody String body, HttpServletRequest req) {
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);

		//return "user email "+ user.getEmail() + " user password " + user.getPassword();
		return userService.login(user.getEmail(), user.getPassword(), req);
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public BasicResponseWrapper logout( HttpServletRequest req) {
		return userService.logout( req);
	}

	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)
	public List<User> getFriends(HttpServletRequest req) {
//		int userId = Integer.valueOf(session.getAttribute("user_id").toString());
//		List<User> friends = userService.getFriends(userId);
		
		HttpSession session = req.getSession(false);
		System.out.println("we entered the myfriends controller");
		if(session==null) {
			System.out.println("session is null!");
		}else {
			System.out.println("trying to print the attribute names: ");
			Iterator<String> iterator = session.getAttributeNames().asIterator();
			while(iterator.hasNext()) {
				System.out.println("iterator item: "+ iterator.next());
			}
		}
		List<User> friends = userService.getFriends(Integer.valueOf(session.getAttribute("user_id").toString()));
		return friends;
	}
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@RequestMapping(value = "/testSession", method = RequestMethod.POST)
	public ArrayList<String> getAllUsers(HttpSession session) {
		ArrayList<String> res = new ArrayList<>();
		Iterator<String> iterator = session.getAttributeNames().asIterator();
		System.out.println("printing out the session attribute names");
		while(iterator.hasNext()) {
			String current = iterator.next();
			res.add(current);
			System.out.println("iterator item: "+ current);
			
		}
		return res;
	}
}
