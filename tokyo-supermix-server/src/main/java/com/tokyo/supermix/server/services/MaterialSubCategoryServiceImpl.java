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
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.QMaterialSubCategory;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class MaterialSubCategoryServiceImpl implements MaterialSubCategoryService {
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<MaterialSubCategory> getMaterialSubCategories() {
    return materialSubCategoryRepository.findAll();
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
  public void saveMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    materialSubCategoryRepository.save(materialSubCategory);
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
}
