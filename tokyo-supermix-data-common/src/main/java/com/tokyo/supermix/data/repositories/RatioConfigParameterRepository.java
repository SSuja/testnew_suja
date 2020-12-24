package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.RatioConfigParameter;

public interface RatioConfigParameterRepository extends JpaRepository<RatioConfigParameter, Long> {

  List<RatioConfigParameter> findByRatioConfigId(Long ratioConfigId);

  boolean existsByRatioConfigId(Long ratioConfigId);
}