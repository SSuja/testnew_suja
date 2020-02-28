package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.PlantEquipment;

public interface PlantEquipmentRepository extends JpaRepository<PlantEquipment, String> {

  boolean existsByserialNo(String serialNo);

  PlantEquipment findPlantEquipmentBySerialNo(String serialNo);
}
