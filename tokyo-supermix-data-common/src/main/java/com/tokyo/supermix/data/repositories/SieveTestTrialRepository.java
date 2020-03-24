package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.SieveTestTrial;

@Repository
public interface SieveTestTrialRepository extends JpaRepository<SieveTestTrial, Long> {
  List<SieveTestTrial> findBySieveTestId(Long sieveTestId);
}
