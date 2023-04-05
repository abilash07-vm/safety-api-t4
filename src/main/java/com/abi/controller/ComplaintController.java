package com.abi.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.abi.dto.ComplaintDto;
import com.abi.dto.ComplaintPatchDto;
import com.abi.entity.Complaint;
import com.abi.security.JwtUtil;
import com.abi.service.ComplaintService;

@RestController
public class ComplaintController {
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	ComplaintService complaintService;


	@PostMapping("/complaints")
	public ResponseEntity newComplaint(@RequestBody ComplaintDto complainData,@RequestHeader("Authorization") String token) {
		
		System.out.println("complainData: "+complainData);
		token=token.substring(7);
		Long userMobileNumber=Long.parseLong(jwtUtil.getUserNameFromToken(token));
		
		if(isNotValidString(complainData.getDescription()) || isNotValidString(complainData.getEvidence()) || isNotValidString(complainData.getLocation())) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			Complaint newComplaint = complaintService.newComplaint(complainData,userMobileNumber);
			return ResponseEntity.status(HttpStatus.CREATED).body(newComplaint);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	private boolean isNotValidString(String value) {
		// TODO Auto-generated method stub
		return value==null || value.length()==0;
	}

	@GetMapping("/complaints")
	public List<Complaint> getAllComplain(@RequestHeader("Authorization") String token) {
		token=token.substring(7);
		String role = jwtUtil.getRoleFromToken(token);
		Long userMobileNumber=Long.parseLong(jwtUtil.getUserNameFromToken(token));
		
		return complaintService.getAllComplaint(role,userMobileNumber);
	}
	
	@DeleteMapping("/complaints/{id}")
	public ResponseEntity deleteComplaint(@PathVariable("id") Long complaintId, @RequestHeader("Authorization") String token) {
		token=token.substring(7);
		try {
			Long userMobileNumber=Long.parseLong(jwtUtil.getUserNameFromToken(token));
			
			complaintService.deleteComplaintById(complaintId,userMobileNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/complaints/{id}")
	@RolesAllowed(value = {"ROLE_OFFICER"})
	public ResponseEntity patchComplaint(@PathVariable("id") Long complaintId,@RequestBody ComplaintPatchDto complaintPatchData) {
		try {
			Complaint patchStatusOfComplaint = complaintService.patchStatusOfComplaint(complaintId,complaintPatchData);
			return ResponseEntity.ok(patchStatusOfComplaint);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
}
