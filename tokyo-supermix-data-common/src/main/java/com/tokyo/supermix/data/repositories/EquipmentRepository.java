package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
  boolean existsByName(String name);
}
