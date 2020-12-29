package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.Plant;


public interface PlantRepository
    extends JpaRepository<Plant, String>, QuerydslPredicateExecutor<Plant> {
  boolean existsByName(String name);

  boolean existsByCode(String code);

  Plant findPlantByCode(String code);

  List<Plant> findByCodeIn(List<String> plantCodes);

  Plant findByName(String name);


  List<Plant> findBySubBusinessUnitId(Long subUnitId);
}
