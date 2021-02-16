package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.AcceptedValueMainDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface MaterialAcceptedValueService {

  public List<MaterialAcceptedValue> saveAcceptedValue(
      List<MaterialAcceptedValue> materialAcceptedValue);

  public void updateMaterialAcceptedValue(MaterialAcceptedValue materialAcceptedValue);

  public List<MaterialAcceptedValue> getAllMaterialAcceptedValues();

  public AcceptedValueMainDto getAllMaterialAcceptedValuesByPage(Pageable pageable,
      Long testConfigureId, CategoryAcceptedType categoryAcceptedType);

  public Long countMaterialAcceptedValues(Long testConfigureId,
      CategoryAcceptedType categoryAcceptedType);

  boolean isMaterialAcceptedValueExist(Long id);

  public MaterialAcceptedValue getMaterialAcceptedValueById(Long id);

  public void deleteMaterialAcceptedValue(Long id);

  public List<MaterialAcceptedValue> getMaterialAcceptedValueByTestConfigure(
      TestConfigure testConfigure);

  boolean isDuplicateEntryExist(Long testConfigureId, Long rawMaterialId);

  public boolean isUpdatedRawMaterialIdExist(Long id, Long testConfigureId, Long rawMaterialId);

  boolean isRawMaterialIdExist(Long rawMaterialId);

  public boolean isTestConfigureIdAndRawMaterialIdAndTestParameterId(Long testConfigureId,
      Long rawMaterialId, Long testParameterId);

  public boolean isCheckValidation(List<MaterialAcceptedValue> materialAcceptedValueList);

  public AcceptedValueMainDto findByTestConfigureId(Long testConfigureId);

  public boolean ExistsTestConfigureId(Long testConfigureId);

  public List<RawMaterial> findRawMaterialByTestConfigureId(Long testConfigureId);

  public void upDateTesConfigureType(Long testConfigureId);

  public List<MaterialSubCategory> getMaterialSubCategoryByTesConfigureId(Long testConfigureId);

  public List<RawMaterial> getRawMaterialByTesConfigureId(Long testConfigureId);

  public List<RawMaterial> getRawMaterialByTesConfigureIdAndTestParameterId(Long testConfigureId,
      Long testParameterId);

  public List<MaterialSubCategory> getMaterialSubCategoryByTesConfigureIdAndTestParameterId(
      Long testConfigureId, Long testParameterId);

  public AcceptedValueMainDto searchAcceptedValue(Long testConfigId,
      CategoryAcceptedType categoryAcceptedType, String testParamName, Condition condition,
      String materialName, BooleanBuilder booleanBuilder, int page, int size, Pageable pageable,
      Pagination pagination);

  public boolean isTestConfigureIdAndSubCategoryAndTestParameterId(Long testConfigureId,
      Long materialSubCategoryId, Long testParameterId);

  public boolean isCheckValidations(MaterialAcceptedValue materialAcceptedValue);

  public boolean isUpdatedTestParameterAndRawMaterialAndTestConfigure(Long id, Long testParameterId,
      Long testConfigureId, Long rawMaterialId);

  public boolean isUpdatedTestParameterAndMaterialSubCategoryAndTestConfigure(Long id,
      Long testParameterId, Long testConfigureId, Long materialSubCategoryId);
}
