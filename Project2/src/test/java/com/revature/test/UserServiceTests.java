	package com.revature.test;

	import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

		
		@BeforeClass
		public static void setUp(){
			userRepo = new UserRepositoryImpl();
			fRepo = new FriendshipRepositoryImpl();
			service = new UserService();
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

		

		@After
		public void perMethodTearDown() {
			
		}
		

		@AfterClass
		public static void tearDown() {
		}
	

}
