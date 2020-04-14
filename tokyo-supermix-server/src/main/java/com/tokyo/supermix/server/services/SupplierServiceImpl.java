package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.repositories.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
  @Autowired
  private SupplierRepository supplierRepository;

  @Transactional
  public List<Supplier> getSuppliers() {
    return supplierRepository.findAll();
  }

  @Transactional
  public Supplier createSupplier(Supplier supplier) {
    return supplierRepository.save(supplier);
  }

  @Transactional
  public void updateSupplier(Supplier supplier) {
    supplierRepository.save(supplier);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSupplierById(Long id) {
    supplierRepository.deleteById(id);
  }

  @Transactional
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
  public List<Supplier> findBySupplierCategoryId(Long supplierCategoryId) {
    return supplierRepository.findBySupplierCategoryId(supplierCategoryId);
  }

}
