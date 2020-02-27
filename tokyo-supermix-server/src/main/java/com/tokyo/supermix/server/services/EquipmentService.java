package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Equipment;

public interface EquipmentService {
  public boolean isNameExist(String name);

  public void saveEquipment(Equipment equipment);

  public List<Equipment> getAllEquipments();

  boolean isEquipmentExist(Long id);

  public void deleteEquipment(Long id);

  public Equipment getEquipmentById(Long id);

  public boolean isUpdatedNameExist(Long id, String name);
}
