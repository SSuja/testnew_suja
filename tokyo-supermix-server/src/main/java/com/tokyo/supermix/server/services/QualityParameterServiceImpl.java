package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.QualityParameter;
import com.tokyo.supermix.data.repositories.QualityParameterRepository;

@Service
public class QualityParameterServiceImpl implements QualityParameterService {
  @Autowired
  private QualityParameterRepository qualityParameterRepository;

  @Transactional
  public void saveQualityParameter(QualityParameter qualityParameter) {
    qualityParameterRepository.save(qualityParameter);
  }

  public boolean isDuplicateRowExists(String name, Long materialSubCategoryId) {
    if (qualityParameterRepository.existsByNameAndMaterialSubCategoryId(name,
        materialSubCategoryId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<QualityParameter> getAllQualityParameters() {
    return qualityParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isQualityParameterIdExist(Long qualityParameterId) {
    return qualityParameterRepository.existsById(qualityParameterId);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteQualityParameter(Long id) {
    qualityParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<QualityParameter> getQualityParametersByMaterialSubCategoryId(
      Long materialSubCategoryId) {
    return qualityParameterRepository.findByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryIdExist(Long materialSubCategoryId) {
    return qualityParameterRepository.existsByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public QualityParameter getQualityParameterById(Long id) {
    return qualityParameterRepository.findById(id).get();
  }
}
