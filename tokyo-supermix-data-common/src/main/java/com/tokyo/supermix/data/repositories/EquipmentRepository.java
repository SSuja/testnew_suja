package com.tokyo.supermix.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>,
    QuerydslPredicateExecutor<Equipment>, PagingAndSortingRepository<Equipment, Long> {
  boolean existsByName(String name);

  public Equipment findByName(String name);

  Page<Equipment> findAllByOrderByIdDesc(Pageable pegeable);
}
