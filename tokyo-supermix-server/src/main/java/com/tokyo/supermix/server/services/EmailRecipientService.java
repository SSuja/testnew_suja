package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.dto.EmailRecipientDto;

public interface EmailRecipientService {
	public boolean createEmailRecipient(EmailRecipientDto emailRecipientDto);

	public boolean isDuplicateDataExists(EmailRecipientDto emailRecipientDto);

	public List<String> getEmailsByEmailGroupIdAndPlantCode(Long emailGroupId, String plantCode);

	public boolean isEmailRecipientExist(Long id);

	public void deleteEmailRecipient(Long id);
}
