package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.QSubBusinessUnit;
import com.tokyo.supermix.data.entities.SubBusinessUnit;
import com.tokyo.supermix.data.repositories.SubBusinessUnitRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class SubBusinessUnitServiceImpl implements SubBusinessUnitService {

  @Autowired
  private SubBusinessUnitRepository subBusinessUnitRepository;

  @Transactional(readOnly = true)
  public List<SubBusinessUnit> getAllSubBusinessUnits() {
    return subBusinessUnitRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isSubBusinessUnitExist(Long id) {
    return subBusinessUnitRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public SubBusinessUnit getSubBusinessUnitById(Long id) {
    return subBusinessUnitRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSubBusinessUnit(Long id) {
    subBusinessUnitRepository.deleteById(id);
  }

  @Transactional
  public void saveSubBusinessUnit(SubBusinessUnit subBusinessUnit) {
    subBusinessUnitRepository.save(subBusinessUnit);
  }

  @Transactional(readOnly = true)
  public boolean isSubBusinessUnitExist(String subBusinessUnit) {
    return subBusinessUnitRepository.existsByName(subBusinessUnit);
  }

  public boolean isUpdatedSubBusinessUnitNameExist(Long id, String subBusinessUnit) {
    if ((!getSubBusinessUnitById(id).getName().equalsIgnoreCase(subBusinessUnit))
        && (isSubBusinessUnitExist(subBusinessUnit))) {
      return true;
    }
    return false;
  }

  @Transactional
  public List<SubBusinessUnit> searchSubBusinessUnit(BooleanBuilder booleanBuilder, String name,
      String description, Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QSubBusinessUnit.subBusinessUnit.name.contains(name));
    }
    if (description != null && !description.isEmpty()) {
      booleanBuilder.and(QSubBusinessUnit.subBusinessUnit.description.contains(description));
    }
    pagination.setTotalRecords(
        ((Collection<SubBusinessUnit>) subBusinessUnitRepository.findAll(booleanBuilder)).stream()
            .count());
    return subBusinessUnitRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional
  public List<SubBusinessUnit> getAllSubBusinessUnits(Pageable pageable) {
    return subBusinessUnitRepository.findAllByOrderByIdDesc(pageable).toList();
  }

  @Transactional
  public Long getCountsubBusinessUnit() {
    return subBusinessUnitRepository.count();
  }
}
