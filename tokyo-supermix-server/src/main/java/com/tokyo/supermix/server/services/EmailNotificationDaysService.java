package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.NotificationDaysResponseDto;
import com.tokyo.supermix.data.entities.NotificationDays;

public interface EmailNotificationDaysService {

  public void createEmailNotification(NotificationDays notificationDays);

  public List<NotificationDays> getAllEmailNotificationDays();

  public boolean isDuplicateExists(Long emailGroupId, Double days);
  
  public List<NotificationDays> getByEmailGroup(String name, String plantCode);

  public  List<NotificationDaysResponseDto> getAllEmailNotificationDaysByGroup();

  public boolean isEmailNotificationDaysExist(Long id);

  public void deleteEmailNotificationDays(Long id);
  
  public List<NotificationDays> getByPlantCode(String plantCode);

}
