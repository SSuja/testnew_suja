package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.EmailRecipient;
import com.tokyo.supermix.data.enums.RecipientType;

@Repository
public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {
  boolean existsByEmailGroupIdAndPlantRoleId(Long emailGroupId, Long plantRoleId);

  boolean existsByEmailGroupIdAndUserId(Long emailGroupId, Long userId);

  List<EmailRecipient> findByEmailGroupId(Long emailGroupId);

  List<EmailRecipient> findByPlantRoleId(Long plantRoleId);
  
  List<EmailRecipient> findByEmailGroupIdAndRecipientType(Long emailGroupId, RecipientType recipientType);
}
