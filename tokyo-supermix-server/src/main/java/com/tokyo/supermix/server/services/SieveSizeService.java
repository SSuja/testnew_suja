package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SieveSize;

public interface SieveSizeService {
  public List<SieveSize> saveSieveSize(List<SieveSize> sieveSize);

  public List<SieveSize> getAllSieveSizes();

  public boolean isSieveSizeExist(Long id);

  public SieveSize getSieveSizeById(Long id);

  public void deleteSieveSize(Long id);

  public List<SieveSize> findByMaterialSubCategoryId(Long materialSubCategoryId);

  public boolean isSizeAndMaterialSubCategoryIdExist(Double size, Long materialSubCategoryId);

  public boolean isDuplicateEntryExist(Long materialSubCategoryId, Double size);

  public SieveSize updateSieveSize(SieveSize sieveSize);

}
