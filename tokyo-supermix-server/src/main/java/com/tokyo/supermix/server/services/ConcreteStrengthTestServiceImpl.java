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
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class ConcreteStrengthTestServiceImpl implements ConcreteStrengthTestService {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  private ConcreteStrengthTestRepository concreteStrengthTestRepository;
  @Autowired
  private MixDesignRepository mixDesignRepository;

  @Transactional
  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest) {
    concreteStrengthTestRepository.save(calculateConcreteStrengthRatio(concreteStrengthTest));
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
        mixDesignRepository.findById(concreteStrengthTest.getMixDesign().getCode()).get();
    concreteStrengthTest.setStrengthGradeRatio(
        roundDoubleValue(concreteStrengthTest.getStrength() / mixDesign.getTargetGrade()));
    if (concreteStrengthTest.getStrength() == 0) {
      concreteStrengthTest.setStatus(Status.PROCESS);
    }
    Long concreteAge = concreteStrengthTest.getConcreteAge();
    Double ratio = concreteStrengthTest.getStrengthGradeRatio();
    if ((ratio >= 0.16 && concreteAge == 1) || (ratio >= 0.40 && concreteAge == 3)
        || (ratio >= 0.50 && concreteAge == 5) || (ratio >= 0.65 && concreteAge == 7)
        || (ratio >= 0.90 && concreteAge == 14) || (ratio >= 0.94 && concreteAge == 21)
        || (ratio >= 0.99 && concreteAge == 28) || (ratio >= 1 && concreteAge == 56)
        || (ratio >= 1 && concreteAge == 128)) {
      concreteStrengthTest.setStatus(Status.PASS);
      String messsage = "Congrete Strength Test is " + concreteStrengthTest.getStatus()
          + " for the mixdesign code is " + concreteStrengthTest.getMixDesign().getCode()
          + "<ul><li> Age : " + concreteStrengthTest.getConcreteAge() + " days </li>"
          + "<li> Strength : " + concreteStrengthTest.getStrength() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
          Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
    } else {
      concreteStrengthTest.setStatus(Status.FAIL);
      String messsage = "Congrete Strength Test is " + concreteStrengthTest.getStatus()
          + " for the mixdesign code is " + concreteStrengthTest.getMixDesign().getCode()
          + "<ul><li> Age : " + concreteStrengthTest.getConcreteAge() + "days </li>"
          + "<li> Strength : " + concreteStrengthTest.getStrength() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
          Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
    }
    return concreteStrengthTest;
  }
}
