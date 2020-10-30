package com.revature.service;

import java.util.List;

import com.revature.models.User;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;

public class UserService {

	private UserRepository userRepo;
	
	public UserService() {
		
		this.userRepo = new UserRepositoryImpl();
	}
	
	public List<User> getFriends(User u){
		return userRepo.getFreinds(u.getUserID());
	}
}
