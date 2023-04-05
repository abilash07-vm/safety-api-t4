package com.abi.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.abi.entity.Complaint;
import com.abi.entity.Users;

public interface ComplaintRepo extends CrudRepository<Complaint	, Long>{
	List<Complaint> findByVictim(Users victim);
	
}

