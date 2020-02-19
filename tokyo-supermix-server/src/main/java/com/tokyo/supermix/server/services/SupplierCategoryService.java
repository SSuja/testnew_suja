package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SupplierCategory;

public interface SupplierCategoryService {
  public SupplierCategory createSupplierCategory(SupplierCategory supplierCategory);

  public boolean isSupplierCategoryExist(String supplierCategory);

  public List<SupplierCategory> getAllSupplierCategories();

  public SupplierCategory updateSupplierCategory(SupplierCategory supplierCategory);

  public boolean isSupplierCategoryExist(Long id);

  public SupplierCategory getSupplierCategoryById(Long id);

  public boolean isUpdatedCategoryExist( Long id, String category);
}
