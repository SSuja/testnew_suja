package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ConcreteMixer;

public interface ConcreteMixerRepository extends JpaRepository<ConcreteMixer, Long>{
  boolean existsByName(String name);
  List<ConcreteMixer> findByPlantCode(String plantCode);
}
