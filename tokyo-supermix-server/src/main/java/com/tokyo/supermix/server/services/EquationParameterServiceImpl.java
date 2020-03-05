package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.EquationParameter;
import com.tokyo.supermix.data.repositories.EquationParameterRepository;

@Service
public class EquationParameterServiceImpl implements EquationParameterService {
  @Autowired
  private EquationParameterRepository equationParameterRepository;

  @Transactional
  public EquationParameter saveEquationParameter(EquationParameter equationParameter) {
    return equationParameterRepository.save(equationParameter);
  }

  @Transactional(readOnly = true)
  public List<EquationParameter> getAllEquationParameters() {
    return equationParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public EquationParameter getEquationParameterById(Long id) {
    return equationParameterRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isEquationParameterExist(Long id) {
    return equationParameterRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<EquationParameter> getEquationByEquationId(Long equationId) {
    return equationParameterRepository.findByEquationId(equationId);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestParameter(Long id) {
    equationParameterRepository.deleteById(id);
  }

  public boolean isDuplicateRowExists(Long equationId, Long parameterId) {
    if (equationParameterRepository.isDuplicateRow(equationId, parameterId) != null) {
      return true;
    }
    return false;
  }
}
