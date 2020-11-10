package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;

public interface MaterialQualityParameterService {

  public void saveAllMaterialQualityParameter(
      List<MaterialQualityParameter> materialQualityParameterList);

  public List<MaterialQualityParameter> getAllMaterialQualityParameters();

  public void deleteMaterialQualityParameterById(Long id);

  public boolean isExistsById(Long id);

  public void updateMaterialQualityParameter(MaterialQualityParameter materialQualityParameter);
}
