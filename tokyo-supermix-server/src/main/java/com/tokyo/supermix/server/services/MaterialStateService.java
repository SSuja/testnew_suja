package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialState;

public interface MaterialStateService {
  public MaterialState saveMaterialState(MaterialState materialState);

  public boolean isMaterialStateExist(String materialState);

  public boolean isMaterialStateExist(Long id);

  public List<MaterialState> getAllMaterialStates();

  public MaterialState getMaterialStateById(Long id);

  public boolean isUpdatedMaterialStateExist(Long id, String materialState);

  public void deleteMaterialState(Long id);
}
