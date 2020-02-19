package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Plant;

public interface PlantService {
  public void savePlant(Plant plant);
  public boolean isPlantNameExist(String plant);
  public List<Plant> getAllPlants();
  public Plant getByCode(String code);
  public boolean isPlantExist(String code);
  public boolean isUpdatedPlantNameExist( String code,String plantName);
}
