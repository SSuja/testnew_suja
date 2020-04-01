package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.SieveSize;
import com.tokyo.supermix.data.repositories.SieveSizeRepository;

@Service
public class SieveSizeServiceImpl implements SieveSizeService {
  @Autowired
  private SieveSizeRepository sieveSizeRepository;

  @Transactional
  public List<SieveSize> saveSieveSize(List<SieveSize> sieveSize) {
    return sieveSizeRepository.saveAll(sieveSize);
  }

  @Transactional(readOnly = true)
  public List<SieveSize> getAllSieveSizes() {
    return sieveSizeRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isSieveSizeExist(Long id) {
    return sieveSizeRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public SieveSize getSieveSizeById(Long id) {
    return sieveSizeRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveSize(Long id) {
    sieveSizeRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<SieveSize> findByMaterialSubCategoryId(Long materialSubCategoryId) {
    return sieveSizeRepository.findByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public boolean isSizeAndMaterialSubCategoryIdExist(Double size, Long materialSubCategoryId) {
    return sieveSizeRepository.existsBySizeAndMaterialSubCategoryId(size, materialSubCategoryId);
  }

  public boolean isDuplicateEntryExist(Long materialSubCategoryId, Double size) {
    if ((!findByMaterialSubCategoryId(materialSubCategoryId).equals(size))
        && (isSizeAndMaterialSubCategoryIdExist(size, materialSubCategoryId))) {
      return true;
    }
    return false;
  }

  @Transactional
  public SieveSize updateSieveSize(SieveSize sieveSize) {
    return sieveSizeRepository.save(sieveSize);
  }

}
