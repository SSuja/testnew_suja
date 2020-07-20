package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.TestEquation;

public interface TestEquationRepository extends JpaRepository<TestEquation, Long> {

}
