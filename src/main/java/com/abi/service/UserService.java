package com.abi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abi.entity.Officer;
import com.abi.entity.Users;
import com.abi.repo.OfficerRepo;
import com.abi.repo.UsersRepo;

@Service
public class UserService implements UserDetailsService{
	
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private OfficerRepo officerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userDetails=null;
		
		try {
			userDetails = getUsers(username);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			userDetails = getOfficers(username);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userDetails;
	}

	private UserDetails getOfficers(String username) {
		// TODO Auto-generated method stub
		Long officerId= Long.parseLong(username);
		// TODO Auto-generated method stub
		Officer officers = officerRepo.findById(officerId).get();
		return new User(officers.getOfficerId()+"", officers.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_OFFICER")));
		
	}

	private UserDetails getUsers(String username) {
		Long mobileNumber= Long.parseLong(username);
		// TODO Auto-generated method stub
		Users users = usersRepo.findByMobileNumber(mobileNumber);
		return new User(users.getMobileNumber()+"", users.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
	}

}
