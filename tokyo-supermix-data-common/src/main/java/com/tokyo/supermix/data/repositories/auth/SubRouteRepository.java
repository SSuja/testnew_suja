package com.tokyo.supermix.data.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.SubRoute;

public interface SubRouteRepository extends JpaRepository<SubRoute, Long>{
}
