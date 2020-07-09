package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.enums.EmailNotifications;

@Repository
public interface EmailGroupRepository extends JpaRepository<EmailGroup, Long> {
  List<EmailGroup> findBySchedule(Boolean schedule);
  boolean existsByEmailNotifications(EmailNotifications emailNotifications);
}
