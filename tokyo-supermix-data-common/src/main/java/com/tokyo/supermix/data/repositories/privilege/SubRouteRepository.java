package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.SubRoute;

public interface SubRouteRepository extends JpaRepository<SubRoute, Long>{
  List<SubRoute> findByMainRouteId(Long mainRouteId);

}