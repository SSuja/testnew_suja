package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.entities.QEquipment;
import com.tokyo.supermix.data.entities.QTest;
import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.enums.EquipmentType;
import com.tokyo.supermix.data.repositories.EquipmentRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class EquipmentServiceImpl implements EquipmentService {
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return equipmentRepository.existsByName(name);
  }

  @Transactional
  public void saveEquipment(Equipment equipment) {
    equipmentRepository.save(equipment);
  }

  @Transactional(readOnly = true)
  public List<Equipment> getAllEquipments(Pageable pageable) {
    return equipmentRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public boolean isEquipmentExist(Long id) {
    return equipmentRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEquipment(Long id) {
    equipmentRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Equipment getEquipmentById(Long id) {
    return equipmentRepository.findById(id).get();
  }

  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getEquipmentById(id).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<Equipment> searchEquipment(String name, EquipmentType equipmentType,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QEquipment.equipment.name.startsWithIgnoreCase(name));
    }
    if (equipmentType != null) {
      booleanBuilder.and(QEquipment.equipment.equipmentType.eq(equipmentType));
    }
    pagination.setTotalRecords(
        ((Collection<Equipment>) equipmentRepository.findAll(booleanBuilder)).stream().count());
    return equipmentRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional(readOnly = true)
  public Long countEquipment() {
    return equipmentRepository.count();
  }
}
