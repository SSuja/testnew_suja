package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.security.UserPrincipal;

public interface PlantEquipmentCalibrationService {
	public PlantEquipmentCalibration savePlantEquipmentCalibration(PlantEquipmentCalibration plantEquipmentCalibration);

	public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibration();

	public PlantEquipmentCalibration getPlantEquipmentCalibrationById(Long id);

	public void deletePlantEquipmentCalibration(Long id);

	public boolean isPlantEquipmentCalibrationExit(Long id);

	public Page<PlantEquipmentCalibration> searchPlantEquipmentCalibration(Predicate predicate, int page, int size);

	public List<PlantEquipmentCalibration> getPlantEquipmentCalibrationsByPlantCode(String plantCode);
	
	 public List<PlantEquipmentCalibration> getAllPlantEquipmentCalibrationsByPlant(UserPrincipal currentUser);
}
