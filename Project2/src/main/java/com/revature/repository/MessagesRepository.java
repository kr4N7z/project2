package com.revature.repository;

import com.revature.models.Messages;

public interface MessagesRepository {
	Messages getMyMessages(int myId);
	void updateSeen(int receiverId, boolean seen);
}