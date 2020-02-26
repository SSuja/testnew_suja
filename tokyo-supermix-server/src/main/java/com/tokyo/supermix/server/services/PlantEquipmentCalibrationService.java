package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;

public interface PlantEquipmentCalibrationService {
	public PlantEquipmentCalibration savePlantEquipmentCalibration(
			PlantEquipmentCalibration plantEquipmentCalibration);

	public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibration();

	public PlantEquipmentCalibration getPlantEquipmentCalibrationById(Long id);

	public void deletePlantEquipmentCalibration(Long id);

	public boolean isPlantEquipmentCalibrationExit(Long id);
}
