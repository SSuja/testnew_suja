package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.security.UserPrincipal;

public interface MaterialTestTrialService {
  public String saveMaterialTestTrial(MaterialTestTrial materialTestTrial);

  public List<MaterialTestTrial> getAllMaterialTestTrial();

  public List<MaterialTestTrial> getAllMaterialTestTrialByplant(UserPrincipal currentUser);

  public MaterialTestTrial getMaterialTestTrialByCode(String code);

  public void deleteMaterialTestTrial(String code);

  public boolean isMaterialTestTrialExits(String code);

  public List<MaterialTestTrial> getMaterialTestTrialByMaterialTestCode(String materialTestCode);

  public boolean isMaterialTestIdExits(String materialTestCode);

  public void getAverageAndStatus(String materialTestCode);

  List<MaterialTestTrial> getMaterialTestTrialByPlantCode(String plantCode);
}
