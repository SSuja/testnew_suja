package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialCategory;

public interface MaterialCategoryService {
  public void saveMaterialCategory(MaterialCategory materialCategory);

  public boolean isNameExist(String name);

  public List<MaterialCategory> getAllMainCategories();

  boolean isMaterialCategoryExist(Long id);

  public MaterialCategory getMaterialCategoryById(Long id);

  public void deleteMaterialCategory(Long id);

  public boolean isUpdatedNameExist(Long id, String name);
}
