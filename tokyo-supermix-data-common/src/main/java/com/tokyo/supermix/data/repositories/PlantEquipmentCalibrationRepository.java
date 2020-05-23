package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;

public interface PlantEquipmentCalibrationRepository
		extends JpaRepository<PlantEquipmentCalibration, Long>, QuerydslPredicateExecutor<PlantEquipmentCalibration> {

	List<PlantEquipmentCalibration> findByPlantEquipmentPlantCode(String plantCode);
}
