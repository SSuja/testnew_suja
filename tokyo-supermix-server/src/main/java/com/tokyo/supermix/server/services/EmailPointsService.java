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

  List<EmailPoints> getAllEmailPointsByAdminStatus(boolean status);

  void deleteByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId);

  void deleteByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  EmailPoints findByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId);

  EmailPoints findByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId);
}
