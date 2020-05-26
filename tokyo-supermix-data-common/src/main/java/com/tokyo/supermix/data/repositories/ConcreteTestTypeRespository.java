package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.ConcreteTestType;

public interface ConcreteTestTypeRespository
		extends JpaRepository<ConcreteTestType, Long>, QuerydslPredicateExecutor<ConcreteTestType> {
	boolean existsByType(String type);

	ConcreteTestType findByType(String type);
}
