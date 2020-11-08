package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.postgresql.geometric.PGpoint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
@JsonIgnoreProperties
public class User {
	@Id
	@GeneratedValue(generator = "users_user_id_seq", strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_type")
	private String userType;
	@Column
	private String email;
	@Column
	private String password;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "last_latitude")
	private float lastLatitude;
	@Column(name = "last_longitude")
	private float lastLongitude;
	@Column(name = "last_state")
	private String lastState;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "last_login")
	private Date lastLogin;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public float getLastLatitude() {
		return lastLatitude;
	}
	public void setLatitude(float lastLatitude) {
		this.lastLatitude = lastLatitude;
	}
	public float getLastLongitude() {
		return lastLongitude;
	}
	public void setLongitude(float lastLongitude) {
		this.lastLongitude = lastLongitude;
	}
	public String getLastState() {
		return lastState;
	}
	public void setLastState(String lastState) {
		this.lastState = lastState;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public User() {
		super();
	}
	public User(String userType, String email, String password, String firstName, String lastName, float lastLatitude,
			float lastLongitude, String lastState, Date createdOn, Date lastLogin) {
		super();
		this.userType = userType;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastLatitude = lastLatitude;
		this.lastLongitude = lastLongitude;
		this.lastState = lastState;
		this.createdOn = createdOn;
		this.lastLogin = lastLogin;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userType=" + userType + ", email=" + email + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", lastLatitude=" + lastLatitude
				+ ", lastLongitude=" + lastLongitude + ", lastState=" + lastState + ", createdOn=" + createdOn
				+ ", lastLogin=" + lastLogin + "]";
	}
	
	
}
