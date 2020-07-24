package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.EmailGroupDto;
import com.tokyo.supermix.data.entities.EmailGroup;

public interface EmailGroupService {
  public List<EmailGroup> getAllEmailGroups();

  public List<EmailGroup> getAllEmailGroupsBySchedule(Boolean schedule);

  public List<EmailGroup> getAllEmailGroupsByPlantCode(String plantCode);

  public List<EmailGroup> getAllEmailGroupsByPlantCodeAndStatus(String plantCode, boolean status);

  public void saveEmailGroup(EmailGroupDto emailGroupDto);

  public void deleteEmailGroup(Long id);

  //public boolean isEmailGroupNameExist(EmailNotifications emailNotifications);

  public boolean isEmailGroupExist(Long id);
  
  public void updateStatus(Long emailPointsId);

  public boolean isEmailPointsStatus(EmailGroupDto emailGroupDto);

  public List<EmailGroup> getAllEmailGroupsByPlantCodeAndStatusAndSchedule(String plantCode, boolean status,
      boolean schedule);
  
  public List<EmailGroup> getAllEmailGroupsByAdminStatus(Boolean adminStatus);

}
