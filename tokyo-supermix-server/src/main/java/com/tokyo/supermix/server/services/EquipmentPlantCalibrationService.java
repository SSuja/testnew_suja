package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.EquipmentPlantCalibration;

public interface EquipmentPlantCalibrationService {
	public EquipmentPlantCalibration createEquipmentPlantCalibration(
			EquipmentPlantCalibration equipmentPlantCalibration);

	public List<EquipmentPlantCalibration> getAllEquipmentPlantCalibration();

	public EquipmentPlantCalibration getEquipmentPlantCalibrationById(Long id);

	public void deleteEquipmentPlantCalibration(Long id);

	public boolean isEquipmentPlantCalibrationExit(Long id);
}
