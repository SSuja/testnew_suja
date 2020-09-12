package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
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
  MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  AcceptedValueRepository acceptedValueRepository;
  @Autowired
  TestParameterService testParameterService;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(Long id) {
    return finishProductTrialRepository.findById(id).get();
  }

  public void saveFinishProductTrial(List<FinishProductTrial> finishProductTrial) {
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
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode) {
    return finishProductTrialRepository
        .findByFinishProductTestCodeOrderByUpdatedAtDesc(finishProductTestCode);
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
  public void saveFinishproductResult(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    TestConfigure testConfigure =
        testConfigureRepository.findById(finishproductTest.getTestConfigure().getId()).get();
    List<TestParameter> testParameterList =
        testParameterRepository.findByTestConfigureId(testConfigure.getId());
    FinishProductParameterResult finishProductParameterResult = new FinishProductParameterResult();
    for (TestParameter testParameter : testParameterList) {
      if (testParameterService.checkEqutaionExistsForTest(
          testParameter.getTestConfigure().getId()) == Constants.CHECK_EQUATION_TRUE) {
        if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
            && testParameter.getType().equals(TestParameterType.RESULT))) {
          finishProductParameterResult.setFinishProductTest(finishproductTest);
          finishProductParameterResult.setTestParameter(testParameter);
          finishProductParameterResult
              .setResult(roundDoubleValue(averageValue(finishProductTestCode)));
        }
      }
      if (testParameterService.checkEqutaionExistsForTest(
          testParameter.getTestConfigure().getId()) == Constants.CHECK_EQUATION_FALSE) {
        if ((testParameter.getInputMethods().equals(InputMethod.OBSERVE)
            && testParameter.getType().equals(TestParameterType.INPUT))
            || (testParameter.getInputMethods().equals(InputMethod.OBSERVE)
                && testParameter.getType().equals(TestParameterType.RESULT))) {
          finishProductParameterResult.setFinishProductTest(finishproductTest);
          finishProductParameterResult.setTestParameter(testParameter);
          finishProductParameterResult
              .setResult(roundDoubleValue(averageValue(finishProductTestCode)));
        }
      }
    }
    finishProductParameterResultRepository.save(finishProductParameterResult);

    checkAcceptedValue(testConfigure.getId(), finishProductTestCode);
  }

  public double averageValue(String finishProductTestCode) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    Long noOfTrial = finishProductTest.getNoOfTrial();
    List<Double> trialValue = new ArrayList<Double>();
    if (testParameterService.checkEqutaionExistsForTest(
        finishProductTest.getTestConfigure().getId()) == Constants.CHECK_EQUATION_TRUE) {
      for (FinishProductTrial finishProductTrial : finishProductTrialRepository
          .findByFinishProductTestCode(finishProductTestCode)) {
        if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && finishProductTrial.getTestParameter().getType().equals(TestParameterType.RESULT))) {
          trialValue.add(finishProductTrial.getValue());
        }
      }
    }
    if (testParameterService.checkEqutaionExistsForTest(
        finishProductTest.getTestConfigure().getId()) == Constants.CHECK_EQUATION_FALSE) {
      for (FinishProductTrial finishProductTrial : finishProductTrialRepository
          .findByFinishProductTestCodeOrderByUpdatedAtDesc(finishProductTestCode)) {
        if ((finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.OBSERVE)
            && finishProductTrial.getTestParameter().getType().equals(TestParameterType.INPUT))
            || (finishProductTrial.getTestParameter().getInputMethods().equals(InputMethod.OBSERVE)
                && finishProductTrial.getTestParameter().getType()
                    .equals(TestParameterType.RESULT))) {
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
    finishProductTrialRepository.saveAll(finishProductTrialList);
  }

  @Transactional
  public void saveAverageCalculationResult(String finishProductCode) {
    for (FinishProductTrial finishProductTrial : finishProductTrialRepository
        .findByFinishProductTestCodeOrderByUpdatedAtDesc(finishProductCode)) {
      if (finishProductTrial.getTestParameter() != null) {
        TestParameter testParameter =
            testParameterRepository.findById(finishProductTrial.getTestParameter().getId()).get();
        if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION)
            && testParameter.getType().equals(TestParameterType.RESULT))) {
          finishProductTrial.setValue(getFinishProductResult(finishProductCode,
              testParameter.getTestConfigure().getId(), finishProductTrial.getTrialNo()));
          finishProductTrialRepository.save(finishProductTrial);
        }
      }
    }
  }

  public String getFormula(Long testConfigId) {
    String equation = " ";
    if (!(testEquationRepository.findByTestConfigureId(testConfigId).isEmpty())) {
      equation = testEquationRepository.findByTestConfigureId(testConfigId).get(0).getEquation()
          .getFormula();
    }
    return equation;
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
    FinishProductTest finishProductTestObj = finishProductTestRepository.save(finishProductTest);
    if (finishProductTestObj != null) {
      emailNotification.sendFinishProductTestEmail(finishProductTestObj);
    }
    updateFinishProductSampleAndMixDesignStatus(finishProductTestCode);
  }

  public double getFinishProductResult(String finishProductTestCode, Long testConfigId,
      Long trialNo) {
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
                && testParameter.getType().equals(TestParameterType.RESULT))) {
          engine.put(testParameter.getAbbreviation(), finishProductTrial.getValue());
        }
      }
    }
    try {
      finishProductResult = (double) engine.eval(getFormula(testConfigId));
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return roundDoubleValue(finishProductResult);
  }

  public void checkAcceptedValue(Long testConfigureId, String finishProductTestCode) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    if (finishProductTest.getTestConfigure().getAcceptedType().equals(AcceptedType.MATERIAL)) {
      materialAcceptedValueRepository.findByTestConfigureId(testConfigureId)
          .forEach(materialAcceptedValue -> {
            if ((materialAcceptedValue.isFinalResult())
                && (materialAcceptedValue.getRawMaterial().getId() == finishProductTest
                    .getFinishProductSample().getMixDesign().getRawMaterial().getId())) {
              checkFinishproductAcceptedValue(materialAcceptedValue.getMinValue(),
                  materialAcceptedValue.getMaxValue(), materialAcceptedValue.getValue(),
                  materialAcceptedValue.getConditionRange(), averageValue(finishProductTestCode),
                  finishProductTestCode);
            }
          });
    } else {
      acceptedValueRepository.findByTestConfigureId(testConfigureId).forEach(acceptedValue -> {
        if (acceptedValue.isFinalResult()) {
          checkFinishproductAcceptedValue(acceptedValue.getMinValue(), acceptedValue.getMaxValue(),
              acceptedValue.getValue(), acceptedValue.getConditionRange(),
              averageValue(finishProductTestCode), finishProductTestCode);
        }
      });
    }
  }

  private int getCountKeyTestPassFinishProductTestsByMaterialSubCategory(String mixDesignCode,
      Long materialSubCategoryId, Status status) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndTestConfigureMaterialSubCategoryIdAndStatus(
            mixDesignCode, materialSubCategoryId, status)
        .size();
  }

  private int getCountKeyTestPassFinishProductTestsByMaterialCategory(String mixDesignCode,
      Long materialCategoryId, Status status) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndTestConfigureMaterialCategoryIdAndStatus(
            mixDesignCode, materialCategoryId, status)
        .size();
  }

  private int getCountKeyTestPassFinishProductTestsByRawMaterial(String mixDesignCode,
      Long rawMaterialId, Status status) {
    return finishProductTestRepository
        .findByFinishProductSampleMixDesignCodeAndTestConfigureCoreTestTrueAndTestConfigureRawMaterialIdAndStatus(
            mixDesignCode, rawMaterialId, status)
        .size();

  }

  private int getCountkeyTestConfigByMaterialSubCategory(Long materialSubCategoryId) {
    return testConfigureRepository.findByMaterialSubCategoryIdAndCoreTestTrue(materialSubCategoryId)
        .size();
  }

  private int getCountKeyTestConfigByMaterialCategory(Long materialCategoryId) {
    return testConfigureRepository.findByMaterialCategoryIdAndCoreTestTrue(materialCategoryId)
        .size();
  }

  private int getCountKeyTestConfigByRawMaterial(Long rawMaterialId) {
    return testConfigureRepository.findByRawMaterialIdAndCoreTestTrue(rawMaterialId).size();
  }

  private void checkStatusAndSaveStatus(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    finishProductSample.setStatus(finishproductTest.getStatus());
    finishProductSampleRepository.save(finishProductSample);
    mixDesign.setStatus(finishproductTest.getStatus());
    mixDesignRepository.save(mixDesign);
  }

  private void checkPassCountAndTestConfigKeyTestCountByMaterialSubCategory(
      String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (getCountKeyTestPassFinishProductTestsByMaterialSubCategory(mixDesign.getCode(),
        mixDesign.getRawMaterial().getMaterialSubCategory().getId(),
        Status.PASS) >= getCountkeyTestConfigByMaterialSubCategory(
            mixDesign.getRawMaterial().getMaterialSubCategory().getId())) {
      checkStatusAndSaveStatus(finishProductTestCode);
    } else {
      finishProductSample.setStatus(Status.PROCESS);
      finishProductSampleRepository.save(finishProductSample);
      mixDesign.setStatus(Status.NEW);
      mixDesignRepository.save(mixDesign);
    }
  }

  private void checkPassCountAndTestConfigKeyTestCountByMaterialCategory(
      String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (getCountKeyTestPassFinishProductTestsByMaterialCategory(mixDesign.getCode(),
        mixDesign.getRawMaterial().getMaterialSubCategory().getMaterialCategory().getId(),
        Status.PASS) >= getCountKeyTestConfigByMaterialCategory(
            mixDesign.getRawMaterial().getMaterialSubCategory().getMaterialCategory().getId())) {
      checkStatusAndSaveStatus(finishProductTestCode);
    } else {
      finishProductSample.setStatus(Status.PROCESS);
      finishProductSampleRepository.save(finishProductSample);
      mixDesign.setStatus(Status.NEW);
      mixDesignRepository.save(mixDesign);
    }
  }

  private void checkPassCountAndTestConfigKeyTestCountByRawMaterial(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (getCountKeyTestPassFinishProductTestsByRawMaterial(mixDesign.getCode(),
        mixDesign.getRawMaterial().getId(),
        Status.PASS) >= getCountKeyTestConfigByRawMaterial(mixDesign.getRawMaterial().getId())) {
      checkStatusAndSaveStatus(finishProductTestCode);
    } else {
      finishProductSample.setStatus(Status.PROCESS);
      finishProductSampleRepository.save(finishProductSample);
      mixDesign.setStatus(Status.NEW);
      mixDesignRepository.save(mixDesign);
    }
  }

  public void updateFinishProductSampleAndMixDesignStatus(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    TestConfigure testConfigure =
        testConfigureRepository.findById(finishproductTest.getTestConfigure().getId()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (testConfigure.isCoreTest()) {
      if (finishproductTest.getStatus().equals(Status.PASS)) {
        if ((testConfigure.getRawMaterial() == null)) {
          checkPassCountAndTestConfigKeyTestCountByMaterialSubCategory(finishProductTestCode);
        }
        if (testConfigure.getMaterialSubCategory() == null) {
          checkPassCountAndTestConfigKeyTestCountByMaterialCategory(finishProductTestCode);
        }
        if (testConfigure.getRawMaterial() != null) {
          checkPassCountAndTestConfigKeyTestCountByRawMaterial(finishProductTestCode);
        }
      } else {
        checkStatusAndSaveStatus(finishProductTestCode);
      }
    } else {
      finishProductSample.setStatus(Status.PROCESS);
      finishProductSampleRepository.save(finishProductSample);
      mixDesign.setStatus(Status.NEW);
      mixDesignRepository.save(mixDesign);
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
}
