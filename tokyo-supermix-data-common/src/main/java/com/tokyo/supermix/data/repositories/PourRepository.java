package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Pour;

public interface PourRepository extends JpaRepository<Pour, Long>, QuerydslPredicateExecutor<Pour> {
	boolean existsByNameAndProjectCode(String name, String projectCode);
	
	List<Pour> findByProjectPlantCode(String plantCode);
}
