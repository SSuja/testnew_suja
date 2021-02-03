package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailPointsRequestDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.util.MailGroupConstance;

@Service
public class EmailPointsServiceImpl implements EmailPointsService {
  @Autowired
  private EmailPointsRepository emailPointsRepository;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private MaterialCategoryService materialCategoryService;
  @Autowired
  private TestService testService;
  @Autowired
  private EmailGroupRepository emailGroupRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public boolean isEmailPointsExist(EmailPointsRequestDto emailPointsRequestDto) {
    return emailPointsRepository.existsByMaterialSubCategoryIdAndTestId(
        emailPointsRequestDto.getMaterialSubCategoryId(), emailPointsRequestDto.getTestId());
  }

  @Transactional
  public void createEmailPoints(EmailPoints emailPoints) {
    String materialSubCategoryName = materialSubCategoryService
        .getMaterialSubCategoryById(emailPoints.getMaterialSubCategory().getId()).getName();
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
    return emailPointsRepository.findByActiveAndAdminLevelEmailConfiguration(status, false);
  }

  @Transactional
  public void updateEmailPointStatus(EmailPoints emailPoints) {
    EmailPoints emailPoint = emailPointsRepository.findById(emailPoints.getId()).get();
    emailPoints.setName(emailPoint.getName());
    emailPoints.setMaterialCategory(emailPoint.getMaterialCategory());
    emailPoints.setMaterialSubCategory(emailPoint.getMaterialSubCategory());
    emailPoints.setTest(emailPoint.getTest());
    if (emailPointsRepository.findById(emailPoints.getId()).get().getName()
        .equalsIgnoreCase(MailGroupConstance.CREATE_PLANT)
        || emailPointsRepository.findById(emailPoints.getId()).get().getName()
            .equalsIgnoreCase(MailGroupConstance.CREATE_RAW_MATERIAL)) {
      emailPoints.setAdminLevelEmailConfiguration(true);
    } else {
      emailPoints.setAdminLevelEmailConfiguration(false);
    }
    List<EmailGroup> emailGroupLists =
        emailGroupRepository.findByEmailPointsId(emailPoints.getId());
    if (emailGroupLists != null) {
      emailGroupLists.forEach(emailGroups -> {
        emailGroups.setStatus(emailPoints.isActive());
      });
    }
    emailPointsRepository.save(emailPoints);
  }

  @Transactional(readOnly = true)
  public boolean isEmailPointIdExists(Long id) {
    return emailPointsRepository.existsById(id);
  }

  @Transactional
  public void createEmailPoints(TestConfigureRequestDto testConfigureRequestDto) {
    EmailPointsRequestDto emailPointsRequestDto = new EmailPointsRequestDto();
    String testName = testService.getTestById(testConfigureRequestDto.getTestId()).getName();
    emailPointsRequestDto.setActive(testConfigureRequestDto.isActive());
    emailPointsRequestDto.setTestId(testConfigureRequestDto.getTestId());
    emailPointsRequestDto.setAdminLevelEmailConfiguration(false);
    String materialCategoryName = materialCategoryService
          .getMaterialCategoryById(testConfigureRequestDto.getMaterialCategoryId()).getName();
      emailPointsRequestDto.setName(materialCategoryName + "_" + testName);
      emailPointsRequestDto.setMaterialCategoryId(testConfigureRequestDto.getMaterialCategoryId());

    emailPointsRepository.save(mapper.map(emailPointsRequestDto, EmailPoints.class));
  }
  
  @Transactional
  public void createScheduleEmailPoints(TestConfigureRequestDto testConfigureRequestDto) {
    EmailPointsRequestDto emailPointsRequestDto = new EmailPointsRequestDto();
    String testName = testService.getTestById(testConfigureRequestDto.getTestId()).getName();
    emailPointsRequestDto.setActive(testConfigureRequestDto.isActive());
    emailPointsRequestDto.setTestId(testConfigureRequestDto.getTestId());
    emailPointsRequestDto.setAdminLevelEmailConfiguration(false);
    emailPointsRequestDto.setSchedule(true);  
    String materialCategoryName = materialCategoryService
          .getMaterialCategoryById(testConfigureRequestDto.getMaterialCategoryId()).getName();
    emailPointsRequestDto.setName(materialCategoryName +"_"  +"Schedule"+ "_"+ testName);
    emailPointsRequestDto.setMaterialCategoryId(testConfigureRequestDto.getMaterialCategoryId());
    
    emailPointsRepository.save(mapper.map(emailPointsRequestDto, EmailPoints.class));
  }

  @Transactional(readOnly = true)
  public List<EmailPoints> getAllEmailPointsByAdminStatus(boolean status) {
    return emailPointsRepository.findByActiveAndAdminLevelEmailConfiguration(true, status);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId) {
    Long emailPointId = emailPointsRepository
        .findByMaterialSubCategoryIdAndTestId(materialSubCategoryId, testId).getId();
    emailPointsRepository.deleteById(emailPointId);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId) {
    Long emailPointId =
        emailPointsRepository.findByMaterialCategoryIdAndTestId(materialCategoryId, testId).getId();
    emailPointsRepository.deleteById(emailPointId);
  }

  @Transactional(readOnly = true)
  public EmailPoints findByTestIdAndMaterialCategoryId(Long testId, Long materialCategoryId) {
    return emailPointsRepository.findByTestIdAndMaterialCategoryId(testId, materialCategoryId);
  }

  @Transactional(readOnly = true)
  public EmailPoints findByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId) {
    return emailPointsRepository.findByTestIdAndMaterialSubCategoryId(testId,
        materialSubCategoryId);
  }
}
