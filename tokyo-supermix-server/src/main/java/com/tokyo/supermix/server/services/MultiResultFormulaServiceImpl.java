package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MultiResultFormula;
import com.tokyo.supermix.data.repositories.MultiResultFormulaRepository;

@Service
public class MultiResultFormulaServiceImpl implements MultiResultFormulaService {

  @Autowired
  private MultiResultFormulaRepository multiResultFormulaRepository;

  @Transactional(readOnly = true)
  public List<MultiResultFormula> getByTestConfigureId(Long testConfigureId) {
    return multiResultFormulaRepository.findByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isExistsTestConfigureId(Long testConfigureId) {
    return multiResultFormulaRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isExistById(Long id) {
    return multiResultFormulaRepository.existsById(id);
  }

  @Transactional
  public void saveMultiResultFormula(MultiResultFormula multiResultFormula) {
    multiResultFormulaRepository.save(multiResultFormula);
  }
}
