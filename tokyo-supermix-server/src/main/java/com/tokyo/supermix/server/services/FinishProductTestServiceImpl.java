package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.FinishProductTestDto;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.QFinishProductTest;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductTestServiceImpl implements FinishProductTestService {

  @Autowired
  private FinishProductTestRepository finishProductTestRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;

  @Transactional
  public String createFinishProductTest(FinishProductTest finishProductTest) {
    if (finishProductTest.getCode() == null) {
      String prefix =
          testConfigureRepository.getOne(finishProductTest.getTestConfigure().getId()).getPrefix()
              + "-";
      List<FinishProductTest> finishProductTestList =
          finishProductTestRepository.findByCodeContaining(prefix);
      if (finishProductTestList.size() == 0) {
        finishProductTest.setCode(prefix + String.format("%04d", 1));
      } else {
        finishProductTest
            .setCode(prefix + String.format("%04d", maxNumberFromCode(finishProductTestList) + 1));
      }
    }
    String specimenCode = finishProductTest.getFinishProductSample().getCode() + "-SP-"
        + String.format("%02d",
            finishProductTestRepository.findByFinishProductSampleCodeAndTestConfigureId(
                finishProductTest.getFinishProductSample().getCode(),
                finishProductTest.getTestConfigure().getId()).size() + 1);

    finishProductTest.setSpecimenCode(specimenCode);
    finishProductTestRepository.save(finishProductTest);
    return finishProductTest.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTest> finishProductTestList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductTestList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode().substring(obj.getCode().lastIndexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(readOnly = true)
  public FinishProductTest getFinishProductTestByCode(String code) {
    return finishProductTestRepository.findFinishProductTestByCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTests() {
    return finishProductTestRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTest(String code) {
    finishProductTestRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExists(String code) {
    return finishProductTestRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTestsByTestConfigure(Long testConfigureId) {
    return finishProductTestRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExistsByTestConfigure(Long testConfigureId) {
    return finishProductTestRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntry(String finishProductSampleCode, Long testConfigureId) {
    if (finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
        finishProductSampleCode, testConfigureId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getFinishProductTestByFinishProductSampleCodeAndTestConfigureId(
      String finishProductSampleCode, Long testConfigureId) {
    return finishProductTestRepository
        .findByFinishProductSampleCodeAndTestConfigureId(finishProductSampleCode, testConfigureId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTestByPlant(UserPrincipal currentUser,
      Pageable pageable) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignPlantCodeInOrderByUpdatedAtDesc(
            currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
                PermissionConstants.VIEW_FINISH_PRODUCT_TEST),
            pageable)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTestByPlant(String plantCode,
      Pageable pageable) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignPlantCodeOrderByUpdatedAtDesc(plantCode, pageable)
        .toList();
  }

  @Transactional
  public void updateFinishProductTestComment(FinishProductTest finishProductTest) {

    finishProductTest.setSpecimenCode(
        finishProductTestRepository.findById(finishProductTest.getCode()).get().getSpecimenCode());
    finishProductTestRepository.save(finishProductTest);
  }

  @Transactional(readOnly = true)
  public boolean isExistsByPlantCode(String plantCode) {
    return finishProductTestRepository.existsByFinishProductSampleMixDesignPlantCode(plantCode);
  }

  private List<MaterialAcceptedValue> getRawMaterialAcceptedValues(Long testConfigId,
      Long rawMaterialId) {
    return materialAcceptedValueRepository.findByTestConfigureIdAndRawMaterialId(testConfigId,
        rawMaterialId);
  }

  private List<MaterialAcceptedValue> getSubMaterialAcceptedValues(Long testConfigId,
      Long subCategoryId) {
    return materialAcceptedValueRepository
        .findByTestConfigureIdAndMaterialSubCategoryId(testConfigId, subCategoryId);
  }

  private void setFinishProductTestsAlready(FinishProductTestDto finishProductTestDto,
      String createdAt, String updatedAt, Long testConfigId, String finishTestCode, Status status,
      String testName, String sampleCode, MainType mainType, Long rawMaterialId,
      String specimenCode) {
    finishProductTestDto.setCreatedDate(createdAt);
    finishProductTestDto.setUpdatedDate(updatedAt);
    finishProductTestDto.setTestConfigId(testConfigId);
    finishProductTestDto.setFinishproductTestCode(finishTestCode);
    finishProductTestDto.setStatus(status);
    finishProductTestDto.setTestName(testName);
    finishProductTestDto.setFinishProductSampleCode(sampleCode);
    finishProductTestDto.setMainType(mainType);
    finishProductTestDto.setRawMaterialId(rawMaterialId);
    finishProductTestDto.setSpecimenCode(specimenCode);
  }

  private void setFinishProductTestsNew(FinishProductTestDto finishProductTestDto,
      Long testConfigId, String testName, String sampleCode, MainType mainType,
      Long rawMaterialId) {
    finishProductTestDto.setTestConfigId(testConfigId);
    finishProductTestDto.setStatus(Status.NEW);
    finishProductTestDto.setTestName(testName);
    finishProductTestDto.setFinishProductSampleCode(sampleCode);
    finishProductTestDto.setMainType(mainType);
    finishProductTestDto.setRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTestDto> getFinishProductTestByFinishProductSample(
      String finishProductSampleCode) {
    ArrayList<FinishProductTestDto> finishProductTestDtoList =
        new ArrayList<FinishProductTestDto>();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleCode).get();
    finishProductTestRepository.findByFinishProductSampleCode(finishProductSampleCode)
        .forEach(finishProductTest -> {
          FinishProductTestDto finishProductTestDto = new FinishProductTestDto();
          if (finishProductTest.getTestConfigure().getAcceptedType() != null) {
            if (finishProductTest.getTestConfigure().getRawMaterial() != null) {
              if (finishProductTest.getTestConfigure().getRawMaterial().getId() == finishProductTest
                  .getFinishProductSample().getMixDesign().getRawMaterial().getId()) {
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.TEST)) {
                  setFinishProductTestsAlready(finishProductTestDto,
                      finishProductTest.getCreatedAt().toString(),
                      finishProductTest.getUpdatedAt().toString(),
                      finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                      finishProductTest.getStatus(),
                      finishProductTest.getTestConfigure().getTest().getName(),
                      finishProductTest.getFinishProductSample().getCode(),
                      finishProductTest.getTestConfigure().getTestType(),
                      finishProductSample.getMixDesign().getRawMaterial().getId(),
                      finishProductTest.getSpecimenCode());
                }
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.MATERIAL)) {
                  for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                      finishProductTest.getTestConfigure().getId(),
                      finishProductSample.getMixDesign().getRawMaterial().getId())) {
                    if (materialAcceptedValue.getCategoryAcceptedType()
                        .equals(CategoryAcceptedType.MATERIAL)
                        && materialAcceptedValue.getRawMaterial().getId() == finishProductSample
                            .getMixDesign().getRawMaterial().getId()) {
                      setFinishProductTestsAlready(finishProductTestDto,
                          finishProductTest.getCreatedAt().toString(),
                          finishProductTest.getUpdatedAt().toString(),
                          finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                          finishProductTest.getStatus(),
                          finishProductTest.getTestConfigure().getTest().getName(),
                          finishProductTest.getFinishProductSample().getCode(),
                          finishProductTest.getTestConfigure().getTestType(),
                          finishProductSample.getMixDesign().getRawMaterial().getId(),
                          finishProductTest.getSpecimenCode());
                    }
                  }

                }
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.SUB_CATEGORY)) {
                  for (MaterialAcceptedValue materialAcceptedValue : getSubMaterialAcceptedValues(
                      finishProductTest.getTestConfigure().getId(), finishProductSample
                          .getMixDesign().getRawMaterial().getMaterialSubCategory().getId())) {
                    if (materialAcceptedValue.getCategoryAcceptedType()
                        .equals(CategoryAcceptedType.SUB_CATEGORY)
                        && materialAcceptedValue.getMaterialSubCategory()
                            .getId() == finishProductSample.getMixDesign().getRawMaterial()
                                .getMaterialSubCategory().getId()) {
                      setFinishProductTestsAlready(finishProductTestDto,
                          finishProductTest.getCreatedAt().toString(),
                          finishProductTest.getUpdatedAt().toString(),
                          finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                          finishProductTest.getStatus(),
                          finishProductTest.getTestConfigure().getTest().getName(),
                          finishProductTest.getFinishProductSample().getCode(),
                          finishProductTest.getTestConfigure().getTestType(),
                          finishProductSample.getMixDesign().getRawMaterial().getId(),
                          finishProductTest.getSpecimenCode());
                    }
                  }

                }
              }
            } else if (finishProductTest.getTestConfigure().getMaterialSubCategory() == null) {
              if (finishProductTest.getFinishProductSample().getMixDesign().getRawMaterial()
                  .getMaterialSubCategory().getMaterialCategory()
                  .getId() == finishProductTest.getTestConfigure().getMaterialCategory().getId()) {
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.TEST)) {
                  setFinishProductTestsAlready(finishProductTestDto,
                      finishProductTest.getCreatedAt().toString(),
                      finishProductTest.getUpdatedAt().toString(),
                      finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                      finishProductTest.getStatus(),
                      finishProductTest.getTestConfigure().getTest().getName(),
                      finishProductTest.getFinishProductSample().getCode(),
                      finishProductTest.getTestConfigure().getTestType(),
                      finishProductSample.getMixDesign().getRawMaterial().getId(),
                      finishProductTest.getSpecimenCode());
                }
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.MATERIAL)) {
                  for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                      finishProductTest.getTestConfigure().getId(),
                      finishProductSample.getMixDesign().getRawMaterial().getId())) {
                    if (materialAcceptedValue.getCategoryAcceptedType()
                        .equals(CategoryAcceptedType.MATERIAL)
                        && materialAcceptedValue.getRawMaterial().getId() == finishProductSample
                            .getMixDesign().getRawMaterial().getId()) {
                      setFinishProductTestsAlready(finishProductTestDto,
                          finishProductTest.getCreatedAt().toString(),
                          finishProductTest.getUpdatedAt().toString(),
                          finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                          finishProductTest.getStatus(),
                          finishProductTest.getTestConfigure().getTest().getName(),
                          finishProductTest.getFinishProductSample().getCode(),
                          finishProductTest.getTestConfigure().getTestType(),
                          finishProductSample.getMixDesign().getRawMaterial().getId(),
                          finishProductTest.getSpecimenCode());
                    }
                  }

                }
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.SUB_CATEGORY)) {
                  for (MaterialAcceptedValue materialAcceptedValue : getSubMaterialAcceptedValues(
                      finishProductTest.getTestConfigure().getId(), finishProductSample
                          .getMixDesign().getRawMaterial().getMaterialSubCategory().getId())) {
                    if (materialAcceptedValue.getCategoryAcceptedType()
                        .equals(CategoryAcceptedType.SUB_CATEGORY)
                        && materialAcceptedValue.getMaterialSubCategory()
                            .getId() == finishProductSample.getMixDesign().getRawMaterial()
                                .getMaterialSubCategory().getId()) {
                      setFinishProductTestsAlready(finishProductTestDto,
                          finishProductTest.getCreatedAt().toString(),
                          finishProductTest.getUpdatedAt().toString(),
                          finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                          finishProductTest.getStatus(),
                          finishProductTest.getTestConfigure().getTest().getName(),
                          finishProductTest.getFinishProductSample().getCode(),
                          finishProductTest.getTestConfigure().getTestType(),
                          finishProductSample.getMixDesign().getRawMaterial().getId(),
                          finishProductTest.getSpecimenCode());
                    }
                  }

                }
              }
            } else {
              if (finishProductTest.getFinishProductSample().getMixDesign().getRawMaterial()
                  .getMaterialSubCategory().getId() == finishProductTest.getTestConfigure()
                      .getMaterialSubCategory().getId()) {
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.TEST)) {
                  setFinishProductTestsAlready(finishProductTestDto,
                      finishProductTest.getCreatedAt().toString(),
                      finishProductTest.getUpdatedAt().toString(),
                      finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                      finishProductTest.getStatus(),
                      finishProductTest.getTestConfigure().getTest().getName(),
                      finishProductTest.getFinishProductSample().getCode(),
                      finishProductTest.getTestConfigure().getTestType(),
                      finishProductSample.getMixDesign().getRawMaterial().getId(),
                      finishProductTest.getSpecimenCode());
                }
                if (finishProductTest.getTestConfigure().getAcceptedType()
                    .equals(AcceptedType.MATERIAL)) {
                  for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                      finishProductTest.getTestConfigure().getId(),
                      finishProductSample.getMixDesign().getRawMaterial().getId())) {
                    if (materialAcceptedValue.getCategoryAcceptedType()
                        .equals(CategoryAcceptedType.MATERIAL)
                        && materialAcceptedValue.getRawMaterial().getId() == finishProductSample
                            .getMixDesign().getRawMaterial().getId()) {
                      setFinishProductTestsAlready(finishProductTestDto,
                          finishProductTest.getCreatedAt().toString(),
                          finishProductTest.getUpdatedAt().toString(),
                          finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                          finishProductTest.getStatus(),
                          finishProductTest.getTestConfigure().getTest().getName(),
                          finishProductTest.getFinishProductSample().getCode(),
                          finishProductTest.getTestConfigure().getTestType(),
                          finishProductSample.getMixDesign().getRawMaterial().getId(),
                          finishProductTest.getSpecimenCode());
                    }
                  }
                }
              }
              if (finishProductTest.getTestConfigure().getAcceptedType()
                  .equals(AcceptedType.SUB_CATEGORY)) {
                for (MaterialAcceptedValue materialAcceptedValue : getSubMaterialAcceptedValues(
                    finishProductTest.getTestConfigure().getId(), finishProductSample.getMixDesign()
                        .getRawMaterial().getMaterialSubCategory().getId())) {
                  if (materialAcceptedValue.getCategoryAcceptedType()
                      .equals(CategoryAcceptedType.SUB_CATEGORY)
                      && materialAcceptedValue.getMaterialSubCategory()
                          .getId() == finishProductSample.getMixDesign().getRawMaterial()
                              .getMaterialSubCategory().getId()) {
                    setFinishProductTestsAlready(finishProductTestDto,
                        finishProductTest.getCreatedAt().toString(),
                        finishProductTest.getUpdatedAt().toString(),
                        finishProductTest.getTestConfigure().getId(), finishProductTest.getCode(),
                        finishProductTest.getStatus(),
                        finishProductTest.getTestConfigure().getTest().getName(),
                        finishProductTest.getFinishProductSample().getCode(),
                        finishProductTest.getTestConfigure().getTestType(),
                        finishProductSample.getMixDesign().getRawMaterial().getId(),
                        finishProductTest.getSpecimenCode());
                  }
                }
              }
            }
          }
          finishProductTestDtoList.add(finishProductTestDto);
        });
    testConfigureRepository
        .findByMaterialCategoryIdOrMaterialSubCategoryIdOrRawMaterialId(
            finishProductSample.getMixDesign().getRawMaterial().getMaterialSubCategory()
                .getMaterialCategory().getId(),
            finishProductSample.getMixDesign().getRawMaterial().getMaterialSubCategory().getId(),
            finishProductSample.getMixDesign().getRawMaterial().getId())
        .forEach(testConfigureMaterial -> {
          if (!finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
              finishProductSampleCode, testConfigureMaterial.getId())) {
            FinishProductTestDto finishProductTestDto = new FinishProductTestDto();
            if (testConfigureMaterial != null && testConfigureMaterial.getAcceptedType() != null)
              if (testConfigureMaterial.getRawMaterial() != null) {
                if (finishProductSample.getMixDesign().getRawMaterial()
                    .getId() == testConfigureMaterial.getRawMaterial().getId()) {
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.TEST)) {
                    setFinishProductTestsNew(finishProductTestDto, testConfigureMaterial.getId(),
                        testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                        testConfigureMaterial.getTestType(),
                        finishProductSample.getMixDesign().getRawMaterial().getId());
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.MATERIAL)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                        testConfigureMaterial.getId(),
                        finishProductSample.getMixDesign().getRawMaterial().getId())) {
                      if (materialAcceptedValue.getRawMaterial().getId() == finishProductSample
                          .getMixDesign().getRawMaterial().getId()) {
                        setFinishProductTestsNew(finishProductTestDto,
                            testConfigureMaterial.getId(),
                            testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                            testConfigureMaterial.getTestType(),
                            finishProductSample.getMixDesign().getRawMaterial().getId());
                      }
                    }
                  }
                }
              } else if (testConfigureMaterial.getMaterialSubCategory() == null) {
                if (finishProductSample.getMixDesign().getRawMaterial().getMaterialSubCategory()
                    .getMaterialCategory()
                    .getId() == testConfigureMaterial.getMaterialCategory().getId()) {
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.TEST)) {
                    setFinishProductTestsNew(finishProductTestDto, testConfigureMaterial.getId(),
                        testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                        testConfigureMaterial.getTestType(),
                        finishProductSample.getMixDesign().getRawMaterial().getId());
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.MATERIAL)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                        testConfigureMaterial.getId(),
                        finishProductSample.getMixDesign().getRawMaterial().getId())) {
                      if (materialAcceptedValue.getCategoryAcceptedType()
                          .equals(CategoryAcceptedType.MATERIAL)
                          && materialAcceptedValue.getRawMaterial().getId() == finishProductSample
                              .getMixDesign().getRawMaterial().getId()) {
                        setFinishProductTestsNew(finishProductTestDto,
                            testConfigureMaterial.getId(),
                            testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                            testConfigureMaterial.getTestType(),
                            finishProductSample.getMixDesign().getRawMaterial().getId());
                      }
                    }
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.SUB_CATEGORY)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getSubMaterialAcceptedValues(
                        testConfigureMaterial.getId(), finishProductSample.getMixDesign()
                            .getRawMaterial().getMaterialSubCategory().getId())) {
                      if (materialAcceptedValue.getCategoryAcceptedType()
                          .equals(CategoryAcceptedType.SUB_CATEGORY)
                          && materialAcceptedValue.getMaterialSubCategory()
                              .getId() == finishProductSample.getMixDesign().getRawMaterial()
                                  .getMaterialSubCategory().getId()) {
                        setFinishProductTestsNew(finishProductTestDto,
                            testConfigureMaterial.getId(),
                            testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                            testConfigureMaterial.getTestType(),
                            finishProductSample.getMixDesign().getRawMaterial().getId());
                      }
                    }
                  }
                }
              } else if (testConfigureMaterial.getMaterialSubCategory() != null
                  && testConfigureMaterial.getRawMaterial() == null) {
                if (finishProductSample.getMixDesign().getRawMaterial().getMaterialSubCategory()
                    .getId() == testConfigureMaterial.getMaterialSubCategory().getId()) {
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.TEST)) {
                    setFinishProductTestsNew(finishProductTestDto, testConfigureMaterial.getId(),
                        testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                        testConfigureMaterial.getTestType(),
                        finishProductSample.getMixDesign().getRawMaterial().getId());
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.MATERIAL)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getRawMaterialAcceptedValues(
                        testConfigureMaterial.getId(),
                        finishProductSample.getMixDesign().getRawMaterial().getId())) {
                      if (materialAcceptedValue.getCategoryAcceptedType()
                          .equals(CategoryAcceptedType.MATERIAL)
                          && materialAcceptedValue.getRawMaterial().getId() == finishProductSample
                              .getMixDesign().getRawMaterial().getId()) {
                        setFinishProductTestsNew(finishProductTestDto,
                            testConfigureMaterial.getId(),
                            testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                            testConfigureMaterial.getTestType(),
                            finishProductSample.getMixDesign().getRawMaterial().getId());
                      }
                    }
                  }
                  if (testConfigureMaterial.getAcceptedType().equals(AcceptedType.SUB_CATEGORY)) {
                    for (MaterialAcceptedValue materialAcceptedValue : getSubMaterialAcceptedValues(
                        testConfigureMaterial.getId(), finishProductSample.getMixDesign()
                            .getRawMaterial().getMaterialSubCategory().getId())) {
                      if (materialAcceptedValue.getCategoryAcceptedType()
                          .equals(CategoryAcceptedType.SUB_CATEGORY)
                          && materialAcceptedValue.getMaterialSubCategory()
                              .getId() == finishProductSample.getMixDesign().getRawMaterial()
                                  .getMaterialSubCategory().getId()) {
                        setFinishProductTestsNew(finishProductTestDto,
                            testConfigureMaterial.getId(),
                            testConfigureMaterial.getTest().getName(), finishProductSampleCode,
                            testConfigureMaterial.getTestType(),
                            finishProductSample.getMixDesign().getRawMaterial().getId());
                      }
                    }
                  }
                }
              }
            finishProductTestDtoList.add(finishProductTestDto);
          }
        });
    return finishProductTestDtoList;

  }

  @Transactional(readOnly = true)
  public Long getCountFinishProductTest() {
    return finishProductTestRepository.count();
  }

  @Transactional(readOnly = true)
  public Long getCountFinishProductTestByPlant(String plantCode) {
    return finishProductTestRepository.countByFinishProductSampleMixDesignPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> searchFinishProductTest(BooleanBuilder booleanBuilder, String code,
      String specimenCode, String finishProductSampleCode, String mixDesignCode, String testName,
      String materialName, String mainCategoryName, String subCategoryName, Status status,
      String date, String plantName, String plantCode, Pageable pageable, Pagination pagination) {
    if (code != null && !code.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.code.contains(code));
    }
    if (specimenCode != null && !specimenCode.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.specimenCode.contains(specimenCode));
    }
    if (finishProductSampleCode != null && !finishProductSampleCode.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.finishProductSample.finishProductCode
          .contains(finishProductSampleCode));
    }
    if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.code
          .contains(mixDesignCode));
    }
    if (testName != null && !testName.isEmpty()) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.testConfigure.test.name.contains(testName));
    }
    if (materialName != null && !materialName.isEmpty()) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.rawMaterial.name
              .contains(materialName));
    }
    if (mainCategoryName != null && !mainCategoryName.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.testConfigure.materialCategory.name
          .contains(mainCategoryName));
    }
    if (subCategoryName != null && !subCategoryName.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.testConfigure.materialSubCategory.name
          .contains(subCategoryName));
    }
    if (plantName != null && !plantName.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.plant.name
              .contains(plantName));
    }
    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.plant.code
              .contains(plantCode));
    }
    if (status != null) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.status.eq(status));
    }
    if (date != null && !date.isEmpty()) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.date.stringValue().startsWithIgnoreCase(date));
    }
    pagination.setTotalRecords(
        ((Collection<FinishProductTest>) finishProductTestRepository.findAll(booleanBuilder))
            .stream().count());
    return finishProductTestRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getFinishProductTestsByFinishProductSampleCode(
      String finishProductSampleCode) {
    return finishProductTestRepository.findByFinishProductSampleCode(finishProductSampleCode);
  }
}
