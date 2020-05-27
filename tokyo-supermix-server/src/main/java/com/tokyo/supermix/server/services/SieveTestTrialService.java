package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.entities.SieveTestTrial;

public interface SieveTestTrialService {
  public void saveSieveTestTrial(List<SieveTestTrial> sieveTestTrials);

  public List<SieveTestTrial> getAllSieveTestTrials();

  public SieveTestTrial getSieveTestTrialById(Long id);

  public void deleteSieveTestTrial(Long id);

  public boolean isSieveTestTrialExist(Long id);

  public List<SieveTestTrial> findSieveTestTrialBySieveTestCode(String sieveTestCode);

  public void updateSieveTestStatus(SieveTest sieveTest);

  public List<SieveTestTrial> getSieveTestTrialByPlantCode(String plantCode);
}
