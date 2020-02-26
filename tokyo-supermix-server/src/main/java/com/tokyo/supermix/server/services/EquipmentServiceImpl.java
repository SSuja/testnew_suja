package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.repositories.EquipmentRepository;

@Service
public class EquipmentServiceImpl implements EquipmentService {
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return equipmentRepository.existsByName(name);
  }

  @Transactional
  public void saveEquipment(Equipment equipment) {
    equipmentRepository.save(equipment);
  }

  @Transactional(readOnly = true)
  public List<Equipment> getAllEquipments() {
    return equipmentRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isEquipmentExist(Long id) {
    return equipmentRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEquipment(Long id) {
    equipmentRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Equipment getEquipmentById(Long id) {
    return equipmentRepository.findById(id).get();
  }

  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getEquipmentById(id).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }
}
