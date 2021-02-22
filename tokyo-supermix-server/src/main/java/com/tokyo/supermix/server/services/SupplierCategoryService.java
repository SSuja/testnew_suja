package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface SupplierCategoryService {
  public SupplierCategory createSupplierCategory(SupplierCategory supplierCategory);

  public boolean isSupplierCategoryExist(String supplierCategory);

  public SupplierCategory getSupplierCategoryById(Long id);

  public void deleteSupplierCategory(Long id);

  public boolean isSupplierCategoryExist(Long id);

  public List<SupplierCategory> getAllSupplierCategories();

  public SupplierCategory updateSupplierCategory(SupplierCategory supplierCategory);

  public boolean isUpdatedCategoryExist(Long id, String category);

  public Long countSupplierCategory();

  public List<SupplierCategory> getAllSupplierCategoryByPageable(Pageable pageable);

  public List<SupplierCategory> searchSupplierCategory(String category,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, Pagination pagination);
}
