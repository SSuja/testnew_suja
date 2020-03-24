package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SieveTest;

public interface SieveTestService {
  public void saveSieveTest(SieveTest sieveTest);

  public List<SieveTest> getAllSieveTests();

  public SieveTest getSieveTestById(Long id);

  public void deleteSieveTest(Long id);

  public boolean isSieveTestExists(Long id);

}
