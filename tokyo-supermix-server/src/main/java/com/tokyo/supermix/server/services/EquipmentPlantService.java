package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.EquipmentPlant;

public interface EquipmentPlantService {

	public void saveEquipmentPlant(EquipmentPlant equipmentPlant);

	public List<EquipmentPlant> getAllEquipmentPlants();

	public void deleteEquipmentPlant(String serialNo);

	public boolean isEquipmentPlantExist(String serialNo);

	public EquipmentPlant getEquipmentPlantBySerialNo(String serialNo);
}
