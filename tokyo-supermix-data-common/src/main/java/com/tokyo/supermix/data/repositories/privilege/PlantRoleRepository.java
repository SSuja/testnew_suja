package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

@Repository
public interface PlantRoleRepository
    extends JpaRepository<PlantRole, Long>, QuerydslPredicateExecutor<PlantRole> {
  boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId);

  List<PlantRole> findByRoleName(String roleName);

  List<PlantRole> findByPlantCode(String plantCode);

  boolean existsByPlantCode(String plantCode);

  List<PlantRole> findAllByPlantCode(String plantCode, Pageable pageable);

  Long countByPlantCode(String plantCode);

  Page<PlantRole> findAll(Pageable pageable);
}
