package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;

public interface MaterialSubCategoryService {
  public List<MaterialSubCategory> getMaterialSubCategories();

  public MaterialSubCategory getMaterialSubCategoryById(long id);

  public void deleteMaterialSubCategory(Long id);

  public void saveMaterialSubCategory(MaterialSubCategory materialSubCategory);

  public List<MaterialSubCategory> getMaterialSubCategoryByCategory(
      MaterialCategory materialCategory);

  public boolean isMaterialSubCategoryExist(Long id);

  public boolean isMaterialSubCategoryNameExist(String name);

  public boolean isUpdatedMaterialSubCategoryNameExist(Long id, String name);

  public boolean isMaterialCategoryIdExist(Long materialCategoryId);

  public MaterialSubCategory getMaterialSubCategoryByName(String name);

  public Page<MaterialSubCategory> searchMaterialSubCategory(Predicate predicate, int size,
      int page);
}
