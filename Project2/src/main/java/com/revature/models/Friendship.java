package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friendship implements Serializable{
	
	//Every friendship relation will have two entries so that the currently logged in user can always be represented as 'sender'
	//when a request is sent it will make a single record
	//when that request is approved, the recipient will also make a record, the reverse of the initial record
	//and both records will be approved
	@Id
	@Column(name = "sender_id")
	private int senderId;
	@Id
	@Column(name = "receiver_id")
	private int receiverId;
	@Column(name = "status")
	private boolean status;
	
	public Friendship() {}
	
	public Friendship(int _mainUserId, int _otherUserId, boolean _status) {
		this.setReceiverId(_mainUserId);
		this.setReceiverId(_otherUserId);
		this.setStatus(_status);
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderrId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean approved) {
		this.status = approved;
	}
}
