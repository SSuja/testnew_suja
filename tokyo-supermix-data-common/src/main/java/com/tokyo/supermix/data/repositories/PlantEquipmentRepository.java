package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.PlantEquipment;

public interface PlantEquipmentRepository extends JpaRepository<PlantEquipment, String> {

  boolean existsByserialNo(String serialNo);
  PlantEquipment findPlantEquipmentBySerialNo(String serialNo);
  List<PlantEquipment> findAllByOrderBySerialNoDesc();
}
