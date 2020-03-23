package com.tokyo.supermix.server.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.repositories.SieveTestTrialRepository;

@Service
public class SieveTestTrialServiceImpl implements SieveTestTrialService {
  @Autowired
  SieveTestTrialRepository sieveTestTrialRepository;

  @Transactional
  public void saveSieveTestTrial(List<SieveTestTrial> sieveTestTrials) {
    sieveTestTrialRepository.saveAll(sieveTestTrials);
  }

}
