package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;

@Service
public class MaterialQualityParameterServiceImpl implements MaterialQualityParameterService {
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;

  @Override
  public void saveMaterialQualityParameter(MaterialQualityParameter materialQualityParameter) {
    materialQualityParameterRepository.save(materialQualityParameter);
  }

  @Override
  public boolean isDuplicateRowExists(Long qualityParameterId, Long rawMaterialId, Double value,
      Long unitId) {
    if (materialQualityParameterRepository
        .existsByQualityParameterIdAndRawMaterialIdAndValueAndUnitId(qualityParameterId,
            rawMaterialId, value, unitId)) {
      return true;
    }
    return false;
  }

  @Override
  public List<MaterialQualityParameter> getAllMaterialQualityParameters() {
    return materialQualityParameterRepository.findAll();
  }

  @Override
  public boolean isMaterialQualityParameterIdExist(Long id) {
    return materialQualityParameterRepository.existsById(id);
  }

  @Override
  public void deleteMaterialQualityParameter(Long id) {
    materialQualityParameterRepository.deleteById(id);
  }

  @Override
  public MaterialQualityParameter getMaterialQualityParameterById(Long id) {
    return materialQualityParameterRepository.findById(id).get();
  }

}
