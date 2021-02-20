package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;

public interface MixDesignRatioConfigRepository extends JpaRepository<MixDesignRatioConfig, Long> {

  List<MixDesignRatioConfig> findByMixDesignCode(String mixDesignCode);

  boolean existsByMixDesignCode(String mixDesignCode);

  MixDesignRatioConfig findByMixDesignCodeAndRatioConfigId(String mixDesignCode,
      Long ratioConfigId);

  boolean existsByRatioConfigId(Long ratioConfigId);
}
