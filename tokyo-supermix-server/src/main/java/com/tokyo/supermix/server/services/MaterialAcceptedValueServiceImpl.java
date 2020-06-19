package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;

@Service
public class MaterialAcceptedValueServiceImpl implements MaterialAcceptedValueService {

  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;

  @Transactional
  public List<MaterialAcceptedValue> saveAcceptedValue(
      List<MaterialAcceptedValue> materialAcceptedValue) {
    return materialAcceptedValueRepository.saveAll(materialAcceptedValue);
  }

  @Transactional
  public void updateMaterialAcceptedValue(MaterialAcceptedValue materialAcceptedValue) {
    materialAcceptedValueRepository.save(materialAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getAllMaterialAcceptedValues() {
    return materialAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialAcceptedValueExist(Long id) {
    return materialAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MaterialAcceptedValue getMaterialAcceptedValueById(Long id) {
    return materialAcceptedValueRepository.findById(id).get();
  }

  @Transactional
  public void deleteMaterialAcceptedValue(Long id) {
    materialAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getMaterialAcceptedValueByTestConfigure(
      TestConfigure testConfigure) {
    return materialAcceptedValueRepository.findByTestConfigure(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialAcceptedValueByTestConfigureId(Long testConfigureId) {
    return materialAcceptedValueRepository
        .existsMaterialAcceptedValueByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedMaterialAcceptedValueTestConfigureIdExist(Long id, Long testConfigureId) {
    if ((!getMaterialAcceptedValueById(id).getTestConfigure().getId().equals(testConfigureId))
        && (isMaterialAcceptedValueByTestConfigureId(testConfigureId))) {
      return true;
    }
    return false;
  }
}
