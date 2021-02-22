package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.QSupplierCategory;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.repositories.SupplierCategoryRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

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

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSupplierCategory(Long id) {
    supplierCategoryRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isSupplierCategoryExist(Long id) {
    return supplierCategoryRepository.existsById(id);
  }

  @Transactional
  public SupplierCategory getSupplierCategoryById(Long id) {
    return supplierCategoryRepository.findById(id).get();
  }

  @Transactional
  public List<SupplierCategory> getAllSupplierCategories() {
    return supplierCategoryRepository.findAll();
  }

  @Transactional
  public SupplierCategory updateSupplierCategory(SupplierCategory supplierCategory) {
    return supplierCategoryRepository.save(supplierCategory);
  }

  public boolean isUpdatedCategoryExist(Long id, String category) {
    if ((!getSupplierCategoryById(id).getCategory().equalsIgnoreCase(category))
        && (isSupplierCategoryExist(category))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Long countSupplierCategory() {
    return supplierCategoryRepository.count();
  }

  @Transactional(readOnly = true)
  public List<SupplierCategory> getAllSupplierCategoryByPageable(Pageable pageable) {
    return supplierCategoryRepository.findAllByOrderByIdDesc(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<SupplierCategory> searchSupplierCategory(String category,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, Pagination pagination) {
    if (category != null && !category.isEmpty()) {
      booleanBuilder.and(QSupplierCategory.supplierCategory.category.contains(category));
    }
    pagination.setTotalRecords(
        ((Collection<SupplierCategory>) supplierCategoryRepository.findAll(booleanBuilder)).stream()
            .count());
    return supplierCategoryRepository.findAll(booleanBuilder, pageable).toList();
  }
}
