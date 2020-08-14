package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
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
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.FinishProductTestType;
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
  public FinishProductTrial getFinishProductTrialByCode(Long id) {
    return finishProductTrialRepository.findById(id).get();
  }

  public void saveFinishProductTrial(FinishProductTrial finishProductTrial) {
    finishProductTrialRepository.save(finishProductTrial);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTrial(Long id) {
    finishProductTrialRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTrialExists(Long id) {
    return finishProductTrialRepository.existsById(id);
  }

  public void updateMixDesignStatus(String mixDesignCode, Status status,
      String finishProductTestCode) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    MixDesign mixDesign = mixDesignRepository.findByCode(mixDesignCode);
    if (mixDesign.getStatus().equals(Status.NEW)) {
      mixDesign.setStatus(status);
      mixDesign.setTargetSlump(roundDoubleValue(averageValue(finishProductTestCode)));
    }
    mixDesignRepository.save(mixDesign);
  }

  public void updateFinishProductSampleStatus(String finishProductSampleCode, Status status) {
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleCode).get();
    finishProductSample.setStatus(status);
    finishProductSampleRepository.save(finishProductSample);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
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
    finishProductTrialRepository.save(finishProductTrial);
  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrialsByPlantCode(String plantCode) {
    return finishProductTrialRepository
        .findByFinishProductTestFinishProductSampleMixDesignPlantCode(plantCode);
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
        finishProductParameterResult
            .setResult(roundDoubleValue(averageValue(finishProductTestCode)));
      }
    }
    finishProductParameterResultRepository.save(finishProductParameterResult);
    saveGradeRatio(finishProductTestCode);
    checkAcceptedValue(testConfigure.getId(), finishProductTestCode);
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
    updateStatusAndValue(finishProductTestCode);
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
            finishProductParameterResultRepository
                .findByTestParameterIdAndFinishProductTestCode(testParameterRepository
                    .findByTestConfigureIdAndInputMethods(testConfigureId, InputMethod.CALCULATION)
                    .get(0).getId(), finishProductTestCode);
        for (FinishProductAcceptedValue finishProductAcceptedValue : finishProductAcceptedValueList) {
          checkFinishproductAcceptedValue(finishProductAcceptedValue.getMinValue(),
              finishProductAcceptedValue.getMaxValue(), finishProductAcceptedValue.getValue(),
              finishProductAcceptedValue.getConditionRange(),
              finishProductParameterResult.getResult(), finishProductTestCode);
        }
      }
    }
  }

  public void updateStatusAndValue(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    FinishProductParameterResult finishProductParameterResult =
        finishProductParameterResultRepository
            .findByTestParameterIdAndFinishProductTestCode(
                testParameterRepository
                    .findByTestConfigureIdAndInputMethods(
                        finishproductTest.getTestConfigure().getId(), InputMethod.OBSERVE)
                    .get(0).getId(),
                finishProductTestCode);
    if (finishproductTest.getTestConfigure().isCoreTest()
        && finishproductTest.getTestConfigure().isName()
        && finishproductTest.getFinishProductSample().getFinishProductTestType()
            .equals(FinishProductTestType.POST_PRODUCTION)) {
      if (finishproductTest.getStatus().equals(Status.PASS)) {
        finishProductSample.setStatus(finishproductTest.getStatus());
        finishProductSampleRepository.save(finishProductSample);
      } else {
        finishProductSample.setStatus(finishproductTest.getStatus());
        mixDesign.setStatus(finishproductTest.getStatus());
        finishProductSampleRepository.save(finishProductSample);
        mixDesignRepository.save(mixDesign);
      }
    } else if (finishproductTest.getTestConfigure().isCoreTest()
        && (!finishproductTest.getTestConfigure().isName())
        && finishproductTest.getFinishProductSample().getFinishProductTestType()
            .equals(FinishProductTestType.PRE_PRODUCTION)) {
      finishProductSample.setStatus(finishproductTest.getStatus());
      finishProductSampleRepository.save(finishProductSample);
    } else if (finishproductTest.getTestConfigure().isCoreTest()
        && (finishproductTest.getTestConfigure().isName())
        && finishproductTest.getFinishProductSample().getFinishProductTestType()
            .equals(FinishProductTestType.PRE_PRODUCTION)) {
      if (finishproductTest.getStatus().equals(Status.PASS)) {
        finishProductSample.setStatus(finishproductTest.getStatus());
        mixDesign.setTargetGrade(finishProductParameterResult.getResult());;
        mixDesignRepository.save(mixDesign);
        finishProductSampleRepository.save(finishProductSample);
      } 
      else {
        finishProductSample.setStatus(finishproductTest.getStatus());
        mixDesign.setStatus(finishproductTest.getStatus());
        finishProductSampleRepository.save(finishProductSample);
        mixDesignRepository.save(mixDesign);
      }
    } else if (!finishproductTest.getTestConfigure().isCoreTest()
        && (finishproductTest.getTestConfigure().isName())
        && finishproductTest.getFinishProductSample().getFinishProductTestType()
            .equals(FinishProductTestType.PRE_PRODUCTION)) {
      if (finishproductTest.getStatus().equals(Status.PASS)) {
        mixDesign.setTargetSlump(finishProductParameterResult.getResult());
        mixDesign.setStatus(Status.PROCESS);
        mixDesignRepository.save(mixDesign);
        finishProductSample.setStatus(Status.PROCESS);
        finishProductSampleRepository.save(finishProductSample);
      } else {
        mixDesign.setStatus(finishproductTest.getStatus());
        mixDesignRepository.save(mixDesign);
        finishProductSample.setStatus(Status.FAIL);
        finishProductSampleRepository.save(finishProductSample);
      }
    } else if (!finishproductTest.getTestConfigure().isCoreTest()
        && (finishproductTest.getTestConfigure().isName())
        && finishproductTest.getFinishProductSample().getFinishProductTestType()
            .equals(FinishProductTestType.POST_PRODUCTION)) {
      if (finishproductTest.getStatus().equals(Status.PASS)) {
        finishProductSample.setStatus(Status.PROCESS);
        finishProductSampleRepository.save(finishProductSample);
      } else {
        mixDesign.setStatus(finishproductTest.getStatus());
        mixDesignRepository.save(mixDesign);
        finishProductSample.setStatus(finishproductTest.getStatus());
        finishProductSampleRepository.save(finishProductSample);
      }
    }
  }
}
