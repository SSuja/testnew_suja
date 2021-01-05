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
import com.tokyo.supermix.data.dto.report.MaterialTestDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.QMaterialTest;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
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
  @Autowired
  private CoreTestConfigureService coreTestConfigureService;
  @Autowired
  private CoreTestConfigureRepository coreTestConfigureRepository;

  @Transactional
  public String saveMaterialTest(MaterialTest materialTest) {
    if (materialTest.getCode() == null) {
      String prefix =
          testConfigureRepository.getOne(materialTest.getTestConfigure().getId()).getPrefix();
      List<MaterialTest> materialTestList = materialTestRepository.findByCodeContaining(prefix);
      materialTest.setCode(materialTestList.size() == 0 ? prefix + "-" + String.format("%04d", 1)
          : prefix + "-" + String.format("%04d", maxNumberFromCode(materialTestList) + 1));
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
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.lastIndexOf("-"))));
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
    if (date != null && !date.isEmpty()) {
      booleanBuilder
          .and(QMaterialTest.materialTest.createdAt.stringValue().startsWithIgnoreCase(date));
    }
    if (specimenCode != null && !specimenCode.isEmpty()) {
      booleanBuilder
          .and(QMaterialTest.materialTest.specimenCode.startsWithIgnoreCase(specimenCode));
    }
    if (status != null && !status.isEmpty()) {
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
    materialTest.setSpecimenCode(
        materialTestRepository.findById(materialTest.getCode()).get().getSpecimenCode());
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

  public void updateIncomingSampleStatusByIncomingSample(MaterialTest materialTestObj) {
    IncomingSample incomingSample = materialTestObj.getIncomingSample();
    if (coreTestConfigureRepository.existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrue(
        materialTestObj.getTestConfigure().getId(), incomingSample.getRawMaterial().getId())) {
      List<CoreTestConfigure> coreTestConfigureList =
          coreTestConfigureService.getCoreTestConfigureByRawMaterialIdAndCoreTestTrue(
              incomingSample.getRawMaterial().getId());

      List<TestConfigure> testConfigureList = coreTestConfigureList.stream()
          .map(testConfigure -> testConfigure.getTestConfigure()).collect(Collectors.toList());

      Status status = Status.NEW;
      for (TestConfigure tc : testConfigureList) {
        if (!materialTestRepository
            .findByIncomingSampleCodeAndTestConfigureIdAndIncomingSamplePlantCode(
                incomingSample.getCode(), tc.getId(), incomingSample.getPlant().getCode())
            .isEmpty()) {

          materialTestRepository
              .findByIncomingSampleCodeAndTestConfigureIdAndIncomingSamplePlantCode(
                  incomingSample.getCode(), tc.getId(), incomingSample.getPlant().getCode())
              .get(0).getUpdatedAt();
          if (materialTestRepository
              .findByIncomingSampleCodeAndTestConfigureIdAndIncomingSamplePlantCodeOrderByUpdatedAtDesc(
                  incomingSample.getCode(), tc.getId(), incomingSample.getPlant().getCode())
              .get(0).getStatus().equals(Status.FAIL)) {
            status = Status.FAIL;
            break;
          } else {
            status = Status.PASS;
          }
        } else {
          status = Status.PROCESS;
          break;
        }
      }
      updateStatusSample(status, incomingSample, "updateed", materialTestObj);
    }
  }

  private List<MaterialAcceptedValue> getRawMaterialAcceptedValues(Long testConfigId) {
    return materialAcceptedValueRepository.findByTestConfigureId(testConfigId);
  }

  @Transactional(readOnly = true)
  public List<MaterialTestDto> getMaterialTestsByIncomingSample(String incomingSampleCode) {
    ArrayList<MaterialTestDto> materialTestList = new ArrayList<MaterialTestDto>();
    IncomingSample incomingSample = incomingSampleRepository.findById(incomingSampleCode).get();
    materialTestRepository.findByIncomingSampleCode(incomingSampleCode).forEach(materialTest -> {
      MaterialTestDto materialTestDto = new MaterialTestDto();
      if (materialTest.getTestConfigure().getAcceptedType() != null) {
        if (materialTest.getTestConfigure().getRawMaterial() != null) {
          if (materialTest.getTestConfigure().getRawMaterial().getId() == materialTest
              .getIncomingSample().getRawMaterial().getId()) {
            if (materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.TEST)) {
              materialTestDto.setCreatedDate(materialTest.getCreatedAt().toString());
              materialTestDto.setUpdatedDate(materialTest.getUpdatedAt().toString());
              materialTestDto.setTestConfigId(materialTest.getTestConfigure().getId());
              materialTestDto.setMaterialTestCode(materialTest.getCode());
              materialTestDto.setStatus(materialTest.getStatus());
              materialTestDto.setTestName(materialTest.getTestConfigure().getTest().getName());
              materialTestDto.setIncomingSampleCode(incomingSampleCode);
              materialTestDto.setMainType(materialTest.getTestConfigure().getTestType());
              materialTestDto
                  .setRawMaterialId(materialTest.getIncomingSample().getRawMaterial().getId());
              materialTestDto.setSpecimenCode(materialTest.getSpecimenCode());
            }
            if (materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL)) {
              for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                  materialTest.getTestConfigure().getId())) {
                if (materialAcceptedValue.getRawMaterial().getId() == incomingSample
                    .getRawMaterial().getId()) {
                  materialTestDto.setCreatedDate(materialTest.getCreatedAt().toString());
                  materialTestDto.setUpdatedDate(materialTest.getUpdatedAt().toString());
                  materialTestDto.setTestConfigId(materialTest.getTestConfigure().getId());
                  materialTestDto.setMaterialTestCode(materialTest.getCode());
                  materialTestDto.setStatus(materialTest.getStatus());
                  materialTestDto.setTestName(materialTest.getTestConfigure().getTest().getName());
                  materialTestDto.setIncomingSampleCode(incomingSampleCode);
                  materialTestDto.setMainType(materialTest.getTestConfigure().getTestType());
                  materialTestDto
                      .setRawMaterialId(materialTest.getIncomingSample().getRawMaterial().getId());
                  materialTestDto.setSpecimenCode(materialTest.getSpecimenCode());
                }
              }

            }
          }
        } else if (materialTest.getTestConfigure().getMaterialSubCategory() == null) {
          if (materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory()
              .getMaterialCategory()
              .getId() == materialTest.getTestConfigure().getMaterialCategory().getId()) {
            if (materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.TEST)) {
              materialTestDto.setCreatedDate(materialTest.getCreatedAt().toString());
              materialTestDto.setUpdatedDate(materialTest.getUpdatedAt().toString());
              materialTestDto.setTestConfigId(materialTest.getTestConfigure().getId());
              materialTestDto.setMaterialTestCode(materialTest.getCode());
              materialTestDto.setStatus(materialTest.getStatus());
              materialTestDto.setTestName(materialTest.getTestConfigure().getTest().getName());
              materialTestDto.setIncomingSampleCode(incomingSampleCode);
              materialTestDto.setMainType(materialTest.getTestConfigure().getTestType());
              materialTestDto
                  .setRawMaterialId(materialTest.getIncomingSample().getRawMaterial().getId());
              materialTestDto.setSpecimenCode(materialTest.getSpecimenCode());
            }
            if (materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL)) {
              for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                  materialTest.getTestConfigure().getId())) {
                if (materialAcceptedValue.getRawMaterial().getId() == incomingSample
                    .getRawMaterial().getId()) {
                  materialTestDto.setCreatedDate(materialTest.getCreatedAt().toString());
                  materialTestDto.setUpdatedDate(materialTest.getUpdatedAt().toString());
                  materialTestDto.setTestConfigId(materialTest.getTestConfigure().getId());
                  materialTestDto.setMaterialTestCode(materialTest.getCode());
                  materialTestDto.setStatus(materialTest.getStatus());
                  materialTestDto.setTestName(materialTest.getTestConfigure().getTest().getName());
                  materialTestDto.setIncomingSampleCode(incomingSampleCode);
                  materialTestDto.setMainType(materialTest.getTestConfigure().getTestType());
                  materialTestDto
                      .setRawMaterialId(materialTest.getIncomingSample().getRawMaterial().getId());
                  materialTestDto.setSpecimenCode(materialTest.getSpecimenCode());
                }
              }

            }
          }
        } else {
          if (materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory()
              .getId() == materialTest.getTestConfigure().getMaterialSubCategory().getId()) {
            if (materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.TEST)) {
              materialTestDto.setCreatedDate(materialTest.getCreatedAt().toString());
              materialTestDto.setUpdatedDate(materialTest.getUpdatedAt().toString());
              materialTestDto.setTestConfigId(materialTest.getTestConfigure().getId());
              materialTestDto.setMaterialTestCode(materialTest.getCode());
              materialTestDto.setStatus(materialTest.getStatus());
              materialTestDto.setTestName(materialTest.getTestConfigure().getTest().getName());
              materialTestDto.setIncomingSampleCode(incomingSampleCode);
              materialTestDto.setMainType(materialTest.getTestConfigure().getTestType());
              materialTestDto
                  .setRawMaterialId(materialTest.getIncomingSample().getRawMaterial().getId());
              materialTestDto.setSpecimenCode(materialTest.getSpecimenCode());
            }
            if (materialTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL)) {
              for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                  materialTest.getTestConfigure().getId())) {
                if (materialAcceptedValue.getRawMaterial().getId() == incomingSample
                    .getRawMaterial().getId()) {
                  materialTestDto.setCreatedDate(materialTest.getCreatedAt().toString());
                  materialTestDto.setUpdatedDate(materialTest.getUpdatedAt().toString());
                  materialTestDto.setTestConfigId(materialTest.getTestConfigure().getId());
                  materialTestDto.setMaterialTestCode(materialTest.getCode());
                  materialTestDto.setStatus(materialTest.getStatus());
                  materialTestDto.setTestName(materialTest.getTestConfigure().getTest().getName());
                  materialTestDto.setIncomingSampleCode(incomingSampleCode);
                  materialTestDto.setMainType(materialTest.getTestConfigure().getTestType());
                  materialTestDto
                      .setRawMaterialId(materialTest.getIncomingSample().getRawMaterial().getId());
                  materialTestDto.setSpecimenCode(materialTest.getSpecimenCode());
                }
              }
            }
          }
        }
      }
      materialTestList.add(materialTestDto);
    });
    testConfigureRepository.findByMaterialCategoryIdOrMaterialSubCategoryIdOrRawMaterialId(
        incomingSample.getRawMaterial().getMaterialSubCategory().getMaterialCategory().getId(),
        incomingSample.getRawMaterial().getMaterialSubCategory().getId(),
        incomingSample.getRawMaterial().getId()).forEach(testConfigureMaterial -> {
          if (!materialTestRepository.existsByIncomingSampleCodeAndTestConfigureId(
              incomingSampleCode, testConfigureMaterial.getId())) {
            MaterialTestDto materialTestDto = new MaterialTestDto();
            if (testConfigureMaterial != null && testConfigureMaterial.getAcceptedType() != null)
              if (testConfigureMaterial.getRawMaterial() != null) {
                if (incomingSample.getRawMaterial().getId() == testConfigureMaterial
                    .getRawMaterial().getId()) {
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.TEST)) {
                    materialTestDto.setTestConfigId(testConfigureMaterial.getId());
                    materialTestDto.setStatus(Status.NEW);
                    materialTestDto.setTestName(testConfigureMaterial.getTest().getName());
                    materialTestDto.setIncomingSampleCode(incomingSampleCode);
                    materialTestDto.setMainType(testConfigureMaterial.getTestType());
                    materialTestDto.setRawMaterialId(incomingSample.getRawMaterial().getId());
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.MATERIAL)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                        testConfigureMaterial.getId())) {
                      if (materialAcceptedValue.getRawMaterial().getId() == incomingSample
                          .getRawMaterial().getId()) {
                        materialTestDto.setTestConfigId(testConfigureMaterial.getId());
                        materialTestDto.setStatus(Status.NEW);
                        materialTestDto.setTestName(testConfigureMaterial.getTest().getName());
                        materialTestDto.setIncomingSampleCode(incomingSampleCode);
                        materialTestDto.setMainType(testConfigureMaterial.getTestType());
                        materialTestDto.setRawMaterialId(incomingSample.getRawMaterial().getId());
                      }
                    }
                  }
                }
              } else if (testConfigureMaterial.getMaterialSubCategory() == null) {
                if (incomingSample.getRawMaterial().getMaterialSubCategory().getMaterialCategory()
                    .getId() == testConfigureMaterial.getMaterialCategory().getId()) {
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.TEST)) {
                    materialTestDto.setTestConfigId(testConfigureMaterial.getId());
                    materialTestDto.setStatus(Status.NEW);
                    materialTestDto.setTestName(testConfigureMaterial.getTest().getName());
                    materialTestDto.setIncomingSampleCode(incomingSampleCode);
                    materialTestDto.setMainType(testConfigureMaterial.getTestType());
                    materialTestDto.setRawMaterialId(incomingSample.getRawMaterial().getId());
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.MATERIAL)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                        testConfigureMaterial.getId())) {
                      if (materialAcceptedValue.getRawMaterial().getId() == incomingSample
                          .getRawMaterial().getId()) {
                        materialTestDto.setTestConfigId(testConfigureMaterial.getId());
                        materialTestDto.setStatus(Status.NEW);
                        materialTestDto.setTestName(testConfigureMaterial.getTest().getName());
                        materialTestDto.setIncomingSampleCode(incomingSampleCode);
                        materialTestDto.setMainType(testConfigureMaterial.getTestType());
                        materialTestDto.setRawMaterialId(incomingSample.getRawMaterial().getId());
                      }
                    }
                  }
                }
              } else if (testConfigureMaterial.getMaterialSubCategory() != null
                  && testConfigureMaterial.getRawMaterial() == null) {
                if (incomingSample.getRawMaterial().getMaterialSubCategory()
                    .getId() == testConfigureMaterial.getMaterialSubCategory().getId()) {
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.TEST)) {
                    materialTestDto.setTestConfigId(testConfigureMaterial.getId());
                    materialTestDto.setStatus(Status.NEW);
                    materialTestDto.setTestName(testConfigureMaterial.getTest().getName());
                    materialTestDto.setIncomingSampleCode(incomingSampleCode);
                    materialTestDto.setMainType(testConfigureMaterial.getTestType());
                    materialTestDto.setRawMaterialId(incomingSample.getRawMaterial().getId());
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.MATERIAL)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                        testConfigureMaterial.getId())) {
                      if (materialAcceptedValue.getRawMaterial().getId() == incomingSample
                          .getRawMaterial().getId()) {
                        materialTestDto.setTestConfigId(testConfigureMaterial.getId());
                        materialTestDto.setStatus(Status.NEW);
                        materialTestDto.setTestName(testConfigureMaterial.getTest().getName());
                        materialTestDto.setIncomingSampleCode(incomingSampleCode);
                        materialTestDto.setMainType(testConfigureMaterial.getTestType());
                        materialTestDto.setRawMaterialId(incomingSample.getRawMaterial().getId());
                      }
                    }
                  }
                }
              }
            materialTestList.add(materialTestDto);
          }
        });
    return materialTestList;

  }
}
