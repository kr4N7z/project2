package com.revature.utility;

import com.revature.models.User;

public class UserWrapper{
	
	String cpassword;
	String firstName;
	String lastName;
	String password;
	String email;

	public UserWrapper(){
		
	}


	

	public String getCpassword() {
		return cpassword;
	}




	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	@Override
	public String toString() {
		return "UserWrapper [cpassword=" + cpassword + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + "]";
	} 
	
}
