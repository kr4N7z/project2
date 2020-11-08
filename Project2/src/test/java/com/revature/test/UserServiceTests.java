package com.revature.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.models.User;
import com.revature.repository.FriendshipRepositoryImpl;
import com.revature.repository.UserRepositoryImpl;
import com.revature.service.UserService;



public class UserServiceTests {
		
		@Mock static UserRepositoryImpl userRepo;
		
		@Mock static FriendshipRepositoryImpl fRepo;
		
		@InjectMocks static UserService service;

		static List<User> Users = new ArrayList<>();
		
		@BeforeClass
		public static void setUp(){
			userRepo = new UserRepositoryImpl();
			fRepo = new FriendshipRepositoryImpl();
			service = new UserService();
			User user1 = new User("standard", "email@email.com", "secret", "first", "last", 100f,
					100f, "Texas", new Date(0), new Date(0));
			User user2 = new User("user", "email", "password", "first", "last",
						0f, 0f, "Indiana", new Date(0), new Date(0));
			User user3 = new User("newuser", "asdfda", "password", "Jen", "Low",
					0f, 0f, "Arkansas", new Date(0), new Date(0)); 
			Users.add(user1);
			Users.add(user2);
			Users.add(user3);
		}

		@Before
		public void perMethodSetUp() {
			MockitoAnnotations.openMocks(this);
			
		}


		
		//employee tests

		
		@Test
		public void testLogin(){
//			Mockito.doNothing().when(empRepo).updateEmployeProfileInfo(Mockito.any());
//			service.setInfo(new Employee());
//			Mockito.verify(empRepo).updateEmployeProfileInfo(Mockito.any());
			
			HttpServletRequest req = null;
			User user = null;
			//user.setPassword("password");
			Mockito.doReturn(user).when(userRepo).findOneByEmail(Mockito.anyString());
			service.login("login", "password",req);
			Mockito.verify(userRepo).findOneByEmail(Mockito.any());
		}
		
		@Test
		public void testRegister(){
			HttpServletRequest req = null;
			User user = new User("user", "email", "password", "first", "last",
						0f, 0f, "", new Date(0), new Date(0));
			//user.setPassword("password");
			Mockito.doNothing().when(userRepo).insert(Mockito.any());
			service.register(user);
			Mockito.verify(userRepo).insert(Mockito.any());
		}
		
		//reimbursement tests

		@Test
		public void testGetFriends() {
			Mockito.doReturn(Users).when(userRepo).getFriends(Mockito.anyInt());
			
			Assert.assertEquals(Users, service.getFriends(1));
		}
		@Test
		public void testGetFriendsBadId() {
			Mockito.doReturn(Users).when(userRepo).getFriends(Mockito.anyInt());
			
			Mockito.verifyNoInteractions(userRepo);
		}
		@Test
		public void testUpdateUser() {
			Mockito.doNothing().when(userRepo).updateUser(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any());
			
			service.updateUser(1, "email@email.com", "Jake", "Last");
			Mockito.verify(userRepo).updateUser(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any());
		}
		@Test
		public void testUpdateUserBadEmail() {
			Mockito.doNothing().when(userRepo).updateUser(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any());
			
			service.updateUser(1, "email@email@com", "Jake", "Last");
			service.updateUser(1, "email.email.com", "Jake", "Last");
			Mockito.verifyNoInteractions(userRepo);
		}
		@Test
		public void testUpdateUserNoFirstName() {
			Mockito.doNothing().when(userRepo).updateUser(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any());
			
			service.updateUser(1, "email@email.com", "", "Last");
			Mockito.verifyNoInteractions(userRepo);
		}
		@Test
		public void testUpdateUserNoLastName() {
			Mockito.doNothing().when(userRepo).updateUser(Mockito.anyInt(), Mockito.any(), Mockito.any(), Mockito.any());
			
			service.updateUser(1, "email@email.com", "First", "");
			Mockito.verifyNoInteractions(userRepo);
		}

		@After
		public void perMethodTearDown() {
			
		}
		

		@AfterClass
		public static void tearDown() {
		}
	

}
