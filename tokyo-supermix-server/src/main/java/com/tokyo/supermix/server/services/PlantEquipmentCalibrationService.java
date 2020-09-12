package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface PlantEquipmentCalibrationService {
  public PlantEquipmentCalibration savePlantEquipmentCalibration(
      PlantEquipmentCalibration plantEquipmentCalibration);

  public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibration();

  public PlantEquipmentCalibration getPlantEquipmentCalibrationById(Long id);

  public void deletePlantEquipmentCalibration(Long id);

  public boolean isPlantEquipmentCalibrationExit(Long id);

  public List<PlantEquipmentCalibration> searchPlantEquipmentCalibration(String serialNo,
      String equipmentName, String calibratedDate, String dueDate, String calibrationType,
      String supplierName, String accuracy, String status, String employeeName, BooleanBuilder booleanBuilder,
      int page, int size, Pageable pageable, String plantCode, Pagination pagination);



  public List<PlantEquipmentCalibration> getPlantEquipmentCalibrationsByPlantCode(String plantCode);

  public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibrationsByPlant(
      UserPrincipal currentUser);

  public Date getLastDueDateByPlantEquipmentSerialNo(String plantEquipmentSerialNo);

  public boolean existsByPlantEquipmentSerialNo(String plantEquipmentSerialNo);

  public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibration(Pageable pageable);

  public List<PlantEquipmentCalibration> getPlantEquipmentCalibrationByPlantCode(String plantCode,
      Pageable pageable);

  public Long getCountPlantEquipmentCalibration();

  public Long getCountPlantEquipmentCalibrationByPlantCode(String plantCode);
}
