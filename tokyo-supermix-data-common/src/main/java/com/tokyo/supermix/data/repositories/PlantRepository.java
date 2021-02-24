package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.tokyo.supermix.data.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, String>,
    QuerydslPredicateExecutor<Plant>, PagingAndSortingRepository<Plant, String> {
  boolean existsByName(String name);

  boolean existsByCode(String code);

  Plant findPlantByCode(String code);

  List<Plant> findByCodeIn(List<String> plantCodes);

  Plant findByName(String name);

  List<Plant> findBySubBusinessUnitId(Long subUnitId);

  List<Plant> findBySentMail(boolean sentMail);

  Page<Plant> findAllByOrderByUpdatedAtDesc(Pageable pegeable);
}
