
package com.revature.service;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.utility.BasicResponseWrapper;
import com.revature.utility.Encryption;
import com.revature.utility.UserResponseWrapper;


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
	public UserResponseWrapper login(String email, String password, HttpServletRequest req) {
		//System.out.println("rawpassword = rawpassword?: " + enc.matches("rawpassword", enc.encode("rawpassword")));
		//System.out.println(enc.encode("secret"));
		User user = userRepo.findOneByEmail(email);
		UserResponseWrapper urw = new UserResponseWrapper(user, "");
		if (user!=null && enc.matches(password, user.getPassword())) {
			System.out.println("got a match trying to create a session");
			System.out.println("nother line");
			System.out.println("we gunna send backa  wrapper!");
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
			System.out.println("created session: " +req.getSession().getId());
			return urw;
		}else {
			System.out.println("there was no match!");
		}

		return urw;
	}

	public BasicResponseWrapper logout(HttpServletRequest req) {
		System.out.println("invalidating session: "+ req.getRequestedSessionId());
		req.getSession().invalidate();
		BasicResponseWrapper bsw = new BasicResponseWrapper();
		bsw.setSuccess(true);
		return bsw;
	}

	public BasicResponseWrapper register(User user) {
		User newUser = new User("user", user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
				0f, 0f, "", new Date(0), new Date(0));
		userRepo.insert(newUser);
		BasicResponseWrapper bsw = new BasicResponseWrapper();
		bsw.setSuccess(true);
		return bsw;
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
	
	public void updateUser(int userId, String email, String firstName, String lastName) {
		userRepo.updateUser(userId, email, firstName, lastName);
	}
}
