package com.revature.test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.models.Friendship;
import com.revature.models.User;
import com.revature.repository.FriendshipRepositoryImpl;
import com.revature.repository.UserRepositoryImpl;

class FriendshipTests {

	static FriendshipRepositoryImpl frimpl;
	static UserRepositoryImpl urimpl;
	Friendship friend;
	User u1;
	User u2;
	
	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		frimpl = new FriendshipRepositoryImpl();
		urimpl = new UserRepositoryImpl();
		friend = Mockito.mock(Friendship.class);
		u1 = Mockito.mock(User.class);
		u2 = Mockito.mock(User.class);
		
		when(friend.getSenderId()).thenReturn(6);
		when(friend.getReceiverId()).thenReturn(7);
		when(friend.getStatus()).thenReturn(true);
		
		when(u1.getEmail()).thenReturn("jonah@email.com");
		when(u1.getFirstName()).thenReturn("Jonah");
		when(u1.getLastName()).thenReturn("Doe");
		when(u1.getUserId()).thenReturn(6);
		
		when(u1.getEmail()).thenReturn("scotty@email.com");
		when(u1.getFirstName()).thenReturn("Scotty");
		when(u1.getLastName()).thenReturn("Doe");
		when(u1.getUserId()).thenReturn(7);
	}
	
	@Test 
	public void testGetFriendshipSender() {
		Friendship fr = frimpl.getFriendship(u1.getUserId(), u2.getUserId());
		assertEquals(fr.getSenderId(), friend.getSenderId());
	}
	
	@Test 
	public void testGetFriendshipReceiver() {
		Friendship fr = frimpl.getFriendship(u1.getUserId(), u2.getUserId());
		assertEquals(fr.getReceiverId(), friend.getReceiverId());
	}
	
	@Test
	public void testGetFriendshipStatus() {
		Friendship fr = frimpl.getFriendship(u1.getUserId(), u2.getUserId());
		assertEquals(fr.getStatus(), friend.getStatus());
	}

	@Test
	public void testGetUserByEmailUId() {
		User ur = urimpl.findOneByEmail(u1.getEmail());
		assertEquals(u1.getUserId(), ur.getUserId());
	}
	
	@Test
	public void testGetUserByEmailFname() {
		User ur = urimpl.findOneByEmail(u1.getEmail());
		assertEquals(u1.getFirstName(), ur.getFirstName());
	}
	
	@Test
	public void testGetUserByEmailLname() {
		User ur = urimpl.findOneByEmail(u1.getEmail());
		assertEquals(u1.getLastName(), ur.getLastName());
	}
}
