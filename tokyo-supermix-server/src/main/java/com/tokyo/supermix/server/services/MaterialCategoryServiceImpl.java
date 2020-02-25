package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.repositories.MaterialCategoryRepository;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService {
  @Autowired
  private MaterialCategoryRepository materialCategoryRepository;

  @Transactional()
  public void saveMaterialCategory(MaterialCategory materialCategory) {
    materialCategoryRepository.save(materialCategory);
  }

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return materialCategoryRepository.existsByName(name);
  }

  @Transactional()
  public List<MaterialCategory> getAllMainCategories() {
    return materialCategoryRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialCategoryExist(Long id) {
    return materialCategoryRepository.existsById(id);
  }

  @Transactional()
  public MaterialCategory getMaterialCategoryById(Long id) {
    return materialCategoryRepository.findById(id).get();
  }

  @Transactional()
  public void deleteMaterialCategory(Long id) {
    materialCategoryRepository.deleteById(id);
  }

  @Override
  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getMaterialCategoryById(id).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }
}
