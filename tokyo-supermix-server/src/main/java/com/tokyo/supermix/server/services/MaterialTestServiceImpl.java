package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
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
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestType;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class MaterialTestServiceImpl implements MaterialTestService {
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private GenerateReportService generateReportService;

  @Transactional
  public String saveMaterialTest(MaterialTest materialTest) {
    if (materialTest.getCode() == null) {
      String prefix =
          testConfigureRepository.getOne(materialTest.getTestConfigure().getId()).getPrefix();
      List<MaterialTest> materialTestList = materialTestRepository.findByCodeContaining(prefix);
      if (materialTestList.size() == 0) {
        materialTest.setCode(prefix + String.format("%04d", 1));
      } else {
        materialTest
            .setCode(prefix + String.format("%04d", maxNumberFromCode(materialTestList) + 1));
      }
    }
    materialTest.setStatus(Status.NEW);
    String codePrefix = materialTest.getIncomingSample().getCode();
    String subPrefix = codePrefix + "-SP-";
    List<MaterialTest> materialTestTrialList =
        materialTestRepository.findByIncomingSampleCode(codePrefix);
    if (materialTestTrialList.size() == 0) {
      materialTest.setSpecimenCode(subPrefix + String.format("%02d", 1));
    } else {
      materialTest
          .setSpecimenCode(subPrefix + String.format("%02d", materialTestTrialList.size() + 1));
    }
    materialTestRepository.save(materialTest);
    return materialTest.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<MaterialTest> materialTestList) {
    List<Integer> list = new ArrayList<Integer>();
    materialTestList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode()));
    });
    return Collections.max(list);
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
  public List<MaterialTest> getMaterialTestByTestConfigureId(Long testConfigureId) {
    return materialTestRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestStatusExists(String status) {
    return materialTestRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestByTestConfigureExists(Long testConfigureId) {
    return materialTestRepository.existsByTestConfigureId(testConfigureId);
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

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByPlantCode(String plantCode) {
    return materialTestRepository.findByIncomingSamplePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByTestConfigureTestType(TestType testType) {
    return materialTestRepository.findByTestConfigureTestType(testType);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getAllMaterialTestByPlant(UserPrincipal currentUser) {
    return materialTestRepository.findByIncomingSamplePlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MATERIAL_TEST));
  }

  public void updateIncomingSampleStatusByIncomingSample(MaterialTest materialTestObj) {
    IncomingSample incomingSample = materialTestObj.getIncomingSample();
    Integer count = 0;
    String bodyMessage = "";
    Integer failCount = 0;
    List<TestConfigure> testConfigureList =
        testConfigureRepository.findByMaterialSubCategoryIdAndCoreTestTrue(
            incomingSample.getRawMaterial().getMaterialSubCategory().getId());
    List<MaterialTest> materialTestList =
        materialTestRepository.findByIncomingSampleCode(incomingSample.getCode());
    for (TestConfigure testConfigure : testConfigureList) {
      for (MaterialTest materialTest : materialTestList) {
        if (testConfigure.getTest().getName()
            .equalsIgnoreCase(materialTest.getTestConfigure().getTest().getName())
            && materialTest.getTestConfigure().isCoreTest()
            && materialTest.getStatus().equals(Status.PASS)) {
          count++;
        }
      }
      if (materialTestRepository.countByIncomingSampleCodeAndStatusAndTestConfigureTestName(
          incomingSample.getCode(), Status.FAIL, testConfigure.getTest().getName()) >= 2) {
        failCount++;
      }
    }
    calculateTest(count, failCount, testConfigureList.size(), incomingSample, bodyMessage,
        materialTestObj);
  }

  private void calculateTest(Integer count, Integer failCount, Integer testSize,
      IncomingSample incomingSample, String bodyMessage, MaterialTest materialTestObj) {
    if (count == testSize) {
      updateStatusSample(Status.PASS, incomingSample, bodyMessage, materialTestObj);
    } else if (failCount == 1) {
      updateStatusSample(Status.FAIL, incomingSample, bodyMessage, materialTestObj);
    } else {
      updateStatusSample(Status.PROCESS, incomingSample, bodyMessage, materialTestObj);
    }
  }

  private void updateStatusSample(Status status, IncomingSample incomingSample, String bodyMessage,
      MaterialTest materialTestObj) {
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
    if (!status.equals(Status.PROCESS)) {
      try {
        generateReportService.generatePdfSummaryDetailReport(incomingSample.getCode());
      } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    }
    if (materialTestObj.getTestConfigure().getTest().getName()
        .equalsIgnoreCase(Constants.DELIVERY_REPORT_MOISTURE_TEST)) {
      try {
        generateReportService.generatePdfDeliveryDetailReport(incomingSample.getCode(),
            materialTestObj.getTestConfigure().getTest().getName());
      } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
      }
    }
  }
}
