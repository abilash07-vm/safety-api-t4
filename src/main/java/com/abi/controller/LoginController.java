package com.abi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abi.dto.OfficerDto;
import com.abi.dto.UserDto;
import com.abi.security.JwtUtil;

@RestController
@RequestMapping()
public class LoginController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDetailsService userDetailsService;

	@PostMapping("/user/login")
	public String doLoginForUser(@RequestBody UserDto userData) {
		System.out.println("UserData: "+userData);

		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userData.getMobileNumber(), userData.getPassword());
			authManager.authenticate(authentication);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Login failed";
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(userData.getMobileNumber());
		System.out.println("Generating token");
		return jwtUtil.generateToken(userDetails);
	}

	
	@PostMapping("/officer/login")
	public String doLoginForOfficer(@RequestBody OfficerDto officerData) {
		System.out.println("OfficerData: "+officerData);

		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					officerData.getOfficerId(), officerData.getPassword());
			authManager.authenticate(authentication);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Login failed";
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(officerData.getOfficerId());
		System.out.println("Generating token");
		return jwtUtil.generateToken(userDetails);
	}

	
	@GetMapping("/public")
	public String publicApi() {
		return "This is public API";
	}

	@GetMapping("/private")
	public String privateApi() {
		return "This is Autheticated API";
	}

}
