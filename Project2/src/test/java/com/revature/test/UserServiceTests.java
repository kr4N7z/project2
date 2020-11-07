	package com.revature.test;

	import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.models.User;
import com.revature.repository.FriendshipRepository;
import com.revature.repository.FriendshipRepositoryImpl;
import com.revature.repository.UserRepository;
import com.revature.repository.UserRepositoryImpl;
import com.revature.service.UserService;


public class UserServiceTests {



		
		@Mock static UserRepository userRepo;
		
		@Mock
		static FriendshipRepository fRepo;
		
		
		@InjectMocks
		static UserService service;

		@BeforeEach
		public static void setUp(){
			userRepo = new UserRepositoryImpl();
			fRepo = new FriendshipRepositoryImpl();
			service = new UserService();
		}

		@BeforeAll
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
			User user = null;
			//user.setPassword("password");
			Mockito.doNothing().when(userRepo).insert(Mockito.any());
			service.register(user);
			Mockito.verify(userRepo).insert(Mockito.any());
		}
		
		//reimbursement tests

		

		@AfterEach
		public void perMethodTearDown() {
			
		}
		

		@AfterAll
		public static void tearDown() {
		}
	

}
