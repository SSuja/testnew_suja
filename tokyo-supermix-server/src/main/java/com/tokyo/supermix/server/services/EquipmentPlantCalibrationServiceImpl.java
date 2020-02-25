package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.EquipmentPlantCalibration;
import com.tokyo.supermix.data.repositories.EquipmentPlantCalibrationRepository;

@Service
public class EquipmentPlantCalibrationServiceImpl implements EquipmentPlantCalibrationService {

  @Autowired
  private EquipmentPlantCalibrationRepository equipmentPlantCalibrationRepository;

  @Transactional
  public EquipmentPlantCalibration createEquipmentPlantCalibration(
      EquipmentPlantCalibration equipmentPlantCalibration) {
    return equipmentPlantCalibrationRepository.save(equipmentPlantCalibration);
  }

  @Transactional(readOnly = true)
  public List<EquipmentPlantCalibration> getAllEquipmentPlantCalibration() {
    return equipmentPlantCalibrationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public EquipmentPlantCalibration getEquipmentPlantCalibrationById(Long id) {
    return equipmentPlantCalibrationRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEquipmentPlantCalibration(Long id) {
    equipmentPlantCalibrationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isEquipmentPlantCalibrationExit(Long id) {
    return equipmentPlantCalibrationRepository.existsById(id);
  }
}
