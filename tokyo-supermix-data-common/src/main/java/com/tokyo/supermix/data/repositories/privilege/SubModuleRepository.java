package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.SubModule;

public interface SubModuleRepository extends JpaRepository<SubModule, Long>{
  List<SubModule> findByMainModuleId(Long mainModuleId);
  SubModule findByName(String name);
}