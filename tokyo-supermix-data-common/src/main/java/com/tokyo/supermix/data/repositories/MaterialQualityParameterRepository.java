package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;

@Repository
public interface MaterialQualityParameterRepository
    extends JpaRepository<MaterialQualityParameter, Long> {

}
