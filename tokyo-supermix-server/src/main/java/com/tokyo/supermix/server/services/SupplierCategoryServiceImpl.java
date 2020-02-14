package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.repositories.SupplierCategoryRepository;

@Service
public class SupplierCategoryServiceImpl implements SupplierCategoryService {

  @Autowired
  private SupplierCategoryRepository supplierCategoryRepository;

  @Transactional
  public SupplierCategory createSupplierCategory(SupplierCategory supplierCategory) {
    return supplierCategoryRepository.save(supplierCategory);
  }

  @Transactional(readOnly = true)
  public boolean isSupplierCategoryExist(String supplierCategory) {
    return supplierCategoryRepository.existsByCategory(supplierCategory);
  }
}
