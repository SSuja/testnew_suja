package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.EmailPointsRequestDto;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.entities.TestConfigure;

public interface EmailPointsService {

  boolean isEmailPointsExist(EmailPointsRequestDto emailPointsRequestDto);

  public void createEmailPoints(EmailPoints emailPoints);

  List<EmailPoints> getAllEmailPoints();

  List<EmailPoints> getAllEmailPointsByStatus(boolean status);

  void updateEmailPointStatus(EmailPoints emailPoints);

  boolean isEmailPointIdExists(Long id);

  void createEmailPoints(TestConfigure testConfigure);

  List<EmailPoints> getAllEmailPointsByAdminStatus(boolean status);

  public void deleteByTestConfigureId(Long testConfigureId);

  void deleteByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  EmailPoints findByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  EmailPoints findByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId);
  
  public void createScheduleEmailPoints(TestConfigure testConfigure);
  
  public void updateEmailPoints(TestConfigure testConfigure);
  
  public void updateScheduleEmailPoints(TestConfigure testConfigure);
}
