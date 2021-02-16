package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;

public interface PlantEquipmentCalibrationRepository
    extends JpaRepository<PlantEquipmentCalibration, Long>,
    QuerydslPredicateExecutor<PlantEquipmentCalibration> , PagingAndSortingRepository<PlantEquipmentCalibration, Long>{

  List<PlantEquipmentCalibration> findByPlantEquipmentPlantCode(String plantCode);

  List<PlantEquipmentCalibration> findByPlantEquipmentPlantCodeIn(List<String> plantCodes);

  PlantEquipmentCalibration findTopByPlantEquipmentSerialNoOrderByIdDesc(
      String plantEquipmentSerialNo);

  boolean existsByPlantEquipmentSerialNo(String plantEquipmentSerialNo);
   
  Page<PlantEquipmentCalibration> findAll(Pageable pageable);

  List<PlantEquipmentCalibration> findAllByPlantEquipmentPlantCode(String plantCode, Pageable pageable);

  Long countByPlantEquipmentPlantCode(String plantCode);
  
  List<PlantEquipmentCalibration> findBySentMail(boolean sentMail);
}
