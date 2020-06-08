package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinenessModulus;

public interface FinenessModulusService {
  public void saveFinenessModulus(FinenessModulus finenessModulus);

  public List<FinenessModulus> getAllFinenessModulus();

  public FinenessModulus getFinenessModulusById(Long id);

  public void deleteFinenessModulus(Long id);

  public boolean isFinenessModulusExists(Long id);

  public List<FinenessModulus> getFinenessModulusByMaterialSubCategory(Long materialSubCategoryId);

  public boolean isMaterialSubCategoryIdExists(Long materialSubCategoryId);

  public boolean isUpdatedMaterialSubCategoryExist(Long id, Long materialSubCategoryId);

  public Page<FinenessModulus> searchFinenessModulus(Predicate predicate, int size, int page);

  public boolean isDuplicateRowExist(Double min, Double max, Long materialSubCategoryId);
}
