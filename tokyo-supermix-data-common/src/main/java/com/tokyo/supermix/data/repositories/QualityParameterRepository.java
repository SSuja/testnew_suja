package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.QualityParameter;

@Repository
public interface QualityParameterRepository extends JpaRepository<QualityParameter, Long> {
  boolean existsByName(String name);
}
