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
      materialTestTrial.setCode(subPrefix + String.format("%02d", 1));
      materialTestTrial.setTrialNo(1l);
    } else {
      materialTestTrial
          .setCode(subPrefix + String.format("%02d", maxNumberFromCode(materialTestTrialList) + 1));
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
      list.add(getNumberFromCode(code.substring(code.indexOf("-"))));
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
    MaterialTest materialTest = materialTestRepository.getOne(materialTestCode);
    int count = 0;
    int imp = 0;
    for (MaterialTestResult materialTestResult : materialTestResults) {
      Status status = Status.FAIL;
      Double average = materialTestResult.getResult();
      if (materialAcceptedValueRepository
          .findByTestConfigureId(materialTest.getTestConfigure().getId()).size() > 0) {
        MaterialAcceptedValue materialAcceptedValue = (materialTestResult.getTestEquation() != null)
            ? materialAcceptedValueRepository
                .findByTestConfigureIdAndRawMaterialIdAndTestEquationId(
                    materialTest.getTestConfigure().getId(),
                    materialTest.getIncomingSample().getRawMaterial().getId(),
                    materialTestResult.getTestEquation().getId())
            : materialAcceptedValueRepository.findByTestConfigureIdAndRawMaterialId(
                materialTest.getTestConfigure().getId(),
                materialTest.getIncomingSample().getRawMaterial().getId());
        if (materialAcceptedValue.isFinalResult()) {
          imp++;
          status = compareAverage(materialAcceptedValue.getMinValue(),
              materialAcceptedValue.getMaxValue(), materialAcceptedValue.getValue(),
              materialAcceptedValue.getConditionRange(), average, materialTestCode);
        }
      } else {
        AcceptedValue acceptedValue = (materialTestResult.getTestEquation() != null)
            ? acceptedValueRepository.findByTestConfigureIdAndTestEquationId(
                materialTest.getTestConfigure().getId(),
                materialTestResult.getTestEquation().getId())
            : acceptedValueRepository.findByTestConfigureId(materialTest.getTestConfigure().getId())
                .get(0);
        if (acceptedValue.isFinalResult()) {
          imp++;
          status = compareAverage(acceptedValue.getMinValue(), acceptedValue.getMaxValue(),
              acceptedValue.getValue(), acceptedValue.getConditionRange(), average,
              materialTestCode);
        }
      }
      count = status.equals(Status.PASS) ? count + 1 : count;
    }
    updateMaterialTestStatus(materialTestCode, (count == imp) ? Status.PASS : Status.FAIL);
  }

  private Status compareAverage(Double minValue, Double maxValue, Double value, Condition condition,
      Double average, String materialTestCode) {
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

  @Transactional
  public MaterialTest updateMaterialTestStatus(String code, Status status) {
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
