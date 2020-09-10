package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.entities.QPlantEquipment;
import com.tokyo.supermix.data.entities.QPlantEquipmentCalibration;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class PlantEquipmentCalibrationServiceImpl implements PlantEquipmentCalibrationService {

  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;


  @Transactional
  public PlantEquipmentCalibration savePlantEquipmentCalibration(
      PlantEquipmentCalibration plantEquipmentCalibration) {
    LocalDate localDueDate = plantEquipmentCalibration.getCalibratedDate().toLocalDate()
        .plusDays(plantEquipmentCalibration.getNoOfDays());
    java.sql.Date dueDate = java.sql.Date.valueOf(localDueDate);
    plantEquipmentCalibration.setDueDate(dueDate);
    PlantEquipmentCalibration plantEquipmentCalibrationobj =plantEquipmentCalibrationRepository.save(plantEquipmentCalibration);
    if (plantEquipmentCalibrationobj != null) {
      emailNotification.sendcalibrationCreationEmail(plantEquipmentCalibrationobj);
    }
    return plantEquipmentCalibrationobj;
    
  }

  @Transactional(readOnly = true)
  public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibration() {
    return plantEquipmentCalibrationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public PlantEquipmentCalibration getPlantEquipmentCalibrationById(Long id) {
    return plantEquipmentCalibrationRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deletePlantEquipmentCalibration(Long id) {
    plantEquipmentCalibrationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isPlantEquipmentCalibrationExit(Long id) {
    return plantEquipmentCalibrationRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipmentCalibration> searchPlantEquipmentCalibration(String serialNo,
      String equipmentName, String calibratedDate, String dueDate, String calibrationType,
      String supplierName, String accuracy, String employeeName, BooleanBuilder booleanBuilder,
      int page, int size, Pageable pageable, String plantCode,Pagination pagination) {
    if (serialNo != null && !serialNo.isEmpty()) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.plantEquipment.serialNo.startsWithIgnoreCase(serialNo));
    }

    if (equipmentName != null && !equipmentName.isEmpty()) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.plantEquipment.equipment.name.startsWithIgnoreCase(equipmentName));
    }

    if (calibratedDate != null ) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.calibratedDate.stringValue().startsWith(calibratedDate));
    }

    if (dueDate != null ) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.dueDate.stringValue().startsWith(dueDate));
    }
    if (calibrationType != null && !calibrationType.isEmpty()) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.calibrationType.stringValue().startsWithIgnoreCase(calibrationType));
    }
    if (supplierName != null && !supplierName.isEmpty()) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.supplier.name.startsWithIgnoreCase(supplierName));
    }
    if (accuracy != null && !accuracy.isEmpty()) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.accuracy.startsWithIgnoreCase(accuracy));
    }
    if (employeeName != null && !employeeName.isEmpty()) {
      booleanBuilder.and(QPlantEquipmentCalibration.plantEquipmentCalibration.employee.firstName.startsWithIgnoreCase(employeeName));
    }

    if(!plantCode.equals("ADMIN")) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.plant.code.contains(plantCode));
      }
    pagination.setTotalRecords(
        (long) ((List<PlantEquipmentCalibration>) plantEquipmentCalibrationRepository.findAll(booleanBuilder)).size());
    return plantEquipmentCalibrationRepository.findAll(booleanBuilder, pageable).toList();
   
  }

  @Transactional(readOnly = true)
  public List<PlantEquipmentCalibration> getPlantEquipmentCalibrationsByPlantCode(
      String plantCode) {
    return plantEquipmentCalibrationRepository.findByPlantEquipmentPlantCode(plantCode);
  }

  @Override
  public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibrationsByPlant(
      UserPrincipal currentUser) {
    return plantEquipmentCalibrationRepository.findByPlantEquipmentPlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_PLANT_EQUIPMENT_CALIBRATION));
  }

  public Date getLastDueDateByPlantEquipmentSerialNo(String plantEquipmentSerialNo) {
    return plantEquipmentCalibrationRepository
        .findTopByPlantEquipmentSerialNoOrderByIdDesc(plantEquipmentSerialNo).getDueDate();
  }

  @Override
  public boolean existsByPlantEquipmentSerialNo(String plantEquipmentSerialNo) {
    return plantEquipmentCalibrationRepository
        .existsByPlantEquipmentSerialNo(plantEquipmentSerialNo);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibration(Pageable pageable) {
  
    return plantEquipmentCalibrationRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<PlantEquipmentCalibration> getPlantEquipmentCalibrationByPlantCode(String plantCode,
      Pageable pageable) {
  
    return plantEquipmentCalibrationRepository.findAllByPlantEquipmentPlantCode(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public Long getCountPlantEquipmentCalibration() {
   
    return plantEquipmentCalibrationRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountPlantEquipmentCalibrationByPlantCode(String plantCode) {
  
    return plantEquipmentCalibrationRepository.countByPlantEquipmentPlantCode(plantCode);
  }
}
