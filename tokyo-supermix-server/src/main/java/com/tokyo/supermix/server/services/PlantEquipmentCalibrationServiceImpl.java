package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class PlantEquipmentCalibrationServiceImpl implements PlantEquipmentCalibrationService {

  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;


  @Transactional
  public PlantEquipmentCalibration savePlantEquipmentCalibration(
      PlantEquipmentCalibration plantEquipmentCalibration) {
    LocalDate localDueDate = plantEquipmentCalibration.getCalibratedDate().toLocalDate()
        .plusDays(plantEquipmentCalibration.getNoOfDays());
    java.sql.Date dueDate = java.sql.Date.valueOf(localDueDate);
    plantEquipmentCalibration.setDueDate(dueDate);
    return plantEquipmentCalibrationRepository.save(plantEquipmentCalibration);
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
  public Page<PlantEquipmentCalibration> searchPlantEquipmentCalibration(Predicate predicate,
      int page, int size) {
    return plantEquipmentCalibrationRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
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
}
