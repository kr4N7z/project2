package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "messages")
@JsonIgnoreProperties
public class Messages {
	@Id
	@GeneratedValue(generator = "", strategy = GenerationType.AUTO)
	@Column(name = "message_id")
	private int messageId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name="sender_id", referencedColumnName="user_id"),
//		@JoinColumn(name="recieved_id", referencedColumnName="user_id")
//	})
//	private User user;

	@Column(name = "sender_id")
	private int senderId;
	@Column(name = "received_id")
	private int receivedId;
	@Column(name = "message")
	private String message;
	@Column(name = "sent_time")
	private Timestamp sentTime;
	@Column(name = "seen")
	private boolean seen;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceivedId() {
		return receivedId;
	}

	public void setReceivedId(int receivedId) {
		this.receivedId = receivedId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getSentTime() {
		return sentTime;
	}

	public void setSentTime(Timestamp sentTime) {
		this.sentTime = sentTime;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	@Override
	public String toString() {
		return "Messages [messageId=" + messageId + ", senderId=" + senderId + ", receivedId=" + receivedId
				+ ", message=" + message + ", sentTime=" + sentTime + ", seen=" + seen + "]";
	}
}
