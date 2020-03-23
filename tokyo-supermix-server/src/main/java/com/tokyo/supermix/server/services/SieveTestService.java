package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SieveTest;

public interface SieveTestService {
  public SieveTest saveSieveTest(SieveTest sieveTest);

  public List<SieveTest> getAllSieveTest();

  public SieveTest getSieveTestById(Long id);

  public void deleteSieveTest(Long id);

  public boolean isSieveTestExit(Long id);
}
