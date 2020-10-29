package com.revature.repository;

import java.util.List;
import com.revature.models.Friendship;

public interface FriendshipRepository {
	
	abstract void insertFriendship(Friendship fr);
	abstract void update(Friendship fr);
	abstract List<Friendship> viewAllFriendships();
	abstract List<Friendship> viewMyFriendships(int senderId);
	abstract Friendship getFriendship(int senderId, int receiverId);
	abstract void removeFriendship(Friendship fr);
	
}
