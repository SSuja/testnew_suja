package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Equipment;

public interface EquipmentService {
  /* Check Existing name */
  public boolean isNameExist(String name);

  /* Save Equipments */
  public void saveEquipment(Equipment equipment);

  /* Get All Equipments */
  public List<Equipment> getAllEquipments();

  /* Check Existing id */
  boolean isEquipmentExist(Long id);

  /* Delete Equipment */
  public void deleteEquipment(Long id);

  /* Get Equipment By Id */
  public Equipment getEquipmentById(Long id);

  /* Check updated name */
  public boolean isUpdatedNameExist(Long id, String name);
}
