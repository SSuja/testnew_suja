package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.ConcreteTestType;

public interface ConcreteTestTypeRespository extends JpaRepository<ConcreteTestType, Long> {
	boolean existsByType(String type);
}
