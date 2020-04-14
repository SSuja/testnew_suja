package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.CountMaterialDto;

public interface IncomingSamplesCountService {
  public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName);

  public Long calculateMaterialSubCategoryCount(String materialSubCategoryName);

  public Long getMaterialSubCategoryStatusCount(String materialSubCategoryName, int status);

  public Long getMaterialCategoryStatusCount(String materialCategoryName, int status);

  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId);

  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId);
}
