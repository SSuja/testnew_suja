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
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.QMaterialSubCategory;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.ParameterRepository;
import com.tokyo.supermix.data.repositories.UnitRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class MaterialSubCategoryServiceImpl implements MaterialSubCategoryService {
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
  @Autowired
  private Mapper mapper;
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;
  @Autowired
  private ParameterRepository parameterRepository;
  @Autowired
  private UnitRepository unitRepository;

  @Transactional(readOnly = true)
  public List<MaterialSubCategory> getMaterialSubCategories() {
    return materialSubCategoryRepository.findAllByOrderByUpdatedAtDesc();
  }

  @Transactional(readOnly = true)
  public MaterialSubCategory getMaterialSubCategoryById(long id) {
    return materialSubCategoryRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialSubCategory(Long id) {
    materialSubCategoryRepository.deleteById(id);
  }

  @Transactional
  public Long saveMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    materialSubCategoryRepository.save(materialSubCategory);
    return materialSubCategory.getId();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryExist(Long id) {
    return materialSubCategoryRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialSubCategoryNameExist(String name) {
    return materialSubCategoryRepository.existsByName(name);
  }

  public boolean isUpdatedMaterialSubCategoryNameExist(Long id, String name,
      Long materialCategoryId) {
    if ((!getMaterialSubCategoryById(id).getName().equalsIgnoreCase(name))
        && (isMaterialSubCategoryNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<MaterialSubCategory> getMaterialSubCategoryByCategory(
      MaterialCategory materialCategory) {
    return materialSubCategoryRepository.findByMaterialCategory(materialCategory);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialCategoryIdExist(Long materialCategoryId) {
    return materialSubCategoryRepository.existsByMaterialCategoryId(materialCategoryId);
  }

  @Transactional(readOnly = true)
  public MaterialSubCategory getMaterialSubCategoryByName(String name) {
    return materialSubCategoryRepository.findByName(name);
  }

  @Transactional(readOnly = true)
  public Page<MaterialSubCategory> searchMaterialSubCategory(Predicate predicate, int size,
      int page) {
    return materialSubCategoryRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public boolean isMaterialCategoryExist(String name, Long materialCategoryId) {
    if ((materialSubCategoryRepository.existsByNameAndMaterialCategoryId(name,
        materialCategoryId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExists(String prefix) {
    if (materialSubCategoryRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix) {
    if ((!getMaterialSubCategoryById(id).getPrefix().equalsIgnoreCase(prefix))
        && materialSubCategoryRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isMaterialCategoryExistUpdate(Long id, String name, Long materialCategoryId) {
    if (!(materialSubCategoryRepository.existsByIdAndNameAndMaterialCategoryId(id, name,
        materialCategoryId))) {
      return false;
    }
    return true;
  }

  @Transactional(readOnly = true)
  public List<MaterialSubCategoryResponseDto> searchByMaterialSubCategory(
      BooleanBuilder booleanBuilder, String name, String materialCategoryName, String prefix,
      MainType materialCategoryMainType, Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QMaterialSubCategory.materialSubCategory.name.containsIgnoreCase(name));
    }
    if (materialCategoryName != null && !materialCategoryName.isEmpty()) {
      booleanBuilder.and(QMaterialSubCategory.materialSubCategory.materialCategory.name
          .containsIgnoreCase(materialCategoryName));
    }
    if (prefix != null && !prefix.isEmpty()) {
      booleanBuilder
          .and(QMaterialSubCategory.materialSubCategory.prefix.containsIgnoreCase(prefix));
    }
    if (materialCategoryMainType != null) {
      booleanBuilder.and(QMaterialSubCategory.materialSubCategory.materialCategory.mainType
          .eq(materialCategoryMainType));
    }
    pagination.setTotalRecords(
        ((Collection<MaterialSubCategory>) materialSubCategoryRepository.findAll(booleanBuilder))
            .stream().count());
    return mapper.map(materialSubCategoryRepository.findAll(booleanBuilder, pageable).toList(),
        MaterialSubCategoryResponseDto.class);
  }

  // check validation for condition range - List of MQP
  @Transactional(readOnly = true)
  public boolean checkValidationForConditionalRange(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if (materialQualityParameterRequestDto.getParameterId() == null) {
        return true;
      } else if (materialQualityParameterRequestDto.getConditionRange() == null) {
        return true;
      } else if (materialQualityParameterRequestDto.getConditionRange().equals(Condition.BETWEEN)
          && (materialQualityParameterRequestDto.getMaxValue() == null
              || materialQualityParameterRequestDto.getMinValue() == null)) {
        return true;
      } else if ((materialQualityParameterRequestDto.getConditionRange()
          .equals(Condition.GREATER_THAN)
          || materialQualityParameterRequestDto.getConditionRange().equals(Condition.LESS_THAN)
          || materialQualityParameterRequestDto.getConditionRange().equals(Condition.EQUAL))
          && materialQualityParameterRequestDto.getValue() == null) {
        return true;
      } else if (materialQualityParameterRequestDto.getUnitId() == null) {
        return true;
      }
    }
    return false;
  }

  @Transactional
  public void saveMQPForSubCategory(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList,
      Long subCategoryId) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      saveToMQParameterFromMAValue(materialQualityParameterRequestDto, subCategoryId);
    }
  }

  private void saveToMQParameterFromMAValue(
      MaterialQualityParameterRequestDto materialQualityParameterRequestDto, Long subCategoryId) {
    MaterialQualityParameter materialQualityParameter = new MaterialQualityParameter();
    materialQualityParameter
        .setMaterialSubCategory(materialSubCategoryRepository.findById(subCategoryId).get());
    materialQualityParameter.setQualityParamaterType(QualityParamaterType.SUB_CATEGORY);
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
}
