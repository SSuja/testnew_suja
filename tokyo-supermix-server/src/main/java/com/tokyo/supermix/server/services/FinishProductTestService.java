package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.FinishProductTestDto;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface FinishProductTestService {

  public String createFinishProductTest(FinishProductTest finishProductTest);

  public FinishProductTest getFinishProductTestByCode(String code);

  List<FinishProductTest> getAllFinishProductTests();

  public void deleteFinishProductTest(String code);

  boolean isFinishProductTestExists(String code);

  List<FinishProductTest> getAllFinishProductTestsByTestConfigure(Long testConfigureId);

  boolean isFinishProductTestExistsByTestConfigure(Long testConfigureId);

  boolean isDuplicateEntry(String finishProductSampleCode, Long testConfigureId);

  List<FinishProductTest> getFinishProductTestByFinishProductSampleCodeAndTestConfigureId(
      String finishProductSampleCode, Long testConfigureId);

  public List<FinishProductTest> getAllFinishProductTestByPlant(UserPrincipal currentUser,
      Pageable pageable);

  public List<FinishProductTest> getAllFinishProductTestByPlant(String plantCode,
      Pageable pageable);

  public void updateFinishProductTestComment(FinishProductTest finishProductTest);

  boolean isExistsByPlantCode(String plantCode);

  public List<FinishProductTestDto> getFinishProductTestByFinishProductSample(
      String finishProductSampleCode);

  public Long getCountFinishProductTest();

  public Long getCountFinishProductTestByPlant(String plantCode);

  public List<FinishProductTest> searchFinishProductTest(BooleanBuilder booleanBuilder,
      String specimenCode, String finishProductSampleCode, String mixDesignCode, String testName,
      String materialName, String status, String date, String plantName, String plantCode,
      Pageable pageable, Pagination pagination);

  public List<FinishProductTest> getFinishProductTestsByFinishProductSampleCode(
      String finishProductSampleCode);
}
