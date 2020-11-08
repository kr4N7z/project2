package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.revature.models.Messages;
import com.revature.models.User;
import com.revature.service.MessageService;

@RestController(value = "messagesController")
@RequestMapping(path = "/messages")
@CrossOrigin
@SessionAttributes("currentUser")
public class MessagesController {
	MessageService messageService;
	
	public MessagesController() {
		messageService = new MessageService();
	}
		
	@RequestMapping(value = "/getMyMessages", method = RequestMethod.GET)
	public List<Messages> getMyMessages(@ModelAttribute("currentUser") User userAttribute){
		List<Messages> myMessages;
		myMessages = messageService.getMyMessages(userAttribute.getUserID());
		return myMessages;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public void sendMessage(@RequestParam("message") String message, @RequestParam("received_id") int receivedId,
			@ModelAttribute("currentUser") User userAttribute) {
		messageService.sendMessage(userAttribute.getUserID(), receivedId, message);
	}
}
