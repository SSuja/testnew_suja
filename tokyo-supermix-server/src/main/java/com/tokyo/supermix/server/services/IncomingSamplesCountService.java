package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.dto.StatusCountResponseDto;

public interface IncomingSamplesCountService {
  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId);

  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId);

  public List<StatusCountResponseDto> getCountByMaterialSubCategory(Long materialSubCategoryId);

  public List<StatusCountResponseDto> getCountByMaterialCategory(Long materialCategoryId);
}
