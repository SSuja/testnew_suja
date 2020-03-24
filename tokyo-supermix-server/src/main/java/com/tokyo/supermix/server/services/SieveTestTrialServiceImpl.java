package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

  @Transactional(readOnly = true)
  public List<SieveTestTrial> getAllSieveTestTrials() {
    return sieveTestTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public SieveTestTrial getSieveTestTrialById(Long id) {
    return sieveTestTrialRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveTestTrial(Long id) {
    sieveTestTrialRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isSieveTestTrialExist(Long id) {
    return sieveTestTrialRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<SieveTestTrial> findBySieveTestId(Long sieveTestId) {
    return sieveTestTrialRepository.findBySieveTestId(sieveTestId);
  }

}
