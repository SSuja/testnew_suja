package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailRecipientDto;
import com.tokyo.supermix.data.dto.EmailRecipientRequestDto;
import com.tokyo.supermix.data.entities.EmailRecipient;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;
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
  public boolean createEmailRecipient(EmailRecipientDto emailRecipientDto) {
    if (emailRecipientDto.getPlantRoleId() != null) {
      for (Long plantRoleId : emailRecipientDto.getPlantRoleId()) {
        EmailRecipientRequestDto emailRecipientRequestDto = new EmailRecipientRequestDto();
        emailRecipientRequestDto.setPlantRoleId(plantRoleId);
        emailRecipientRequestDto.setEmailGroupId(emailRecipientDto.getEmailGroupId());
        emailRecipientRequestDto.setRecipientType(emailRecipientDto.getRecipientType());
        emailRecipientRepository.save(mapper.map(emailRecipientRequestDto, EmailRecipient.class));
      }
    }
    if (emailRecipientDto.getUserId() != null) {
      for (Long userId : emailRecipientDto.getUserId()) {
        EmailRecipientRequestDto emailRecipientRequestDto = new EmailRecipientRequestDto();
        emailRecipientRequestDto.setUserId(userId);
        emailRecipientRequestDto.setEmailGroupId(emailRecipientDto.getEmailGroupId());
        emailRecipientRequestDto.setRecipientType(emailRecipientDto.getRecipientType());
        emailRecipientRepository.save(mapper.map(emailRecipientRequestDto, EmailRecipient.class));
      }
    }
    return false;
  }

  public boolean isDuplicateDataExists(EmailRecipientDto emailRecipientDto) {
    if (emailRecipientDto.getPlantRoleId() != null) {
      for (Long plantRoleId : emailRecipientDto.getPlantRoleId()) {
        if (emailRecipientRepository
            .existsByEmailGroupIdAndPlantRoleId(emailRecipientDto.getEmailGroupId(), plantRoleId)) {
          return true;
        }
      }
    }
    if (emailRecipientDto.getUserId() != null) {
      for (Long userId : emailRecipientDto.getUserId()) {
        if (emailRecipientRepository
            .existsByEmailGroupIdAndUserId(emailRecipientDto.getEmailGroupId(), userId)) {
          return true;
        }
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<String> getEmailsByEmailGroupIdAndPlantCode(Long emailGroupId, String plantCode) {
    List<EmailRecipient> emailRecipientList =
        emailRecipientRepository.findByEmailGroupIdAndPlantCode(emailGroupId, plantCode);
    List<String> emaillist = new ArrayList<String>();
    emailRecipientList.forEach(emailRecipient -> {
      if (emailRecipient.getPlantRole() != null) {
        List<UserPlantRole> userPlantRoleList =
            userPlantRoleRepository.findByPlantRoleId(emailRecipient.getPlantRole().getId());
        userPlantRoleList.forEach(userPlantRole -> {

          emaillist.add(userPlantRole.getUser().getEmployee().getEmail());
          System.out.println("email 1 "+emaillist.toString());
        });
      }
      if (emailRecipient.getUser() != null) {
        emaillist.add(emailRecipient.getUser().getEmployee().getEmail());
        System.out.println("email 2 "+emaillist.toString());
      }
    });
    return emaillist;
  }

  @Transactional(readOnly = true)
  public boolean isEmailRecipientExist(Long id) {
    return emailRecipientRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEmailRecipient(Long id) {
    emailRecipientRepository.deleteById(id);
  }
}
