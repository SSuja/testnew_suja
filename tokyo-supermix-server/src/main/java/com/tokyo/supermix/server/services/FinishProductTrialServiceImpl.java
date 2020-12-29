package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignConfirmationToken;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MixDesignConfirmationTokenRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
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

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(Long id) {
    return finishProductTrialRepository.findById(id).get();
  }

  public void saveFinishProductTrial(List<FinishProductTrial> finishProductTrial) {
    FinishProductTest finishProductTest = finishProductTestRepository
        .findById(finishProductTrial.get(0).getFinishProductTest().getCode()).get();
    finishProductTest.setStatus(Status.PROCESS);
    finishProductTestRepository.save(finishProductTest);
    finishProductTrialRepository.saveAll(finishProductTrial);
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
      if (testParameterService.checkEqutaionExistsForTest(
          testParameter.getTestConfigure().getId()) == Constants.CHECK_EQUATION_TRUE) {
        if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
            && testParameter.getType().equals(TestParameterType.RESULT))) {
          if (finishProductParameterResultRepository.findByTestParameterIdAndFinishProductTestCode(
              testParameter.getId(), finishProductTestCode) != null) {
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
          if (finishProductParameterResultRepository.findByTestParameterIdAndFinishProductTestCode(
              testParameter.getId(), finishProductTestCode) != null) {
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
          if (finishProductParameterResultRepository.findByTestParameterIdAndFinishProductTestCode(
              testParameter.getId(), finishProductTestCode) != null) {
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
      if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.OBSERVE)
          && finishProductTrial.getTestParameter().getType().equals(TestParameterType.RESULT))) {
        trialValue.add(finishProductTrial.getValue());
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
        if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && finishProductTrial.getTestParameter().getType().equals(TestParameterType.RESULT))) {
          trialValue.add(finishProductTrial.getValue());
        }
      }
    }
    if (testParameterService.checkEqutaionExistsForTest(
        finishProductTest.getTestConfigure().getId()) == Constants.CHECK_EQUATION_FALSE) {
      for (FinishProductTrial finishProductTrial : finishProductTrialRepository
          .findByFinishProductTestCodeAndTestParameterIdOrderByCreatedAtDesc(finishProductTestCode,
              testParameterId)) {
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

  @Transactional
  public void saveAverageCalculationFinishProductTrials(
      List<FinishProductTrial> finishProductTrialList) {
    FinishProductTest finishProductTest = finishProductTestRepository
        .findById(finishProductTrialList.get(0).getFinishProductTest().getCode()).get();
    finishProductTest.setStatus(Status.PROCESS);
    finishProductTestRepository.save(finishProductTest);
    finishProductTrialRepository.saveAll(finishProductTrialList);
  }

  @Transactional
  public void saveAverageCalculationResult(String finishProductCode) {
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeOrderByCreatedAtDesc(finishProductCode)) {
      if (finishProductTrial.getTestParameter() != null) {
        TestParameter testParameter =
            testParameterRepository.findById(finishProductTrial.getTestParameter().getId()).get();
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

  private int getCountkeyTestConfigByMaterialSubCategory(Long materialSubCategoryId) {
    return testConfigureRepository.findByMaterialSubCategoryIdAndCoreTestTrue(materialSubCategoryId)
        .stream().filter(listTest -> listTest.getRawMaterial() == null).collect(Collectors.toList())
        .size();
  }

  private int getCountKeyTestConfigByMaterialCategory(Long materialCategoryId) {
    return testConfigureRepository.findByMaterialCategoryIdAndCoreTestTrue(materialCategoryId)
        .stream().filter(listTest -> listTest.getMaterialSubCategory() == null)
        .collect(Collectors.toList()).size();
  }

  private int getCountKeyTestConfigByRawMaterial(Long rawMaterialId) {
    return testConfigureRepository.findByRawMaterialIdAndCoreTestTrue(rawMaterialId).size();
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
    MixDesignConfirmationToken mixDesignConfirmationToken =
        new MixDesignConfirmationToken(mixDesignObj);
    mixDesignConfirmationTokenRepository.save(mixDesignConfirmationToken);
    emailNotification.sendMixdesinApprovelEmail(finishProductSampleobj, mixDesignConfirmationToken,
        request);
    if (mixDesignObj.getStatus().equals(Status.PASS)) {
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
    if (finishproductTest.getStatus().equals(Status.PASS)) {
      checkPassCountAndTestConfigKeyTestCount(finishProductTestCode, request);
    } else if (finishproductTest.getStatus().equals(Status.FAIL)) {
      checkStatusAndSaveStatus(finishProductTestCode, request);
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
      Condition condition, Double average, String materialTestCode) {
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
    ArrayList<Status> statusList = new ArrayList<Status>();
    if (finishProductTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL)) {
      materialAcceptedValueRepository.findByTestConfigureId(testConfigureId)
          .forEach(materialAcceptedValue -> {
            List<FinishProductParameterResult> finishProductResultList =
                finishProductParameterResultRepository
                    .findByFinishProductTestCode(finishProductTestCode);
            if ((materialAcceptedValue.isFinalResult())) {
              finishProductResultList.forEach(result -> {
                if ((materialAcceptedValue.getRawMaterial().getId() == result.getFinishProductTest()
                    .getFinishProductSample().getMixDesign().getRawMaterial().getId())
                    && (materialAcceptedValue.getTestParameter().getId() == result
                        .getTestParameter().getId())) {
                  statusList.add(checkFinishproductAcceptedValue(
                      materialAcceptedValue.getMinValue(), materialAcceptedValue.getMaxValue(),
                      materialAcceptedValue.getValue(), materialAcceptedValue.getConditionRange(),
                      result.getResult(), finishProductTestCode));
                }
              });
            }
          });
    } else {
      List<FinishProductParameterResult> finishProductResultList =
          finishProductParameterResultRepository.findByFinishProductTestCode(finishProductTestCode);
      acceptedValueRepository.findByTestConfigureId(testConfigureId).forEach(acceptedValue -> {
        if (acceptedValue.isFinalResult()) {
          finishProductResultList.forEach(finish -> {
            if (finish.getTestParameter().getId() == acceptedValue.getTestParameter().getId()) {
              statusList.add(checkFinishproductAcceptedValue(acceptedValue.getMinValue(),
                  acceptedValue.getMaxValue(), acceptedValue.getValue(),
                  acceptedValue.getConditionRange(), finish.getResult(), finishProductTestCode));
            }
          });
        }
      });
    }
    Long passCount = (long) statusList.stream().filter(sta -> (sta.equals(Status.PASS)))
        .collect(Collectors.toList()).size();
    if (passCount == conutrRelavantFinalResult(testConfigureId,
        finishProductTest.getTestConfigure().getAcceptedType(),
        finishProductTest.getFinishProductSample().getMixDesign().getRawMaterial().getId())) {
      updateStatus(finishProductTestCode, Status.PASS, request);
    } else {
      updateStatus(finishProductTestCode, Status.FAIL, request);
    }
  }

  private Long conutrRelavantFinalResult(Long testConfigId, AcceptedType acceptedType,
      Long rawMaterialId) {
    Long count = (long) 0;
    if (acceptedType.equals(AcceptedType.MATERIAL)) {
      count = materialAcceptedValueRepository
          .countByTestConfigureIdAndAndRawMaterialIdAndFinalResultTrue(testConfigId, rawMaterialId);
    } else {
      count = acceptedValueRepository.countByTestConfigureIdAndFinalResultTrue(testConfigId);
    }
    return count;
  }
}
