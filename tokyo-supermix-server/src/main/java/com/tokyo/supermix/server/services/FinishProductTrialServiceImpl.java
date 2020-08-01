package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class FinishProductTrialServiceImpl implements FinishProductTrialService {
  @Autowired
  private FinishProductTrialRepository finishProductTrialRepository;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  FinishProductTestRepository finishProductTestRepository;
  @Autowired
  TestParameterRepository testParameterRepository;
  @Autowired
  FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  FinishProductParameterResultRepository finishProductParameterResultRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  TestConfigureRepository testConfigureRepository;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(String code) {
    return finishProductTrialRepository.findFinishProductTrialByCode(code);
  }

  @Transactional
  public String saveFinishProductTrial(FinishProductTrial finishProductTrial) {
    if (finishProductTrial.getCode() == null) {
      String prefix = finishProductTestRepository
          .getOne(finishProductTrial.getFinishProductTest().getCode()).getCode();
      List<FinishProductTrial> FinishProductTrialList =
          finishProductTrialRepository.findByCodeContaining(prefix);
      if (FinishProductTrialList.size() == 0) {
        finishProductTrial.setCode(prefix + String.format("%03d", 1));
      } else {
        finishProductTrial
            .setCode(prefix + String.format("%03d", maxNumberFromCode(FinishProductTrialList) + 1));
      }
    }
    finishProductTrialRepository.save(finishProductTrial);
    updateFinishProductResult(finishProductTrial);
    return finishProductTrial.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTrial> FinishProductTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    FinishProductTrialList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode()));
    });
    return Collections.max(list);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTrial(String code) {
    finishProductTrialRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTrialExists(String code) {
    return finishProductTrialRepository.existsByCode(code);
  }

  public void updateFinishProductResult(FinishProductTrial finishProductTrial) {
//    FinishProductTest finishProductTest = finishProductTestRepository
//        .findById(finishProductTrial.getFinishProductTest().getCode()).get();
//    FinishProductSample finishProductSample = finishProductSampleRepository
//        .findById(finishProductTest.getFinishProductSample().getCode()).get();
//    Boolean check = finishProductTest.getTestConfigure().isCoreTest();
//    if (finishProductTrial.getTestParameter() != null) {
//      FinishProductParameterResult finishProductParameterResult =
//          new FinishProductParameterResult();
//      finishProductParameterResult.setFinishProductSample(finishProductSample);
//      finishProductParameterResult.setTestParameter(finishProductTrial.getTestParameter());
//      if (check) {
//        finishProductParameterResult
//            .setResult(roundDoubleValue(averageResult(finishProductTest.getCode())));
//        finishProductTest.setStatus(Status.PROCESS);
//        finishProductSample.setStatus(
//            getSlumpTestStatus(finishProductTest.getFinishProductSample().getMixDesign().getCode(),
//                finishProductTrial.getValue()));
//        finishProductParameterResultRepository.save(finishProductParameterResult);
//        finishProductTest.setResult(finishProductParameterResult.getResult());
//        finishProductSampleRepository.save(finishProductSample);
//        updateMixDesignStatus(finishProductTest.getFinishProductSample().getMixDesign().getCode(),
//            finishProductTest.getFinishProductSample().getStatus());
//      } else {
//        finishProductParameterResult
//            .setResult(roundDoubleValue(averageResult(finishProductTest.getCode())));
//        finishProductParameterResultRepository.save(finishProductParameterResult);
//        finishProductTest.setResult(finishProductParameterResult.getResult());
//        finishProductSampleRepository.save(finishProductSample);
//      }
//    } else {
//      if (check) {
//        finishProductTest
//            .setResult(roundDoubleValue(strengthGradeRatio(finishProductTrial.getValue(),
//                finishProductSample.getMixDesign().getCode())));
//        finishProductTestRepository.save(finishProductTest);
//
//      } else {
//        finishProductTest.setResult(roundDoubleValue(slumpGradeRatio(finishProductTrial.getValue(),
//            finishProductSample.getMixDesign().getCode())));
//        finishProductTestRepository.save(finishProductTest);
//      }
//    }
  }

  public double averageResult(String finidhProductTestCode) {
    List<FinishProductTrial> finishProductTrialList =
        finishProductTrialRepository.findByFinishProductTestCode(finidhProductTestCode);
    double Result = 0;
    double trialNo = 0;
    for (FinishProductTrial finishProductTrial : finishProductTrialList) {
      Result = Result + finishProductTrial.getValue();
      trialNo++;
    }
    return Result / trialNo;
  }

  public void updateMixDesignStatus(String mixDesignCode, Status status) {
    MixDesign mixDesign = mixDesignRepository.findByCode(mixDesignCode);
    if (mixDesign.getStatus().equals(Status.NEW)) {
      mixDesign.setStatus(status);
    }
    mixDesignRepository.save(mixDesign);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  private double getTargetSlump(String mixDesignCode) {
    return mixDesignRepository.findByCode(mixDesignCode).getTargetSlump();
  }

  private double getTargetGrade(String mixDesignCode) {
    return mixDesignRepository.findByCode(mixDesignCode).getTargetGrade();
  }

  private Status getSlumpTestStatus(String mixDesignCode, Double slump) {
    Double targetSlump = getTargetSlump(mixDesignCode);
    if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
      return Status.PASS;
    } else {
      return Status.FAIL;
    }
  }

  public double slumpGradeRatio(double trialslumpValue, String mixDesignCode) {
    return trialslumpValue / getTargetSlump(mixDesignCode);
  }

  public double strengthGradeRatio(double trialStrengthValue, String mixDesignCode) {
    return trialStrengthValue / getTargetGrade(mixDesignCode);

  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode) {
    return finishProductTrialRepository.findByFinishProductTestCode(finishProductTestCode);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExists(String finishProductTestCode) {
    return finishProductTrialRepository.existsByFinishProductTestCode(finishProductTestCode);
  }

  @Transactional
  public Status upadateFinishProductStatusByFinishProductCode(String finishProductTestCode) {
    return null;
//    FinishProductTest finishProductTest = finishProductTestRepository.getOne(finishProductTestCode);
//    finishProductTest.setStatus(Status.COMPLETED);
//    finishProductTestRepository.save(finishProductTest);
//    emailNotification.sendFinishProductTestEmail(finishProductTest);
//    return finishProductTest.getStatus();
  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrialsByPlant(UserPrincipal currentUser) {
    return finishProductTrialRepository
        .findByFinishProductTestFinishProductSampleMixDesignPlantCodeIn(
            currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
                PermissionConstants.VIEW_MATERIAL_TEST_TRIAL));
  }

  @Transactional
  public void updateFinishProductTestTrial(FinishProductTrial finishProductTrial) {
    finishProductTestRepository.findById(finishProductTrial.getFinishProductTest().getCode()).get()
        .setStatus(Status.PROCESS);
    updateFinishProductResult(finishProductTrial);
    finishProductTrialRepository.save(finishProductTrial);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrialsByPlantCode(String plantCode) {
    return finishProductTrialRepository
        .findByFinishProductTestFinishProductSampleMixDesignPlantCode(plantCode);
  }
}
