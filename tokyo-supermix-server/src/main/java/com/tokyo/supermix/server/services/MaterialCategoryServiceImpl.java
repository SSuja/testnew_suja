package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.repositories.MaterialCategoryRepository;

@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService {
  @Autowired
  private MaterialCategoryRepository materialCategoryRepository;

  @Transactional
  public void saveMaterialCategory(MaterialCategory materialCategory) {
    materialCategoryRepository.save(materialCategory);
  }

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return materialCategoryRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public List<MaterialCategory> getAllMainCategories() {
    return materialCategoryRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialCategoryExist(Long id) {
    return materialCategoryRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MaterialCategory getMaterialCategoryById(Long id) {
    return materialCategoryRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialCategory(Long id) {
    materialCategoryRepository.deleteById(id);
  }

  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getMaterialCategoryById(id).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public MaterialCategory getMaterialCategoryByName(String name) {
    return materialCategoryRepository.findByName(name);
  }

  @Transactional(readOnly = true)
  public List<MaterialCategory> getByMainType(MainType mainType) {
    return materialCategoryRepository.findByMainType(mainType);
  }

  @Transactional(readOnly = true)
  public boolean isMainTypeExist(MainType mainType) {
    return materialCategoryRepository.existsByMainType(mainType);
  }

  @Transactional(readOnly = true)
  public boolean isExistByNameAndMainType(String name, MainType mainType) {
    return materialCategoryRepository.existsByNameAndMainType(name, mainType);
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExists(String prefix) {
    if (materialCategoryRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix) {
    if ((!getMaterialCategoryById(id).getPrefix().equalsIgnoreCase(prefix))
        && materialCategoryRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }
}
