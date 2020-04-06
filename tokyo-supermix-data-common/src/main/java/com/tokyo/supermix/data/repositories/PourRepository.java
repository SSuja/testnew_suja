package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.Pour;

public interface PourRepository extends JpaRepository<Pour, Long>{
	boolean existsByNameAndProjectCode(String name,String projectCode);
}
