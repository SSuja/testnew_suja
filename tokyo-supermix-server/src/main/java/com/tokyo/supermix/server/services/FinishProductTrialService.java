package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.security.UserPrincipal;

public interface FinishProductTrialService {
  public List<FinishProductTrial> getAllFinishProductTrials();

  public List<FinishProductTrial> getAllFinishProductTrialsByPlant(UserPrincipal currentUser);

  public FinishProductTrial getFinishProductTrialByCode(Long id);

  public void saveFinishProductTrial(FinishProductTrial finishProductTrial);

  public void deleteFinishProductTrial(Long id);

  public boolean isFinishProductTrialExists(Long id);

  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode);

  public boolean isFinishProductTestExists(String finishProductTestCode);

  public void saveFinishproductResult(String finishProductTestCode);

  public void updateFinishProductTestTrial(FinishProductTrial finishProductTrial);
}

