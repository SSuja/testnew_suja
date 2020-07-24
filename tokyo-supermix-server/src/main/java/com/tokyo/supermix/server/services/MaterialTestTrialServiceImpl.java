package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestResultRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class MaterialTestTrialServiceImpl implements MaterialTestTrialService {
  @Autowired
  private MaterialTestTrialRepository materialTestTrialRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  TestParameterRepository testParameterRepository;
  @Autowired
  ParameterResultRepository parameterResultRepository;
  @Autowired
  MaterialTestService materialTestService;
  @Autowired
  MaterialTestResultRepository materialTestResultRepository;

  @Transactional
  public String saveMaterialTestTrial(MaterialTestTrial materialTestTrial) {
    String codePrefix = materialTestTrial.getMaterialTest().getCode();
    String subPrefix = codePrefix + "-T-";
    List<MaterialTestTrial> materialTestTrialList =
        materialTestTrialRepository.findByCodeContaining(subPrefix);
    if (materialTestTrialList.size() == 0) {
      materialTestTrial.setCode(subPrefix + String.format("%04d", 1));
      materialTestTrial.setTrialNo(1l);
    } else {
      materialTestTrial
          .setCode(subPrefix + String.format("%04d", maxNumberFromCode(materialTestTrialList) + 1));
      materialTestTrial.setTrialNo(maxNumberFromCode(materialTestTrialList).longValue() + 1l);
    }
    materialTestTrialRepository.save(materialTestTrial);
    return materialTestTrial.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<MaterialTestTrial> materialTestTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    materialTestTrialList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getAllMaterialTestTrial() {
    return materialTestTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public MaterialTestTrial getMaterialTestTrialByCode(String code) {
    return materialTestTrialRepository.findByCode(code);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialTestTrial(String code) {
    materialTestTrialRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestTrialExits(String code) {
    return materialTestTrialRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getMaterialTestTrialByMaterialTestCode(String materialTestCode) {
    return materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestIdExits(String materialTestCode) {
    return materialTestTrialRepository.existsByMaterialTestCode(materialTestCode);
  }

  @Transactional
  public void getAverageAndStatus(String materialTestCode) {

    List<MaterialTestResult> materialTestResults =
        materialTestResultRepository.findByMaterialTestCode(materialTestCode);

    materialTestResults.forEach(materialTestResult -> {
      MaterialTest materialTest = materialTestRepository.getOne(materialTestCode);
      if (materialTestResult.getTestEquation() == null) {

        MaterialAcceptedValue materialAcceptedValue = materialAcceptedValueRepository
            .findByTestConfigureIdAndRawMaterialId(materialTest.getTestConfigure().getId(),
                materialTest.getIncomingSample().getRawMaterial().getId());
        compareAverage(materialAcceptedValue.getMinValue(), materialAcceptedValue.getMaxValue(),
            materialAcceptedValue.getValue(), materialAcceptedValue.getConditionRange(),
            materialTestResult.getResult(), materialTestCode);
      } else {
        List<AcceptedValue> acceptedValueslist =
            acceptedValueRepository.findByTestConfigure(materialTest.getTestConfigure());
        for (AcceptedValue acceptedValue : acceptedValueslist) {
          Double average = materialTestResultRepository.findByTestEquationAndMaterialTestCode(
              acceptedValue.getTestEquation(), materialTestCode).getResult();
          compareAverage(acceptedValue.getMinValue(), acceptedValue.getMaxValue(),
              acceptedValue.getValue(), acceptedValue.getConditionRange(), average,
              materialTestCode);
        }
      }
    });

  }

  private void compareAverage(Double minValue, Double maxValue, Double value, Condition condition,
      Double average, String materialTestCode) {
    if (condition == Condition.BETWEEN) {
      if (minValue <= average && maxValue >= average) {
        updateAverage(materialTestCode, Status.PASS);
      } else {
        updateAverage(materialTestCode, Status.FAIL);
      }
    } else if (condition == Condition.EQUAL) {
      if (value == average) {
        updateAverage(materialTestCode, Status.PASS);
      } else {
        updateAverage(materialTestCode, Status.FAIL);
      }
    } else if (condition == Condition.GREATER_THAN) {
      if (value <= average) {
        updateAverage(materialTestCode, Status.PASS);
      } else {
        updateAverage(materialTestCode, Status.FAIL);
      }

    } else if (condition == Condition.LESS_THAN) {
      if (value >= average) {
        updateAverage(materialTestCode, Status.PASS);
      } else {
        updateAverage(materialTestCode, Status.FAIL);
      }
    }

  }

  @Transactional
  public MaterialTest updateAverage(String code, Status status) {
    MaterialTest materialTest = materialTestRepository.findByCode(code);
    materialTest.setStatus(status);
    materialTestRepository.save(materialTest);
    return materialTest;
  }


  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getMaterialTestTrialByPlantCode(String plantCode) {
    return materialTestTrialRepository.findByMaterialTestIncomingSamplePlantCode(plantCode);
  }

  @Override
  public List<MaterialTestTrial> getAllMaterialTestTrialByplant(UserPrincipal currentUser) {
    return materialTestTrialRepository.findByMaterialTestIncomingSamplePlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_MATERIAL_TEST_TRIAL));
  }
}
