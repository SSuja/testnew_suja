package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.QMaterialTest;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.SieveTestRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestTypeRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class MaterialTestServiceImpl implements MaterialTestService {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private SieveTestRepository sieveTestRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private TestTypeRepository testTypeRepository;

  @Transactional
  public void saveMaterialTest(MaterialTest materialTest) {
    materialTest.setStatus(Status.NEW);
    materialTestRepository.save(materialTest);
  }

  @Transactional(readOnly = true)
  public MaterialTest getMaterialTestByCode(String code) {
    return materialTestRepository.findByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getAllMaterialTests() {
    return materialTestRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialTest(String code) {
    materialTestRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestExists(String code) {
    return materialTestRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByStatus(String status) {
    return materialTestRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByTestConfigure(Long testConfigureId) {
    return materialTestRepository.findByTestConfigure(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestStatusExists(String status) {
    return materialTestRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestByTestConfigureExists(Long testConfigureId) {
    return materialTestRepository.existsByTestConfigure(testConfigureId);
  }

  public void updateIncomingSampleStatusByIncomingSample(IncomingSample incomingSample) {
    String bodyMessage = "";
    Integer count = 0;
    Integer passCount = 0;
    List<MaterialTest> materialTestList =
        materialTestRepository.findByIncomingSampleCode(incomingSample.getCode());
    List<TestConfigure> testConfigureList = testConfigureRepository
        .findByTestTypeAndCoreTest(testTypeRepository.findTestTypeByMaterialSubCategoryId(
            incomingSample.getRawMaterial().getMaterialSubCategory().getId()), true);
    SieveTest seiveTest = sieveTestRepository.findByIncomingSampleCode(incomingSample.getCode());
    for (TestConfigure testConfigure : testConfigureList) {
      for (MaterialTest materialTest : materialTestList) {
        if (testConfigure.getTest().getName()
            .equalsIgnoreCase(materialTest.getTestConfigure().getTest().getName())
            && materialTest.getTestConfigure().isCoreTest()) {
          bodyMessage = bodyMessage + "<li>" + materialTest.getTestConfigure().getTest().getName()
              + " : " + materialTest.getStatus() + "</li>";
          count = count + 1;
          if (materialTest.getStatus() == Status.PASS) {
            passCount = passCount + 1;
          }
        }
      }
    }

    if (incomingSample.getRawMaterial().getMaterialSubCategory().getMaterialCategory().getName()
        .equalsIgnoreCase("Aggregates")) {
      if (seiveTest != null) {
        if (seiveTest.getStatus() == Status.PASS) {
          bodyMessage = bodyMessage + "<li> Seive Test : " + seiveTest.getStatus() + "</li>";
          calculateTest(count, passCount, testConfigureList.size(), incomingSample, bodyMessage);
        } else if (sieveTestRepository.findByIncomingSampleCode(incomingSample.getCode())
            .getStatus() == Status.FAIL) {
          bodyMessage = bodyMessage + "<li> Seive Test : " + seiveTest.getStatus() + "</li>";
          updateStatusSample(Status.FAIL, incomingSample, bodyMessage);
        }
      } else {
        incomingSample.setStatus(Status.PROCESS);
        incomingSampleRepository.save(incomingSample);
      }
    } else {
      calculateTest(count, passCount, testConfigureList.size(), incomingSample, bodyMessage);
    }
  }

  private void calculateTest(Integer count, Integer passCount, Integer testSize,
      IncomingSample incomingSample, String bodyMessage) {
    if (count == testSize) {
      if (passCount == count) {
        updateStatusSample(Status.PASS, incomingSample, bodyMessage);
      } else {
        updateStatusSample(Status.FAIL, incomingSample, bodyMessage);
      }
    } else {
      incomingSample.setStatus(Status.PROCESS);
      incomingSampleRepository.save(incomingSample);
    }
  }

  private void updateStatusSample(Status status, IncomingSample incomingSample,
      String bodyMessage) {
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
    emailService.sendMailWithFormat(mailConstants.getMailUpdateIncomingSampleStatus(),
        Constants.SUBJECT_INCOMING_SAMPLE_RESULT,
        "<p>The Incoming Sample is <b>" + status + "</b> The Sample Code is <b>"
            + incomingSample.getCode() + "</b>. This Sample arrived on <b>"
            + incomingSample.getDate() + "</b>. The Sample Material is <b>"
            + incomingSample.getRawMaterial().getName() + "</b>.</p><ul>" + bodyMessage + "</ul>");
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode) {
    return materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
  }

  @Transactional(readOnly = true)
  public Page<MaterialTest> searchMaterialTest(String incomingSampleCode, Status status,
      Double average, String testName, Double averageMin, Double averageMax,
      BooleanBuilder booleanBuilder, int page, int size) {
    if (incomingSampleCode != null && !incomingSampleCode.isEmpty()) {
      booleanBuilder.and(QMaterialTest.materialTest.incomingSample.code.eq(incomingSampleCode));
    }
    if (status != null) {
      booleanBuilder.and(QMaterialTest.materialTest.status.eq(status));
    }
    if (averageMax != null && averageMax != 0 && averageMin == null && average == null) {
      booleanBuilder.and(QMaterialTest.materialTest.average.lt(averageMax));
    }
    if (averageMin != null && averageMin != 0 && averageMax == null && average == null) {
      booleanBuilder.and(QMaterialTest.materialTest.average.gt(averageMin));
    }

    if (averageMin != null && averageMin != 0 && averageMax != null && averageMax != null
        && average == null) {
      booleanBuilder.and(QMaterialTest.materialTest.average.between(averageMin, averageMax));
    }
    if (average != null && average != 0 && averageMax == null && averageMin == null) {
      booleanBuilder.and(QMaterialTest.materialTest.average.eq(average));
    }
    if (testName != null && !testName.isEmpty()) {
      booleanBuilder.and(QMaterialTest.materialTest.testConfigure.test.name.eq(testName));
    }
    return materialTestRepository.findAll(booleanBuilder,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }
}
