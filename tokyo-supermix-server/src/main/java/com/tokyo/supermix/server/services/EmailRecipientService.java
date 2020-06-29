package com.tokyo.supermix.server.services;

import java.util.List;

public interface EmailRecipientService {
  
  public List<String> getEmailById(Long emailGroupId);

}
