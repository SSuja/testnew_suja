package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.NotificationDays;

public interface EmailNotificationDaysRepository extends JpaRepository<NotificationDays, Long>{

  boolean existsByEmailGroupIdAndDays(Long emailGroupId, Double days);

}
