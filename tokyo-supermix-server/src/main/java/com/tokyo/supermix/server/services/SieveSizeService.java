package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.SieveSize;

public interface SieveSizeService {
  public List<SieveSize> saveSieveSize(List<SieveSize> sieveSize);

  public List<SieveSize> getAllSieveSizes();

  public boolean isSieveSizeExist(Long id);

  public SieveSize getSieveSizeById(Long id);

  public void deleteSieveSize(Long id);

  public List<SieveSize> findByMaterialSubCategory(Long materialSubCategoryId);

  public boolean isSizeAndMaterialSubCategoryIdExist(Double size, Long materialSubCategoryId);

  public boolean isDuplicateEntryExist(Long materialSubCategoryId, Double size);

  public SieveSize updateSieveSize(SieveSize sieveSize);

  public boolean isMaterialSubCategoryIdNull(Long materialSubCategoryId);

  public List<SieveSize> findAcceptedValueSieveSizeByMaterialSubCategoryId(
      Long materialSubCategoryId);

  public Page<SieveSize> searchSieveSize(Predicate predicate, int page, int size);
}
