package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;
import com.tokyo.supermix.data.entities.SieveSize;
import com.tokyo.supermix.data.repositories.SieveSizeRepository;

@Service
public class SieveSizeServiceImpl implements SieveSizeService {
  @Autowired
  private SieveSizeRepository sieveSizeRepository;
  @Autowired
  private SieveAcceptedValueService sieveAcceptedValueService;

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
    List<Double> sieveSizeList = new ArrayList<>();
    List<Double> sieveAcceptedValueSizeList = new ArrayList<>();
    for (SieveSize sieveSize : sieveSizeRepository.findByMaterialSubCategoryId(materialSubCategoryId)) {
      System.out.println("sieve size" + sieveSize.getSize());
      sieveSizeList.add(sieveSize.getSize());

      for (SieveAcceptedValue sieveAcceptedValue : sieveAcceptedValueService.getAllSieveAcceptedValues()) {
        if (materialSubCategoryId == sieveAcceptedValue.getSieveSize().getMaterialSubCategory()
            .getId() && (sieveAcceptedValue.getSieveSize().getSize() != sieveSize.getSize()))
          sieveAcceptedValueSizeList.add(sieveAcceptedValue.getSieveSize().getSize());
        System.out.println("sieveAcceptedValue size" + sieveAcceptedValue.getSieveSize().getSize());
      }
    }
    List<Double> newSieveAcceptedValueSizeList = new ArrayList<>(sieveSizeList);
    newSieveAcceptedValueSizeList.removeAll(sieveAcceptedValueSizeList);
    ArrayList<SieveSize> sieveSize = new ArrayList<SieveSize>();
    for (int i = 0; i < newSieveAcceptedValueSizeList.size(); i++) {
      sieveSize.add(sieveSizeRepository.findBySizeAndMaterialSubCategoryId(
          newSieveAcceptedValueSizeList.get(i), materialSubCategoryId));
    }
    return sieveSize;
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

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryIdNull(Long materialSubCategoryId) {
    if (materialSubCategoryId == null) {
      return true;
    }
    return false;
  }

}
