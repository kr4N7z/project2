package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Messages;
import com.revature.service.MessageService;

@RestController(value = "messagesController")
@RequestMapping(path = "/messages")
public class MessagesController {
	MessageService messageService;
	
	public MessagesController() {
		messageService = new MessageService();
	}
		
	@RequestMapping(value = "/getMyMessages", method = RequestMethod.GET)
	public List<Messages> getMyMessages(HttpServletRequest req){
		List<Messages> myMessages;
		HttpSession session= req.getSession(false);
		int userId = Integer.valueOf(session.getAttribute("user_id").toString());
		myMessages = messageService.getMyMessages(userId);
		return myMessages;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void sendMessage(@RequestParam("message") String message, @RequestParam("received_id") int receivedId,
			HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		int senderId = Integer.valueOf(session.getAttribute("user_id").toString());
		messageService.sendMessage(senderId, receivedId, message);
	}
}
