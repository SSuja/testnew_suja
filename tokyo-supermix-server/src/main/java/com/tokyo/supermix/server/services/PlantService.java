package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.entities.Plant;

public interface PlantService {
public void createPlant(Plant plant);
public boolean isPlantAlreadyExist(String plant);
}
