package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.EmailRecipientRequestDto;
import com.tokyo.supermix.data.dto.EmailRecipientResponseDto;
import com.tokyo.supermix.data.entities.EmailRecipient;
import com.tokyo.supermix.data.enums.RecipientType;

public interface EmailRecipientService {
  public boolean createEmailRecipient(EmailRecipientRequestDto emailRecipientRequestDto);

  public boolean isDuplicateDataExists(EmailRecipientRequestDto emailRecipientRequestDto);

  public List<String> getEmailsByEmailNotificationAndPlantCode(String name,
      String plantCode);

  public List<EmailRecipientResponseDto> getEmailRecipientByRecipient(Long emailGroupId,
      RecipientType recipientType);

  public boolean isEmailRecipientExist(Long id);

  public void deleteEmailRecipient(Long id);

  public List<EmailRecipient> getEmailRecipient();
}
