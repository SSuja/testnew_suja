package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailPointsRequestDto;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;

@Service
public class EmailPointsServiceImpl implements EmailPointsService {
  @Autowired
  private EmailPointsRepository emailPointsRepository;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService ;
  @Autowired
  private TestService testService;

  @Transactional(readOnly = true)
  public boolean isEmailPointsExist(EmailPointsRequestDto emailPointsRequestDto) {
    return emailPointsRepository.existsByMaterialSubCategoryIdAndTestId(emailPointsRequestDto.getMaterialSubCategoryId(),emailPointsRequestDto.getTestId());
  }

  @Transactional
  public void createEmailPoints(EmailPoints emailPoints) {
    String materialSubCategoryName =  materialSubCategoryService.getMaterialSubCategoryById(emailPoints.getMaterialSubCategory().getId()).getName();
    String testName = testService.getTestById(emailPoints.getTest().getId()).getName();
    emailPoints.setName(materialSubCategoryName + "_" + testName);
    emailPoints.setActive(true);
    emailPointsRepository.save(emailPoints); 
  }

  @Transactional(readOnly = true)
  public List<EmailPoints> getAllEmailPoints() {
    return emailPointsRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<EmailPoints> getAllEmailPointsByStatus(boolean status) {
    return emailPointsRepository.findByActive(status);
  }

  @Transactional
  public void updateEmailPointStatus(EmailPoints emailPoints) {
   emailPoints.setName(emailPointsRepository.findById(emailPoints.getId()).get().getName());
    emailPointsRepository.save(emailPoints);
  }

  @Transactional(readOnly = true)
  public boolean isEmailPointIdExists(Long id) {
    return emailPointsRepository.existsById(id);
  }
}
