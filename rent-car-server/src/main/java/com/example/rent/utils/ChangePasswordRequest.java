package com.example.rent.utils;

public class ChangePasswordRequest {
	private String currentPassword;
	private String newPassword;
	
	public ChangePasswordRequest() {
		super();
	}

	public ChangePasswordRequest(String currentPassword, String newPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
