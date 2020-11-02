package com.revature.service;

import java.sql.Timestamp;
import java.util.List;

import com.revature.models.Messages;
import com.revature.repository.MessagesRepository;
import com.revature.repository.MessagesRepositoryImpl;

public class MessageService {
	MessagesRepository messagesRepository;
	public MessageService() {
		messagesRepository = new MessagesRepositoryImpl();
	}
	
	public List<Messages> getMyMessages(int userId){
		List<Messages> myMessages;
		myMessages = messagesRepository.getMyMessages(userId);
		return myMessages;
	}
	
	public void sendMessage(int senderId, int receivedId, String messageTxt) {
		Messages newMessage = new Messages();
		newMessage.setSenderId(senderId);
		newMessage.setReceivedId(receivedId);
		newMessage.setMessage(messageTxt);
		newMessage.setSentTime(new Timestamp(System.currentTimeMillis()));
		messagesRepository.sendMessage(newMessage);
	}
}
