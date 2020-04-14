package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;

public interface PlantEquipmentCalibrationRepository extends JpaRepository<PlantEquipmentCalibration, Long> {
  List<PlantEquipmentCalibration> findAllByOrderByIdDesc();
}
