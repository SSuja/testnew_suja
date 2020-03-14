package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialTest;

public interface MaterialTestRepository extends JpaRepository<MaterialTest, Long> {
	
	boolean existsByStatus (String status);
	
	List<MaterialTest> findByStatus (String status);

}
