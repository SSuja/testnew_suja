package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ConcreteTestElement;

public interface ConcreteTestElementRepository extends JpaRepository<ConcreteTestElement, Long> {
  boolean existsByName(String name);
  boolean existsByAbbreviation(String abbreviation);
}
