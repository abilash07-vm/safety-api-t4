package com.abi.dto;

public class UserDto {
	private String mobileNumber;
	private String password;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDto [mobileNumber=" + mobileNumber + ", password=" + password + "]";
	}

	
	
}
