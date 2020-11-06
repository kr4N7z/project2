package com.revature.repository;

import java.util.List;

import org.springframework.expression.spel.ast.FloatLiteral;

import com.revature.models.User;

public interface UserRepository {

	void insert(User user);

	User findOneByEmail(String email);

	List<User> getFreinds(int senderID);
	List<User> getAllUsers();
	
	void updateLocation(int userId, float latitude, float longitude, String state);
	void updateUser(int userId, String email,String firstName, String lastName);
	User findOneByUserId(int userId); 
}
