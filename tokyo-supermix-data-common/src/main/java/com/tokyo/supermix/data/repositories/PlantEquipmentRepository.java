package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tokyo.supermix.data.entities.PlantEquipment;

public interface PlantEquipmentRepository
		extends JpaRepository<PlantEquipment, String>, QuerydslPredicateExecutor<PlantEquipment> {

	boolean existsByserialNo(String serialNo);

	PlantEquipment findPlantEquipmentBySerialNo(String serialNo);
	
	List<PlantEquipment> findByPlantCode(String plantCode);
}
