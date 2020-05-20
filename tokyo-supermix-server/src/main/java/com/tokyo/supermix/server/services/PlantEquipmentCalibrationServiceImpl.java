package com.tokyo.supermix.server.services;

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

@Service
public class PlantEquipmentCalibrationServiceImpl implements PlantEquipmentCalibrationService {

  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;

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
}
