package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Plant;

public interface PlantService {
  public void createPlant(Plant plant);
  public boolean isPlantAlreadyExist(String plant);
  public List<Plant> getAllPlants();
  public Plant getByCode(String code);
  public boolean isPlantExist(String code);
  public Plant updatePlant(Plant plant);
}
