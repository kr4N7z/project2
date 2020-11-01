package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.models.Friendship;
import com.revature.repository.FriendshipRepositoryImpl;

@Controller(value = "friendshipController")
@RequestMapping(path = "/friendship")
public class FriendshipController {
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertFriendship(@RequestParam("receiver_id") int receiverId, @RequestParam("approved") boolean approved, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship((int) session.getAttribute("sender_id"), receiverId, approved);
		frimpl.insertFriendship(fr);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestParam("receiver_id") int receiverId, @RequestParam("approved") boolean approved, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship((int) session.getAttribute("sender_id"), receiverId, approved);
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
		List<Friendship> friends = frimpl.viewMyFriendships((int) session.getAttribute("sender_id"));
		
		return friends; 
	}
	
	@RequestMapping(value = "/viewFriendship", method = RequestMethod.GET)
	public Friendship getFriendship(@RequestParam("receiver_id") int receiverId, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship((int) session.getAttribute("sender_id"), receiverId);
		
		return fr;
	}
	
	@RequestMapping(value = "/removeFriendship", method = RequestMethod.POST)
	public void removeFriendship(@RequestParam("receiver_id") int receiverId, HttpSession session) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship((int) session.getAttribute("sender_id"), receiverId);
		frimpl.removeFriendship(fr);
	}
}
