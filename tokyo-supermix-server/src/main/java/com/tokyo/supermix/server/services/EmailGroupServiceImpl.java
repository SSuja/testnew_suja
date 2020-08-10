package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailGroupDto;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.util.MailGroupConstance;

@Service
public class EmailGroupServiceImpl implements EmailGroupService {
  @Autowired
  private EmailGroupRepository emailGroupRepository;
  @Autowired
  EmailPointsRepository emailPointsRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroups() {
    return emailGroupRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroupsBySchedule(Boolean schedule) {
    return emailGroupRepository.findBySchedule(schedule);
  }

  @Transactional
  public void saveEmailGroup(EmailGroupDto emailGroupDto) {
    boolean status = (emailPointsRepository.findById(emailGroupDto.getEmailPointsId()).get()
        .getName().equalsIgnoreCase(MailGroupConstance.MIX_DESIGN_EMAIL_GROUP)
        || emailPointsRepository.findById(emailGroupDto.getEmailPointsId()).get().getName()
            .equalsIgnoreCase(MailGroupConstance.PLANT_EQUIPMENT_CALIBRATION_GROUP)) ? true : false;
    emailGroupDto.setSchedule(status);
    emailGroupRepository.save(mapper.map(emailGroupDto, EmailGroup.class));
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

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroupsByPlantCodeAndStatusAndSchedule(String plantCode,
      boolean status, boolean schedule) {
    return emailGroupRepository.findByPlantCodeAndStatusAndSchedule(plantCode, status, schedule);
  }

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroupsByPlantCodeAndAdminStatus(String plantCode,
      Boolean adminStatus) {
    return emailGroupRepository.findByPlantCodeAndEmailPointsAdminLevelEmailConfiguration(plantCode,
        adminStatus);
  }

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroupsByAdminStatus(Boolean adminStatus) {
    return emailGroupRepository.findByEmailPointsAdminLevelEmailConfiguration(adminStatus);
  }

  @Override
  public boolean isEmailGroupNameAndPlantCode(String name, String plantCode) {
    return emailGroupRepository.existsByNameAndPlantCode(name, plantCode);
  }

  @Override
  public boolean isEmailGroupName(String name) {
    return emailGroupRepository.existsByName(name);
  }
}
