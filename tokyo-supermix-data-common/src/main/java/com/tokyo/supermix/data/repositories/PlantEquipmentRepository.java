package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.PlantEquipment;

public interface PlantEquipmentRepository extends JpaRepository<PlantEquipment, String>,
    QuerydslPredicateExecutor<PlantEquipment>, PagingAndSortingRepository<PlantEquipment, String> {

  boolean existsByserialNo(String serialNo);

  PlantEquipment findPlantEquipmentBySerialNo(String serialNo);

  List<PlantEquipment> findByPlantCode(String plantCode);

  List<PlantEquipment> findByPlantCodeIn(List<String> plantCodes);

  List<PlantEquipment> findByCalibrationExistsTrueAndEquipmentId(Long equipmentId);

  List<PlantEquipment> findByCalibrationExistsTrueAndEquipmentIdAndPlantCode(Long equipmentId,
      String plantCode);

  Page<PlantEquipment> findAll(Pageable pageable);

  List<PlantEquipment> findAllByPlantCode(String plantCode, Pageable pageable);

  Long countByPlantCode(String plantCode);

  boolean existsByserialNoAndPlantCode(String serialNo, String plantCode);
}
