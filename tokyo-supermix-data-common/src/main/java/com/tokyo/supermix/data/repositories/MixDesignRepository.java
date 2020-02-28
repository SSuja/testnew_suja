package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MixDesign;

public interface MixDesignRepository extends JpaRepository<MixDesign, String> {
	  boolean existsByCode(String code);
}
