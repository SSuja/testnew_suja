package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.EmailGroup;

@Repository
public interface EmailGroupRepository extends JpaRepository<EmailGroup, Long> {
  List<EmailGroup> findBySchedule(Boolean schedule);
  List<EmailGroup> findByPlantCode(String plantCode);
  List<EmailGroup> findByPlantCodeAndStatus(String plantCode, boolean status);
  List<EmailGroup> findByEmailPointsId(Long emailPointsId);
  EmailGroup findByPlantCodeAndEmailPointsName(String plantCode, String name);
//boolean existsByEmailNotifications(EmailNotifications emailNotifications);
  EmailGroup findByEmailPointsName(String name);
  List<EmailGroup> findByPlantCodeAndStatusAndSchedule(String plantCode, boolean status,
      boolean schedule);
}
