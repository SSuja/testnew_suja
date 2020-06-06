package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.entities.ConcreteTestType;
import com.tokyo.supermix.data.entities.CubeTestFinding;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.QConcreteTestResult;
import com.tokyo.supermix.data.enums.ConcreteStatus;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteTestRepository;
import com.tokyo.supermix.data.repositories.ConcreteTestResultRepository;
import com.tokyo.supermix.data.repositories.ConcreteTestStatusRepository;
import com.tokyo.supermix.data.repositories.ConcreteTestTypeRespository;
import com.tokyo.supermix.data.repositories.CubeTestFindingRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class ConcreteTestResultServiceImpl implements ConcreteTestResultService {
  @Autowired
  private ConcreteTestResultRepository concreteTestResultRepository;
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private CubeTestFindingRepository cubeTestFindingRepository;
  @Autowired
  private ConcreteTestStatusRepository concreteTestStatusRepository;
  @Autowired
  private ConcreteTestRepository concreteTestRepository;
  @Autowired
  private ConcreteTestTypeRespository concreteTestTypeRespository;
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;

  @Transactional
  public void saveConcreteTestResult(ConcreteTestResult concreteTestResult) {
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestResult> getAllConcreteTestResults() {
    return concreteTestResultRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ConcreteTestResult getConcreteTestResultById(Long id) {
    return concreteTestResultRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteTestResult(Long id) {
    concreteTestResultRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestResultExists(Long id) {
    return concreteTestResultRepository.existsById(id);
  }

  public void setConcreteTestSlumpStatus(Long finishProductSampleId, Status status) {
    ConcreteTestStatus concreteTestStatus = concreteTestStatusRepository
        .findByConcreteTestTypeTypeAndFinishProductSampleId(Constants.SLUMP, finishProductSampleId);
    if (status.equals(null)) {
      concreteTestStatus.setConcreteStatus(ConcreteStatus.PROGRESS);
    } else if (status.equals(Status.PASS)) {
      concreteTestStatus.setConcreteStatus(ConcreteStatus.COMPLETED);
    } else if (status.equals(Status.FAIL)) {
      concreteTestStatus.setConcreteStatus(ConcreteStatus.INCOMPLETED);
    }
  }

  @Transactional
  public void saveConcreteTestStrengthStatus(Long finishProductSampleId) {
    ConcreteTestType concreteTestType = concreteTestTypeRespository.findByType(Constants.STRENGTH);
    ConcreteTestStatus concreteTestStatus = new ConcreteTestStatus();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    concreteTestStatus.setConcreteTestType(concreteTestType);
    concreteTestStatus.setFinishProductSample(finishProductSample);
    concreteTestStatus.setConcreteStatus(ConcreteStatus.NEW);
    concreteTestStatusRepository.save(concreteTestStatus);
  }

  @Transactional
  public void saveConcreteTestSlumpStatus(Long finishProductSampleId) {
    ConcreteTestType concreteTestType = concreteTestTypeRespository.findByType(Constants.SLUMP);
    ConcreteTestStatus concreteTestStatus = new ConcreteTestStatus();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    concreteTestStatus.setConcreteTestType(concreteTestType);
    concreteTestStatus.setFinishProductSample(finishProductSample);
    concreteTestStatus.setConcreteStatus(ConcreteStatus.NEW);
    concreteTestStatusRepository.save(concreteTestStatus);
  }

  @Transactional
  public void saveConcreteTestMoistureStatus(Long finishProductSampleId) {
    ConcreteTestType concreteTestType = concreteTestTypeRespository.findByType(Constants.MOISTURE);
    ConcreteTestStatus concreteTestStatus = new ConcreteTestStatus();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    concreteTestStatus.setConcreteTestType(concreteTestType);
    concreteTestStatus.setFinishProductSample(finishProductSample);
    concreteTestStatus.setConcreteStatus(ConcreteStatus.NEW);
    concreteTestStatusRepository.save(concreteTestStatus);
  }

  public void setConcreteTestStrengthStatus(Long finishProductSampleId, Status status,
      Long concreteAge) {
    ConcreteTestStatus concreteTestStatus =
        concreteTestStatusRepository.findByConcreteTestTypeTypeAndFinishProductSampleId(
            Constants.STRENGTH, finishProductSampleId);
    if ((concreteAge == 28 || concreteAge == 7) && status.equals(Status.PASS)) {
      concreteTestStatus.setConcreteStatus(ConcreteStatus.COMPLETED);
    } else if ((concreteAge == 28 || concreteAge == 7) && status.equals(Status.FAIL)) {
      concreteTestStatus.setConcreteStatus(ConcreteStatus.INCOMPLETED);
    } else {
      concreteTestStatus.setConcreteStatus(ConcreteStatus.PROGRESS);
    }
  }

  @Transactional
  public void saveConcreteSlumpTestWaterCementRatioResult(ConcreteTestResult concreteTestResult) {
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(concreteTestResult.getFinishProductSample().getId()).get();
    List<MixDesignProportion> mixDesignProportionList = mixDesignProportionRepository
        .findByMixDesignCode(finishProductSample.getMixDesign().getCode());
    Long quantity = null;
    for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {
      if (mixDesignProportion.getRawMaterial().getName()
          .equalsIgnoreCase(Constants.RAW_MATERIAL_CEMENT)) {
        quantity = mixDesignProportion.getQuantity();
        concreteTestResult.setResult(calculateWaterCementRatio(concreteTestResult.getWaterContent(),
            quantity.doubleValue()));
      }
    }
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.WATER_CEMENT_RATIO);
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    ConcreteTestResult concreteTestResultSlump =
        concreteTestResultRepository.findByFinishProductSampleIdAndConcreteTestName(
            concreteTestResult.getFinishProductSample().getId(), Constants.SLUMP_TEST);
    if (concreteTestResultSlump.getStatus().equals(Status.PASS)) {
      concreteTestResult.setStatus(Status.PASS);
    } else if (concreteTestResultSlump.getStatus().equals(Status.FAIL)) {
      concreteTestResult.setStatus(Status.FAIL);
    }
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional
  public void saveConcreteTestWaterBinderRatioResult(ConcreteTestResult concreteTestResult) {
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(concreteTestResult.getFinishProductSample().getId()).get();
    List<MixDesignProportion> mixDesignProportionList = mixDesignProportionRepository
        .findByMixDesignCode(finishProductSample.getMixDesign().getCode());
    Long quantity = null;
    Long binderquantity = 0L;
    for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {

      if (mixDesignProportion.getRawMaterial().getName()
          .equalsIgnoreCase(Constants.RAW_MATERIAL_CEMENT)) {
        quantity = mixDesignProportion.getQuantity();
      }
      if (mixDesignProportion.getRawMaterial().getName()
          .equalsIgnoreCase(Constants.RAW_MATERIAL_FLYASH)) {
        binderquantity = mixDesignProportion.getQuantity();
      }
    }
    concreteTestResult.setResult(calculateWaterBinderRatio(concreteTestResult.getWaterContent(),
        binderquantity.doubleValue(), quantity.doubleValue()));
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.WATER_BINDER_RATIO);
    ConcreteTestResult concreteTestResultSlump =
        concreteTestResultRepository.findByFinishProductSampleIdAndConcreteTestName(
            concreteTestResult.getFinishProductSample().getId(), Constants.SLUMP_TEST);
    if (concreteTestResultSlump.getStatus().equals(Status.PASS)) {
      concreteTestResult.setStatus(Status.PASS);
    } else if (concreteTestResultSlump.getStatus().equals(Status.FAIL)) {
      concreteTestResult.setStatus(Status.FAIL);
    }
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional
  public void saveConcreteSlumpTestSlumpResult(ConcreteTestResult concreteTestResult) {
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.SLUMP_TEST);
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    concreteTestResult.setStatus(calculateConcreteStatus(
        concreteTestResult.getFinishProductSample().getId(), concreteTestResult.getResult()));
    setConcreteTestSlumpStatus(concreteTestResult.getFinishProductSample().getId(),
        concreteTestResult.getStatus());
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional
  public void saveConcreteMoistureResult(ConcreteTestResult concreteTestResult) {
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.MOISTURE);
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional
  public void saveConcreteSlumpTestSlumpGradeRatioResult(ConcreteTestResult concreteTestResult) {
    concreteTestResult.setResult(calculateSlumpGradeRatio(
        concreteTestResult.getFinishProductSample().getId(), concreteTestResult.getResult()));
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.SLUMP_GRADE_RATIO);
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    ConcreteTestResult concreteTestResultSlump =
        concreteTestResultRepository.findByFinishProductSampleIdAndConcreteTestName(
            concreteTestResult.getFinishProductSample().getId(), Constants.SLUMP_TEST);
    if (concreteTestResultSlump.getStatus().equals(Status.PASS)) {
      concreteTestResult.setStatus(Status.PASS);
    } else if (concreteTestResultSlump.getStatus().equals(Status.FAIL)) {
      concreteTestResult.setStatus(Status.FAIL);
    }
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional
  public void saveConcreteStrengthTestAverageStrengthResult(ConcreteTestResult concreteTestResult) {
    concreteTestResult.setResult(roundDoubleValue(calculateAverageCubeStrength(
        concreteTestResult.getFinishProductSample().getId(), concreteTestResult.getAge())));
    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);
    concreteTestResult.setDate(date);
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.STRENGTH_TEST);
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    concreteTestResultRepository.save(concreteTestResult);
  }

  @Transactional
  public void saveConcreteStrengthTestStrengthGradeRatioResult(
      ConcreteTestResult concreteTestResult) {
    concreteTestResult.setResult(roundDoubleValue(calculateCubeStrengthRatio(
        concreteTestResult.getFinishProductSample().getId(), concreteTestResult.getAge())));
    calculateConcreteStrengthStatus(concreteTestResult);
    long millis = System.currentTimeMillis();
    java.sql.Date date = new java.sql.Date(millis);
    concreteTestResult.setDate(date);
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.STRENGTH_GRADE_RATIO);
    concreteTestResult.getConcreteTest().setId(concreteTest.getId());
    setConcreteTestStrengthStatus(concreteTestResult.getFinishProductSample().getId(),
        concreteTestResult.getStatus(), concreteTestResult.getAge());
    concreteTestResultRepository.save(concreteTestResult);
  }

  private Double calculateWaterCementRatio(Double waterContent, Double cementQuantity) {
    return roundDoubleValue(100 * waterContent / cementQuantity);
  }

  private Double calculateWaterBinderRatio(Double waterContent, Double binderquantity,
      Double quantity) {
    return roundDoubleValue(100 * waterContent / (quantity + binderquantity));
  }

  private Double calculateSlumpGradeRatio(Long finishProductSampleId, Double slump) {
    Double targetSlump = finishProductSampleRepository.findById(finishProductSampleId).get()
        .getMixDesign().getTargetSlump();
    return roundDoubleValue(slump / targetSlump);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  private void calculateConcreteStrengthStatus(ConcreteTestResult concreteTestResult) {
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(concreteTestResult.getFinishProductSample().getId()).get();
    Double ratio = concreteTestResult.getResult();
    Long concreteAge = concreteTestResult.getAge();
    if ((ratio >= 0.16 && concreteAge == 1) || (ratio >= 0.40 && concreteAge == 3)
        || (ratio >= 0.50 && concreteAge == 5) || (ratio >= 0.65 && concreteAge == 7)
        || (ratio >= 0.90 && concreteAge == 14) || (ratio >= 0.94 && concreteAge == 21)
        || (ratio >= 0.99 && concreteAge == 28) || (ratio >= 1 && concreteAge == 56)
        || (ratio >= 1 && concreteAge == 128)) {
      concreteTestResult.setStatus(Status.PASS);
      String messsage = "Congrete Strength Test is " + concreteTestResult.getStatus()
          + " for the mixdesign code is " + finishProductSample.getMixDesign().getCode()
          + "<ul><li> Age : " + concreteTestResult.getAge() + " days </li>" + "<li> Strength : "
          + concreteTestResult.getResult() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
          Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
    } else if (ratio > 0) {
      concreteTestResult.setStatus(Status.FAIL);
      String messsage = "Congrete Strength Test is " + concreteTestResult.getStatus()
          + " for the mixdesign code is " + finishProductSample.getMixDesign().getCode()
          + "<ul><li> Age : " + concreteTestResult.getAge() + "days </li>" + "<li> Strength : "
          + concreteTestResult.getResult() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailCongreteStrengthTestStatus(),
          Constants.SUBJECT_NEW_CONGRETE_STRENGTH_TEST, messsage);
    }
  }

  public Double calculateAverageCubeStrength(Long finishProductSampleId, Long concretAge) {
    List<CubeTestFinding> CubeTestFindingList =
        cubeTestFindingRepository.findByFinishProductSampleId(finishProductSampleId);
    double cubeResult = 0;
    double cubeTotalNum = 0;
    for (CubeTestFinding cubeTestFinding : CubeTestFindingList) {
      if (cubeTestFinding.getAge() == concretAge) {
        cubeResult = cubeResult + cubeTestFinding.getValue();
        cubeTotalNum++;
      }
    }
    return (cubeResult / cubeTotalNum);
  }

  public Double getTargetGradre(Long finishProductSampleId) {
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    return finishProductSample.getMixDesign().getTargetGrade();
  }

  private Status calculateConcreteStatus(Long finishProductSampleId, Double slump) {
    Double targetSlump = finishProductSampleRepository.findById(finishProductSampleId).get()
        .getMixDesign().getTargetSlump();
    if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
      return Status.PASS;
    } else {
      return Status.FAIL;
    }
  }

  public Double calculateCubeStrengthRatio(Long finishProductSampleId, Long age) {
    return calculateAverageCubeStrength(finishProductSampleId, age)
        / getTargetGradre(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public Page<ConcreteTestResult> searchConcreteTestResult(Long finishProductSampleId,
      Long ConcreteTestId, Status status, Double result, Double resultMin, Double resultMax,
      BooleanBuilder booleanBuilder, int page, int size) {
    if (finishProductSampleId != null) {
      booleanBuilder.and(QConcreteTestResult.concreteTestResult.id.eq(finishProductSampleId));
    }
    if (ConcreteTestId != null) {
      booleanBuilder.and(QConcreteTestResult.concreteTestResult.id.eq(ConcreteTestId));
    }
    if (status != null) {
      booleanBuilder.and(QConcreteTestResult.concreteTestResult.status.eq(status));
    }
    if (resultMax != null && resultMax != 0 && resultMin == null && result == null) {
      booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.lt(resultMax));
    }
    if (resultMin != null && resultMin != 0 && resultMax == null && result == null) {
      booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.gt(resultMin));
    }

    if (resultMin != null && resultMin != 0 && resultMax != null && resultMax != null
        && result == null) {
      booleanBuilder
          .and(QConcreteTestResult.concreteTestResult.result.between(resultMin, resultMax));
    }
    if (result != null && result != 0 && resultMax == null && resultMin == null) {
      booleanBuilder.and(QConcreteTestResult.concreteTestResult.result.eq(result));
    }
    return concreteTestResultRepository.findAll(booleanBuilder,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public ConcreteTestResult findByConcreteTestId(Long concreteTestId) {
    return concreteTestResultRepository.findByConcreteTestId(concreteTestId);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestResult> findByConcreteTestTypeId(Long concreteTestTypeId) {
    return concreteTestResultRepository.findByConcreteTestConcreteTestTypeId(concreteTestTypeId);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestResult> findByFinishProductSampleId(Long finishProductSampleId) {
    return concreteTestResultRepository.findByFinishProductSampleId(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public List<ConcreteTestResult> getConcreteTestResultByConcreteTestConcreteTestTypeIdAndFinishProductSampleId(
      Long concreteTestTypeId, Long finishProductSampleId) {
    return concreteTestResultRepository
        .findByConcreteTestConcreteTestTypeIdAndFinishProductSampleId(concreteTestTypeId,
            finishProductSampleId);
  }
}
