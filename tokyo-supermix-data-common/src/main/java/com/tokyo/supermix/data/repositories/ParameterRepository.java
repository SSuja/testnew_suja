package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {
	  boolean existsByName(String name);

}
