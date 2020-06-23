package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MixDesignParameter;

public interface MixDesignParameterRepository extends JpaRepository<MixDesignParameter, Long> {
  MixDesignParameter findByEquationId(Long equationId);
}
