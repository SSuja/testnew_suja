package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductTest;

public interface FinishProductTestService {

  public void createFinishProductTest(FinishProductTest finishProductTest);

  public FinishProductTest getFinishProductTestById(Long id);

  List<FinishProductTest> getAllFinishProductTests();

  public void deleteFinishProductTest(Long id);

  boolean isFinishProductTestExists(Long id);
}
