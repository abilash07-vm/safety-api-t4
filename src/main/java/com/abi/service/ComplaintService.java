package com.abi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abi.dto.ComplaintDto;
import com.abi.dto.ComplaintPatchDto;
import com.abi.entity.Complaint;
import com.abi.entity.Users;
import com.abi.repo.ComplaintRepo;
import com.abi.repo.UsersRepo;

@Service
public class ComplaintService {
	
	@Autowired
	ComplaintRepo complaintRepo;
	
	@Autowired
	UsersRepo usersRepo;

	public Complaint newComplaint(ComplaintDto complainData, Long userMobileNumber) {
		// TODO Auto-generated method stub
		Users user=usersRepo.findByMobileNumber(userMobileNumber);
		
		
		Complaint complaint=new Complaint();
		complaint.setDescription(complainData.getDescription());
		complaint.setEvidence(complainData.getEvidence());
		complaint.setLocation(complainData.getLocation());
		complaint.setVictim(user);
		return complaintRepo.save(complaint);
	}

	public ArrayList<Complaint> getAllComplaint(String role, Long userMobileNumber) {
		// TODO Auto-generated method stub
		
		ArrayList<Complaint> complaints= new ArrayList<>();
		if(role.equals("ROLE_OFFICER")) {
			complaintRepo.findAll().forEach(complaints::add);
		} else {
			Users user=usersRepo.findByMobileNumber(userMobileNumber);
			complaintRepo.findByVictim(user).forEach(complaints::add);
		}
		return complaints;
	}

	public void deleteComplaintById(Long complaintId, Long userMobileNumber) throws Exception {
		// TODO Auto-generated method stub
		Complaint complaint = complaintRepo.findById(complaintId).get();
		if(complaint.getVictim().getMobileNumber().equals(userMobileNumber)) {
			complaintRepo.deleteById(complaintId);
		} else {
			System.out.println("Num1: "+ userMobileNumber +" , Num2: "+complaint.getVictim().getMobileNumber());
			throw new Exception("Complaint not belongs to User");
		}
		
	}

	public Complaint patchStatusOfComplaint(Long complaintId, ComplaintPatchDto complaintPatchData) {
		// TODO Auto-generated method stub
		Complaint complaint=complaintRepo.findById(complaintId).get();
		complaint.setStatus(complaintPatchData.getStatus());
		return complaintRepo.save(complaint);
	}
	
	
	
	
}
