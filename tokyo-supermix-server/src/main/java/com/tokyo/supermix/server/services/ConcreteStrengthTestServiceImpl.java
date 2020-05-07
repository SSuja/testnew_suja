package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteStrengthTestRepository;

@Service
public class ConcreteStrengthTestServiceImpl implements ConcreteStrengthTestService {
  @Autowired
  private ConcreteStrengthTestRepository concreteStrengthTestRepository;

  @Transactional
  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest) {
    concreteStrengthTestRepository.save(concreteStrengthTest);
    if (concreteStrengthTest.getStrength() == 0) {
      concreteStrengthTest.setStatus(Status.PROCESS);
    }
  }

  @Transactional(readOnly = true)
  public boolean checkConcreteAge(Long concreteAge) {
    if (!(concreteAge == 1 || concreteAge == 3 || concreteAge == 5 || concreteAge == 7
        || concreteAge == 14 || concreteAge == 21 || concreteAge == 28 || concreteAge == 56
        || concreteAge == 128)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<ConcreteStrengthTest> getAllConcreteStrengthTests() {
    return concreteStrengthTestRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteStrengthTest(Long id) {
    concreteStrengthTestRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public ConcreteStrengthTest getConcreteStrengthTestById(Long id) {
    return concreteStrengthTestRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isConcreteStrengthTestExist(Long id) {
    return concreteStrengthTestRepository.existsById(id);
  }
}
