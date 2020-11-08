package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.validation.Validator;

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
import com.revature.utility.UserResponseWrapper;
import com.revature.utility.UserWrapper;
import com.revature.utility.Validation;


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
	public BasicResponseWrapper registertUser( @RequestBody User user) {
		
		BasicResponseWrapper brw = new BasicResponseWrapper();
		Validation emailValidator = Validation.getInstance();
//		
//		if(!emailValidator.validEmail(user.getEmail())) {
//			brw.setSuccess(false);
//			brw.setErrorMessage("Invalid email format");
//		}
////		else if(!user.getFirstName().isBlank()) {
////			brw.setSuccess(false);
////			brw.setErrorMessage("First name must not be blank");
////		}
////		else if (!user.getLastName().isBlank()) {
////			brw.setSuccess(false);
////			brw.setErrorMessage("Last name must not be blank");
////		}
//		else if(!(user.getPassword().length()>5)) {
//			brw.setSuccess(false);
//			brw.setErrorMessage("Password is too short");
//		}
//		else if( !user.getPassword().equals("")) {
//			brw.setSuccess(false);
//			brw.setErrorMessage("Passwords do not match");
//		}
//		else {
			brw =userService.register(user);
//		}

		return brw;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public UserResponseWrapper login(@RequestBody String body, HttpSession session, HttpServletResponse response,@ModelAttribute("currentUser") User userAttribute) {
		Gson gson = new Gson();
		User user = gson.fromJson(body, User.class);
		Validation emailValidator = Validation.getInstance();
		UserResponseWrapper urw = null;
		if(!emailValidator.validEmail(user.getEmail())) {
			urw = new UserResponseWrapper();
			urw.setErrorMessage("invalid email format");
			return urw;
		}

		User userDb = userRepo.findOneByEmail(user.getEmail());

		if (userDb!=null &&enc.matches(user.getPassword(), userDb.getPassword())) {
			System.out.println("got a match trying to create a session");
			System.out.println("hitting the controller");
			urw = new UserResponseWrapper(userDb, "");
			System.out.println(userDb);
			System.out.println(urw.toString());
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

			return urw;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logout( HttpServletRequest req) {
		 userService.logout( req);
	}

	@RequestMapping(value = "/myfriends", method = RequestMethod.GET)

	public List<User> getFriends(@ModelAttribute("currentUser") User userAttribute, HttpSession session,HttpServletRequest req, HttpServletResponse response) {
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
		List<User> friends = userService.getFriends(Integer.valueOf(req.getParameter("userId")));
		
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
