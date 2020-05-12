package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.entities.QConcreteStrengthTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteStrengthTestRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class ConcreteStrengthTestServiceImpl implements ConcreteStrengthTestService {
  @Autowired
  private ConcreteStrengthTestRepository concreteStrengthTestRepository;
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;

  @Transactional
  public void saveConcreteStrengthTest(ConcreteStrengthTest concreteStrengthTest) {
    concreteStrengthTestRepository
        .save(concreteStrengthTestEmailNotification(concreteStrengthTest));
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

  private ConcreteStrengthTest concreteStrengthTestEmailNotification(
      ConcreteStrengthTest concreteStrengthTest) {
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
          + " for the mixdesign code is "
          + concreteStrengthTest.getFinishProductSample().getMixDesign().getCode()
          + "<ul><li> Age : " + concreteStrengthTest.getConcreteAge() + " days </li>"
          + "<li> Strength : " + concreteStrengthTest.getStrength() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
          Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
    } else if (ratio > 0) {
      concreteStrengthTest.setStatus(Status.FAIL);
      String messsage = "Congrete Strength Test is " + concreteStrengthTest.getStatus()
          + " for the mixdesign code is "
          + concreteStrengthTest.getFinishProductSample().getMixDesign().getCode()
          + "<ul><li> Age : " + concreteStrengthTest.getConcreteAge() + "days </li>"
          + "<li> Strength : " + concreteStrengthTest.getStrength() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
          Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
    }
    return concreteStrengthTest;
  }

  @Transactional(readOnly = true)
  public ConcreteStrengthTest getConcreteStrengthTestById(Long id) {
    return concreteStrengthTestRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isConcreteStrengthTestExist(Long id) {
    return concreteStrengthTestRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Page<ConcreteStrengthTest> searchConcreteStrengthTest(Long finishProductSampleId,
      Status status, Double strengh, Double strenghMin, Double strenghMax, Double strenghGradeRatio,
      Double strenghGradeRatioMin, Double strenghGradeRatioMax, BooleanBuilder booleanBuilder,
      int page, int size) {
    if (finishProductSampleId != null) {
      booleanBuilder.and(QConcreteStrengthTest.concreteStrengthTest.id.eq(finishProductSampleId));
    }
    if (status != null) {
      booleanBuilder.and(QConcreteStrengthTest.concreteStrengthTest.status.eq(status));
    }
    if (strenghMax != null && strenghMax != 0 && strenghMin == null && strengh == null) {
      booleanBuilder.and(QConcreteStrengthTest.concreteStrengthTest.strength.lt(strenghMax));
    }
    if (strenghMin != null && strenghMin != 0 && strenghMax == null && strengh == null) {
      booleanBuilder.and(QConcreteStrengthTest.concreteStrengthTest.strength.gt(strenghMin));
    }

    if (strenghMin != null && strenghMin != 0 && strenghMax != null && strenghMax != null
        && strengh == null) {
      booleanBuilder
          .and(QConcreteStrengthTest.concreteStrengthTest.strength.between(strenghMin, strenghMax));
    }
    if (strengh != null && strengh != 0 && strenghMax == null && strenghMin == null) {
      booleanBuilder.and(QConcreteStrengthTest.concreteStrengthTest.strength.eq(strengh));
    }
    if (strenghGradeRatioMax != null && strenghGradeRatioMax != 0 && strenghGradeRatioMin == null
        && strenghGradeRatio == null) {
      booleanBuilder.and(
          QConcreteStrengthTest.concreteStrengthTest.strengthGradeRatio.lt(strenghGradeRatioMax));
    }
    if (strenghGradeRatioMin != null && strenghGradeRatioMin != 0 && strenghGradeRatioMax == null
        && strenghGradeRatio == null) {
      booleanBuilder.and(
          QConcreteStrengthTest.concreteStrengthTest.strengthGradeRatio.gt(strenghGradeRatioMin));
    }

    if (strenghGradeRatioMin != null && strenghGradeRatioMin != 0 && strenghGradeRatioMax != null
        && strenghGradeRatioMax != null && strenghGradeRatio == null) {
      booleanBuilder.and(QConcreteStrengthTest.concreteStrengthTest.strengthGradeRatio
          .between(strenghGradeRatioMin, strenghGradeRatioMax));
    }
    if (strenghGradeRatio != null && strenghGradeRatio != 0 && strenghGradeRatioMax == null
        && strenghGradeRatioMin == null) {
      booleanBuilder
          .and(QConcreteStrengthTest.concreteStrengthTest.strengthGradeRatio.eq(strenghGradeRatio));
    }
    return concreteStrengthTestRepository.findAll(booleanBuilder,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
}
