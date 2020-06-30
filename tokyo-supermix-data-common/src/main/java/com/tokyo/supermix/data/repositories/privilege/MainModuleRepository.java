package com.tokyo.supermix.data.repositories.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.MainModule;

public interface MainModuleRepository extends JpaRepository<MainModule, Long> {
  public MainModule findByName(String mainModule);
}
