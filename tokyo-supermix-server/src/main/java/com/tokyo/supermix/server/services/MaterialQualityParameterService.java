package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;

public interface MaterialQualityParameterService {
  public void saveMaterialQualityParameter(MaterialQualityParameter materialQualityParameter);

  public boolean isDuplicateRowExists(Long qualityParameterId, Long rawMaterialId, Double value,
      Long unitId);

  public List<MaterialQualityParameter> getAllMaterialQualityParameters();

  public boolean isMaterialQualityParameterIdExist(Long materialQualityParameterId);

  public void deleteMaterialQualityParameter(Long id);

  public MaterialQualityParameter getMaterialQualityParameterById(Long id);

  public List<MaterialQualityParameter> getAllMaterialQualityParameterByMaterial(
      Long rawMaterialId);

  public boolean isMaterialIdExists(Long rawMaterialId);
}
