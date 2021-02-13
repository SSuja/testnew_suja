package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface MaterialSubCategoryService {
  public List<MaterialSubCategory> getMaterialSubCategories();

  public MaterialSubCategory getMaterialSubCategoryById(long id);

  public void deleteMaterialSubCategory(Long id);

  public Long saveMaterialSubCategory(MaterialSubCategory materialSubCategory);

  public List<MaterialSubCategory> getMaterialSubCategoryByCategory(
      MaterialCategory materialCategory);

  public boolean isMaterialSubCategoryExist(Long id);

  public boolean isMaterialSubCategoryNameExist(String name);

  public boolean isUpdatedMaterialSubCategoryNameExist(Long id, String name,
      Long materialCategoryId);

  public boolean isMaterialCategoryIdExist(Long materialCategoryId);

  public MaterialSubCategory getMaterialSubCategoryByName(String name);

  public Page<MaterialSubCategory> searchMaterialSubCategory(Predicate predicate, int size,
      int page);

  public boolean isMaterialCategoryExist(String name, Long materialCategoryId);

  public boolean isPrefixAlreadyExists(String prefix);

  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix);

  public boolean isMaterialCategoryExistUpdate(Long id, String name, Long materialCategoryId);

  public List<MaterialSubCategoryResponseDto> searchByMaterialSubCategory(
      BooleanBuilder booleanBuilder, String name, String materialCategoryName, String prefix,
      MainType materialCategoryMainType, Pageable pageable, Pagination pagination);
}
