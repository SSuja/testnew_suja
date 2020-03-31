package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SieveSize;

public interface SieveSizeService {
  public SieveSize saveSieveSize(SieveSize sieveSize);

  public List<SieveSize> getAllSieveSizes();

  public boolean isSieveSizeExist(Long id);

  public SieveSize getSieveSizeById(Long id);

  public void deleteSieveSize(Long id);

  public List<SieveSize> findByMaterialSubCategoryId(Long materialSubCategoryId);

  public boolean isSieveSizeExist(Long materialSubCategoryId, Double size);

  public boolean isSizeExist(Double size);
}
