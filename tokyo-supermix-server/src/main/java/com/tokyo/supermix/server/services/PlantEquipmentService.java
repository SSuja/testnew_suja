package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.PlantEquipment;

public interface PlantEquipmentService {

  public void savePlantEquipment(PlantEquipment Plantequipment);

  public List<PlantEquipment> getAllPlantEquipments();

  public void deletePlantEquipment(String serialNo);

  public boolean isPlantEquipmentExist(String serialNo);

  public PlantEquipment getPlantEquipmentBySerialNo(String serialNo);
}
