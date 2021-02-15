package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.EmailPointsRequestDto;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailNotificationDaysRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.data.repositories.EmailRecipientRepository;
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
  private RawMaterialService rawMaterialService;
  @Autowired
  private TestService testService;
  @Autowired
  private EmailGroupRepository emailGroupRepository;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmailRecipientRepository emailRecipientRepository;
  @Autowired
  private EmailNotificationDaysRepository emailNotificationDaysRepository;

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
    emailPoints.setTestConfigure(emailPoint.getTestConfigure());
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
  public void createEmailPoints(TestConfigure testConfigure) {
    EmailPointsRequestDto emailPointsRequestDto = new EmailPointsRequestDto();
    String testName = testService.getTestById(testConfigure.getTest().getId()).getName();
    emailPointsRequestDto.setActive(false);
    emailPointsRequestDto.setTestId(testConfigure.getTest().getId());
    emailPointsRequestDto.setAdminLevelEmailConfiguration(false);
    emailPointsRequestDto.setTestConfigureId(testConfigure.getId());
    emailPointsRequestDto.setSchedule(false);
    if (testConfigure.getRawMaterial() != null) {
      String materialName =
          rawMaterialService.getRawMaterialById(testConfigure.getRawMaterial().getId()).getName();
      emailPointsRequestDto.setName(materialName + "_" + testName);
    } else if (testConfigure.getMaterialSubCategory() != null) {
      String materialSubCategoryName = materialSubCategoryService
          .getMaterialSubCategoryById(testConfigure.getMaterialSubCategory().getId()).getName();
      emailPointsRequestDto.setName(materialSubCategoryName + "_" + testName);
    } else {
      String materialCategoryName = materialCategoryService
          .getMaterialCategoryById(testConfigure.getMaterialCategory().getId()).getName();
      emailPointsRequestDto.setName(materialCategoryName + "_" + testName);
    }
    emailPointsRequestDto.setMaterialCategoryId(testConfigure.getMaterialCategory().getId());
    emailPointsRepository.save(mapper.map(emailPointsRequestDto, EmailPoints.class));
  }

  @Transactional
  public void createScheduleEmailPoints(TestConfigure testConfigure) {
    EmailPointsRequestDto emailPointsRequestDto = new EmailPointsRequestDto();
    String testName = testService.getTestById(testConfigure.getTest().getId()).getName();
    emailPointsRequestDto.setActive(false);
    emailPointsRequestDto.setTestId(testConfigure.getTest().getId());
    emailPointsRequestDto.setAdminLevelEmailConfiguration(false);
    emailPointsRequestDto.setSchedule(true);
    emailPointsRequestDto.setTestConfigureId(testConfigure.getId());
    if (testConfigure.getRawMaterial() != null) {
      String materialName =
          rawMaterialService.getRawMaterialById(testConfigure.getRawMaterial().getId()).getName();
      emailPointsRequestDto.setName(materialName + "_" + "Schedule" + "_" + testName);
    } else if (testConfigure.getMaterialSubCategory() != null) {
      String materialSubCategoryName = materialSubCategoryService
          .getMaterialSubCategoryById(testConfigure.getMaterialSubCategory().getId()).getName();
      emailPointsRequestDto.setName(materialSubCategoryName + "_" + "Schedule" + "_" + testName);
    } else {
      String materialCategoryName = materialCategoryService
          .getMaterialCategoryById(testConfigure.getMaterialCategory().getId()).getName();
      emailPointsRequestDto.setName(materialCategoryName + "_" + "Schedule" + "_" + testName);
    }
    emailPointsRequestDto.setMaterialCategoryId(testConfigure.getMaterialCategory().getId());
    emailPointsRepository.save(mapper.map(emailPointsRequestDto, EmailPoints.class));

  }

  @Transactional(readOnly = true)
  public List<EmailPoints> getAllEmailPointsByAdminStatus(boolean status) {
    return emailPointsRepository.findByActiveAndAdminLevelEmailConfiguration(true, status);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteByTestConfigureId(Long testConfigureId) {
    emailPointsRepository.findByTestConfigureId(testConfigureId).forEach(emailpoints -> {
      emailGroupRepository.findByEmailPointsId(emailpoints.getId()).forEach(emailGroup -> {
        emailRecipientRepository.findByEmailGroupId(emailGroup.getId()).stream()
            .filter(n -> n != null)
            .forEach(emailRecipient -> emailRecipientRepository.deleteById(emailRecipient.getId()));
        emailNotificationDaysRepository.findByEmailGroupId(emailGroup.getId())
            .forEach(emailNotificationDays -> emailNotificationDaysRepository
                .deleteById(emailNotificationDays.getId()));
        emailGroupRepository.deleteById(emailGroup.getId());
      });
      emailPointsRepository.deleteById(emailpoints.getId());
    });
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

  @Transactional
  public void updateEmailPoints(TestConfigure testConfigure) {
    EmailPoints emailpoint =
        emailPointsRepository.findByTestConfigureIdAndSchedule(testConfigure.getId(), false);

    EmailPointsRequestDto emailPointsRequestDto = new EmailPointsRequestDto();
    emailPointsRequestDto.setId(emailpoint.getId());
    String testName = testConfigure.getTest().getName();
    emailPointsRequestDto.setActive(emailpoint.isActive());
    emailPointsRequestDto.setTestId(testConfigure.getTest().getId());
    emailPointsRequestDto.setAdminLevelEmailConfiguration(false);
    emailPointsRequestDto.setTestConfigureId(testConfigure.getId());
    emailPointsRequestDto.setSchedule(emailpoint.isSchedule());
    if (testConfigure.getRawMaterial() != null) {
      String materialName =
          rawMaterialService.getRawMaterialById(testConfigure.getRawMaterial().getId()).getName();
      emailPointsRequestDto.setName(materialName + "_" + testName);
    } else if (testConfigure.getMaterialSubCategory() != null) {
      String materialSubCategoryName = materialSubCategoryService
          .getMaterialSubCategoryById(testConfigure.getMaterialSubCategory().getId()).getName();
      emailPointsRequestDto.setName(materialSubCategoryName + "_" + testName);
    } else {
      String materialCategoryName = materialCategoryService
          .getMaterialCategoryById(testConfigure.getMaterialCategory().getId()).getName();
      emailPointsRequestDto.setName(materialCategoryName + "_" + testName);
    }
    emailPointsRequestDto.setMaterialCategoryId(testConfigure.getMaterialCategory().getId());
    emailPointsRepository.save(mapper.map(emailPointsRequestDto, EmailPoints.class));
  }

  @Transactional
  public void updateScheduleEmailPoints(TestConfigure testConfigure) {
    EmailPoints emailpoint =
        emailPointsRepository.findByTestConfigureIdAndSchedule(testConfigure.getId(), true);
    if (!(emailpoint == null)) {
      EmailPointsRequestDto emailPointsRequestDto = new EmailPointsRequestDto();
      emailPointsRequestDto.setId(emailpoint.getId());
      String testName = testConfigure.getTest().getName();
      emailPointsRequestDto.setActive(emailpoint.isActive());
      emailPointsRequestDto.setTestId(testConfigure.getTest().getId());
      emailPointsRequestDto.setAdminLevelEmailConfiguration(false);
      emailPointsRequestDto.setSchedule(emailpoint.isSchedule());
      emailPointsRequestDto.setTestConfigureId(testConfigure.getId());
      if (testConfigure.getRawMaterial() != null) {
        String materialName =
            rawMaterialService.getRawMaterialById(testConfigure.getRawMaterial().getId()).getName();
        emailPointsRequestDto.setName(materialName + "_" + "Schedule" + "_" + testName);
      } else if (testConfigure.getMaterialSubCategory() != null) {
        String materialSubCategoryName = materialSubCategoryService
            .getMaterialSubCategoryById(testConfigure.getMaterialSubCategory().getId()).getName();
        emailPointsRequestDto.setName(materialSubCategoryName + "_" + "Schedule" + "_" + testName);
      } else {
        String materialCategoryName = materialCategoryService
            .getMaterialCategoryById(testConfigure.getMaterialCategory().getId()).getName();
        emailPointsRequestDto.setName(materialCategoryName + "_" + "Schedule" + "_" + testName);
      }
      emailPointsRequestDto.setMaterialCategoryId(testConfigure.getMaterialCategory().getId());
      emailPointsRepository.save(mapper.map(emailPointsRequestDto, EmailPoints.class));
    } else {
      createScheduleEmailPoints(testConfigure);
    }
  }
}
