package com.revature.utility;

public class Validation {

	private static Validation validation;

	private Validation() {

	}

	public static Validation getInstance() {
		if (validation == null) {
			validation = new Validation();
		}
		return validation;
	}

	public boolean validEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}
