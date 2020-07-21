package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.EmailPoints;

@Repository
public interface EmailPointsRepository extends JpaRepository<EmailPoints, Long>{

  boolean existsByMaterialSubCategoryIdAndTestId(Long materialSubCategoryId, Long testId);

  List<EmailPoints> findByActive(boolean status);
  
  EmailPoints findByMaterialSubCategoryIdAndTestId(Long materialSubCategoryId, Long testId);

}
