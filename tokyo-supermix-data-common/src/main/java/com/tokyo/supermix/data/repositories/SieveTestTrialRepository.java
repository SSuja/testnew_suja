package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.SieveTestTrial;

@Repository
public interface SieveTestTrialRepository extends JpaRepository<SieveTestTrial, Long> {
  @Query(value = "SELECT sum(retained_weight) FROM sieve_test_trial where sieve_test_id= ?1",
      nativeQuery = true)
  public Double findTotal(Long sieveTestId);

  List<SieveTestTrial> findBySieveTestId(Long sieveTestId);
}
