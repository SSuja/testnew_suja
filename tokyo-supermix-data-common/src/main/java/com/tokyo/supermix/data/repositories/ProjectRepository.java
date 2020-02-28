package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
  boolean existsByName(String name);
}
