package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MaterialCategory;

public interface MaterialCategoryService {
  /* Save Material Category */
  public void saveMaterialCategory(MaterialCategory materialCategory);

  /* Check Existing name */
  public boolean isNameExist(String name);

  /* Get All MainCategories */
  public List<MaterialCategory> getAllMainCategories();

  /* Check Existing id */
  boolean isMaterialCategoryExist(Long id);

  /* Get Material Category By Id */
  public MaterialCategory getMaterialCategoryById(Long id);

  /* Delete Material Category */
  public void deleteMaterialCategory(Long id);

  /* Check updated name */
  public boolean isUpdatedNameExist(Long id, String name);
}
