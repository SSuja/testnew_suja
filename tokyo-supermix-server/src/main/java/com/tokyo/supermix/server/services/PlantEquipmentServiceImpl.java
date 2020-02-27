package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;

@Service
public class PlantEquipmentServiceImpl implements PlantEquipmentService {

  @Autowired
  private PlantEquipmentRepository PlantEquipmentRepository;

  @Transactional
  public void savePlantEquipment(PlantEquipment Plantequipment) {
    PlantEquipmentRepository.save(Plantequipment);
  }

  @Transactional(readOnly = true)
  public List<PlantEquipment> getAllPlantEquipments() {

    return PlantEquipmentRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deletePlantEquipment(String serialNo) {
    PlantEquipmentRepository.deleteById(serialNo);

  }

  @Transactional(readOnly = true)
  public boolean isPlantEquipmentExist(String serialNo) {

    return PlantEquipmentRepository.existsByserialNo(serialNo);
  }

  @Transactional(readOnly = true)
  public PlantEquipment getPlantEquipmentBySerialNo(String serialNo) {

    return PlantEquipmentRepository.findPlantEquipmentBySerialNo(serialNo);
  }

}
