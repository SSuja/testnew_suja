package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.QMaterialTest;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
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
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;

  @Transactional
  public String saveMaterialTest(MaterialTest materialTest) {
    if (materialTest.getCode() == null) {
      String prefix =
          testConfigureRepository.getOne(materialTest.getTestConfigure().getId()).getPrefix();
      List<MaterialTest> materialTestList = materialTestRepository.findByCodeContaining(prefix);
      materialTest.setCode(materialTestList.size() == 0 ? prefix + String.format("%04d", 1)
          : prefix + String.format("%04d", maxNumberFromCode(materialTestList) + 1));
    }
    materialTest.setStatus(Status.PROCESS);
    String codePrefix = materialTest.getIncomingSample().getCode();
    String subPrefix = codePrefix + "-SP-";
    List<MaterialTest> materialTestTrialList =
        materialTestRepository.findByIncomingSampleCode(codePrefix);
    String specimenCode = (materialTestTrialList.size() == 0) ? subPrefix + String.format("%02d", 1)
        : subPrefix + String.format("%02d", materialTestTrialList.size() + 1);
    materialTest.setSpecimenCode(specimenCode);
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
  public List<MaterialTest> getMaterialTestByTestConfigureIdByPlant(Long testConfigureId,
      String plantCode) {
    return materialTestRepository.findByTestConfigureIdAndIncomingSamplePlantCode(testConfigureId,
        plantCode);
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
  public List<MaterialTest> searchMaterialTest(String incomingSampleCode, String date,
      String specimenCode, String status, String supplierName, String testName,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, String plantCode,
      Pagination pagination) {
    if (incomingSampleCode != null && !incomingSampleCode.isEmpty()) {
      booleanBuilder.and(
          QMaterialTest.materialTest.incomingSample.code.startsWithIgnoreCase(incomingSampleCode));
    }
    if (date != null && date.isEmpty()) {
      booleanBuilder
          .and(QMaterialTest.materialTest.createdAt.stringValue().startsWithIgnoreCase(date));
    }
    if (specimenCode != null && specimenCode.isEmpty()) {
      booleanBuilder
          .and(QMaterialTest.materialTest.specimenCode.startsWithIgnoreCase(specimenCode));
    }
    if (status != null && status.isEmpty()) {
      booleanBuilder
          .and(QMaterialTest.materialTest.status.stringValue().startsWithIgnoreCase(status));
    }
    if (testName != null && !testName.isEmpty()) {
      booleanBuilder
          .and(QMaterialTest.materialTest.testConfigure.test.name.startsWithIgnoreCase(testName));
    }
    if (supplierName != null && !supplierName.isEmpty()) {
      booleanBuilder.and(QMaterialTest.materialTest.incomingSample.supplier.name
          .startsWithIgnoreCase(supplierName));
    }

    if (!plantCode.equals("ADMIN")) {
      booleanBuilder.and(QMaterialTest.materialTest.incomingSample.plant.code.contains(plantCode));
    }
    pagination
        .setTotalRecords(((Collection<MaterialTest>) materialTestRepository.findAll(booleanBuilder))
            .stream().count());
    return materialTestRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByPlantCode(String plantCode) {
    return materialTestRepository.findByIncomingSamplePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByTestConfigureTestType(MainType testType) {
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
    List<TestConfigure> RawtestConfigureList = new ArrayList<>();
    if (testConfigureRepository.existsByRawMaterialId(incomingSample.getRawMaterial().getId())) {
      RawtestConfigureList = testConfigureRepository
          .findByRawMaterialIdAndCoreTestTrue(incomingSample.getRawMaterial().getId());
    }

    List<TestConfigure> SubtestConfigureList = testConfigureRepository
        .findByMaterialSubCategoryIdAndCoreTestTrue(
            incomingSample.getRawMaterial().getMaterialSubCategory().getId())
        .stream()
        .filter(testConfigure -> testConfigure.getRawMaterial() == null
            && getMaterialAcceptedForTest(testConfigure,
                incomingSample.getRawMaterial().getId()) == true)
        .collect(Collectors.toList());
    if (!RawtestConfigureList.isEmpty()) {
      SubtestConfigureList.addAll(RawtestConfigureList);
    }
    List<MaterialTest> materialTestList =
        materialTestRepository.findByIncomingSampleCode(incomingSample.getCode());
    for (TestConfigure testConfigure : SubtestConfigureList) {
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
    calculateTest(count, failCount, SubtestConfigureList.size(), incomingSample, bodyMessage,
        materialTestObj);
  }

  private void calculateTest(Integer count, Integer failCount, Integer testSize,
      IncomingSample incomingSample, String bodyMessage, MaterialTest materialTestObj) {
    updateStatusSample(
        (count == testSize && testSize != 0) ? Status.PASS
            : (failCount == 1) ? Status.FAIL : Status.PROCESS,
        incomingSample, bodyMessage, materialTestObj);
  }

  public boolean getMaterialAcceptedForTest(TestConfigure testConfigure, Long rawMaterialId) {
    return (testConfigure.getAcceptedType().equals(AcceptedType.MATERIAL))
        ? (materialAcceptedValueRepository
            .existsByTestConfigureIdAndRawMaterialId(testConfigure.getId(), rawMaterialId)) ? true
                : false
        : true;
  }

  private void updateStatusSample(Status status, IncomingSample incomingSample, String bodyMessage,
      MaterialTest materialTestObj) {
    incomingSample.setStatus(status);
    incomingSampleRepository.save(incomingSample);
    if (!status.equals(Status.PROCESS)) {
      try {
        generateReportService.generatePdfSummaryDetailReport(incomingSample.getCode());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (materialTestObj.getTestConfigure().getReportFormat().equals(ReportFormat.DELIVERY_REPORT)) {
      try {
        generateReportService.generatePdfDeliveryDetailReport(incomingSample.getCode());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Transactional
  public void updateMaterialTestComment(MaterialTest materialTest) {
    materialTestRepository.save(materialTest);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestsByincomingSampleCodeAndPlantCode(
      String incomingSampleCode, String plantCode) {
    return materialTestRepository
        .findByIncomingSampleCodeAndIncomingSamplePlantCode(incomingSampleCode, plantCode);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestsByIncomingSampleCodeAndTestConfigId(
      String incomingSampleCode, Long testConfigId) {
    return materialTestRepository.findByIncomingSampleCodeAndTestConfigureId(incomingSampleCode,
        testConfigId);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestsByIncomingSampleCodeAndTestConfigIdAndPlantCode(
      String incomingSampleCode, Long testConfigId, String plantCode) {
    return materialTestRepository
        .findByIncomingSampleCodeAndTestConfigureIdAndIncomingSamplePlantCode(incomingSampleCode,
            testConfigId, plantCode);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByPlant(String plantCode) {

    return materialTestRepository.findByIncomingSamplePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getAllMaterialTests(Pageable pageable) {

    return materialTestRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<MaterialTest> getMaterialTestByPlant(String plantCode, Pageable pageable) {

    return materialTestRepository.findAllByIncomingSamplePlantCode(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public Long getCountMaterialTest() {

    return materialTestRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountMaterialTestByPlantCode(String plantCode) {

    return materialTestRepository.countByIncomingSamplePlantCode(plantCode);
  }
}
