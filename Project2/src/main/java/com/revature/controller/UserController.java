package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Using a Spring controller we can easily define endpoints and mappings! 
 * its all annotation-driven!
 * 
 * Note that @Controller is a spring stereotype
 * 
 * First we need to define a mapping for this controller
 */
@Controller(value = "userController")
@RequestMapping(path = "/user")
public class UserController {
	
}
