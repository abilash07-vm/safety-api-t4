package com.abi.repo;

import org.springframework.data.repository.CrudRepository;

import com.abi.entity.Officer;

public interface OfficerRepo extends CrudRepository<Officer	, Long>{

}
