package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductTest;

public interface FinishProductTestService {

  public String createFinishProductTest(FinishProductTest finishProductTest);

  public FinishProductTest getFinishProductTestByCode(String code);

  List<FinishProductTest> getAllFinishProductTests();

  public void deleteFinishProductTest(String code);

  boolean isFinishProductTestExists(String code);

  List<FinishProductTest> getAllFinishProductTestsByTestConfigure(Long testConfigureId);

  boolean isFinishProductTestExistsByTestConfigure(Long testConfigureId);

  boolean isDuplicateEntry(Long finishProductSampleId, Long testConfigureId);
}
