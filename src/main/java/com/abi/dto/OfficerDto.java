package com.abi.dto;

public class OfficerDto {
	private String officerId;
	private String password;
	public String getOfficerId() {
		return officerId;
	}
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "OfficerDto [officerId=" + officerId + ", password=" + password + "]";
	}
	

	
	
	
}
