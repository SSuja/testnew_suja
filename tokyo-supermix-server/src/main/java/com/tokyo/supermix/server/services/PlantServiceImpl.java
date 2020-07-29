package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.notification.EmailNotification;

@Service
public class PlantServiceImpl implements PlantService {
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional
  public Plant savePlant(Plant plant) { 
    Plant plantObj = plantRepository.save(plant);
    if (plantObj != null) {
      emailNotification.sendPlantCreationEmail(plantObj);
    }
    return plantRepository.save(plant);
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
    if ((!getPlantByCode(code).getName().equalsIgnoreCase(plantName))
        && (isPlantNameExist(plantName))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deletePlant(String code) {
    plantRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public Page<Plant> searchPlant(Predicate predicate, int size, int page) {
    return plantRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }

}
