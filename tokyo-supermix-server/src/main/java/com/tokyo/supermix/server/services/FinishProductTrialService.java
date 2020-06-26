package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductTrial;

public interface FinishProductTrialService {
  public List<FinishProductTrial> getAllFinishProductTrials();

  public FinishProductTrial getFinishProductTrialByCode(String code);

  public String saveFinishProductTrial(FinishProductTrial finishProductTrial);

  public void updateFinishProductResult(FinishProductTrial finishProductTrial);

  public void deleteFinishProductTrial(String code);

  public boolean isFinishProductTrialExists(String code);

  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode);

  public boolean isFinishProductTestExists(String finishProductTestCode);
}

