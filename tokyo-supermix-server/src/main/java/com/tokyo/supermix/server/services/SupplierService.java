package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface SupplierService {
  public List<Supplier> getSuppliers();

  // public List<Supplier> getSuppliersByPlant(UserPrincipal currentUser);

  public void createSupplier(Supplier supplier, List<Long> supplierCategoryIds);

  public Supplier getSupplierById(Long id);

  public void updateSupplier(Supplier supplier, List<Long> supplierCategoryIds);

  public void deleteSupplierById(Long id);

  public boolean isSupplierExist(Long id);

  public boolean isEmailExist(String email);

  public boolean isPhoneNumberExist(String phoneNumber);

  public boolean isUpdatedEmailExist(Long id, String email);

  public boolean isUpdatedPhoneNumberExist(Long id, String phoneNumber);

  public List<Supplier> findBySupplierCategoryIdAndPlantCode(Long suppilerCategoryId,
      String plantCode);

//  public List<Supplier> searchSupplier(String name, String address, String phoneNumber,
//      String email,String plantName, BooleanBuilder booleanBuilder, Pageable pageable);

  // public List<Supplier> getSupplierByPlantCode(String plantCode);

  public List<Supplier> findBySupplierCategoryId(Long suppilerCategoryId);

  public List<Supplier> getByPlantCodeAndSupplierCategoryId(String plantCode,
      Long supplierCategoryId,String name);

  public boolean isPlantCodeAndSupplierCategoryIdExist(String plantCode, Long supplierCategoryId);

  public List<Supplier> getSuppliersByPlant(UserPrincipal currentUser, Pageable pageable);

  public List<Supplier> getSupplierByPlantCode(String plantCode, Pageable pageable);

  public Long getCountSupplier();

  public Long getCountSupplierByPlantCode(String plantCode);

  public List<Supplier> getSupplierNameByPlantCode(String plantCode, String name);

  public List<Supplier> getSupplierName(String name);
  
  public List<Supplier> searchSupplier(String name, String address, String phoneNumber,
      String email,String plantName, BooleanBuilder booleanBuilder, Pageable pageable,String plantCode,Pagination pagination);
}
