package com.revature.service;

import java.io.File;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	public User login(String email, String password, HttpServletRequest req) {
		System.out.println("rawpassword = rawpassword?: " + enc.matches("rawpassword", enc.encode("rawpassword")));
		System.out.println(enc.encode("secret"));
		User user = userRepo.findOneByEmail(email);
		if (enc.matches(password, user.getPassword())) {
			System.out.println("got a match trying to create a session");
			try {
				//String remoteAddress = req.getRemoteAddr();
				String remoteAddress = req.getLocalAddr();
				InetAddress ipAddress = InetAddress.getByName(remoteAddress);
				GeoIpService geoIpService = new GeoIpService();
				GeoIp location = geoIpService.getLocation(ipAddress);
				user.setLastState(location.getState());
				user.setLatitude(Float.valueOf(location.getLatitude()));
				user.setLongitude(Float.valueOf(location.getLongitude()));

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HttpSession session = req.getSession();
			session.setAttribute("user_id", user.getUserID());
			session.setAttribute("first_name", user.getFirstName());
			session.setAttribute("last_name", user.getLastName());
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

}
