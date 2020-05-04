package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.enums.Status;

public interface SieveTestService {
  public void saveSieveTest(SieveTest sieveTest);

  public List<SieveTest> getAllSieveTests();

  public SieveTest getSieveTestByCode(String code);

  public void deleteSieveTest(String code);

  public boolean isSieveTestExists(String code);

  public Page<SieveTest> searchSieveTest(String incomingSampleCode, Status status,
      Double finenessModulus, Double finenessModulusMin, Double finenessModulusMax,
      String plantCode, BooleanBuilder booleanBuilder, int page, int size);
}
