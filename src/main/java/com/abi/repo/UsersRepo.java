package com.abi.repo;

import org.springframework.data.repository.CrudRepository;

import com.abi.entity.Users;

public interface UsersRepo extends CrudRepository<Users	, Long>{
	Users findByMobileNumber(Long mobileNumber);
}
