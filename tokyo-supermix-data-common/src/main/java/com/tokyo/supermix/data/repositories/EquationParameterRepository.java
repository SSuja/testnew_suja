package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.EquationParameter;

public interface EquationParameterRepository extends JpaRepository<EquationParameter, Long> {

}
