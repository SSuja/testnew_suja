package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface PlantEquipmentService {

  public void savePlantEquipment(PlantEquipment Plantequipment);

  public List<PlantEquipment> getAllPlantEquipments();

  public void deletePlantEquipment(String serialNo);

  public boolean isPlantEquipmentExist(String serialNo);

  public PlantEquipment getPlantEquipmentBySerialNo(String serialNo);

  public List<PlantEquipment> searchPlantEquipment(String serialNo, String brandName,
      String modelName, String plantName, String equipmentName, BooleanBuilder booleanBuilder,
      int page, int size, Pageable pageable, String plantCode, Pagination pagination);

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
