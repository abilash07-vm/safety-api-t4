package com.abi.dto;

public class ComplaintDto {
	private String location;
	
	private String description;
	
	private String evidence;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	@Override
	public String toString() {
		return "ComplaintDto [location=" + location + ", description=" + description + ", evidence=" + evidence + "]";
	}
	
	
	
}
