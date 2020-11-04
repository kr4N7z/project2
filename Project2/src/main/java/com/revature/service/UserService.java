package com.revature.service;

import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.jr.ob.impl.JSONReader;
import com.google.gson.Gson;
import com.revature.models.GeoIp;
import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.utility.Encryption;

@Service
public class UserService {
	UserRepository userRepo;
	PasswordEncoder enc;

	public UserService() {
		userRepo = new UserRepositoryImpl();
		enc = Encryption.getEncoder();
	}

	// Think this needs HTTPSession session = req.getSession();
	// then session.setAttribute() etc.. because right now this is setting the
	// request attribute userId I think.
	public User login(String email, String password, HttpServletRequest req, HttpServletResponse response) {
		System.out.println("rawpassword = rawpassword?: " + enc.matches("rawpassword", enc.encode("rawpassword")));
		System.out.println(enc.encode("secret"));
		User user = userRepo.findOneByEmail(email);
		if (enc.matches(password, user.getPassword())) {
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
			Gson gson = new Gson();
			
			req.getSession().setAttribute("user_id", user.getUserID());
			req.getSession().setAttribute("first_name", user.getFirstName());
			req.getSession().setAttribute("last_name", user.getLastName());
			//String valueString =gson.toJson(user);
			//Cookie createSession = new Cookie(req.getSession().getId(), valueString);
			//createSession.setPath(req.getContextPath());
			//response.addCookie(createSession);
			return user;
		}

		return user;
	}

	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}

	public void register(User user) {
		User newUser = new User("user", user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
				0f, 0f, "", new Date(0), new Date(0));
		userRepo.insert(newUser);
	}

	public List<User> getFriends(int userId) {
		return userRepo.getFreinds(userId);

	}

	public List<User> getAllUsers() {
		return userRepo.getAllUsers();
	}

	public User getUserByEmail(String email) {
        return userRepo.findOneByEmail(email);
    }
}
