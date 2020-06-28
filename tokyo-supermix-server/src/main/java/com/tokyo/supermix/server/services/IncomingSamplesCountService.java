package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.dto.StatusCountResponseDto;
import com.tokyo.supermix.security.UserPrincipal;

public interface IncomingSamplesCountService {
  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId, UserPrincipal currentUser);
  
  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId,  UserPrincipal currentUser);

  public List<StatusCountResponseDto> getCountByMaterialSubCategory(Long materialSubCategoryId, UserPrincipal currentUser);

  public List<StatusCountResponseDto> getCountByMaterialCategory(Long materialCategoryId, UserPrincipal currentUser);
}
