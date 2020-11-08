package com.revature.utility;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.revature.models.User;

public class UserResponseWrapper extends User{
	String errorMessage;

	public UserResponseWrapper(){

	}

	public UserResponseWrapper(User user,String  errorMessage){
		super();
		this.setEmail(user.getEmail());
		this.setPassword(user.getPassword());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setUserId(user.getUserId());
		this.setUserType(user.getUserType());
		this.setLatitude(user.getLastLatitude());
		this.setLongitude(user.getLastLongitude());
		this.setLastState(user.getLastState());
		this.setLastLogin(user.getLastLogin());
		this.errorMessage=errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "UserResponseWrapper [errorMessage=" + errorMessage + ", getUserId()=" + getUserId() + ", getUserType()="
				+ getUserType() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getLastLatitude()="
				+ getLastLatitude() + ", getLastLongitude()=" + getLastLongitude() + ", getLastState()="
				+ getLastState() + ", getCreatedOn()=" + getCreatedOn() + ", getLastLogin()=" + getLastLogin()
				+ ", toString()=" + "]";
	}


}
