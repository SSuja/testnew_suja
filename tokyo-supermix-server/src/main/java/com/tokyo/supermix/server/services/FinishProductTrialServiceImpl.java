package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
@Service
public class FinishProductTrialServiceImpl implements FinishProductTrialService {

  @Autowired
  private FinishProductTrialRepository finishProductTrialRepository;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialById(Long id) {
    return finishProductTrialRepository.findById(id).get();
  }

  @Transactional
  public void saveFinishProductTrial(FinishProductTrial finishProductTrial) {
    finishProductTrialRepository.save(finishProductTrial);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTrial(Long id) {
    finishProductTrialRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTrialExists(Long id) {
    return finishProductTrialRepository.existsById(id);
  }

}
