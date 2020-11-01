package com.revature.service;


import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.utility.Encryption;

public class UserService {
	UserRepository userRepo;
	PasswordEncoder enc;
	public UserService(){
		userRepo = new UserRepositoryImpl();
		enc = Encryption.getEncoder();
	}
	public void login(String email, String password, HttpServletRequest req) {
		System.out.println("rawpassword = rawpassword?: "+ enc.matches("rawpassword", enc.encode("rawpassword")));
		User user = userRepo.findOneByEmail(email);
		if(enc.matches(password, user.getPassword())) {
			req.getSession();
			req.setAttribute("userid", user.getUserID());
		}

	}
	public void logout(HttpServletRequest req) {
		req.getSession().invalidate();
	}
	public void register(User user) {
		User newUser = new User("user",user.getEmail(),user.getPassword(),user.getFirstName(),user.getLastName(),0f,0f,"",new Date(0),new Date(0));
		userRepo.insert(newUser);
	}
	public List<User> getFriends(int userId){
		return userRepo.getFreinds(userId);

	}
	public List<User> getAllUsers(){
		return userRepo.getAllUsers();
	}

}
