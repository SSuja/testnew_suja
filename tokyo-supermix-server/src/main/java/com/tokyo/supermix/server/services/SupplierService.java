package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Supplier;

public interface SupplierService {
  public List<Supplier> getSuppliers();

  public void createSupplier(Supplier supplier, List<Long> supplierCategoryIds);

  public Supplier getSupplierById(Long id);

  public void updateSupplier(Supplier supplier);

  public void deleteSupplierById(Long id);

  public boolean isSupplierExist(Long id);

  public boolean isEmailExist(String email);

  public boolean isPhoneNumberExist(String phoneNumber);

  public boolean isUpdatedEmailExist(Long id, String email);

  public boolean isUpdatedPhoneNumberExist(Long id, String phoneNumber);

  public List<Supplier> findBySupplierCategoryId(Long suppilerCategoryId);

  public Page<Supplier> searchSupplier(Predicate predicate, int page, int size);

  public List<Supplier> getSupplierByPlantCode(String plantCode);

  public List<Long> supplierCategoriesIds(List<Long> suppilerCategoryIds, Long id);

}
