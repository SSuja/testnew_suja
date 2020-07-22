package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailGroupDto;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.util.MailGroupConstance;

@Service
public class EmailGroupServiceImpl implements EmailGroupService {
  @Autowired
  private EmailGroupRepository emailGroupRepository;
  @Autowired
  EmailPointsRepository emailPointsRepository;

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
    if (emailGroup.getEmailPoints().getName() == MailGroupConstance.MIX_DESIGN_EMAIL_GROUP
        || emailGroup.getEmailPoints()
            .getName() == MailGroupConstance.PLANT_EQUIPMENT_CALIBRATION_GROUP) {
      emailGroup.setSchedule(true);
    } else {
      emailGroup.setSchedule(false);
    }
    emailGroupRepository.save(emailGroup);
  }

  @Transactional
  public void deleteEmailGroup(Long id) {
    emailGroupRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isEmailGroupExist(Long id) {
    return emailGroupRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroupsByPlantCode(String plantCode) {

    return emailGroupRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroupsByPlantCodeAndStatus(String plantCode, boolean status) {
    return emailGroupRepository.findByPlantCodeAndStatus(plantCode, status);
  }

  @Transactional
  public void updateStatus(Long emailPointsId) {
    emailGroupRepository.findByEmailPointsId(emailPointsId).forEach(emailGroup -> {
      EmailGroupDto emailGroupDto = new EmailGroupDto();
      emailGroupDto.setStatus(emailGroup.isStatus());
      emailGroupRepository.save(emailGroup);
    });
  }

  @Transactional(readOnly = true)
  public boolean isEmailPointsStatus(EmailGroupDto emailGroupDto) {
    if (emailPointsRepository.findById(emailGroupDto.getEmailPointsId()).get()
        .isActive() == emailGroupDto.isStatus()) {
      return true;
    }
    return false;
  }


}
