package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.SieveTest;
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
  public SieveTest getSieveTestById(Long id) {
    return sieveTestRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveTest(Long id) {
    sieveTestRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isSieveTestExists(Long id) {
    return sieveTestRepository.existsById(id);
  }
}
