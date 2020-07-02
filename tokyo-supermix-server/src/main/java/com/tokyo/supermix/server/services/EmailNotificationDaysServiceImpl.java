package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.repositories.EmailNotificationDaysRepository;

@Service
public class EmailNotificationDaysServiceImpl implements EmailNotificationDaysService {
  @Autowired
  private EmailNotificationDaysRepository emailNotificationDaysRepository;
  @Transactional
  public void createEmailNotification(NotificationDays notificationDays) {
    emailNotificationDaysRepository.save(notificationDays);
  }

  @Transactional(readOnly = true)
  public List<NotificationDays> getAllEmailNotificationDays() {
    return emailNotificationDaysRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateExists(Long emailGroupId, Double days) {
    if(emailNotificationDaysRepository.existsByEmailGroupIdAndDays(emailGroupId, days)) {
      return true;
    }
    return false;
  }
}
