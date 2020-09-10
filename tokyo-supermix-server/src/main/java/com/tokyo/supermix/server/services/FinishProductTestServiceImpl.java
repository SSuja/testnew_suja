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
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.QFinishProductTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
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
  private Mapper mapper;

  @Transactional
  public String createFinishProductTest(FinishProductTest finishProductTest) {
    if (finishProductTest.getCode() == null) {
      String prefix =
          testConfigureRepository.getOne(finishProductTest.getTestConfigure().getId()).getPrefix();
      List<FinishProductTest> finishProductTestList =
          finishProductTestRepository.findByCodeContaining(prefix);
      if (finishProductTestList.size() == 0) {
        finishProductTest.setCode(prefix + String.format("%04d", 1));
      } else {
        finishProductTest
            .setCode(prefix + String.format("%04d", maxNumberFromCode(finishProductTestList) + 1));
      }
    }
    String specimenCode =
        finishProductTest.getFinishProductSample().getCode() + "-SP-"
            + String.format("%02d", finishProductTestRepository
                .findByFinishProductSampleCode(finishProductTest.getFinishProductSample().getCode())
                .size() + 1);
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
      list.add(getNumberFromCode(obj.getCode()));
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
    finishProductTestRepository.save(finishProductTest);
  }

  @Transactional(readOnly = true)
  public boolean isExistsByPlantCode(String plantCode) {
    return finishProductTestRepository.existsByFinishProductSampleMixDesignPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTestDto> getFinishProductTestByFinishProductSample(
      String finishProductSampleCode) {
    ArrayList<FinishProductTestDto> finishProductTestDtoList =
        new ArrayList<FinishProductTestDto>();
//    if (testConfigureRepository
//        .findByMaterialSubCategoryId(finishProductSampleRepository.findById(finishProductSampleCode)
//            .get().getMixDesign().getRawMaterial().getMaterialSubCategory().getId())
//        .isEmpty()) {
//      testConfigureRepository
//          .findByMaterialCategoryId(
//              finishProductSampleRepository.findById(finishProductSampleCode).get().getMixDesign()
//                  .getRawMaterial().getMaterialSubCategory().getMaterialCategory().getId())
//          .forEach(testConfigureMaterial -> {
//            FinishProductTestDto finishProductTestDto = new FinishProductTestDto();
//            finishProductTestRepository.findByTestConfigureId(testConfigureMaterial.getId())
//                .forEach(finishProductTest -> {
//                  if (finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
//                      finishProductSampleCode, testConfigureMaterial.getId())) {
//                    finishProductTestDto
//                        .setCreatedDate(finishProductTest.getCreatedAt().toString());
//                    finishProductTestDto
//                        .setUpdatedDate(finishProductTest.getUpdatedAt().toString());
//                    finishProductTestDto
//                        .setTestConfigId(finishProductTest.getTestConfigure().getId());
//                    finishProductTestDto.setFinishproductTestCode(finishProductTest.getCode());
//                    finishProductTestDto.setStatus(finishProductTest.getStatus());
//                    finishProductTestDto
//                        .setTestName(finishProductTest.getTestConfigure().getTest().getName());
//                    finishProductTestDto.setFinishProductSampleCode(
//                        finishProductTest.getFinishProductSample().getCode());
//                    finishProductTestDto
//                        .setMainType(finishProductTest.getTestConfigure().getTestType());
//                  }
//                });
//            if (!(finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
//                finishProductSampleCode, testConfigureMaterial.getId()))) {
//              finishProductTestDto.setTestConfigId(testConfigureMaterial.getId());
//              finishProductTestDto.setStatus(Status.NEW);
//              finishProductTestDto.setTestName(testConfigureMaterial.getTest().getName());
//              finishProductTestDto.setFinishProductSampleCode(finishProductSampleCode);
//              finishProductTestDto.setMainType(testConfigureMaterial.getTestType());
//            }
//            finishProductTestDtoList.add(finishProductTestDto);
//          });
//    } else 
//    if (testConfigureRepository.findByRawMaterialId(finishProductSampleRepository
//        .findById(finishProductSampleCode).get().getMixDesign().getRawMaterial().getId())
//        .isEmpty()) {
//      testConfigureRepository
//          .findByMaterialSubCategoryId(
//              finishProductSampleRepository.findById(finishProductSampleCode).get().getMixDesign()
//                  .getRawMaterial().getMaterialSubCategory().getId())
//          .forEach(testConfigureMaterialSubCategory -> {
//            FinishProductTestDto finishProductTestDto = new FinishProductTestDto();
//            finishProductTestRepository
//                .findByTestConfigureId(testConfigureMaterialSubCategory.getId())
//                .forEach(finishProductTest -> {
//                  if (finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
//                      finishProductSampleCode, testConfigureMaterialSubCategory.getId())) {
//                    finishProductTestDto
//                        .setCreatedDate(finishProductTest.getCreatedAt().toString());
//                    finishProductTestDto
//                        .setUpdatedDate(finishProductTest.getUpdatedAt().toString());
//                    finishProductTestDto
//                        .setTestConfigId(finishProductTest.getTestConfigure().getId());
//                    finishProductTestDto.setFinishproductTestCode(finishProductTest.getCode());
//                    finishProductTestDto.setStatus(finishProductTest.getStatus());
//                    finishProductTestDto
//                        .setTestName(finishProductTest.getTestConfigure().getTest().getName());
//                    finishProductTestDto.setFinishProductSampleCode(
//                        finishProductTest.getFinishProductSample().getCode());
//                    finishProductTestDto
//                        .setMainType(finishProductTest.getTestConfigure().getTestType());
//                  }
//                });
//            if (!(finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
//                finishProductSampleCode, testConfigureMaterialSubCategory.getId()))) {
//              finishProductTestDto.setTestConfigId(testConfigureMaterialSubCategory.getId());
//              finishProductTestDto.setStatus(Status.NEW);
//              finishProductTestDto
//                  .setTestName(testConfigureMaterialSubCategory.getTest().getName());
//              finishProductTestDto.setFinishProductSampleCode(finishProductSampleCode);
//              finishProductTestDto.setMainType(testConfigureMaterialSubCategory.getTestType());
//            }
//            finishProductTestDtoList.add(finishProductTestDto);
//          });
//    } else
//    if (!(testConfigureRepository.findByRawMaterialId(finishProductSampleRepository
//        .findById(finishProductSampleCode).get().getMixDesign().getRawMaterial().getId())
//        .isEmpty())) {
      testConfigureRepository.findByRawMaterialId(finishProductSampleRepository
          .findById(finishProductSampleCode).get().getMixDesign().getRawMaterial().getId())
          .forEach(testConfigureRawMaterial -> {
            FinishProductTestDto finishProductTestDto = new FinishProductTestDto();
            finishProductTestRepository.findByTestConfigureId(testConfigureRawMaterial.getId())
                .forEach(finishProductTest -> {
                  if (finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
                      finishProductSampleCode, testConfigureRawMaterial.getId())) {
                    finishProductTestDto
                        .setCreatedDate(finishProductTest.getCreatedAt().toString());
                    finishProductTestDto
                        .setUpdatedDate(finishProductTest.getUpdatedAt().toString());
                    finishProductTestDto
                        .setTestConfigId(finishProductTest.getTestConfigure().getId());
                    finishProductTestDto.setFinishproductTestCode(finishProductTest.getCode());
                    finishProductTestDto.setStatus(finishProductTest.getStatus());
                    finishProductTestDto
                        .setTestName(finishProductTest.getTestConfigure().getTest().getName());
                    finishProductTestDto.setFinishProductSampleCode(
                        finishProductTest.getFinishProductSample().getCode());
                    finishProductTestDto
                        .setMainType(finishProductTest.getTestConfigure().getTestType());
                  }
                });
            if (!(finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
                finishProductSampleCode, testConfigureRawMaterial.getId()))) {
              finishProductTestDto.setTestConfigId(testConfigureRawMaterial.getId());
              finishProductTestDto.setStatus(Status.NEW);
              finishProductTestDto.setTestName(testConfigureRawMaterial.getTest().getName());
              finishProductTestDto.setFinishProductSampleCode(finishProductSampleCode);
              finishProductTestDto.setMainType(testConfigureRawMaterial.getTestType());
            }
            finishProductTestDtoList.add(finishProductTestDto);
          });
    //}
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
  public List<FinishProductTestDto> searchFinishProductTest(BooleanBuilder booleanBuilder,
      String specimenCode, String finishProductSampleCode, String mixDesignCode, String testName,
      String materialName, String plantCode, Pageable pageable, Pagination pagination) {

    if (specimenCode != null && !specimenCode.isEmpty()) {
      booleanBuilder.and(
          QFinishProductTest.finishProductTest.specimenCode.startsWithIgnoreCase(specimenCode));
    }
    if (finishProductSampleCode != null && !finishProductSampleCode.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.finishProductSample.finishProductCode
          .startsWithIgnoreCase(finishProductSampleCode));
    }
    if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.code
          .startsWithIgnoreCase(mixDesignCode));
    }
    if (testName != null && !testName.isEmpty()) {
      booleanBuilder.and(QFinishProductTest.finishProductTest.testConfigure.test.name
          .startsWithIgnoreCase(testName));
    }
    if (materialName != null && !materialName.isEmpty()) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.rawMaterial.name
              .startsWithIgnoreCase(materialName));
    }
    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder
          .and(QFinishProductTest.finishProductTest.finishProductSample.mixDesign.plant.code
              .startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<FinishProductTest>) finishProductTestRepository.findAll(booleanBuilder))
            .stream().count());
    return mapper.map(finishProductTestRepository.findAll(booleanBuilder, pageable).toList(),
        FinishProductTestDto.class);
  }
}
