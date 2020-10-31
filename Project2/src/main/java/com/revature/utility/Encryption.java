package com.revature.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encryption {
	public static PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
