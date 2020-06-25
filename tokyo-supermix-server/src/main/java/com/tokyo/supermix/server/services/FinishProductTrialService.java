package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;

public interface FinishProductTrialService {

  public List<FinishProductTrial> getAllFinishProductTrials();

  public FinishProductTrial getFinishProductTrialById(Long id);

  public void saveFinishProductTrial(FinishProductTrial finishProductTrial);

  public void deleteFinishProductTrial(Long id);

  public boolean isFinishProductTrialExists(Long id);
  
  public void updateFinishProductResult(FinishProductTest finishProductTest);
}
