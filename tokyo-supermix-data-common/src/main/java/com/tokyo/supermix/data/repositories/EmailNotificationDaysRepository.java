package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.NotificationDays;

public interface EmailNotificationDaysRepository extends JpaRepository<NotificationDays, Long>{

  boolean existsByEmailGroupIdAndDays(Long emailGroupId, Double days);

  List<NotificationDays> getByEmailGroupName(String emailGroupName);

  List<NotificationDays> findByEmailGroupId(Long id);
  

}