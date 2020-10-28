package com.revature.models;

public class Friendship {
	private int mainUserId;
	private int otherUserId;
	private boolean approved;
	
	public Friendship() {}
	
	public Friendship(int _mainUserId, int _otherUserId, boolean _approved) {
		this.setMainUserId(_mainUserId);
		this.setOtherUserId(_otherUserId);
		this.setApproved(_approved);
	}

	public int getMainUserId() {
		return mainUserId;
	}

	public void setMainUserId(int mainUserId) {
		this.mainUserId = mainUserId;
	}

	public int getOtherUserId() {
		return otherUserId;
	}

	public void setOtherUserId(int otherUserId) {
		this.otherUserId = otherUserId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
}
