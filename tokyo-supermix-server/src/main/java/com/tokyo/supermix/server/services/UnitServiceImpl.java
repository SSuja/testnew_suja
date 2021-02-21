package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.QUnit;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.repositories.UnitRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class UnitServiceImpl implements UnitService {
  @Autowired
  private UnitRepository unitRepository;

  @Transactional
  public void saveUnit(Unit unit) {
    unitRepository.save(unit);
  }

  @Transactional(readOnly = true)
  public List<Unit> getAllUnits() {
    return unitRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteUnit(Long id) {
    unitRepository.deleteById(id);

  }

  @Transactional(readOnly = true)
  public Unit getUnitById(Long id) {
    return unitRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isUnitExist(Long id) {
    return unitRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isUnitExist(String unit) {
    return unitRepository.existsByUnit(unit);
  }

  @Transactional
  public boolean isUpdatedUnitExist(Long id, String unit) {
    if ((!getUnitById(id).getUnit().equalsIgnoreCase(unit)) && (isUnitExist(unit))) {
      return true;
    }
    return false;
  }

  @Transactional
  public List<Unit> searchUnit(BooleanBuilder booleanBuilder, String unit, Pageable pageable,
      String plantCode, Pagination pagination) {
    if (unit != null && !unit.isEmpty()) {
      booleanBuilder.and(QUnit.unit1.unit.contains(unit));
    }
    pagination.setTotalRecords(
        ((Collection<Unit>) unitRepository.findAll(booleanBuilder)).stream().count());
    return unitRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional
  public List<Unit> getAllUnits(Pageable pageable) {
    return unitRepository.findAll(pageable).toList();
  }

  @Transactional
  public Long getCountUnit() {
    return unitRepository.count();
  }
}
