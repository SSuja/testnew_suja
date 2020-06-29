package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.EmailRecipient;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;
import com.tokyo.supermix.data.repositories.EmailRecipientRepository;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;

@Service
public class EmailRecipientServiceImpl implements EmailRecipientService {

  @Autowired
  EmailRecipientRepository emailRecipientRepository;
  @Autowired
  UserPlantRoleRepository userPlantRoleRepository;

  @Override
  public List<String> getEmailById(Long emailGroupId) {

    List<EmailRecipient> emailRecipientList =
        emailRecipientRepository.findByEmailGroupId(emailGroupId);
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

}
