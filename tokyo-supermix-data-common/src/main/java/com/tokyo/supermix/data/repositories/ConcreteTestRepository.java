package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.ConcreteTest;

public interface ConcreteTestRepository
		extends JpaRepository<ConcreteTest, Long>, QuerydslPredicateExecutor<ConcreteTest> {
	boolean existsByName(String name);

	List<ConcreteTest> findByConcreteTestTypeId(Long concreteTestTypeId);

	ConcreteTest findByName(String name);
}
