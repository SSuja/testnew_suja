package com.tokyo.supermix.server.services;

import java.util.ArrayList;
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
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.repositories.SupplierCategoryRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class SupplierServiceImpl implements SupplierService {
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private SupplierCategoryRepository supplierCategoryRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional(readOnly = true)
  public List<Supplier> getSuppliers() {
    return supplierRepository.findAll();
  }

  @Override
  public List<Supplier> getSuppliersByPlant(UserPrincipal currentUser, Pageable pageable) {
    return supplierRepository.findAllByPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_SUPPLIER),
        pageable);
  }

  @Transactional
  public void createSupplier(Supplier supplier, List<Long> supplierCategoryIds) {
    List<SupplierCategory> supplierList = new ArrayList<SupplierCategory>();
    supplierCategoryIds
        .forEach(id -> supplierList.add(supplierCategoryRepository.findById(id).get()));
    supplier.setSupplierCategories(supplierList);
    Supplier supplierObj = supplierRepository.save(supplier);
    if (supplierObj != null) {
      emailNotification.sendSupplierEmail(supplierObj);
    }
  }

  @Transactional
  public void updateSupplier(Supplier supplier, List<Long> supplierCategoryIds) {
    List<SupplierCategory> supplierList = new ArrayList<SupplierCategory>();
    supplierCategoryIds
        .forEach(id -> supplierList.add(supplierCategoryRepository.findById(id).get()));
    supplier.setSupplierCategories(supplierList);
    supplierRepository.save(supplier);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSupplierById(Long id) {
    supplierRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Supplier getSupplierById(Long id) {
    return supplierRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isSupplierExist(Long id) {
    return supplierRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isEmailExist(String email) {
    return supplierRepository.existsByEmail(email);
  }

  @Transactional(readOnly = true)
  public boolean isPhoneNumberExist(String phoneNumber) {
    return supplierRepository.existsByPhoneNumber(phoneNumber);
  }

  public boolean isUpdatedEmailExist(Long id, String email) {
    if ((!getSupplierById(id).getEmail().equalsIgnoreCase(email)) && (isEmailExist(email))) {
      return true;
    }
    return false;
  }

  public boolean isUpdatedPhoneNumberExist(Long id, String phoneNumber) {
    if ((!getSupplierById(id).getPhoneNumber().equalsIgnoreCase(phoneNumber))
        && (isPhoneNumberExist(phoneNumber))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<Supplier> findBySupplierCategoryIdAndPlantCode(Long suppilerCategoryId,
      String plantCode) {
    return supplierRepository.findBySupplierCategoriesIdAndPlantCode(suppilerCategoryId, plantCode);
  }

  @Transactional(readOnly = true)
  public Page<Supplier> searchSupplier(Predicate predicate, int page, int size) {
    return supplierRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<Supplier> getSupplierByPlantCode(String plantCode, Pageable pageable) {
    return supplierRepository.findAllByPlantCode(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public List<Supplier> findBySupplierCategoryId(Long suppilerCategoryId) {
    return supplierRepository.findBySupplierCategoriesId(suppilerCategoryId);
  }

  @Transactional(readOnly = true)
  public List<Supplier> getByPlantCodeAndSupplierCategoryId(String plantCode,
      Long supplierCategoryId) {
    return supplierRepository.findByPlantCodeAndSupplierCategoriesId(plantCode, supplierCategoryId);
  }

  @Transactional(readOnly = true)
  public boolean isPlantCodeAndSupplierCategoryIdExist(String plantCode, Long supplierCategoryId) {
    return supplierRepository.existsByPlantCodeAndSupplierCategoriesId(plantCode,
        supplierCategoryId);
  }

  @Transactional(readOnly = true)
  public Long getCountSupplier() {
    return supplierRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountSupplierByPlantCode(String plantCode) {
    return supplierRepository.countByPlantCode(plantCode);
  }
}
