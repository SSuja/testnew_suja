package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.NotificationDaysResponseDto;
import com.tokyo.supermix.data.entities.NotificationDays;

public interface EmailNotificationDaysService {

  public void createEmailNotification(NotificationDays notificationDays);

  public List<NotificationDaysResponseDto> getAllEmailNotificationDays();

  public boolean isDuplicateExists(Long emailGroupId, Double days);
  
  public List<NotificationDays> getByEmailGroupName(String emailGroupName);
}
