package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailRecipientRequestDto;
import com.tokyo.supermix.data.dto.EmailRecipientResponseDto;
import com.tokyo.supermix.data.entities.EmailRecipient;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;
import com.tokyo.supermix.data.enums.RecipientType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.EmailRecipientRepository;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;

@Service
public class EmailRecipientServiceImpl implements EmailRecipientService {
  @Autowired
  private EmailRecipientRepository emailRecipientRepository;
  @Autowired
  private Mapper mapper;
  @Autowired
  UserPlantRoleRepository userPlantRoleRepository;

  @Transactional
  public boolean createEmailRecipient(EmailRecipientRequestDto emailRecipientRequestDto) {
    if (emailRecipientRequestDto.getPlantRoleId() != null) {
      for (Long plantRoleId : emailRecipientRequestDto.getPlantRoleId()) {
        EmailRecipientResponseDto emailRecipientRequestDtoObj = new EmailRecipientResponseDto();
        emailRecipientRequestDtoObj.setPlantRoleId(plantRoleId);
        emailRecipientRequestDtoObj.setEmailGroupId(emailRecipientRequestDto.getEmailGroupId());
        emailRecipientRequestDtoObj.setRecipientType(emailRecipientRequestDto.getRecipientType());
        emailRecipientRequestDtoObj.setPlantCode(emailRecipientRequestDto.getPlantCode());
        emailRecipientRepository.save(mapper.map(emailRecipientRequestDtoObj, EmailRecipient.class));
      }
    }
    if (emailRecipientRequestDto.getUserId() != null) {
      for (Long userId : emailRecipientRequestDto.getUserId()) {
        EmailRecipientResponseDto emailRecipientRequestDtoObj = new EmailRecipientResponseDto();
        emailRecipientRequestDtoObj.setUserId(userId);
        emailRecipientRequestDtoObj.setEmailGroupId(emailRecipientRequestDto.getEmailGroupId());
        emailRecipientRequestDtoObj.setRecipientType(emailRecipientRequestDto.getRecipientType());
        emailRecipientRequestDtoObj.setPlantCode(emailRecipientRequestDto.getPlantCode());
        emailRecipientRepository.save(mapper.map(emailRecipientRequestDtoObj, EmailRecipient.class));
      }
    }
    return false;
  }

  public boolean isDuplicateDataExists(EmailRecipientRequestDto emailRecipientRequestDto) {
    if (emailRecipientRequestDto.getPlantRoleId() != null) {
      for (Long plantRoleId : emailRecipientRequestDto.getPlantRoleId()) {
        if (emailRecipientRepository
            .existsByEmailGroupIdAndPlantRoleId(emailRecipientRequestDto.getEmailGroupId(), plantRoleId)) {
          return true;
        }
      }
    }
    if (emailRecipientRequestDto.getUserId() != null) {
      for (Long userId : emailRecipientRequestDto.getUserId()) {
        if (emailRecipientRepository
            .existsByEmailGroupIdAndUserId(emailRecipientRequestDto.getEmailGroupId(), userId)) {
          return true;
        }
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<String> getEmailsByEmailGroupNameAndPlantCode(String emailGroupName,
      String plantCode) {
    List<EmailRecipient> emailRecipientList =
        emailRecipientRepository.findByEmailGroupNameAndPlantCode(emailGroupName, plantCode);
    List<String> emaillist = new ArrayList<String>();
    emailRecipientList.forEach(emailRecipient -> {
      if (emailRecipient.getPlantRole() != null) {
        List<UserPlantRole> userPlantRoleList =
            userPlantRoleRepository.findByPlantRoleId(emailRecipient.getPlantRole().getId());
        userPlantRoleList.forEach(userPlantRole -> {
          emaillist.add(userPlantRole.getUser().getEmployee().getEmail());
        });
      }
      if (emailRecipient.getUser() != null) {
        emaillist.add(emailRecipient.getUser().getEmployee().getEmail());
      }
    });
    return emaillist;
  }

  @Transactional(readOnly = true)
  public boolean isEmailRecipientExist(Long id) {
    return emailRecipientRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<EmailRecipientResponseDto> getEmailRecipientByRecipient(Long emailGroupId,
      RecipientType recipientType) {
    List<EmailRecipient> emailRecipientList =
        emailRecipientRepository.findByEmailGroupIdAndRecipientType(emailGroupId, recipientType);
    return mapper.map(emailRecipientList, EmailRecipientResponseDto.class);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEmailRecipient(Long id) {
    emailRecipientRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<EmailRecipient> getEmailRecipient() {
    return emailRecipientRepository.findAll();
  }
}
