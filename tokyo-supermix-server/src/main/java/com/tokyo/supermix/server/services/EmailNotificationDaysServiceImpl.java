package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.NotificationDaysResponseDto;
import com.tokyo.supermix.data.entities.EmailGroup;
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
  public List<NotificationDaysResponseDto> getAllEmailNotificationDaysByGroup() {
    List<NotificationDays> notificationDaysList =  emailNotificationDaysRepository.findAll();
    Set<EmailGroup> em=new HashSet<>();
    for(NotificationDays notificationDay: notificationDaysList) {
     em.add(notificationDay.getEmailGroup());
    }
    List<NotificationDaysResponseDto> notificationDays = new ArrayList<NotificationDaysResponseDto>();
    NotificationDaysResponseDto notificationDaysResponseDto= new NotificationDaysResponseDto();
    List<Double> days=new ArrayList<>();
    em.forEach(email ->{
            emailNotificationDaysRepository.findByEmailGroupId(email.getId()).forEach(day ->{
              days.add(day.getDays());          
            });;
            notificationDaysResponseDto.setEmailGroupName(email.getName());
    });
    notificationDaysResponseDto.setDays(days);
    notificationDays.add(notificationDaysResponseDto);
    return notificationDays;
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateExists(Long emailGroupId, Double days) {
    if(emailNotificationDaysRepository.existsByEmailGroupIdAndDays(emailGroupId, days)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<NotificationDays> getByEmailGroupName(String emailGroupName) {
    return emailNotificationDaysRepository.getByEmailGroupName(emailGroupName);
  }

  @Transactional(readOnly = true)
  public List<NotificationDays> getAllEmailNotificationDays() {
    return emailNotificationDaysRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isEmailNotificationDaysExist(Long id) {
    return emailNotificationDaysRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEmailNotificationDays(Long id) {
    emailNotificationDaysRepository.deleteById(id);
  }
}
