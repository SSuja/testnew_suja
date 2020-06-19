package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.privilege.PlantAccessLevel;

@Repository
public interface PlantAccessLevelRepository extends JpaRepository<PlantAccessLevel , Long>{

  public boolean existsByPlantCodeAndPlantRoleId(String plantCode, Long plantRoleId);

  public boolean existsByPlantCodeAndStatus(String plantCode, boolean status);

  public List<PlantAccessLevel> findByPlantCodeAndStatus(String plantCode, boolean status);

  public PlantAccessLevel findByPlantCodeAndPlantRoleId(String plantCode, Long plantRoleId);

}
