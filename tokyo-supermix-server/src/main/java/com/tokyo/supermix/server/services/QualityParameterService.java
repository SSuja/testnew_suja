package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.QualityParameter;

public interface QualityParameterService {
  public void saveQualityParameter(QualityParameter qualityParameter);

  public List<QualityParameter> getAllQualityParameters();

  public boolean isQualityParameterIdExist(Long qualityParameterId);

  public void deleteQualityParameter(Long id);

  public QualityParameter getQualityParameterById(Long id);

  public boolean isNameExists(String name);

  public boolean isDuplicateEnty(Long id, String name);
}
