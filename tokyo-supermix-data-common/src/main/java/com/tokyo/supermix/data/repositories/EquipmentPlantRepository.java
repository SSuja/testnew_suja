package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.EquipmentPlant;

public interface EquipmentPlantRepository extends JpaRepository<EquipmentPlant, String> {

	boolean existsByserialNo(String serialNo);

	EquipmentPlant findEquipmentPlantBySerialNo(String serialNo);
}
