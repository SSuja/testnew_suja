package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;

public interface MaterialQualityParameterService {

  public void saveAllMaterialQualityParameter(
      List<MaterialQualityParameter> materialQualityParameterList);

  public List<MaterialQualityParameter> getAllMaterialQualityParameters();

  public void deleteMaterialQualityParameterById(Long id);

  public boolean isExistsById(Long id);

  public void updateMaterialQualityParameter(MaterialQualityParameter materialQualityParameter);

  public boolean checkCommonNullFieldValues(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList);

  public boolean checkValidationByType(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList);

  public boolean checkValidationForConditionalRange(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList);

  public boolean checkDuplicateEntry(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList);

  public boolean checkAlreadyExistsForUpdate(
      MaterialQualityParameterRequestDto materialQualityParameterRequestDto);

  public List<MaterialQualityParameter> getAllMaterialParametersByRawMaterial(Long rawMaterialId);

  public List<MaterialQualityParameter> getAllMaterialParametersBySubCategory(Long subCategoryId);

  public boolean existsMaterialParametersByRawMaterial(Long rawMaterialId);

  public boolean existsMaterialParametersBySubCategory(Long subCategoryId);
}
