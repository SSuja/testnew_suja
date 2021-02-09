package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.entities.QPlantEquipment;
import com.tokyo.supermix.data.enums.EquipmentType;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class PlantEquipmentServiceImpl implements PlantEquipmentService {
  @Autowired
  private PlantEquipmentRepository plantEquipmentRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional
  public void savePlantEquipment(PlantEquipment plantequipment) {
    if (!plantEquipmentRepository.existsByserialNo(plantequipment.getSerialNo())) {
      PlantEquipment plantequipmentObj = plantEquipmentRepository.save(plantequipment);
      if (plantequipmentObj != null) {
        emailNotification.sendPlantEquipmentEmail(plantequipmentObj);
      }
    } else {
      plantEquipmentRepository.save(plantequipment);
    }
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getAllPlantEquipments() {
    return plantEquipmentRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deletePlantEquipment(String serialNo) {
    plantEquipmentRepository.deleteById(serialNo);
  }

  @Transactional(readOnly = true)
  public boolean isPlantEquipmentExist(String serialNo) {
    return plantEquipmentRepository.existsByserialNo(serialNo);
  }

  @Transactional(readOnly = true)
  public PlantEquipment getPlantEquipmentBySerialNo(String serialNo) {
    return plantEquipmentRepository.findPlantEquipmentBySerialNo(serialNo);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> searchPlantEquipment(String serialNo, String brandName,
      String modelName, String plantName, String equipmentName, EquipmentType equipmentType,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, String plantCode,
      Pagination pagination) {

    if (serialNo != null && !serialNo.isEmpty()) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.serialNo.contains(serialNo));
    }

    if (brandName != null && !brandName.isEmpty()) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.brandName.contains(brandName));
    }

    if (modelName != null && !modelName.isEmpty()) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.modelName.contains(modelName));
    }

    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.plant.name.contains(plantName));
    }
    if (equipmentName != null && !equipmentName.isEmpty()) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.equipment.name.contains(equipmentName));
    }
    if (equipmentType != null) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.equipment.equipmentType.eq(equipmentType));
    }
    if (!plantCode.equals("ADMIN")) {
      booleanBuilder.and(QPlantEquipment.plantEquipment.plant.code.contains(plantCode));
    }
    pagination.setTotalRecords(
        (long) ((List<PlantEquipment>) plantEquipmentRepository.findAll(booleanBuilder)).size());
    return plantEquipmentRepository.findAll(booleanBuilder, pageable).toList();

  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getPlantEquipmentByPlantCode(String plantCode) {
    return plantEquipmentRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getAllPlantEquipmentByPlant(UserPrincipal currentUser) {
    return plantEquipmentRepository.findByPlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_PLANT_EQUIPMENT));
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getAllPlantEquipmentsByCalibrationExistTrueAndEquipmentId(
      Long equipmentId) {
    return plantEquipmentRepository.findByCalibrationExistsTrueAndEquipmentId(equipmentId);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getAllPlantEquipmentsByCalibrationExistTrueAndEquipmentIdAndPlantCode(
      Long equipmentId, String plantCode) {

    return plantEquipmentRepository
        .findByCalibrationExistsTrueAndEquipmentIdAndPlantCode(equipmentId, plantCode);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getAllPlantEquipment(Pageable pageable) {

    return plantEquipmentRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getPlantEquipmentByPlantCode(String plantCode, Pageable pageable) {
    return plantEquipmentRepository.findAllByPlantCode(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public Long getCountPlantEquipment() {

    return plantEquipmentRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountPlantEquipmentByPlantCode(String plantCode) {
    return plantEquipmentRepository.countByPlantCode(plantCode);
  }
}
