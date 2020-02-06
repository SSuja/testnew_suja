package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.Plant;


public interface PlantRepository extends JpaRepository<Plant, String>{
	boolean existsByName(String name);
}
