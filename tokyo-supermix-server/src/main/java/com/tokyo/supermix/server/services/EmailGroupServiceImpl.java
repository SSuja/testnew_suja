package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.enums.EmailNotifications;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;

@Service
public class EmailGroupServiceImpl implements EmailGroupService {
	@Autowired
	private EmailGroupRepository emailGroupRepository;

	@Transactional(readOnly = true)
	public List<EmailGroup> getAllEmailGroups() {
		return emailGroupRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<EmailGroup> getAllEmailGroupsBySchedule(Boolean schedule) {
		return emailGroupRepository.findBySchedule(schedule);
	}

	@Transactional
	public void saveEmailGroup(EmailGroup emailGroup) {

		emailGroupRepository.save(emailGroup);
	}

	
	@Transactional
	public void deleteEmailGroup(Long id) {
		emailGroupRepository.deleteById(id);
	}
	

	@Override
	public boolean isEmailGroupNameExist(EmailNotifications emailNotifications) {
		
		return emailGroupRepository.existsByEmailGroupName(emailNotifications);
	}

	@Transactional(readOnly = true)
	public boolean isEmailGroupExist(Long id) {
		
		return emailGroupRepository.existsById(id);
	}
}
