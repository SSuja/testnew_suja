package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.EquipmentPlant;
import com.tokyo.supermix.data.repositories.EquipmentPlantRepository;

@Service
public class EquipmentPlantServiceImpl implements EquipmentPlantService {

	@Autowired
	private EquipmentPlantRepository equipmentPlantRepository;

	@Transactional
	public void saveEquipmentPlant(EquipmentPlant equipmentPlant) {
		equipmentPlantRepository.save(equipmentPlant);
	}

	@Transactional(readOnly = true)
	public List<EquipmentPlant> getAllEquipmentPlants() {

		return equipmentPlantRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteEquipmentPlant(String serialNo) {
		equipmentPlantRepository.deleteById(serialNo);

	}

	@Transactional(readOnly = true)
	public boolean isEquipmentPlantExist(String serialNo) {

		return equipmentPlantRepository.existsByserialNo(serialNo);
	}

	@Transactional(readOnly = true)
	public EquipmentPlant getEquipmentPlantBySerialNo(String serialNo) {

		return equipmentPlantRepository.findEquipmentPlantBySerialNo(serialNo);
	}

}
