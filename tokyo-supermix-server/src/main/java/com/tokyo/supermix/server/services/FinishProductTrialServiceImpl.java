package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductAcceptedValue;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.MixDesignField;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.FinishProductAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestEquationRepository;
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
  @Autowired
  TestEquationRepository testEquationRepository;
  @Autowired
  FinishProductAcceptedValueRepository finishProductAcceptedValueRepository;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(String code) {
    return finishProductTrialRepository.findFinishProductTrialByCode(code);
  }

  @Transactional
  public void saveFinishProductTrial(FinishProductTrial finishProductTrial) {
    // if (finishProductTrial.getCode() == null) {
    // String prefix = finishProductTestRepository
    // .getOne(finishProductTrial.getFinishProductTest().getCode()).getCode();
    // List<FinishProductTrial> FinishProductTrialList =
    // finishProductTrialRepository.findByCodeContaining(prefix);
    //
    // if (FinishProductTrialList.size() == 0) {
    // finishProductTrial.setCode(prefix + String.format("%04d", 1));
    // finishProductTrial.setTrialNo(1l);
    // } else {
    // finishProductTrial
    // .setCode(prefix + String.format("%04d", maxNumberFromCode(FinishProductTrialList) + 1));
    // finishProductTrial.setTrialNo(maxNumberFromCode(FinishProductTrialList).longValue() + 1l);
    // }
    finishProductTrialRepository.save(finishProductTrial);
    // }
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTrial> finishProductTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductTrialList.forEach(obj -> {
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

  // private Status getSlumpTestStatus(String mixDesignCode, Double slump) {
  // Double targetSlump = getTargetSlump(mixDesignCode);
  // if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
  // return Status.PASS;
  // } else {
  // return Status.FAIL;
  // }
  // }

  // public double slumpGradeRatio(double trialslumpValue, String mixDesignCode) {
  // return trialslumpValue / getTargetSlump(mixDesignCode);
  // }
  //
  // public double strengthGradeRatio(double trialStrengthValue, String mixDesignCode) {
  // return trialStrengthValue / getTargetGrade(mixDesignCode);
  //
  // }

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
    // FinishProductTest finishProductTest =
    // finishProductTestRepository.getOne(finishProductTestCode);
    // finishProductTest.setStatus(Status.COMPLETED);
    // finishProductTestRepository.save(finishProductTest);
    // emailNotification.sendFinishProductTestEmail(finishProductTest);
    // return finishProductTest.getStatus();
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
    // updateFinishProductResult(finishProductTrial);
    finishProductTrialRepository.save(finishProductTrial);
  }

  @Transactional
  public void saveFinishproductResult(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    TestConfigure testConfigure =
        testConfigureRepository.findById(finishproductTest.getTestConfigure().getId()).get();
    List<TestParameter> testParameterList =
        testParameterRepository.findByTestConfigureId(testConfigure.getId());
    FinishProductParameterResult finishProductParameterResult = new FinishProductParameterResult();
    for (TestParameter testParameter : testParameterList) {
      if (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
          && testParameter.getType().equals(TestParameterType.INPUT)
          && testParameter.getMixDesignField() == null) {
        finishProductParameterResult.setFinishProductTest(finishproductTest);
        finishProductParameterResult.setTestParameter(testParameter);
        finishProductParameterResult.setResult(averageValue(finishProductTestCode));
      }
    }
    finishProductParameterResultRepository.save(finishProductParameterResult);
    saveGradeRatio(finishProductTestCode);
    checkAcceptedValue(testConfigure.getId(),finishProductTestCode);
  }

  public void saveGradeRatio(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    TestConfigure testConfigure =
        testConfigureRepository.findById(finishproductTest.getTestConfigure().getId()).get();
    List<TestParameter> testParameterList =
        testParameterRepository.findByTestConfigureId(testConfigure.getId());
    FinishProductParameterResult finishProductParameterResult = new FinishProductParameterResult();
    for (TestParameter testParameter : testParameterList) {
      if (testParameter.getInputMethods().equals(InputMethod.CALCULATION)
          && testParameter.getType().equals(TestParameterType.RESULT)
          && testParameter.getMixDesignField() == null) {
        finishProductParameterResult.setFinishProductTest(finishproductTest);
        finishProductParameterResult.setTestParameter(testParameter);
        finishProductParameterResult.setResult(getFinishProductResult(finishProductTestCode));
      }
    }
    finishProductParameterResultRepository.save(finishProductParameterResult);
  }

  public double averageValue(String finishProductTestCode) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    Long noOfTrial = finishProductTest.getNoOfTrial();
    System.out.println();
    double sumOfValue = 0.0;
    Long count = (long) 0;
    List<FinishProductTrial> finishProductTrialList =
        finishProductTrialRepository.findByFinishProductTestCode(finishProductTestCode);
    for (FinishProductTrial finishProductTrial : finishProductTrialList) {
      sumOfValue = sumOfValue + finishProductTrial.getValue();
      count++;
    }
    return (sumOfValue / noOfTrial);
  }


  public String getFormula(String finishProductTestCode) {
    List<FinishProductTrial> finishProductTrialList =
        finishProductTrialRepository.findByFinishProductTestCode(finishProductTestCode);
    String equation = " ";
    for (FinishProductTrial finishProductTrial : finishProductTrialList) {
      equation = testEquationRepository
          .findByTestConfigureId(
              finishProductTrial.getFinishProductTest().getTestConfigure().getId())
          .get(0).getEquation().getFormula();
    }
    return equation;
  }

  public double getFinishProductResult(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    TestConfigure testConfigure =
        testConfigureRepository.findById(finishproductTest.getTestConfigure().getId()).get();
    List<TestParameter> testParameterList =
        testParameterRepository.findByTestConfigureId(testConfigure.getId());
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
    double finishProductResult = 0.0;
    MixDesign mixDesign = mixDesignRepository
        .findByCode(finishproductTest.getFinishProductSample().getMixDesign().getCode());
    for (TestParameter testParameter : testParameterList) {
      if (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
          && testParameter.getType().equals(TestParameterType.INPUT)
          && testParameter.getMixDesignField() == null) {
        engine.put(testParameter.getAbbreviation(), averageValue(finishProductTestCode));
      }
      if (testParameter.getMixDesignField() != null) {
        if (testParameter.getType().equals(TestParameterType.INPUT)
            && testParameter.getInputMethods().equals(InputMethod.OBSERVE)
            && testParameter.getMixDesignField().equals(MixDesignField.TARGETSLUMP)) {
          engine.put(testParameter.getAbbreviation(), mixDesign.getTargetSlump());
        } else if (testParameter.getType().equals(TestParameterType.INPUT)
            && testParameter.getInputMethods().equals(InputMethod.OBSERVE)
            && testParameter.getMixDesignField().equals(MixDesignField.TARGETGRADE)) {
          engine.put(testParameter.getAbbreviation(), mixDesign.getTargetGrade());
        }
      }
    }
    try {
      finishProductResult = (double) engine.eval(getFormula(finishProductTestCode));
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return roundDoubleValue(finishProductResult);
  }


  public void checkFinishproductAcceptedValue(Double minValue, Double maxValue, Double value,
      Condition condition, Double gradeRatio, String finishProductTestCode) {
    if (condition == Condition.BETWEEN) {
      if (minValue <= gradeRatio && maxValue >= gradeRatio) {
        updateStatus(finishProductTestCode, Status.PASS);
      } else {
        updateStatus(finishProductTestCode, Status.FAIL);
      }
    } else if (condition == Condition.EQUAL) {
      if (value == gradeRatio) {
        updateStatus(finishProductTestCode, Status.PASS);
      } else {
        updateStatus(finishProductTestCode, Status.FAIL);
      }
    } else if (condition == Condition.GREATER_THAN) {
      if (value <= gradeRatio) {
        updateStatus(finishProductTestCode, Status.PASS);
      } else {
        updateStatus(finishProductTestCode, Status.FAIL);
      }

    } else if (condition == Condition.LESS_THAN) {
      if (value >= gradeRatio) {
        updateStatus(finishProductTestCode, Status.PASS);
      } else {
        updateStatus(finishProductTestCode, Status.FAIL);
      }
    }
  }

  public void updateStatus(String finishProductTestCode, Status status) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    finishProductTest.setStatus(status);
    finishProductTestRepository.save(finishProductTest);
  }

  public void checkAcceptedValue(Long testConfigureId, String finishProductTestCode) {
    List<TestParameter> testParamterList =
        testParameterRepository.findByTestConfigureId(testConfigureId);
    for (TestParameter testParameter : testParamterList) {
      if (testParameter.getInputMethods().equals(InputMethod.CALCULATION)
          && testParameter.getType().equals(TestParameterType.RESULT)) {
        List<FinishProductAcceptedValue> finishProductAcceptedValueList =
            finishProductAcceptedValueRepository.findByTestParameterId(testParameter.getId());
        FinishProductParameterResult finishProductParameterResult =
            finishProductParameterResultRepository.findByTestParameterId(testParameter.getId());
        for (FinishProductAcceptedValue finishProductAcceptedValue : finishProductAcceptedValueList) {
          checkFinishproductAcceptedValue(finishProductAcceptedValue.getMinValue(),
              finishProductAcceptedValue.getMaxValue(), finishProductAcceptedValue.getValue(),
              finishProductAcceptedValue.getConditionRange(),
              finishProductParameterResult.getResult(), finishProductTestCode);
        }
      }
    }
  }
}
