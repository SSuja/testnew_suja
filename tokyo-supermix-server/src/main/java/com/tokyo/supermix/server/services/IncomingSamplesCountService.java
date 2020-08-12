package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.dto.StatusCountResponseDto;

public interface IncomingSamplesCountService {
  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId,
      String plantCode);

  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId, String plantCode);

  public List<StatusCountResponseDto> getCountByMaterialSubCategory(Long materialSubCategoryId,
      String plantCode);

  public List<StatusCountResponseDto> getCountByMaterialCategory(Long materialCategoryId, String plantCode);
  

}
