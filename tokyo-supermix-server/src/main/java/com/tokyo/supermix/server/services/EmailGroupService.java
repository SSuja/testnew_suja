package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.enums.EmailNotifications;

public interface EmailGroupService {
	public List<EmailGroup> getAllEmailGroups();

	public List<EmailGroup> getAllEmailGroupsBySchedule(Boolean schedule);

	public void saveEmailGroup(EmailGroup emailGroup);

	public void deleteEmailGroup(Long id);
	
	public boolean isEmailGroupNameExist(EmailNotifications emailNotifications);
	
	public boolean isEmailGroupExist(Long id);

}
