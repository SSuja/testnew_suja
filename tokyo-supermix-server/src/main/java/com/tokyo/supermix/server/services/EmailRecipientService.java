package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.EmailRecipientDto;

public interface EmailRecipientService {
	public boolean createEmailRecipient(EmailRecipientDto emailRecipientDto);

	public boolean isDuplicateDataExists(EmailRecipientDto emailRecipientDto);
}
