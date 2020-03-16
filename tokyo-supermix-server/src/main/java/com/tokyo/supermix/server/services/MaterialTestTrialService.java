package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialTestTrial;

public interface MaterialTestTrialService {
  public MaterialTestTrial saveMaterialTestTrial(MaterialTestTrial materialTestTrial);

  public List<MaterialTestTrial> getAllMaterialTestTrial();

  public MaterialTestTrial getMaterialTestTrialByCode(String code);

  public void deleteMaterialTestTrial(String code);

  public boolean isMaterialTestTrialExit(String code);

  public List<MaterialTestTrial> getMaterialTestTrialByMaterialTestId(String materialTestCode);

  public boolean isMaterialTestIdExit(String materialTestCode);
  
  public void getAverageAndStatus(String materialTestCode);
}
