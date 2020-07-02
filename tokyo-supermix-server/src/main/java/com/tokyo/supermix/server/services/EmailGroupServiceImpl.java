package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;

@Service
public class EmailGroupServiceImpl implements EmailGroupService {

  @Autowired
  private EmailGroupRepository emailGroupRepository;

  @Transactional(readOnly = true)
  public List<EmailGroup> getAllEmailGroups() {
    return emailGroupRepository.findAll();
  }

}
