package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.security.UserPrincipal;

public interface PlantEquipmentService {

  public void savePlantEquipment(PlantEquipment Plantequipment);

  public List<PlantEquipment> getAllPlantEquipments();

  public void deletePlantEquipment(String serialNo);

  public boolean isPlantEquipmentExist(String serialNo);

  public PlantEquipment getPlantEquipmentBySerialNo(String serialNo);

  public Page<PlantEquipment> searchPlantEquipment(Predicate predicate, int page, int size);

  public List<PlantEquipment> getPlantEquipmentByPlantCode(String plantCode);

  public List<PlantEquipment> getAllPlantEquipmentByPlant(UserPrincipal currentUser);

  public List<PlantEquipment> getAllPlantEquipmentsByCalibrationExistTrueAndEquipmentId(
      Long equipmentId);

  public List<PlantEquipment> getAllPlantEquipmentsByCalibrationExistTrueAndEquipmentIdAndPlantCode(
      Long equipmentId, String code);
  
  public List<PlantEquipment> getAllPlantEquipment(Pageable pageable);

  public List<PlantEquipment> getPlantEquipmentByPlantCode(String plantCode, Pageable pageable);

  public Long getCountPlantEquipment();

  public Long getCountPlantEquipmentByPlantCode(String plantCode);
}
