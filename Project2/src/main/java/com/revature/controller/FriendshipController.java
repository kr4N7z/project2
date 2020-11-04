package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.revature.models.Friendship;
import com.revature.models.User;
import com.revature.repository.FriendshipRepositoryImpl;

@RestController(value = "friendshipController")
@RequestMapping(path = "/friendship")
@CrossOrigin
@SessionAttributes("currentUser")

public class FriendshipController {
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertFriendship(@RequestParam("receiver_id") int receiverId, @RequestParam("approved") boolean approved, @ModelAttribute("currentUser") User userAttribute) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship(userAttribute.getUserID(), receiverId, approved);
		frimpl.insertFriendship(fr);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestParam("receiver_id") int receiverId, @RequestParam("approved") boolean approved, @ModelAttribute("currentUser") User userAttribute) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship(userAttribute.getUserID(), receiverId, approved);
		frimpl.update(fr);
	}
	
	@RequestMapping(value = "/viewAllFriendships", method = RequestMethod.GET)
	public List<Friendship> viewAllFriendships() {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<Friendship> friends = frimpl.viewAllFriendships();
		
		return friends;
	}
	
	@RequestMapping(value = "/viewMyFriendships", method = RequestMethod.GET)
	public List<Friendship> viewMyFriendships(@ModelAttribute("currentUser") User userAttribute) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<Friendship> friends = frimpl.viewMyFriendships(userAttribute.getUserID());
		
		return friends; 
	}
	
	@RequestMapping(value = "/viewFriendship", method = RequestMethod.GET)
	public Friendship getFriendship(@RequestParam("receiver_id") int receiverId, @ModelAttribute("currentUser") User userAttribute) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship(userAttribute.getUserID(), receiverId);
		
		return fr;
	}
	
	@RequestMapping(value = "/removeFriendship", method = RequestMethod.POST)
	public void removeFriendship(@RequestParam("receiver_id") int receiverId, @ModelAttribute("currentUser") User userAttribute) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship(userAttribute.getUserID(), receiverId);
		frimpl.removeFriendship(fr);
	}
}
