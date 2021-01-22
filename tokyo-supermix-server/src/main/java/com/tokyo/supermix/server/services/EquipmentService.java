package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.enums.EquipmentType;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface EquipmentService {
  public boolean isNameExist(String name);

  public void saveEquipment(Equipment equipment);

  public List<Equipment> getAllEquipments(Pageable pageable);

  boolean isEquipmentExist(Long id);

  public void deleteEquipment(Long id);

  public Equipment getEquipmentById(Long id);

  public boolean isUpdatedNameExist(Long id, String name);

  public List<Equipment> searchEquipment(String name, EquipmentType equipmentType,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, Pagination pagination);

  public Long countEquipment();
}
