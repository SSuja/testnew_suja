package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.QualityParameter;

public interface QualityParameterService {
  public void saveQualityParameter(QualityParameter qualityParameter);

  public boolean isDuplicateRowExists(String name, Long materialSubCategoryId);

  public List<QualityParameter> getAllQualityParameters();

  public boolean isQualityParameterIdExist(Long qualityParameterId);

  public boolean isMaterialSubCategoryIdExist(Long materialSubCategoryId);

  public void deleteQualityParameter(Long id);

  public List<QualityParameter> getQualityParametersByMaterialSubCategoryId(
      Long materialSubCategoryId);

  public QualityParameter getQualityParameterById(Long id);
}
