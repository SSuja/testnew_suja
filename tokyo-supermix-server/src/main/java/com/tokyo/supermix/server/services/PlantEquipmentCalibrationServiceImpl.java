package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;

@Service
public class PlantEquipmentCalibrationServiceImpl implements PlantEquipmentCalibrationService {

  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;

  @Transactional
  public PlantEquipmentCalibration savePlantEquipmentCalibration(
      PlantEquipmentCalibration plantEquipmentCalibration) {
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
}
