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
public class FriendshipController {
	
	//
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertFriendship(@RequestParam("receiverId") int receiverId, @RequestParam("approved") boolean approved, @RequestParam("userId") int userId) {
		try {
			FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
			Friendship fr = new Friendship(userId, receiverId, approved);
			frimpl.insertFriendship(fr);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestParam("receiverId") int receiverId, @RequestParam("approved") boolean approved, @RequestParam("userId") int userId) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = new Friendship(receiverId, userId, approved);
		frimpl.update(fr);
	}
	
	@RequestMapping(value = "/viewAllFriendships", method = RequestMethod.GET)
	public List<Friendship> viewAllFriendships() {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<Friendship> friends = frimpl.viewAllFriendships();
		
		return friends;
	}
	
	@RequestMapping(value = "/viewMyFriendships", method = RequestMethod.GET)
	public List<Friendship> viewMyFriendships( @RequestParam("userId") int userId) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<Friendship> friends = frimpl.viewMyFriendships(userId);
		
		System.out.println(friends);
		System.out.println("hi");
		return friends; 
	}
	
	@RequestMapping(value = "/viewFriendship", method = RequestMethod.GET)
	public Friendship getFriendship(@RequestParam("receiverId") int receiverId,  @RequestParam("userId") int userId) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		Friendship fr = frimpl.getFriendship(userId, receiverId);
		
		return fr;
	}
	
	@RequestMapping(value = "/removeFriendship", method = RequestMethod.POST)
	public void removeFriendship(@RequestParam("receiverId") int receiverId,  @RequestParam("userId") int userId) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		//System.out.println(userId + " " + receiverId);
		Friendship fr = frimpl.getFriendship(userId, receiverId);
		frimpl.removeFriendship(fr);
		Friendship fr2 = frimpl.getFriendship(receiverId, userId);
		frimpl.removeFriendship(fr2);
	}
	
	@RequestMapping(value = "/getUnapproved", method = RequestMethod.GET) 
	public List<User> getUnapproved(@RequestParam("userId") int userId) {
		FriendshipRepositoryImpl frimpl = new FriendshipRepositoryImpl();
		List<User> tmp = frimpl.getMyUnapproved(userId);
		return tmp;
	}
		
	
}
