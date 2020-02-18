package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.repositories.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {
  @Autowired
  private PlantRepository plantRepository;

  @Transactional
  public void createPlant(Plant plant) {
    plantRepository.save(plant);
  }


  @Transactional(readOnly = true)
  public boolean isPlantAlreadyExist(String plant) {
    return plantRepository.existsByName(plant);
  }


  @Transactional(readOnly = true)
  public List<Plant> getAllPlants() {
    return plantRepository.findAll();
  }

}
