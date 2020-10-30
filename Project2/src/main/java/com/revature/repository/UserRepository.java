package com.revature.repository;

import com.revature.models.User;

public interface UserRepository {

	void insert(User user);

	User findOneByEmail(String email);
}
