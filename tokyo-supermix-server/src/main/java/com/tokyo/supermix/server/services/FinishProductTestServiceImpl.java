package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;

@Service
public class FinishProductTestServiceImpl implements FinishProductTestService {

  @Autowired
  private FinishProductTestRepository finishProductTestRepository;

  @Transactional
  public void createFinishProductTest(FinishProductTest finishProductTest) {
    finishProductTestRepository.save(finishProductTest);
  }

  @Transactional(readOnly = true)
  public FinishProductTest getFinishProductTestById(Long id) {
    return finishProductTestRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTests() {
    return finishProductTestRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTest(Long id) {
    finishProductTestRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExists(Long id) {
    return finishProductTestRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTestsByTestConfigure(Long testConfigureId) {
    return getAllFinishProductTestsByTestConfigure(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExistsByTestConfigure(Long testConfigureId) {
    return finishProductTestRepository.existsByTestConfigureId(testConfigureId);
  }

}
