package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.EmailPointsRequestDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.entities.EmailPoints;

public interface EmailPointsService {

  boolean isEmailPointsExist(EmailPointsRequestDto emailPointsRequestDto);

  public void createEmailPoints(EmailPoints emailPoints);

  List<EmailPoints> getAllEmailPoints();

  List<EmailPoints> getAllEmailPointsByStatus(boolean status);

  void updateEmailPointStatus(EmailPoints emailPoints);

  boolean isEmailPointIdExists(Long id);

  void createEmailPoints(TestConfigureRequestDto testConfigureRequestDto);
}
