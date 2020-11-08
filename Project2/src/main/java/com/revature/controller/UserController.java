package com.revature.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;
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
import com.revature.models.GeoIp;
import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.service.GeoIpService;
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
	public User login(@RequestBody String body, HttpSession session,HttpServletRequest req, HttpServletResponse response,@ModelAttribute("currentUser") User userAttribute) {
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);

		User userDb = userRepo.findOneByEmail(user.getEmail());
		if (enc.matches(user.getPassword(), userDb.getPassword())) {
			System.out.println("got a match trying to create a session");
			try {
				String remoteAddress = req.getRemoteAddr();
				InetAddress ipAddress = InetAddress.getByName(remoteAddress);
				GeoIpService geoIpService = new GeoIpService();
				GeoIp location = geoIpService.getLocation(ipAddress);
				userDb.setLastState(location.getState().replaceAll("\"", ""));
				userDb.setLatitude(Float.valueOf(location.getLatitude()));
				userDb.setLongitude(Float.valueOf(location.getLongitude()));
				Date current = new Date(Calendar.getInstance().getTime().getTime());
				userRepo.updateLocation(userDb.getUserId(), userDb.getLastLatitude(), userDb.getLastLongitude(), userDb.getLastState(), current);
			} catch (UnknownHostException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}


			session.setAttribute("user_id", userDb.getUserId());
			session.setAttribute("first_name", userDb.getFirstName());
			session.setAttribute("last_name", userDb.getLastName());
			userAttribute.setFirstName(userDb.getFirstName());
			userAttribute.setLastName(userDb.getLastName());
			userAttribute.setUserId(userDb.getUserId());
			session.setAttribute("user_id", userDb.getUserId());
			session.setAttribute("first_name", userDb.getFirstName());
			session.setAttribute("last_name", userDb.getLastName());

		}
			return userDb;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout( HttpServletRequest req) {
		 userService.logout( req);
	}

	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)

	public List<User> getFriends(@RequestParam("userId") int userId,@ModelAttribute("currentUser") User userAttribute, HttpSession session,HttpServletRequest req, HttpServletResponse response) {
//		int userId = Integer.valueOf(session.getAttribute("user_id").toString());
//		List<User> friends = userService.getFriends(userId);

		//if(session==null) {
			//System.out.println("session is null!");
		//}else {
			//System.out.println("trying to print the attribute names: ");
			//Iterator<String> iterator = session.getAttributeNames().asIterator();
			//while(iterator.hasNext()) {
			//	System.out.println("iterator item: "+ iterator.next());
			//}
		//}
		//System.out.println("getfriends session : " +session.getId());
		//System.out.println(session.getAttribute("user_id"));
		//System.out.println("userid: "+ Integer.valueOf(userAttribute.getUserID()));
		List<User> friends = userService.getFriends(userId);
		
//		Gson gson = new Gson();
//		response.setContentType("application/json");
//		String rrsJson = gson.toJson(friends);
//		return rrsJson;
		System.out.println(friends);
		return friends;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest req) {
		int userId = Integer.valueOf(req.getParameter("userId"));
		String email = req.getParameter("email");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		userService.updateUser(userId, email, firstName, lastName);
	}
	
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
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
