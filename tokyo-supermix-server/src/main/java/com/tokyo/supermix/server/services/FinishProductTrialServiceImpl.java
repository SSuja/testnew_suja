package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.CoreTestConfigure;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignConfirmationToken;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.entities.TestEquationParameter;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.enums.TestResultType;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MixDesignConfirmationTokenRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.MultiResultFormulaRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestEquationParameterRepository;
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
  MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  AcceptedValueRepository acceptedValueRepository;
  @Autowired
  TestParameterService testParameterService;
  @Autowired
  ParameterEquationRepository parameterEquationRepository;
  @Autowired
  CoreTestConfigureRepository coreTestConfigureRepository;
  @Autowired
  MixDesignConfirmationTokenRepository mixDesignConfirmationTokenRepository;
  @Autowired
  MultiResultFormulaRepository multiResultFormulaRepository;
  @Autowired
  TestEquationParameterRepository testEquationParameterRepository;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(Long id) {
    return finishProductTrialRepository.findById(id).get();
  }

  public void saveFinishProductTrial(List<FinishProductTrial> finishProductTrialList) {
    if (finishProductTrialRepository.existsByFinishProductTestCode(
        finishProductTrialList.get(0).getFinishProductTest().getCode())
        && (finishProductTrialRepository.findByFinishProductTestCodeAndTestParameterIdAndTrialNo(
            finishProductTrialList.get(0).getFinishProductTest().getCode(),
            finishProductTrialList.get(0).getTestParameter().getId(),
            finishProductTrialList.get(0).getTrialNo()).size() == 1)) {
      for (FinishProductTrial finishProductTrial : finishProductTrialList) {
        List<FinishProductTrial> finishProductTrialAlready =
            finishProductTrialRepository.findByFinishProductTestCodeAndTestParameterIdAndTrialNo(
                finishProductTrial.getFinishProductTest().getCode(),
                finishProductTrial.getTestParameter().getId(), finishProductTrial.getTrialNo());
        finishProductTrialAlready.get(0).setDateValue(finishProductTrial.getDateValue());
        finishProductTrialAlready.get(0).setValue(finishProductTrial.getValue());
        finishProductTrialRepository.save(finishProductTrialAlready.get(0));
      }
    } else {
      FinishProductTest finishProductTest = finishProductTestRepository
          .findById(finishProductTrialList.get(0).getFinishProductTest().getCode()).get();
      finishProductTest.setStatus(Status.PROCESS);
      finishProductTestRepository.save(finishProductTest);
      finishProductTrialRepository.saveAll(finishProductTrialList);
    }
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTrial(Long id) {
    finishProductTrialRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTrialExists(Long id) {
    return finishProductTrialRepository.existsById(id);
  }

  public void updateFinishProductSampleStatus(String finishProductSampleCode, Status status) {
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleCode).get();
    finishProductSample.setStatus(status);
    finishProductSampleRepository.save(finishProductSample);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT_FINISH);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode) {
    return finishProductTrialRepository
        .findByFinishProductTestCodeOrderByCreatedAtDesc(finishProductTestCode);
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
                PermissionConstants.VIEW_FINISH_PRODUCT_TRAIL));
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
  public void saveFinishproductResult(String finishProductTestCode, HttpServletRequest request) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    TestConfigure testConfigure =
        testConfigureRepository.findById(finishproductTest.getTestConfigure().getId()).get();
    List<TestParameter> testParameterList =
        testParameterRepository.findByTestConfigureId(testConfigure.getId());
    for (TestParameter testParameter : testParameterList) {
      if (testParameter.getParameter().getParameterDataType().equals(ParameterDataType.NUMBER)) {
        if (testParameterService.checkEqutaionExistsForTest(
            testParameter.getTestConfigure().getId()) == Constants.CHECK_EQUATION_TRUE) {
          if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
              && testParameter.getType().equals(TestParameterType.RESULT))) {
            if (finishProductParameterResultRepository
                .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                    finishProductTestCode) != null) {
              FinishProductParameterResult finishProductAlready =
                  finishProductParameterResultRepository
                      .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                          finishProductTestCode);
              finishProductAlready.setResult(roundDoubleValue(
                  averageValueForResultObserve(finishProductTestCode, testParameter.getId())));
              finishProductParameterResultRepository.save(finishProductAlready);
            } else {
              FinishProductParameterResult finishProductParameterResult =
                  new FinishProductParameterResult();
              finishProductParameterResult.setFinishProductTest(finishproductTest);
              finishProductParameterResult.setTestParameter(testParameter);
              finishProductParameterResult.setResult(roundDoubleValue(
                  averageValueForResultObserve(finishProductTestCode, testParameter.getId())));
              finishProductParameterResultRepository.save(finishProductParameterResult);
            }
          }
          if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
              && testParameter.getType().equals(TestParameterType.RESULT))) {
            if (finishProductParameterResultRepository
                .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                    finishProductTestCode) != null) {
              FinishProductParameterResult finishProductAlready =
                  finishProductParameterResultRepository
                      .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                          finishProductTestCode);
              finishProductAlready.setResult(
                  roundDoubleValue(averageValue(finishProductTestCode, testParameter.getId())));
              finishProductParameterResultRepository.save(finishProductAlready);
            } else {
              FinishProductParameterResult finishProductParameterResult =
                  new FinishProductParameterResult();
              finishProductParameterResult.setFinishProductTest(finishproductTest);
              finishProductParameterResult.setTestParameter(testParameter);
              finishProductParameterResult.setResult(
                  roundDoubleValue(averageValue(finishProductTestCode, testParameter.getId())));
              finishProductParameterResultRepository.save(finishProductParameterResult);
            }
          }

        }
        if (testParameterService.checkEqutaionExistsForTest(
            testParameter.getTestConfigure().getId()) == Constants.CHECK_EQUATION_FALSE) {
          if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
              && testParameter.getType().equals(TestParameterType.RESULT))) {
            if (finishProductParameterResultRepository
                .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                    finishProductTestCode) != null) {
              FinishProductParameterResult finishProductAlready =
                  finishProductParameterResultRepository
                      .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                          finishProductTestCode);
              finishProductAlready.setResult(
                  roundDoubleValue(averageValue(finishProductTestCode, testParameter.getId())));
              finishProductParameterResultRepository.save(finishProductAlready);
            } else {
              FinishProductParameterResult finishProductParameterResult =
                  new FinishProductParameterResult();
              finishProductParameterResult.setFinishProductTest(finishproductTest);
              finishProductParameterResult.setTestParameter(testParameter);
              finishProductParameterResult.setResult(
                  roundDoubleValue(averageValue(finishProductTestCode, testParameter.getId())));
              finishProductParameterResultRepository.save(finishProductParameterResult);
            }
          }
        }
      } else {
        if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
            && testParameter.getType().equals(TestParameterType.RESULT))) {
          if (finishProductParameterResultRepository.findByTestParameterIdAndFinishProductTestCode(
              testParameter.getId(), finishProductTestCode) != null) {
            FinishProductParameterResult finishProductAlready =
                finishProductParameterResultRepository
                    .findByTestParameterIdAndFinishProductTestCode(testParameter.getId(),
                        finishProductTestCode);
            finishProductAlready.setResult(roundDoubleValue(finalDateValue(finishProductTestCode,
                testParameter.getId(), finishproductTest.getNoOfTrial())));
            finishProductParameterResultRepository.save(finishProductAlready);
          } else {
            FinishProductParameterResult finishProductParameterResult =
                new FinishProductParameterResult();
            finishProductParameterResult.setFinishProductTest(finishproductTest);
            finishProductParameterResult.setTestParameter(testParameter);
            finishProductParameterResult
                .setResult(roundDoubleValue(finalDateValue(finishProductTestCode,
                    testParameter.getId(), finishproductTest.getNoOfTrial())));
            finishProductParameterResultRepository.save(finishProductParameterResult);
          }
        }
      }
    }
    checkAcceptedValue(testConfigure.getId(), finishProductTestCode, request);
  }

  public double averageValueForResultObserve(String finishProductTestCode, Long testParameterId) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    Long noOfTrial = finishProductTest.getNoOfTrial();
    List<Double> trialValue = new ArrayList<Double>();
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeAndTestParameterIdOrderByCreatedAtDesc(finishProductTestCode,
            testParameterId)) {
      if (finishProductTrial.getTestParameter().getParameter().getParameterDataType()
          .equals(ParameterDataType.NUMBER)) {
        if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.OBSERVE)
            && finishProductTrial.getTestParameter().getType().equals(TestParameterType.RESULT))) {
          trialValue.add(finishProductTrial.getValue());
        }
      }
    }
    double sumOfValue = 0.0;
    for (int i = 0; i < noOfTrial; i++) {
      sumOfValue = sumOfValue + trialValue.get(i);
    }
    return (sumOfValue / noOfTrial);
  }

  public double averageValue(String finishProductTestCode, Long testParameterId) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    Long noOfTrial = finishProductTest.getNoOfTrial();
    List<Double> trialValue = new ArrayList<Double>();
    if (testParameterService.checkEqutaionExistsForTest(
        finishProductTest.getTestConfigure().getId()) == Constants.CHECK_EQUATION_TRUE) {
      for (FinishProductTrial finishProductTrial : finishProductTrialRepository
          .findByFinishProductTestCodeAndTestParameterIdOrderByCreatedAtDesc(finishProductTestCode,
              testParameterId)) {
        if (finishProductTrial.getTestParameter().getParameter().getParameterDataType()
            .equals(ParameterDataType.NUMBER)) {
          if ((finishProductTrial.getTestParameter().getInputMethods()
              .equals(InputMethod.CALCULATION)
              && finishProductTrial.getTestParameter().getType()
                  .equals(TestParameterType.RESULT))) {
            trialValue.add(finishProductTrial.getValue());
          }
        }
      }
    }
    if (testParameterService.checkEqutaionExistsForTest(
        finishProductTest.getTestConfigure().getId()) == Constants.CHECK_EQUATION_FALSE) {
      for (FinishProductTrial finishProductTrial : finishProductTrialRepository
          .findByFinishProductTestCodeAndTestParameterIdOrderByCreatedAtDesc(finishProductTestCode,
              testParameterId)) {
        if (finishProductTrial.getTestParameter().getParameter().getParameterDataType()
            .equals(ParameterDataType.NUMBER)) {
          if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.OBSERVE)
              && finishProductTrial.getTestParameter().getType()
                  .equals(TestParameterType.RESULT))) {
            trialValue.add(finishProductTrial.getValue());
          }
        }
      }
    }
    double sumOfValue = 0.0;
    for (int i = 0; i < noOfTrial; i++) {
      sumOfValue = sumOfValue + trialValue.get(i);
    }
    return (sumOfValue / noOfTrial);
  }

  @Transactional
  public void saveAverageCalculationFinishProductTrials(
      List<FinishProductTrial> finishProductTrialList) {
    if (finishProductTrialRepository.existsByFinishProductTestCode(
        finishProductTrialList.get(0).getFinishProductTest().getCode())
        && (finishProductTrialRepository.findByFinishProductTestCodeAndTestParameterIdAndTrialNo(
            finishProductTrialList.get(0).getFinishProductTest().getCode(),
            finishProductTrialList.get(0).getTestParameter().getId(),
            finishProductTrialList.get(0).getTrialNo()).size() == 1)) {
      for (FinishProductTrial finishProductTrial : finishProductTrialList) {
        List<FinishProductTrial> finishProductTrialAlready =
            finishProductTrialRepository.findByFinishProductTestCodeAndTestParameterIdAndTrialNo(
                finishProductTrial.getFinishProductTest().getCode(),
                finishProductTrial.getTestParameter().getId(), finishProductTrial.getTrialNo());
        finishProductTrialAlready.get(0).setDateValue(finishProductTrial.getDateValue());
        finishProductTrialAlready.get(0).setValue(finishProductTrial.getValue());
        finishProductTrialRepository.save(finishProductTrialAlready.get(0));
      }
    } else {
      FinishProductTest finishProductTest = finishProductTestRepository
          .findById(finishProductTrialList.get(0).getFinishProductTest().getCode()).get();
      finishProductTest.setStatus(Status.PROCESS);
      finishProductTestRepository.save(finishProductTest);
      finishProductTrialRepository.saveAll(finishProductTrialList);
    }
  }

  @Transactional
  public void saveAverageCalculationResult(String finishProductCode) {
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeOrderByCreatedAtDesc(finishProductCode)) {
      if (finishProductTrial.getTestParameter() != null) {
        TestParameter testParameter =
            testParameterRepository.findById(finishProductTrial.getTestParameter().getId()).get();
        if (testParameter.getParameter().getParameterDataType().equals(ParameterDataType.NUMBER)) {
          if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
              && testParameter.getType().equals(TestParameterType.INPUT))) {
            finishProductTrial.setValue(getFinishProductResultParameter(finishProductCode,
                testParameter.getTestConfigure().getId(), finishProductTrial.getTrialNo(),
                testParameter.getId()));
            finishProductTrialRepository.save(finishProductTrial);
          }
          if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
              && testParameter.getType().equals(TestParameterType.RESULT))) {
            finishProductTrial.setValue(
                getFinishProductResult(finishProductCode, testParameter.getTestConfigure().getId(),
                    finishProductTrial.getTrialNo(), testParameter.getId()));
            finishProductTrialRepository.save(finishProductTrial);
          }
        } else {
          if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
              && testParameter.getType().equals(TestParameterType.RESULT))) {
            finishProductTrial.setValue(
                getDateTimeResults(finishProductCode, testParameter.getTestConfigure().getId(),
                    finishProductTrial.getTrialNo(), testParameter.getId()));
            finishProductTrialRepository.save(finishProductTrial);
          }
        }
      }
    }
  }

  public String getTestEquationFormula(Long testConfigId, Long testParameterId) {
    String testEquation = " ";
    if (testEquationRepository.findByTestConfigureIdAndTestParameterId(testConfigId,
        testParameterId) != null) {
      testEquation = testEquationRepository
          .findByTestConfigureIdAndTestParameterId(testConfigId, testParameterId).getEquation()
          .getFormula();
    }
    return testEquation;
  }

  public String getParameterEquationFormula(Long testParameterId, Long configId) {
    String parameterEquation = " ";
    if (parameterEquationRepository
        .findByTestParameterIdAndTestParameterTestConfigureId(testParameterId, configId) != null) {
      parameterEquation = parameterEquationRepository
          .findByTestParameterIdAndTestParameterTestConfigureId(testParameterId, configId)
          .getEquation().getFormula();
    }
    return parameterEquation;
  }

  public void updateStatus(String finishProductTestCode, Status status,
      HttpServletRequest request) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    finishProductTest.setStatus(status);
    FinishProductTest finishProductTestObj = finishProductTestRepository.save(finishProductTest);
    if (finishProductTestObj != null) {
      emailNotification.sendFinishProductTestEmail(finishProductTestObj);
    }
    updateFinishProductSampleAndMixDesignStatus(finishProductTestCode, request);
  }

  public double getFinishProductResultParameter(String finishProductTestCode, Long testConfigId,
      Long trialNo, Long testParameterId) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
    double finishProductResult = 0.0;
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeAndTrialNo(finishProductTestCode, trialNo)) {
      if (finishProductTrial.getTestParameter() != null) {
        TestParameter testParameter =
            testParameterRepository.findById(finishProductTrial.getTestParameter().getId()).get();
        if (testParameter.getParameter().getParameterDataType().equals(ParameterDataType.NUMBER)) {
          if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
              && testParameter.getType().equals(TestParameterType.INPUT))
              || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                  && testParameter.getType().equals(TestParameterType.RESULT))
              || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                  && testParameter.getType().equals(TestParameterType.CONFIG))) {
            engine.put(testParameter.getAbbreviation(), finishProductTrial.getValue());
          }
        }
      }
    }
    try {
      finishProductResult =
          (double) engine.eval(getParameterEquationFormula(testParameterId, testConfigId));
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return roundDoubleValue(finishProductResult);
  }

  public double getFinishProductResult(String finishProductTestCode, Long testConfigId,
      Long trialNo, Long testParameterId) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
    double finishProductResult = 0.0;
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeAndTrialNo(finishProductTestCode, trialNo)) {
      if (finishProductTrial.getTestParameter() != null) {
        TestParameter testParameter =
            testParameterRepository.findById(finishProductTrial.getTestParameter().getId()).get();
        if (testParameter.getParameter().getParameterDataType().equals(ParameterDataType.NUMBER)) {
          if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
              && testParameter.getType().equals(TestParameterType.INPUT))
              || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                  && testParameter.getType().equals(TestParameterType.RESULT))
              || testParameter.getInputMethods().equals(InputMethod.CALCULATION)
                  && testParameter.getType().equals(TestParameterType.INPUT)
              || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                  && testParameter.getType().equals(TestParameterType.CONFIG))) {
            engine.put(testParameter.getAbbreviation(), finishProductTrial.getValue());
          }
        }
      }
    }
    try {
      finishProductResult =
          (double) engine.eval(getTestEquationFormula(testConfigId, testParameterId));
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return roundDoubleValue(finishProductResult);
  }

  private int getCountKeyTestPassFinishProductTestsByMaterialSubCategory(String mixDesignCode,
      String finishProductSampleCode, Status status) {
    ArrayList<Long> testConfigIds = new ArrayList<Long>();
    List<FinishProductTest> finishProductTestList = finishProductTestRepository
        .findByFinishProductSampleMixDesignCodeAndFinishProductSampleCodeAndStatus(mixDesignCode,
            finishProductSampleCode, status);
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findByCode(finishProductSampleCode);
    for (FinishProductTest finishProductTest : finishProductTestList) {
      for (CoreTestConfigure coreTestConfigure : coreTestConfigureRepository
          .findByrawMaterialIdAndCoreTestTrue(
              finishProductSample.getMixDesign().getRawMaterial().getId())) {
        if (finishProductTest.getTestConfigure().getId() == coreTestConfigure.getTestConfigure()
            .getId())
          testConfigIds.add(finishProductTest.getTestConfigure().getId());
      }
    }
    return testConfigIds.stream().distinct().collect(Collectors.toList()).size();
  }

  private void checkStatusAndSaveStatus(String finishProductTestCode, HttpServletRequest request) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    finishProductSample.setStatus(finishproductTest.getStatus());
    FinishProductSample finishProductSampleobj =
        finishProductSampleRepository.save(finishProductSample);
    mixDesign.setStatus(finishproductTest.getStatus());
    MixDesign mixDesignObj = mixDesignRepository.save(mixDesign);
    if (mixDesignObj.getStatus().equals(Status.PASS) && finishProductSampleobj.getWorkOrderNumber()==null) {
      MixDesignConfirmationToken mixDesignConfirmationToken =
          new MixDesignConfirmationToken(mixDesignObj);
      mixDesignConfirmationTokenRepository.save(mixDesignConfirmationToken);
      emailNotification.sendMixdesinApprovelEmail(finishProductSampleobj,
          mixDesignConfirmationToken, request);
    }
  }

  private void checkPassCountAndTestConfigKeyTestCount(String finishProductTestCode,
      HttpServletRequest request) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (getCountKeyTestPassFinishProductTestsByMaterialSubCategory(mixDesign.getCode(),
        finishProductSample.getCode(), Status.PASS) == (coreTestConfigureRepository
            .findByrawMaterialIdAndCoreTestTrue(mixDesign.getRawMaterial().getId()).size())) {
      checkStatusAndSaveStatus(finishProductTestCode, request);
    } else {
      finishProductSample.setStatus(Status.PROCESS);
      finishProductSampleRepository.save(finishProductSample);
      if (finishProductSample.getWorkOrderNumber() == null) {
        mixDesign.setStatus(Status.PROCESS);
        mixDesignRepository.save(mixDesign);
      }
    }
  }

  public void updateFinishProductSampleAndMixDesignStatus(String finishProductTestCode,
      HttpServletRequest request) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (coreTestConfigureRepository.existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrue(
        finishproductTest.getTestConfigure().getId(), mixDesign.getRawMaterial().getId())) {
      if (finishproductTest.getStatus().equals(Status.PASS)) {
        checkPassCountAndTestConfigKeyTestCount(finishProductTestCode, request);
      } else {
        finishProductSample.setStatus(Status.FAIL);
        finishProductSampleRepository.save(finishProductSample);
        if (finishProductSample.getWorkOrderNumber() == null) {
          mixDesign.setStatus(Status.FAIL);
          mixDesignRepository.save(mixDesign);
        }
      }
    } else {
      if (finishProductSample.getStatus().equals(Status.NEW)) {
        finishProductSample.setStatus(Status.PROCESS);
        finishProductSampleRepository.save(finishProductSample);
        if (finishProductSample.getWorkOrderNumber() == null) {
          mixDesign.setStatus(Status.PROCESS);
          mixDesignRepository.save(mixDesign);
        }
      }
    }
  }

  public boolean getEquationCheck(String finishProductTestCode) {
    if (testParameterService
        .checkEqutaionExistsForTest(finishProductTestRepository.findById(finishProductTestCode)
            .get().getTestConfigure().getId()) == Constants.CHECK_EQUATION_TRUE) {
      return true;
    }
    return false;
  }

  private Status checkFinishproductAcceptedValue(Double minValue, Double maxValue, Double value,
      Condition condition, Double average) {
    switch (condition) {
      case BETWEEN:
        return (minValue <= average && maxValue >= average) ? Status.PASS : Status.FAIL;
      case EQUAL:
        return value.equals(average) ? Status.PASS : Status.FAIL;
      case GREATER_THAN:
        return value <= average ? Status.PASS : Status.FAIL;
      case LESS_THAN:
        return value >= average ? Status.PASS : Status.FAIL;
      default:
        return Status.FAIL;
    }
  }

  public void checkAcceptedValue(Long testConfigureId, String finishProductTestCode,
      HttpServletRequest request) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    HashMap<String, Boolean> status = new HashMap<String, Boolean>();
    if (finishProductTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL)) {
      List<FinishProductParameterResult> finishProductResultList =
          finishProductParameterResultRepository.findByFinishProductTestCode(finishProductTestCode);
      finishProductResultList.forEach(result -> {
        MaterialAcceptedValue materialAcceptedValue =
            materialAcceptedValueRepository
                .findByTestConfigureIdAndRawMaterialIdAndTestParameterIdAndFinalResultTrue(
                    testConfigureId, result.getFinishProductTest().getFinishProductSample()
                        .getMixDesign().getRawMaterial().getId(),
                    result.getTestParameter().getId());
        if (materialAcceptedValue != null && materialAcceptedValue.getCategoryAcceptedType()
            .equals(CategoryAcceptedType.MATERIAL)) {
          if (finishProductTest.getTestConfigure().getTestResultType()
              .equals(TestResultType.SINGLE_RESULT)) {
            if (checkFinishproductAcceptedValue(materialAcceptedValue.getMinValue(),
                materialAcceptedValue.getMaxValue(), materialAcceptedValue.getValue(),
                materialAcceptedValue.getConditionRange(), result.getResult())
                    .equals(Status.PASS)) {
              updateStatus(finishProductTestCode, Status.PASS, request);
            } else {
              updateStatus(finishProductTestCode, Status.FAIL, request);
            }
          } else {
            materialAcceptedValueRepository.findByTestConfigureIdAndRawMaterialIdAndFinalResultTrue(
                testConfigureId,
                finishProductTest.getFinishProductSample().getMixDesign().getRawMaterial().getId())
                .forEach(mc -> {
                  if (mc.getCategoryAcceptedType().equals(CategoryAcceptedType.MATERIAL)) {
                    status.put(mc.getTestParameter().getAbbreviation(),
                        checkFinishproductAcceptedValueBoolean(mc.getMinValue(), mc.getMaxValue(),
                            mc.getValue(), mc.getConditionRange(), result.getResult()));
                  }
                });
            if (!getFinalResultStatus(status, testConfigureId)) {
              updateStatus(finishProductTestCode, Status.FAIL, request);
            } else {
              updateStatus(finishProductTestCode, Status.PASS, request);
            }
          }
        }
      });
    } else if (finishProductTest.getTestConfigure().getAcceptedType().equals(AcceptedType.TEST)) {
      List<FinishProductParameterResult> finishProductResultList =
          finishProductParameterResultRepository.findByFinishProductTestCode(finishProductTestCode);
      acceptedValueRepository.findByTestConfigureId(testConfigureId).forEach(acceptedValue -> {
        if (acceptedValue.isFinalResult()) {
          finishProductResultList.forEach(finish -> {
            if (finish.getTestParameter().getId() == acceptedValue.getTestParameter().getId()) {
              if (finish.getFinishProductTest().getTestConfigure().getTestResultType()
                  .equals(TestResultType.SINGLE_RESULT)) {
                if (checkFinishproductAcceptedValue(acceptedValue.getMinValue(),
                    acceptedValue.getMaxValue(), acceptedValue.getValue(),
                    acceptedValue.getConditionRange(), finish.getResult()).equals(Status.PASS)) {
                  updateStatus(finishProductTestCode, Status.PASS, request);
                } else {
                  updateStatus(finishProductTestCode, Status.FAIL, request);
                }
              } else {
                status.put(acceptedValue.getTestParameter().getAbbreviation(),
                    checkFinishproductAcceptedValueBoolean(acceptedValue.getMinValue(),
                        acceptedValue.getMaxValue(), acceptedValue.getValue(),
                        acceptedValue.getConditionRange(), finish.getResult()));
                if (!getFinalResultStatus(status, testConfigureId)) {
                  updateStatus(finishProductTestCode, Status.FAIL, request);
                } else {
                  updateStatus(finishProductTestCode, Status.PASS, request);
                }
              }
            }
          });
        }
      });
    } else {
      List<FinishProductParameterResult> finishProductResultList =
          finishProductParameterResultRepository.findByFinishProductTestCode(finishProductTestCode);
      finishProductResultList.forEach(result -> {
        MaterialAcceptedValue materialAcceptedValue = materialAcceptedValueRepository
            .findByTestConfigureIdAndMaterialSubCategoryIdAndTestParameterIdAndFinalResultTrue(
                testConfigureId,
                result.getFinishProductTest().getFinishProductSample().getMixDesign()
                    .getRawMaterial().getMaterialSubCategory().getId(),
                result.getTestParameter().getId());
        if (materialAcceptedValue != null && materialAcceptedValue.getCategoryAcceptedType()
            .equals(CategoryAcceptedType.SUB_CATEGORY)) {
          if (finishProductTest.getTestConfigure().getTestResultType()
              .equals(TestResultType.SINGLE_RESULT)) {
            if (checkFinishproductAcceptedValue(materialAcceptedValue.getMinValue(),
                materialAcceptedValue.getMaxValue(), materialAcceptedValue.getValue(),
                materialAcceptedValue.getConditionRange(), result.getResult())
                    .equals(Status.PASS)) {
              updateStatus(finishProductTestCode, Status.PASS, request);
            } else {
              updateStatus(finishProductTestCode, Status.FAIL, request);
            }
          } else {
            materialAcceptedValueRepository
                .findByTestConfigureIdAndMaterialSubCategoryIdAndFinalResultTrue(testConfigureId,
                    finishProductTest.getFinishProductSample().getMixDesign().getRawMaterial()
                        .getMaterialSubCategory().getId())
                .forEach(msub -> {
                  if (msub.getCategoryAcceptedType().equals(CategoryAcceptedType.SUB_CATEGORY)) {
                    status.put(msub.getTestParameter().getAbbreviation(),
                        checkFinishproductAcceptedValueBoolean(msub.getMinValue(),
                            msub.getMaxValue(), msub.getValue(), msub.getConditionRange(),
                            result.getResult()));
                  }
                });
            if (!getFinalResultStatus(status, testConfigureId)) {
              updateStatus(finishProductTestCode, Status.FAIL, request);
            } else {
              updateStatus(finishProductTestCode, Status.PASS, request);
            }
          }
        }
      });
    }
  }

  private Boolean checkFinishproductAcceptedValueBoolean(Double minValue, Double maxValue,
      Double value, Condition condition, Double average) {
    switch (condition) {
      case BETWEEN:
        return (minValue <= average && maxValue >= average) ? true : false;
      case EQUAL:
        return value.equals(average) ? true : false;
      case GREATER_THAN:
        return value <= average ? true : false;
      case LESS_THAN:
        return value >= average ? true : false;
      default:
        return false;
    }
  }

  private String getConditionFormula(Long testConfigureId) {
    return multiResultFormulaRepository.findByTestConfigureId(testConfigureId).getLogicalFormula();
  }

  private Boolean getFinalResultStatus(HashMap<String, Boolean> status, Long testConfigId) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
    boolean statusFinal = false;
    for (String i : status.keySet()) {
      engine.put(i, status.get(i));
    }
    try {
      statusFinal = (Boolean) engine.eval(getConditionFormula(testConfigId));
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return statusFinal;
  }


  private Double findTimeDifference(List<LocalDateTime> lis) {
    Duration duration = Duration.between(lis.get(0), lis.get(1));
    Double value = Math.abs((double) duration.toMinutes());
    return value;
  }

  public double getDateTimeResults(String finishProductTestCode, Long testConfigId, Long trialNo,
      Long testParameterId) {
    TestEquation testEquation = testEquationRepository
        .findByTestConfigureIdAndTestParameterId(testConfigId, testParameterId);
    List<TestEquationParameter> testEquationParameterEle =
        testEquationParameterRepository.findByTestEquationId(testEquation.getId());
    double finishProductResult = 0.0;
    List<LocalDateTime> dateResultlist = new ArrayList<>();
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeAndTrialNoOrderByCreatedAtDesc(finishProductTestCode,
            trialNo)) {
      if (!testEquationParameterEle.isEmpty()) {
        for (TestEquationParameter testEquationParameter : testEquationParameterEle) {
          if (finishProductTrial.getTestParameter() != null && testEquationParameter
              .getTestParameter().getId() == finishProductTrial.getTestParameter().getId()) {
            TestParameter testParameter = testParameterRepository
                .findById(finishProductTrial.getTestParameter().getId()).get();
            if (testParameter.getParameter().getParameterDataType()
                .equals(ParameterDataType.DATETIME)) {
              if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                  && testParameter.getType().equals(TestParameterType.INPUT))
                  || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                      && testParameter.getType().equals(TestParameterType.RESULT))
                  || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                      && testParameter.getType().equals(TestParameterType.CONFIG))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime =
                    LocalDateTime.parse(finishProductTrial.getDateValue(), formatter);
                dateResultlist.add(dateTime);
              }
            }
          }
        }
      }

    }
    if (!dateResultlist.isEmpty()) {
      finishProductResult = findTimeDifference(dateResultlist);
    }
    return roundDoubleValue(finishProductResult);
  }

  public double finalDateValue(String finishProductTestCode, Long testParameterId, Long noOfTrial) {
    List<Double> trialValue = new ArrayList<Double>();
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeAndTestParameterIdOrderByCreatedAtDesc(finishProductTestCode,
            testParameterId)) {
      if (finishProductTrial.getTestParameter().getParameter().getParameterDataType()
          .equals(ParameterDataType.DATETIME)) {
        if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && finishProductTrial.getTestParameter().getType().equals(TestParameterType.RESULT))) {
          trialValue.add(finishProductTrial.getValue());
        }
      }
    }

    double sumOfValue = 0.0;
    for (int i = 0; i < noOfTrial; i++) {
      sumOfValue = sumOfValue + trialValue.get(i);
    }
    return (sumOfValue / noOfTrial);
  }

}
