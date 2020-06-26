package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;

@Service
public class FinishProductTestServiceImpl implements FinishProductTestService {

  @Autowired
  private FinishProductTestRepository finishProductTestRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;

  @Transactional
  public String createFinishProductTest(FinishProductTest finishProductTest) {
    if (finishProductTest.getCode() == null) {
      String prefix =
          testConfigureRepository.getOne(finishProductTest.getTestConfigure().getId()).getPrefix();
      List<FinishProductTest> finishProductTestList =
          finishProductTestRepository.findByCodeContaining(prefix);
      if (finishProductTestList.size() == 0) {
        finishProductTest.setCode(prefix + String.format("%04d", 1));
      } else {
        finishProductTest
            .setCode(prefix + String.format("%03d", maxNumberFromCode(finishProductTestList) + 1));
      }
    }
    finishProductTestRepository.save(finishProductTest);
    return finishProductTest.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTest> finishProductTestList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductTestList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode()));
    });
    return Collections.max(list);
  }

  @Transactional(readOnly = true)
  public FinishProductTest getFinishProductTestByCode(String code) {
    return finishProductTestRepository.findFinishProductTestByCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTests() {
    return finishProductTestRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTest(String code) {
    finishProductTestRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExists(String code) {
    return finishProductTestRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTest> getAllFinishProductTestsByTestConfigure(Long testConfigureId) {
    return finishProductTestRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExistsByTestConfigure(Long testConfigureId) {
    return finishProductTestRepository.existsByTestConfigureId(testConfigureId);
  }
}
