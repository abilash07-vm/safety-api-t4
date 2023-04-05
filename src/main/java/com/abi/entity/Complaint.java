package com.abi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.abi.enums.ComplaintStatus;

@Entity
@Table
public class Complaint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long complaintId;
	
	private String location;
	
	private String description;
	
	private String evidence;
	
	@ManyToOne
	private Users victim;
	
	private ComplaintStatus status;

	public Long getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}

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

	public Users getVictim() {
		return victim;
	}

	public void setVictim(Users victim) {
		this.victim = victim;
	}

	public ComplaintStatus getStatus() {
		return status;
	}

	public void setStatus(ComplaintStatus complaintStatus) {
		this.status = complaintStatus;
	}
	

	
	
}
