package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Messages;
import com.revature.service.MessageService;

@RestController(value = "messagesController")
@RequestMapping(path = "/messages")
@CrossOrigin(origins = "http://wheretheboysat.s3-website-us-east-1.amazonaws.com")
public class MessagesController {
	MessageService messageService;
	
	public MessagesController() {
		messageService = new MessageService();
	}
		
	@RequestMapping(value = "/getMyMessages", method = RequestMethod.GET)
	public List<Messages> getMyMessages(HttpSession session){
		List<Messages> myMessages;
		int userId = Integer.valueOf(session.getAttribute("user_id").toString());
		myMessages = messageService.getMyMessages(userId);
		return myMessages;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void sendMessage(@RequestParam("message") String message, @RequestParam("received_id") int receivedId,
			HttpSession session) {
		int senderId = Integer.valueOf(session.getAttribute("user_id").toString());
		messageService.sendMessage(senderId, receivedId, message);
	}
}
