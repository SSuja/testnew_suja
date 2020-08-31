package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.FinishProductTestDto;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductTestServiceImpl implements FinishProductTestService {

  @Autowired
  private FinishProductTestRepository finishProductTestRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
//  @Autowired
//  private FinishProductSampleRepository finishProductSampleRepository;

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
  public List<FinishProductTest> getAllFinishProductTestByPlant(UserPrincipal currentUser) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignPlantCodeInOrderByUpdatedAtDesc(
            currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
                PermissionConstants.VIEW_FINISH_PRODUCT_TEST));
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTestByPlant(String plantCode) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignPlantCodeOrderByUpdatedAtDesc(plantCode);
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
//    testConfigureRepository.findByMaterialCategoryId(finishProductSampleRepository
//        .findById(finishProductSampleCode).get().getMixDesign().getMaterialCategory().getId())
//        .forEach(testConfigure -> {
//          FinishProductTestDto finishProductTestDto = new FinishProductTestDto();
//          finishProductTestRepository.findByTestConfigureId(testConfigure.getId())
//              .forEach(finishProductTest -> {
//                if (finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
//                    finishProductSampleCode, testConfigure.getId())) {
//                  finishProductTestDto.setCreatedDate(finishProductTest.getCreatedAt().toString());
//                  finishProductTestDto.setUpdatedDate(finishProductTest.getUpdatedAt().toString());
//                  finishProductTestDto
//                      .setTestConfigId(finishProductTest.getTestConfigure().getId());
//                  finishProductTestDto.setFinishproductTestCode(finishProductTest.getCode());
//                  finishProductTestDto.setStatus(finishProductTest.getStatus());
//                  finishProductTestDto
//                      .setTestName(finishProductTest.getTestConfigure().getTest().getName());
//                  finishProductTestDto.setFinishProductSampleCode(
//                      finishProductTest.getFinishProductSample().getCode());
//                  finishProductTestDto
//                      .setMainType(finishProductTest.getTestConfigure().getTestType());
//                }
//              });
//          if (!(finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
//              finishProductSampleCode, testConfigure.getId()))) {
//            finishProductTestDto.setTestConfigId(testConfigure.getId());
//            finishProductTestDto.setStatus(Status.NEW);
//            finishProductTestDto.setTestName(testConfigure.getTest().getName());
//            finishProductTestDto.setFinishProductSampleCode(finishProductSampleCode);
//            finishProductTestDto.setMainType(testConfigure.getTestType());
//          }
//          finishProductTestDtoList.add(finishProductTestDto);
//        });
    return finishProductTestDtoList;
  }

}
