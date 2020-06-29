package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.EmailRecipientDto;
import com.tokyo.supermix.data.dto.EmailRecipientRequestDto;
import com.tokyo.supermix.data.enums.RecipientType;

public interface EmailRecipientService {
  public boolean createEmailRecipient(EmailRecipientDto emailRecipientDto);

  public boolean isDuplicateDataExists(EmailRecipientDto emailRecipientDto);

  public List<String> getEmailById(Long emailGroupId);

  public List<EmailRecipientRequestDto> getEmailRecipient(Long emailGroupId, RecipientType recipientType);

	public boolean isEmailRecipientExist(Long id);

	public void deleteEmailRecipient(Long id);
}
