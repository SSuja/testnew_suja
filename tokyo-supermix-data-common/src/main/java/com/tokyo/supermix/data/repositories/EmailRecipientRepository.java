package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.EmailRecipient;

@Repository
public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {
  boolean existsByEmailGroupIdAndPlantRoleId(Long emailGroupId, Long plantRoleId);

  boolean existsByEmailGroupIdAndUserId(Long emailGroupId, Long userId);
}
