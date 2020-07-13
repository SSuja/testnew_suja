package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.enums.EmailNotifications;

public interface EmailNotificationDaysRepository extends JpaRepository<NotificationDays, Long>{

  boolean existsByEmailGroupIdAndDays(Long emailGroupId, Double days);

  List<NotificationDays> findByEmailGroupEmailNotifications(EmailNotifications emailNotifications);

  List<NotificationDays> findByEmailGroupId(Long id);
  

}
