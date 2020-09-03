package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.security.UserPrincipal;

public interface PlantService {
  public Plant savePlant(Plant plant);

  public boolean isPlantNameExist(String plant);

  public List<Plant> getAllPlants(UserPrincipal currentUser);

  public Plant getPlantByCode(String code);

  public boolean isPlantExist(String code);

  public boolean isUpdatedPlantNameExist(String code, String plantName);

  public void deletePlant(String code);

  public Page<Plant> searchPlant(Predicate predicate, int size, int page);
  
  public Plant editPlant(Plant plant);
}
