package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.MaterialType;

public interface RawMaterialService {
  public Long saveRawMaterial(RawMaterial rawMaterial);

  public boolean isRawMaterialNameExist(String name);

  public boolean isRawMaterialExist(Long id);

  public List<RawMaterial> getAllRawMaterials();

  public RawMaterial getRawMaterialById(Long id);

  public boolean isUpdatedNameExist(Long id, String name);

  public void deleteRawMaterial(Long id);

  public Page<RawMaterial> searchRawMaterial(Predicate predicate, int page, int size);

  public List<RawMaterial> getAllActiveRawMaterials();

  public List<RawMaterial> getByMaterialSubCategoryId(Long materialSubCategoryId);

  public List<RawMaterial> getRawMaterialsByPlantCode(String plantCode, Pageable pageable);

  public List<RawMaterial> getRawMaterialsByMaterialSubCategoryAndPlantCode(
      Long materialSubCategoryId, String plantCode);

  public Long countRawMaterials();

  public Long countRawMaterialByPlant(String plantCode);

  public List<RawMaterialResponseDto> searchRawMaterial(BooleanBuilder booleanBuilder, String name,
      String materialSubCategoryName, String plantName, String prefix, String plantCode,
      String erpCode, String mainCategoryName, String subBusinessUnitName, Pageable pageable,
      Pagination pagination);

  public List<RawMaterial> getNameByPlantCode(String plantCode, String name);

  public List<RawMaterial> getName(String name);

  public boolean isPrefixAlreadyExists(String prefix);

  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix);

  public List<RawMaterial> getRawMaterialsByMainType(MainType mainType);

  public List<RawMaterial> getAllRawMaterialsPage(Pageable pageable);

  public boolean isPrefixAndMaterialSubCategoryExists(String prefix, Long materialSubCategoryId,
      String plantCode);

  public Long countRawMaterialByPlantCount(String plantCode);

  public boolean isPrefixAndMaterialSubCategoryAndErpCodeExists(String prefix,
      Long materialSubCategoryId, String plantCode, String erpCode);

  public boolean isMaterialSubCategoryAndRawMaterialNameAndMaterialType(Long materialCategoryId,
      String name, MaterialType materialType);

  public boolean isRawMaterialNameAndPrefixAndMaterialType(String prefix, Long materialCategoryId,
      MaterialType materialType);

  public boolean isMaterialSubCategoryAndRawMaterialNameAndMaterialTypeAndSbu(
      Long materialCategoryId, String name, Long sbuId);

  public boolean isRawMaterialNameAndPrefixAndMaterialTypeAndSbu(String prefix,
      Long materialCategoryId, Long sbuId);

  public boolean isMaterialSubCategoryAndRawMaterialNameAndMaterialTypeAndPlant(
      Long materialCategoryId, String name, MaterialType materialType, String plantCode);

  public boolean isRawMaterialNameAndPrefixAndMaterialTypeAndPlant(String prefix,
      Long materialCategoryId, MaterialType materialType, String plantCode);

  public boolean isUpdatedSubCategoryIdAndRawMaterialNameForCommonWise(Long id,
      Long materialSubCategoryId, String rawMaterialName, MaterialType materialType);

  public boolean isUpdatedSubCategoryIdAndPrefixForCommonWise(Long id, Long materialSubCategoryId,
      String prefix, MaterialType materialType);

  public boolean isUpdatedSubCategoryIdAndRawMaterialNameForPlantWise(Long id,
      Long materialSubCategoryId, String rawMaterialName, MaterialType materialType,
      String plantCode);

  public boolean isUpdatedSubCategoryIdAndPrefixForPlantWise(Long id, Long materialSubCategoryId,
      String prefix, MaterialType materialType, String plantCode);

  public boolean isUpdatedSubCategoryIdAndRawMaterialNameForSBUWise(Long id,
      Long materialSubCategoryId, String rawMaterialName, Long sbuId);

  public boolean isUpdatedSubCategoryIdAndPrefixForSBUWise(Long id, Long materialSubCategoryId,
      String prefix, Long sbuId);

  public boolean checkPlantDependForCommonMaterial(RawMaterialRequestDto rawMaterialRequestDto);

  public boolean checkSbuDependForCommonMaterial(RawMaterialRequestDto rawMaterialRequestDto);

  public boolean isExistsSBU(Long id, Long sbuId);

  public boolean isExistsByPlant(Long id, String plantCode);

  public void deleteMaterialByCoreTestConfigure(Long rawMaterialId);

  public void saveMQPForRawMaterial(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList,
      Long rawMaterialId);

  public List<RawMaterial> searchRawMaterialByMainType(BooleanBuilder booleanBuilder, String name,
      String materialSubCategoryName, String plantCode, String mainCategoryName);
}
