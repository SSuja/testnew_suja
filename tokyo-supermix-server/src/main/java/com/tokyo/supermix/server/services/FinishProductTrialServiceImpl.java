package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    finishProductParameterResultRepository.save(finishProductParameterResult);
    checkAcceptedValue(testConfigure.getId(), finishProductTestCode);
  }

  public double averageValue(String finishProductTestCode) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    Long noOfTrial = finishProductTest.getNoOfTrial();
    List<Double> trialValue = new ArrayList<Double>();
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
    double sumOfValue = 0.0;
    for (int i = 0; i < noOfTrial; i++) {
      sumOfValue = sumOfValue + trialValue.get(i);
    }
    return (sumOfValue / noOfTrial);
  }

  public String getFormula(String finishProductTestCode) {
    List<FinishProductTrial> finishProductTrialList = finishProductTrialRepository
        .findByFinishProductTestCodeOrderByUpdatedAtDesc(finishProductTestCode);
    String equation = " ";
    for (FinishProductTrial finishProductTrial : finishProductTrialList) {
      equation = testEquationRepository
          .findByTestConfigureId(
              finishProductTrial.getFinishProductTest().getTestConfigure().getId())
          .get(0).getEquation().getFormula();
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

  public void updateFinishProductSampleAndMixDesignStatus(String finishProductTestCode) {
    FinishProductTest finishproductTest =
        finishProductTestRepository.findById(finishProductTestCode).get();
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishproductTest.getFinishProductSample().getCode()).get();
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    if (finishproductTest.getTestConfigure().isCoreTest()) {
      finishProductSample.setStatus(finishproductTest.getStatus());
      finishProductSampleRepository.save(finishProductSample);
      mixDesign.setStatus(finishproductTest.getStatus());
      mixDesignRepository.save(mixDesign);
    } else {
      finishProductSample.setStatus(Status.PROCESS);
      finishProductSampleRepository.save(finishProductSample);
    }
  }
}
