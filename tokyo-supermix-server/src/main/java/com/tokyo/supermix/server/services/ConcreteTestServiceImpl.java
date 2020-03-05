package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.repositories.ConcreteTestRepository;

@Service
public class ConcreteTestServiceImpl implements ConcreteTestService {
  @Autowired
  private ConcreteTestRepository ConcreteTestRepository;
  @Autowired
  private MixDesignService mixDesignService;
  @Autowired
  private MixDesignProportionService mixDesignProportionService;

  @Transactional
  public ConcreteTest saveConcreteTest(ConcreteTest concreteTest) {
    MixDesign mixDesign = mixDesignService.getMixDesignById(concreteTest.getMixDesign().getCode());
    concreteTest
        .setSlumpGradeRatio(concreteTest.getSlump() / mixDesign.getTargetSlump().doubleValue());
    List<MixDesignProportion> mixDesignProportionList =
        mixDesignProportionService.findByMixDesign(mixDesign);
    Long quantity = null;
    Long binderquantity = 0L;
    for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {
      if (mixDesignProportion.getRawMaterial().getName().equalsIgnoreCase("cement")) {
        quantity = mixDesignProportion.getQuantity();
      }
      if (mixDesignProportion.getRawMaterial().getName().equalsIgnoreCase("FlyAsh")) {
        binderquantity = mixDesignProportion.getQuantity();
      }
    }
    concreteTest.setWaterCementRatio(concreteTest.getWaterContent() / quantity.doubleValue());
    concreteTest.setWaterBinderRatio(
        concreteTest.getWaterContent() / (binderquantity + quantity.doubleValue()));
    return ConcreteTestRepository.save(concreteTest);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTest> getAllConcreteTest() {
    return ConcreteTestRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ConcreteTest getConcreteTestById(Long id) {
    return ConcreteTestRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteTest(Long id) {
    ConcreteTestRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestExit(Long id) {
    return ConcreteTestRepository.existsById(id);
  }

}
