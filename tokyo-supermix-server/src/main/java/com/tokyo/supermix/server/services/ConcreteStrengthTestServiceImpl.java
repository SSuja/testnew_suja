package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteStrengthTestRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ConcreteStrengthTestServiceImpl implements ConcreteStrengthTestService {

  @Autowired
  private ConcreteStrengthTestRepository concreteStrengthTestRepository;
  @Autowired
  private MixDesignService mixDesignService;

  @Transactional
  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest) {
    concreteStrengthTestRepository.save(calculateConcreteStrengthRatio(concreteStrengthTest));
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
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

  private ConcreteStrengthTest calculateConcreteStrengthRatio(
      ConcreteStrengthTest concreteStrengthTest) {
    MixDesign mixDesign =
        mixDesignService.getMixDesignByCode(concreteStrengthTest.getMixDesign().getCode());
    concreteStrengthTest.setStrengthGradeRatio(
        roundDoubleValue(concreteStrengthTest.getStrength() / mixDesign.getTargetGrade()));
    if (concreteStrengthTest.getStrength() == 0) {
      concreteStrengthTest.setStatus(Status.PROCESS);
    }
    if (concreteStrengthTest.getStrengthGradeRatio() >= 1) {
      concreteStrengthTest.setStatus(Status.PASS);
    } else if (concreteStrengthTest.getStrengthGradeRatio() > 0
        && concreteStrengthTest.getStrengthGradeRatio() < 1) {
      concreteStrengthTest.setStatus(Status.FAIL);
    }
    return concreteStrengthTest;
  }
}
