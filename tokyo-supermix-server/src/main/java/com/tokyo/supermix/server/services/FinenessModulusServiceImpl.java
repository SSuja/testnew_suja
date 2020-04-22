package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinenessModulus;
import com.tokyo.supermix.data.repositories.FinenessModulusRepository;

@Service
public class FinenessModulusServiceImpl implements FinenessModulusService {
  @Autowired
  private FinenessModulusRepository finenessModulusRepository;

  @Transactional
  public void saveFinenessModulus(FinenessModulus FinenessModulus) {
    finenessModulusRepository.save(FinenessModulus);
  }

  @Transactional(readOnly = true)
  public List<FinenessModulus> getAllFinenessModulus() {
    return finenessModulusRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinenessModulus getFinenessModulusById(Long id) {
    return finenessModulusRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinenessModulus(Long id) {
    finenessModulusRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isFinenessModulusExists(Long id) {
    return finenessModulusRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<FinenessModulus> getFinenessModulusByMaterialSubCategory(Long materialSubCategoryId) {
    return finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryIdExists(Long materialSubCategoryId) {
    return finenessModulusRepository.existsByMaterialSubCategoryId(materialSubCategoryId);
  }

  public boolean isUpdatedMaterialSubCategoryExist(Long id, Long materialSubCategoryId) {
    if ((!getFinenessModulusById(id).getMaterialSubCategory().equals(materialSubCategoryId))
        && (isMaterialSubCategoryIdExists(materialSubCategoryId))) {
      return true;
    }
    return false;
  }

}
