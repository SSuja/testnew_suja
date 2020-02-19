package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SupplierCategory;

public interface SupplierCategoryService {
  public SupplierCategory createSupplierCategory(SupplierCategory supplierCategory);

  public boolean isSupplierCategoryExist(String supplierCategory);

  public List<SupplierCategory> getAllSupplierCategories();
}
