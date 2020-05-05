package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.PlantEquipment;

public interface PlantEquipmentRepository
    extends JpaRepository<PlantEquipment, String>, QuerydslPredicateExecutor<PlantEquipment> {

  boolean existsByserialNo(String serialNo);

  PlantEquipment findPlantEquipmentBySerialNo(String serialNo);
}
