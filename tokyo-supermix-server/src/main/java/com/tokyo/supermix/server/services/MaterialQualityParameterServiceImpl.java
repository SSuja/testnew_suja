package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;

@Service
public class MaterialQualityParameterServiceImpl implements MaterialQualityParameterService {
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;

  @Transactional
  public void saveAllMaterialQualityParameter(
      List<MaterialQualityParameter> materialQualityParameterList) {
    materialQualityParameterRepository.saveAll(materialQualityParameterList);
  }

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialQualityParameters() {
    return materialQualityParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public void deleteMaterialQualityParameterById(Long id) {
    materialQualityParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isExistsById(Long id) {
    return materialQualityParameterRepository.existsById(id);
  }

  @Transactional
  public void updateMaterialQualityParameter(MaterialQualityParameter materialQualityParameter) {
    materialQualityParameterRepository.save(materialQualityParameter);
  }
}
