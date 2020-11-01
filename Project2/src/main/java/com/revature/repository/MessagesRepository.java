package com.revature.repository;

import java.util.List;


import com.revature.models.Messages;

public interface MessagesRepository {
	List<Messages> getMyMessages(int myId);
	void sendMessage(Messages newMessage);
	void updateSeen(int receiverId, boolean seen);
}