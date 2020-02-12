package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long>{

	boolean existsByName(String name);
}
