package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.QSieveTest;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.SieveTestRepository;

@Service
public class SieveTestServiceImpl implements SieveTestService {
  @Autowired
  private SieveTestRepository sieveTestRepository;

  @Transactional
  public void saveSieveTest(SieveTest sieveTest) {
    sieveTestRepository.save(sieveTest);
  }

  @Transactional(readOnly = true)
  public List<SieveTest> getAllSieveTests() {
    return sieveTestRepository.findAll();
  }

  @Transactional(readOnly = true)
  public SieveTest getSieveTestByCode(String code) {
    return sieveTestRepository.findByCode(code);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveTest(String code) {
    sieveTestRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isSieveTestExists(String code) {
    return sieveTestRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public Page<SieveTest> searchSieveTest(String incomingSampleCode, Status status,
      Double finenessModulus, Double finenessModulusMin, Double finenessModulusMax,
      String plantCode, BooleanBuilder booleanBuilder, int page, int size) {
    if (incomingSampleCode != null && !incomingSampleCode.isEmpty()) {
      booleanBuilder.and(QSieveTest.sieveTest.incomingSample.code.eq(incomingSampleCode));
    }
    if (plantCode != null && !plantCode.isEmpty()) {
      booleanBuilder.and(QSieveTest.sieveTest.plant.code.eq(plantCode));
    }
    if (status != null) {
      booleanBuilder.and(QSieveTest.sieveTest.status.eq(status));
    }
    if (finenessModulusMin != null && finenessModulusMin != 0 && finenessModulusMax == null
        && finenessModulus == null) {
      booleanBuilder.and(QSieveTest.sieveTest.finenessModulus.lt(finenessModulusMin));
    }
    if (finenessModulusMax != null && finenessModulusMax != 0 && finenessModulusMin == null
        && finenessModulus == null) {
      booleanBuilder.and(QSieveTest.sieveTest.finenessModulus.gt(finenessModulusMax));
    }
    if (finenessModulusMin != null && finenessModulusMin != 0 && finenessModulusMax != null
        && finenessModulusMax != null && finenessModulus == null) {
      booleanBuilder.and(
          QSieveTest.sieveTest.finenessModulus.between(finenessModulusMin, finenessModulusMax));
    }
    if (finenessModulus != null && finenessModulus != 0 && finenessModulusMax == null
        && finenessModulusMin == null) {
      booleanBuilder.and(QSieveTest.sieveTest.finenessModulus.eq(finenessModulus));
    }
    return sieveTestRepository.findAll(booleanBuilder,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }
}
