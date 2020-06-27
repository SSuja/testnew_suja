package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.ConcreteMixer;

public interface ConcreteMixerRepository
    extends JpaRepository<ConcreteMixer, Long>, QuerydslPredicateExecutor<ConcreteMixer> {
  List<ConcreteMixer> findByPlantCode(String plantCode);
  List<ConcreteMixer> findByPlantCodeIn(List<String> plantCodes);
  boolean existsByNameAndPlantCode(String name, String plantCode);
}
