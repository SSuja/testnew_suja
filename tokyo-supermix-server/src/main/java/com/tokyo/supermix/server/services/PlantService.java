package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
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

  public Long countPlant();

  public List<Plant> getAllPlantByPageable(Pageable pageable);

  public List<Plant> searchPlant(String code, String name, String address,
      String subBusinessUnitName, String phoneNumber, BooleanBuilder booleanBuilder, int page,
      int size, Pageable pageable, Pagination pagination);
}
