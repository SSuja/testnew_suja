package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SieveTest;

public interface SieveTestService {
  public void saveSieveTest(SieveTest sieveTest);

  public List<SieveTest> getAllSieveTests();

  public SieveTest getSieveTestByCode(String code);

  public void deleteSieveTest(String code);

  public boolean isSieveTestExists(String code);
}
