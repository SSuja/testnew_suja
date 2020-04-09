package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
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
  private TestConfigureRepository testRepository;
  @Autowired
  private TestTypeRepository testTypeRepository;

  @Transactional
  public void saveMaterialTest(MaterialTest materialTest) {
    IncomingSample incomingSample = incomingSampleRepository
        .findIncomingSampleByCode(materialTest.getIncomingSample().getCode());
    incomingSample.setStatus(Status.PROCESS);
    incomingSampleRepository.save(incomingSample);
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

  public void updateIncomingSampleStatusByIncomingSampleCode(String incomingSampleCode) {
    IncomingSample incomingSample =
        incomingSampleRepository.findIncomingSampleByCode(incomingSampleCode);
    String bodyMessage = "";
    Integer count = 0;
    Integer passCount = 0;
    Status status = Status.NEW;
    List<MaterialTest> materialTestList =
        materialTestRepository.findByIncomingSampleCode(incomingSampleCode);
    List<TestConfigure> testConfigureList =
        testRepository.findByTestType(testTypeRepository.findTestTypeByMaterialSubCategoryId(
            incomingSample.getRawMaterial().getMaterialSubCategory().getId()));
    for (TestConfigure testConfigure : testConfigureList) {
      for (MaterialTest materialTest : materialTestList) {
        if (testConfigure.getTest().getName()
            .equalsIgnoreCase(materialTest.getTestConfigure().getTest().getName())) {
          bodyMessage = bodyMessage + "<li>" + materialTest.getTestConfigure().getTest().getName()
              + " : " + materialTest.getStatus() + "</li>";
          count = count + 1;
          if (materialTest.getStatus() == Status.PASS) {
            passCount = passCount + 1;
          }
          break;
        }
      }
    }
    for (MaterialTest materialTest : materialTestList) {
      if (materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory()
          .getMaterialCategory().getName().equalsIgnoreCase("Aggregates")) {
        if (sieveTestRepository.findByIncomingSampleCode(incomingSampleCode)
            .getStatus() == Status.FAIL) {
          incomingSample.setStatus(Status.FAIL);
          incomingSampleRepository.save(incomingSample);
          if (status == Status.PASS || status == Status.FAIL) {
            emailService.sendMailWithFormat(mailConstants.getMailUpdateIncomingSampleStatus(),
                Constants.SUBJECT_INCOMING_SAMPLE_RESULT,
                "<p>The Incoming Sample is " + status + " The Sample Code is <b>"
                    + incomingSampleCode + "</b>. This Sample arrived on "
                    + incomingSample.getDate() + ". The Sample Material is <b>"
                    + incomingSample.getRawMaterial().getName() + "</b>.</p><ul>" + bodyMessage
                    + "</ul>");
          }
          break;
        } else if (sieveTestRepository.findByIncomingSampleCode(incomingSampleCode)
            .getStatus() == Status.PASS) {
          calculateTest(count, passCount, testConfigureList.size(), incomingSample);
        } else {
          status = Status.PROCESS;
          incomingSample.setStatus(status);
          incomingSampleRepository.save(incomingSample);
          if (status == Status.PASS || status == Status.FAIL) {
            emailService.sendMailWithFormat(mailConstants.getMailUpdateIncomingSampleStatus(),
                Constants.SUBJECT_INCOMING_SAMPLE_RESULT,
                "<p>The Incoming Sample is " + status + " The Sample Code is <b>"
                    + incomingSampleCode + "</b>. This Sample arrived on "
                    + incomingSample.getDate() + ". The Sample Material is <b>"
                    + incomingSample.getRawMaterial().getName() + "</b>.</p><ul>" + bodyMessage
                    + "</ul>");
          }
        }
      } else {
        calculateTest(count, passCount, testConfigureList.size(), incomingSample);
      }

    }

  }

  private void calculateTest(Integer count, Integer passCount, Integer siz,
      IncomingSample incomingSample) {
    Status status = Status.NEW;
    if (count == siz) {
      if (passCount == count) {
        status = Status.PASS;

      } else {
        status = Status.FAIL;
      }
      incomingSample.setStatus(status);
      incomingSampleRepository.save(incomingSample);
    }
  }

  public void updateIncomingSampleStatusBySeheduler() {
    List<IncomingSample> incomingSamplelist = incomingSampleRepository.findByStatus(Status.PROCESS);
    for (IncomingSample incomingSample : incomingSamplelist) {
      {
        updateIncomingSampleStatusByIncomingSampleCode(incomingSample.getCode());
      }
    }
  }
}
