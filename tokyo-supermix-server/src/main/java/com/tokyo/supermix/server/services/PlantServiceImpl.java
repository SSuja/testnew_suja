package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.repositories.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {
	@Autowired
	private PlantRepository plantRepository;

	@Transactional
	public void savePlant(Plant plant) {
		plantRepository.save(plant);
	}

	@Transactional(readOnly = true)
	public boolean isPlantNameExist(String plant) {
		return plantRepository.existsByName(plant);
	}

	@Transactional(readOnly = true)
	public List<Plant> getAllPlants() {
		return plantRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean isPlantExist(String code) {
		return plantRepository.existsByCode(code);
	}

	@Transactional(readOnly = true)
	public Plant getPlantByCode(String code) {
		return plantRepository.findPlantByCode(code);
	}

	public boolean isUpdatedPlantNameExist(String code, String plantName) {
		if ((!getPlantByCode(code).getName().equalsIgnoreCase(plantName)) && (isPlantNameExist(plantName))) {
			return true;
		}
		return false;
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deletePlant(String code) {
	  plantRepository.deleteById(code);
	}

}
