package com.revature.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.service.UserService;
import com.revature.utility.BasicResponseWrapper;
import com.revature.utility.Encryption;


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
@CrossOrigin
@SessionAttributes("currentUser")
//@CrossOrigin(origins = "http://wheretheboysat.s3-website-us-east-1.amazonaws.com")
public class UserController {
	UserService userService;
	UserRepository userRepo;
	PasswordEncoder enc;

	UserController(){
		userService = new UserService();
		userRepo = new UserRepositoryImpl();
		enc = Encryption.getEncoder();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public BasicResponseWrapper registertUser(@RequestBody User user) {
		System.out.println(user.toString());
		return userService.register(user);
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public User login(@RequestBody String body, HttpServletRequest req, HttpServletResponse response,@ModelAttribute("currentUser") User userAttribute) {
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);

		User userDb = userRepo.findOneByEmail(user.getEmail());
		if (enc.matches(user.getPassword(), userDb.getPassword())) {
			System.out.println("got a match trying to create a session");
			//try {
				//String remoteAddress = req.getRemoteAddr();
				//String remoteAddress = req.getLocalAddr();
				//InetAddress ipAddress = InetAddress.getByName(remoteAddress);
				//GeoIpService geoIpService = new GeoIpService();
				//GeoIp location = geoIpService.getLocation(ipAddress);
				//user.setLastState(location.getState());
				//user.setLatitude(Float.valueOf(location.getLatitude()));
				//user.setLongitude(Float.valueOf(location.getLongitude()));

			//} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
//

			req.getSession().setAttribute("user_id", userDb.getUserID());
			req.getSession().setAttribute("first_name", userDb.getFirstName());
			req.getSession().setAttribute("last_name", userDb.getLastName());
			userAttribute.setFirstName(userDb.getFirstName());
			userAttribute.setLastName(userDb.getLastName());
			userAttribute.setUserID(userDb.getUserID());

			//String valueString =gson.toJson(user);
			//Cookie createSession = new Cookie(req.getSession().getId(), valueString);
			//createSession.setPath(req.getContextPath());
			//response.addCookie(createSession);
		}
			return userDb;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout( HttpServletRequest req) {
		 userService.logout( req);
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

	@ModelAttribute("currentUser")
	public User userAttributes() {
		return new User();
	}

	@RequestMapping(value = "/userByEmail", method = RequestMethod.GET)
	public User getUserByEmail(@RequestParam("email") String email) {
		return userService.getUserByEmail(email);
	}
}
