package com.tokyo.supermix.data.repositories.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.MainRoute;

public interface MainRouteRepository extends JpaRepository<MainRoute, Long> {
  public MainRoute findByName(String mainRoute);
}
