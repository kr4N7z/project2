package com.revature.repository;

import java.util.List;

import com.revature.models.User;

public interface UserRepository {

	void insert(User user);
	List<User> getFreinds(int senderID);
}
