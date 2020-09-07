package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;

@Service
public class MaterialSubCategoryServiceImpl implements MaterialSubCategoryService {
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;

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
        && (!getMaterialSubCategoryById(id).getMaterialCategory().getId()
            .equals(materialCategoryId))
        && (isMaterialCategoryExist(name, materialCategoryId))) {
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
    if (materialSubCategoryRepository.existsByNameAndMaterialCategoryId(name, materialCategoryId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<MaterialSubCategory> getAllMaterialSubCategories(Pageable pageable) {
    return materialSubCategoryRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long getCountMaterialSubCategory() {
    return materialSubCategoryRepository.count();
  }
}
