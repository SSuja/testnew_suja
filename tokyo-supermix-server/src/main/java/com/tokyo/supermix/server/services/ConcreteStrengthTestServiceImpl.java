package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.repositories.ConcreteStrengthTestRepository;

@Service
public class ConcreteStrengthTestServiceImpl implements ConcreteStrengthTestService {

  @Autowired
  private ConcreteStrengthTestRepository concreteStrengthTestRepository;

  @Autowired
  private MixDesignService mixDesignService;

  @Transactional
  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest) {
    MixDesign mixDesign =
        mixDesignService.getMixDesignById(concreteStrengthTest.getMixDesign().getCode());
    concreteStrengthTest
        .setStrengthGradeRatio(concreteStrengthTest.getStrength() / mixDesign.getTargetGrade());
    concreteStrengthTestRepository.save(concreteStrengthTest);
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
