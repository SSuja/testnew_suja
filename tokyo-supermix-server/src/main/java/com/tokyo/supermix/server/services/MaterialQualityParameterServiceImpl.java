package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;


@Service
public class MaterialQualityParameterServiceImpl implements MaterialQualityParameterService {
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;

  @Transactional
  public void saveMaterialQualityParameter(MaterialQualityParameter materialQualityParameter) {
    materialQualityParameterRepository.save(materialQualityParameter);
  }

  @Transactional
  public boolean isDuplicateRowExists(Long qualityParameterId, Long rawMaterialId, Double value,
      Long unitId) {
    if (materialQualityParameterRepository
        .existsByQualityParameterIdAndRawMaterialIdAndValueAndUnitId(qualityParameterId,
            rawMaterialId, value, unitId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialQualityParameters() {
    return materialQualityParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialQualityParameterIdExist(Long id) {
    return materialQualityParameterRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialQualityParameter(Long id) {
    materialQualityParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public MaterialQualityParameter getMaterialQualityParameterById(Long id) {
    return materialQualityParameterRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialQualityParameterByMaterial(
      Long rawMaterialId) {
    return materialQualityParameterRepository.findByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialIdExists(Long rawMaterialId) {
    return materialQualityParameterRepository.existsByRawMaterialId(rawMaterialId);
  }
}


