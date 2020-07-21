package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.notification.EmailNotification;
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
    if (plantEquipmentRepository.save(plantequipment) != null)
      emailNotification.sendPlantEquipmentCalibrationEmail(plantequipment);
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
  public Page<PlantEquipment> searchPlantEquipment(Predicate predicate, int page, int size) {
    return plantEquipmentRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "serialNo")));
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
}
