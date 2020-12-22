package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.RatioConfigEquation;

public interface RatioConfigEquationRepository extends JpaRepository<RatioConfigEquation, Long> {

  boolean existsByRatio(String ratio);

  List<RatioConfigEquation> findByRatioConfigId(Long ratioConfigId);

  boolean existsByRatioConfigId(Long ratioConfigId);
}
