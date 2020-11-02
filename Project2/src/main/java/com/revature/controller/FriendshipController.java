package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Friendship;
import com.revature.models.User;
import com.revature.repository.FriendshipRepositoryImpl;

@RestController(value = "friendshipController")
@RequestMapping(path = "/friendship")
public class FriendshipController {
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertFriendship(@RequestParam("receiver_id") int receiverId, @RequestParam("approved") boolean approved, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship((int) session.getAttribute("user_id"), receiverId, approved);
		frimpl.insertFriendship(fr);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestParam("receiver_id") int receiverId, @RequestParam("approved") boolean approved, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship((int) session.getAttribute("user_id"), receiverId, approved);
		frimpl.update(fr);
	}
	
	@RequestMapping(value = "/viewAllFriendships", method = RequestMethod.GET)
	public List<Friendship> viewAllFriendships() {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<Friendship> friends = frimpl.viewAllFriendships();
		
		return friends;
	}
	
	@RequestMapping(value = "/viewMyFriendships", method = RequestMethod.GET)
	public List<Friendship> viewMyFriendships(HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<Friendship> friends = frimpl.viewMyFriendships((int) session.getAttribute("user_id"));
		
		return friends; 
	}
	
	@RequestMapping(value = "/viewFriendship", method = RequestMethod.GET)
	public Friendship getFriendship(@RequestParam("receiver_id") int receiverId, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship((int) session.getAttribute("user_id"), receiverId);
		
		return fr;
	}
	
	//currently only removes the listing for one user
	@RequestMapping(value = "/removeFriendship", method = RequestMethod.POST)
	public void removeFriendship(@RequestParam("receiver_id") int receiverId, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship((int) session.getAttribute("user_id"), receiverId);
		frimpl.removeFriendship(fr);
	}
	
	@RequestMapping(value = "/getUnapproved", method = RequestMethod.GET)
	public List<User> getUnapproved(HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		return frimpl.getMyUnapproved((int) session.getAttribute("user_id"));
	}
}
