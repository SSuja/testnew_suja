package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductTest;
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

  public List<FinishProductTest> getAllFinishProductTestByPlant(UserPrincipal currentUser);

  public  List<FinishProductTest> getAllFinishProductTestByPlant(String plantCode);
  public void updateFinishProductTestComment(FinishProductTest finishProductTest);
}
