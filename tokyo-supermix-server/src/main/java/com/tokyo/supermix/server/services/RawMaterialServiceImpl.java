package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialRequestDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.entities.QRawMaterial;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.MaterialType;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.ParameterRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.UnitRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.util.Constants;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private Mapper mapper;
  @Autowired
  PlantRepository plantRepository;
  @Autowired
  MixDesignRepository mixDesignRepository;
  @Autowired
  IncomingSampleRepository incomingSampleRepository;
  @Autowired
  CoreTestConfigureRepository coreTestConfigureRepository;
  @Autowired
  TestConfigureRepository testConfigureRepository;
  @Autowired
  MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;
  @Autowired
  private ParameterRepository parameterRepository;
  @Autowired
  private UnitRepository unitRepository;

  @Transactional
  public Long saveRawMaterial(RawMaterial rawMaterial) {
    RawMaterial rawMaterialObj = null;
    if (rawMaterial.getId() == null) {
      rawMaterialObj = rawMaterialRepository.save(rawMaterial);
      if (rawMaterialObj != null) {
        emailNotification.sendRawmaterialCreationEmail(rawMaterialObj);
      }
    } else {
      rawMaterialObj = rawMaterialRepository.save(rawMaterial);
    }
    return rawMaterialObj.getId();
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialNameExist(String name) {
    return rawMaterialRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialExist(Long id) {
    return rawMaterialRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getAllRawMaterials(Pageable pageable) {
    return rawMaterialRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public RawMaterial getRawMaterialById(Long id) {
    return rawMaterialRepository.findById(id).get();
  }

  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getRawMaterialById(id).getName().equalsIgnoreCase(name))
        && (isRawMaterialNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRawMaterial(Long id) {
    rawMaterialRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Page<RawMaterial> searchRawMaterial(Predicate predicate, int page, int size) {
    return rawMaterialRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.Direction.ASC, "id"));
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getAllActiveRawMaterials() {
    return rawMaterialRepository.findByActiveTrue();
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getByMaterialSubCategoryId(Long materialSubCategoryId) {
    return rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public List<RawMaterialResponseDto> searchRawMaterial(BooleanBuilder booleanBuilder, String name,
      String materialSubCategoryName, String plantName, String prefix, String plantCode,
      String erpCode, String mainCategoryName, String subBusinessUnitName, Pageable pageable,
      Pagination pagination) {
    if (plantCode != null && !plantCode.isEmpty()
        && (plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      if (name != null && !name.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.name.stringValue().contains(name));
      }
      if (materialSubCategoryName != null && !materialSubCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.name.stringValue()
            .contains(materialSubCategoryName));
      }
      if (plantName != null && !plantName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.plant.name.stringValue().contains(plantName));
      }
      if (prefix != null && !prefix.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.prefix.stringValue().contains(prefix));
      }
      if (erpCode != null && !erpCode.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.erpCode.stringValue().contains(erpCode));
      }
      if (mainCategoryName != null && !mainCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.materialCategory.name
            .stringValue().contains(mainCategoryName));
      }
      if (subBusinessUnitName != null && !subBusinessUnitName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.subBusinessUnit.name.stringValue()
            .contains(subBusinessUnitName));
      }

    } else {
      booleanBuilder.or(QRawMaterial.rawMaterial.plant.code.eq(plantCode));
      booleanBuilder.or(QRawMaterial.rawMaterial.subBusinessUnit.id
          .eq(plantRepository.findById(plantCode).get().getSubBusinessUnit().getId()));
      booleanBuilder.or(QRawMaterial.rawMaterial.materialType.eq(MaterialType.COMMON));
      if (name != null && !name.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.name.stringValue().contains(name));
      }
      if (materialSubCategoryName != null && !materialSubCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.name.stringValue()
            .contains(materialSubCategoryName));
      }
      if (plantName != null && !plantName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.plant.name.stringValue().contains(plantName));
      }
      if (prefix != null && !prefix.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.prefix.stringValue().contains(prefix));
      }
      if (erpCode != null && !erpCode.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.erpCode.stringValue().contains(erpCode));
      }
      if (mainCategoryName != null && !mainCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.materialCategory.name
            .stringValue().contains(mainCategoryName));
      }
      if (subBusinessUnitName != null && !subBusinessUnitName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.subBusinessUnit.name.stringValue()
            .contains(subBusinessUnitName));
      }
    }
    pagination.setTotalRecords(
        ((Collection<RawMaterial>) rawMaterialRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(rawMaterialRepository.findAll(booleanBuilder, pageable).toList(),
        RawMaterialResponseDto.class);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getRawMaterialsByPlantCode(String plantCode, Pageable pageable) {
    return rawMaterialRepository
        .findByPlantCodeOrMaterialTypeOrSubBusinessUnitIdOrderByUpdatedAtDesc(plantCode, MaterialType.COMMON,
            plantRepository.findById(plantCode).get().getSubBusinessUnit().getId(), pageable)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getRawMaterialsByMaterialSubCategoryAndPlantCode(
      Long materialSubCategoryId, String plantCode) {
    return rawMaterialRepository
        .findByMaterialSubCategoryIdAndPlantCodeOrPlantNull(materialSubCategoryId, plantCode);
  }

  @Transactional(readOnly = true)
  public Long countRawMaterials() {
    return rawMaterialRepository.count();
  }

  @Transactional(readOnly = true)
  public Long countRawMaterialByPlant(String plantCode) {
    return rawMaterialRepository.countByPlantCodeOrPlantNull(plantCode);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getNameByPlantCode(String plantCode, String name) {
    if (name.isEmpty()) {
      return null;
    }
    return rawMaterialRepository.findByPlantCodeOrMaterialTypeOrSubBusinessUnitIdAndNameStartsWith(
        plantCode, MaterialType.COMMON,
        plantRepository.findById(plantCode).get().getSubBusinessUnit().getId(), name);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getName(String name) {
    if (name.isEmpty()) {
      return null;
    }
    return rawMaterialRepository.findByNameContainsIgnoreCase(name);
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExists(String prefix) {
    if (rawMaterialRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix) {
    if ((!getRawMaterialById(id).getPrefix().equalsIgnoreCase(prefix))
        && rawMaterialRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getRawMaterialsByMainType(MainType mainType) {
    return rawMaterialRepository.findByMaterialSubCategoryMaterialCategoryMainType(mainType);

  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getAllRawMaterialsPage(Pageable pageable) {
    return rawMaterialRepository.findAllByOrderByUpdatedAtDesc(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getAllRawMaterials() {
    return rawMaterialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAndMaterialSubCategoryExists(String prefix, Long materialSubCategoryId,
      String plantCode) {
    return rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndPlantCode(prefix,
        materialSubCategoryId, plantCode);
  }

  @Transactional(readOnly = true)
  public Long countRawMaterialByPlantCount(String plantCode) {
    return rawMaterialRepository.countByPlantCodeOrPlantNullOrSubBusinessUnitId(plantCode,
        plantRepository.findById(plantCode).get().getSubBusinessUnit().getId());
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAndMaterialSubCategoryAndErpCodeExists(String prefix,
      Long materialSubCategoryId, String plantCode, String erpCode) {
    return rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndPlantCodeAndErpCode(
        prefix, materialSubCategoryId, plantCode, erpCode);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryAndRawMaterialNameAndMaterialType(Long materialCategoryId,
      String name, MaterialType materialType) {
    return rawMaterialRepository.existsByMaterialSubCategoryIdAndNameAndMaterialType(
        materialCategoryId, name, materialType);
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialNameAndPrefixAndMaterialType(String prefix, Long materialCategoryId,
      MaterialType materialType) {
    return rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndMaterialType(prefix,
        materialCategoryId, materialType);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryAndRawMaterialNameAndMaterialTypeAndSbu(
      Long materialCategoryId, String name, Long sbuId) {
    return rawMaterialRepository
        .existsByMaterialSubCategoryIdAndNameAndSubBusinessUnitId(materialCategoryId, name, sbuId);
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialNameAndPrefixAndMaterialTypeAndSbu(String prefix,
      Long materialCategoryId, Long sbuId) {
    return rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndSubBusinessUnitId(prefix,
        materialCategoryId, sbuId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryAndRawMaterialNameAndMaterialTypeAndPlant(
      Long materialCategoryId, String name, MaterialType materialType, String plantCode) {
    return rawMaterialRepository.existsByMaterialSubCategoryIdAndNameAndMaterialTypeAndPlantCode(
        materialCategoryId, name, materialType, plantCode);
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialNameAndPrefixAndMaterialTypeAndPlant(String prefix,
      Long materialCategoryId, MaterialType materialType, String plantCode) {
    return rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndMaterialTypeAndPlantCode(
        prefix, materialCategoryId, materialType, plantCode);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedSubCategoryIdAndRawMaterialNameForCommonWise(Long id,
      Long materialSubCategoryId, String rawMaterialName, MaterialType materialType) {
    if (!((getRawMaterialById(id).getMaterialSubCategory().getId().equals(materialSubCategoryId))
        && (getRawMaterialById(id).getName().equalsIgnoreCase(rawMaterialName))
        && (getRawMaterialById(id).getMaterialType().equals(materialType)))
        && rawMaterialRepository.existsByMaterialSubCategoryIdAndNameAndMaterialType(
            materialSubCategoryId, rawMaterialName, materialType)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedSubCategoryIdAndPrefixForCommonWise(Long id, Long materialSubCategoryId,
      String prefix, MaterialType materialType) {
    if (!((getRawMaterialById(id).getMaterialSubCategory().getId().equals(materialSubCategoryId))
        && (getRawMaterialById(id).getPrefix().equals(prefix))
        && (getRawMaterialById(id).getMaterialType().equals(materialType)))
        && rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndMaterialType(prefix,
            materialSubCategoryId, materialType)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedSubCategoryIdAndRawMaterialNameForPlantWise(Long id,
      Long materialSubCategoryId, String rawMaterialName, MaterialType materialType,
      String plantCode) {
    if (!((getRawMaterialById(id).getMaterialSubCategory().getId().equals(materialSubCategoryId))
        && (getRawMaterialById(id).getName().equalsIgnoreCase(rawMaterialName))
        && (getRawMaterialById(id).getMaterialType().equals(materialType))
        && (getRawMaterialById(id).getPlant().getCode().equals(plantCode)))
        && rawMaterialRepository.existsByMaterialSubCategoryIdAndNameAndMaterialTypeAndPlantCode(
            materialSubCategoryId, rawMaterialName, materialType, plantCode)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedSubCategoryIdAndPrefixForPlantWise(Long id, Long materialSubCategoryId,
      String prefix, MaterialType materialType, String plantCode) {
    if (!((getRawMaterialById(id).getMaterialSubCategory().getId().equals(materialSubCategoryId))
        && (getRawMaterialById(id).getPrefix().equals(prefix))
        && (getRawMaterialById(id).getMaterialType().equals(materialType))
        && (getRawMaterialById(id).getPlant().getCode().equals(plantCode)))
        && rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndMaterialTypeAndPlantCode(
            prefix, materialSubCategoryId, materialType, plantCode)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedSubCategoryIdAndRawMaterialNameForSBUWise(Long id,
      Long materialSubCategoryId, String rawMaterialName, Long sbuId) {
    if (!((getRawMaterialById(id).getMaterialSubCategory().getId().equals(materialSubCategoryId))
        && (getRawMaterialById(id).getName().equalsIgnoreCase(rawMaterialName))
        && (getRawMaterialById(id).getSubBusinessUnit().getId().equals(sbuId)))
        && rawMaterialRepository.existsByMaterialSubCategoryIdAndNameAndSubBusinessUnitId(
            materialSubCategoryId, rawMaterialName, sbuId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedSubCategoryIdAndPrefixForSBUWise(Long id, Long materialSubCategoryId,
      String prefix, Long sbuId) {
    if (!((getRawMaterialById(id).getMaterialSubCategory().getId().equals(materialSubCategoryId))
        && (getRawMaterialById(id).getPrefix().equalsIgnoreCase(prefix))
        && (getRawMaterialById(id).getSubBusinessUnit().getId().equals(sbuId)))
        && rawMaterialRepository.existsByPrefixAndMaterialSubCategoryIdAndSubBusinessUnitId(prefix,
            materialSubCategoryId, sbuId)) {
      return true;
    }
    // }
    return false;
  }

  public boolean checkPlantDependForCommonMaterial(RawMaterialRequestDto rawMaterialRequestDto) {
    boolean check = true;
    if (mixDesignRepository.existsByRawMaterialId(rawMaterialRequestDto.getId())
        || incomingSampleRepository.existsByRawMaterialId(rawMaterialRequestDto.getId())) {
      if (mixDesignRepository.existsByRawMaterialIdAndPlantCode(rawMaterialRequestDto.getId(),
          rawMaterialRequestDto.getPlantCode())
          || incomingSampleRepository.existsByRawMaterialIdAndPlantCode(
              rawMaterialRequestDto.getId(), rawMaterialRequestDto.getPlantCode())) {
        return check = false;
      } else {
        return check;
      }
    } else {
      return check = false;
    }
  }

  public boolean checkSbuDependForCommonMaterial(RawMaterialRequestDto rawMaterialRequestDto) {
    boolean check = true;
    if (mixDesignRepository.existsByRawMaterialId(rawMaterialRequestDto.getId())
        || incomingSampleRepository.existsByRawMaterialId(rawMaterialRequestDto.getId())) {
      if (mixDesignRepository.existsByRawMaterialIdAndPlantSubBusinessUnitId(
          rawMaterialRequestDto.getId(), rawMaterialRequestDto.getSubBusinessUnitId())
          || incomingSampleRepository.existsByRawMaterialIdAndPlantSubBusinessUnitId(
              rawMaterialRequestDto.getId(), rawMaterialRequestDto.getSubBusinessUnitId())) {
        return check = false;
      } else {
        return check;
      }
    } else {
      return check = false;
    }
  }

  @Transactional(readOnly = true)
  public boolean isExistsSBU(Long id, Long sbuId) {
    if (rawMaterialRepository.existsByIdAndSubBusinessUnitId(id, sbuId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isExistsByPlant(Long id, String plantCode) {
    if (rawMaterialRepository.existsByIdAndPlantCode(id, plantCode)) {
      return true;
    }

    return false;
  }

  @Transactional
  public void deleteMaterialByCoreTestConfigure(Long rawMaterialId) {
    if (coreTestConfigureRepository.existsByRawMaterialId(rawMaterialId)) {
      if (!testConfigureRepository.existsByRawMaterialId(rawMaterialId)
          || !materialAcceptedValueRepository.existsByRawMaterialId(rawMaterialId)) {
        coreTestConfigureRepository.deleteByRawMaterialId(rawMaterialId);
        rawMaterialRepository.deleteById(rawMaterialId);
      }
    } else {
      rawMaterialRepository.deleteById(rawMaterialId);
    }
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getAllMaterials(String plantCode, MaterialType materialType,
      Long sbuId) {
    return rawMaterialRepository.findByPlantCodeOrMaterialTypeOrPlantSubBusinessUnitId(plantCode,
        MaterialType.COMMON, sbuId);
  }
  @Transactional
  public void saveMQPForRawMaterial(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList,
      Long rawMaterialId) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      saveToMQParameterFromMAValue(materialQualityParameterRequestDto, rawMaterialId);
    }
  }

  private void saveToMQParameterFromMAValue(
      MaterialQualityParameterRequestDto materialQualityParameterRequestDto, Long rawMaterialId) {
    MaterialQualityParameter materialQualityParameter = new MaterialQualityParameter();
    materialQualityParameter.setRawMaterial(rawMaterialRepository.findById(rawMaterialId).get());
    materialQualityParameter.setQualityParamaterType(QualityParamaterType.MATERIAL);
    if (materialQualityParameterRequestDto.getConditionRange() != null) {
      if (materialQualityParameterRequestDto.getConditionRange().equals(Condition.BETWEEN)) {
        materialQualityParameter
            .setConditionRange(materialQualityParameterRequestDto.getConditionRange());
        materialQualityParameter.setMaxValue(materialQualityParameterRequestDto.getMaxValue());
        materialQualityParameter.setMinValue(materialQualityParameterRequestDto.getMinValue());
      } else if (materialQualityParameterRequestDto.getConditionRange().equals(Condition.EQUAL)
          || materialQualityParameterRequestDto.getConditionRange().equals(Condition.GREATER_THAN)
          || materialQualityParameterRequestDto.getConditionRange().equals(Condition.LESS_THAN)) {
        materialQualityParameter
            .setConditionRange(materialQualityParameterRequestDto.getConditionRange());
        materialQualityParameter.setValue(materialQualityParameterRequestDto.getValue());
      }
    }
    materialQualityParameter
        .setUnit(unitRepository.findById(materialQualityParameterRequestDto.getUnitId()).get());
    materialQualityParameter.setParameter(
        parameterRepository.findById(materialQualityParameterRequestDto.getParameterId()).get());
    materialQualityParameterRepository.save(materialQualityParameter);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> searchRawMaterialByMainType(BooleanBuilder booleanBuilder, String name,
      String materialSubCategoryName, String plantCode, String mainCategoryName) {
    if (plantCode != null && !plantCode.isEmpty()
        && (plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.materialCategory.mainType
          .eq(MainType.RAW_MATERIAL));
      if (name != null && !name.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.name.stringValue().contains(name));
      }
      if (materialSubCategoryName != null && !materialSubCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.name.stringValue()
            .contains(materialSubCategoryName));
      }
      if (mainCategoryName != null && !mainCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.materialCategory.name
            .stringValue().contains(mainCategoryName));
      }

    } else {
      booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.materialCategory.mainType
          .eq(MainType.RAW_MATERIAL));
      booleanBuilder.or(QRawMaterial.rawMaterial.plant.code.eq(plantCode));
      booleanBuilder.or(QRawMaterial.rawMaterial.subBusinessUnit.id
          .eq(plantRepository.findById(plantCode).get().getSubBusinessUnit().getId()));
      booleanBuilder.or(QRawMaterial.rawMaterial.materialType.eq(MaterialType.COMMON));
      if (name != null && !name.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.name.stringValue().contains(name));
      }
      if (materialSubCategoryName != null && !materialSubCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.name.stringValue()
            .contains(materialSubCategoryName));
      }
      if (mainCategoryName != null && !mainCategoryName.isEmpty()) {
        booleanBuilder.and(QRawMaterial.rawMaterial.materialSubCategory.materialCategory.name
            .stringValue().contains(mainCategoryName));
      }

    }
    return (List<RawMaterial>) rawMaterialRepository.findAll(booleanBuilder);
  }
}
