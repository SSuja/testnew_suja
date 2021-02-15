package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestResultRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.MultiResultFormulaRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.notification.EmailNotification;
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
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private MultiResultFormulaRepository multiResultFormulaRepository;
  @Autowired
  IncomingSampleRepository incomingSampleRepository;

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
      list.add(getNumberFromCode(code.substring(code.lastIndexOf("-"))));
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
    
    if(materialTest.getIncomingSample().getStatus().equals(Status.NEW)){
      IncomingSample incomingSample= incomingSampleRepository.getOne(materialTest.getIncomingSample().getCode());
      incomingSample.setStatus(Status.PROCESS);
      incomingSampleRepository.save(incomingSample);
    }
    
    HashMap<String, Boolean> acceptedParameters = new HashMap<>();
    String singleEquation = null;
    for (MaterialTestResult materialTestResult : materialTestResults) {
      Status status = Status.FAIL;
      Double average = materialTestResult.getResult();
      if (materialTestResult.getMaterialTest().getTestConfigure().getAcceptedType()
          .equals(AcceptedType.MATERIAL)) {
        MaterialAcceptedValue materialAcceptedValue =
            materialAcceptedValueRepository.findByTestConfigureIdAndTestParameterIdAndRawMaterialId(
                materialTest.getTestConfigure().getId(),
                materialTestResult.getTestParameter().getId(),
                materialTest.getIncomingSample().getRawMaterial().getId());

        if (materialAcceptedValue != null) {
          if (materialAcceptedValue.isFinalResult()) {
            status = compareAverage(materialAcceptedValue.getMinValue(),
                materialAcceptedValue.getMaxValue(), materialAcceptedValue.getValue(),
                materialAcceptedValue.getConditionRange(), average, materialTestCode);
            Boolean st = (status.equals(Status.PASS)) ? true : false;
            acceptedParameters.put(materialTestResult.getTestParameter().getAbbreviation(), st);
            singleEquation = materialTestResult.getTestParameter().getAbbreviation();
          }
        }
      } else if (materialTestResult.getMaterialTest().getTestConfigure().getAcceptedType()
          .equals(AcceptedType.SUB_CATEGORY)) {
        MaterialAcceptedValue materialAcceptedValue = materialAcceptedValueRepository
            .findByTestConfigureIdAndTestParameterIdAndMaterialSubCategoryId(
                materialTest.getTestConfigure().getId(),
                materialTestResult.getTestParameter().getId(),
                materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory().getId());
        if (materialAcceptedValue != null) {
          if (materialAcceptedValue.isFinalResult()) {
            status = compareAverage(materialAcceptedValue.getMinValue(),
                materialAcceptedValue.getMaxValue(), materialAcceptedValue.getValue(),
                materialAcceptedValue.getConditionRange(), average, materialTestCode);
            Boolean st = (status.equals(Status.PASS)) ? true : false;
            acceptedParameters.put(materialTestResult.getTestParameter().getAbbreviation(), st);
            singleEquation = materialTestResult.getTestParameter().getAbbreviation();
          }
        }
      } else {
        AcceptedValue acceptedValue = acceptedValueRepository
            .findByTestParameterIdAndTestConfigureId(materialTestResult.getTestParameter().getId(),
                materialTest.getTestConfigure().getId());
        if (acceptedValue != null) {
          if (acceptedValue.isFinalResult()) {
            status = compareAverage(acceptedValue.getMinValue(), acceptedValue.getMaxValue(),
                acceptedValue.getValue(), acceptedValue.getConditionRange(), average,
                materialTestCode);
            Boolean st = (status.equals(Status.PASS)) ? true : false;
            acceptedParameters.put(materialTestResult.getTestParameter().getAbbreviation(), st);
            singleEquation = materialTestResult.getTestParameter().getAbbreviation();
          }
        }
      }
    }
    if (acceptedParameters.size() == 1) {
      updateMaterialTestStatus(materialTestCode,
          resultAccepted(acceptedParameters, singleEquation) ? Status.PENDING_PASS
              : Status.PENDING_FAIL,false);
    } else {
      String eqation = multiResultFormulaRepository
          .findByTestConfigureId(materialTest.getTestConfigure().getId()).getLogicalFormula();
      updateMaterialTestStatus(materialTestCode,
          resultAccepted(acceptedParameters, eqation) ? Status.PENDING_PASS : Status.PENDING_FAIL,false);
    }
  }

  public void materialTestApproved(String materialTestCode) {
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    if(!materialTest.isApproved()) {
    Status finalStatus = Status.PROCESS;
    if (materialTest.getStatus().equals(Status.PENDING_PASS)) {
      finalStatus = Status.PASS;
    } else if (materialTest.getStatus().equals(Status.PENDING_FAIL)) {
      finalStatus = Status.FAIL;
    } else {
      finalStatus = Status.PROCESS;
    }
    updateMaterialTestStatus(materialTestCode, finalStatus,true);
  }}

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
  public MaterialTest updateMaterialTestStatus(String code, Status status,boolean approved) {
    MaterialTest materialTest = materialTestRepository.findByCode(code);
    materialTest.setStatus(status);
    materialTest.setApproved(approved);
    MaterialTest materialTestObj = materialTestRepository.save(materialTest);
    if (materialTestObj != null && approved) {
      emailNotification.sendTestEmail(materialTestObj);
    }
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

  public boolean resultAccepted(HashMap<String, Boolean> acceptedParameters, String equation) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");

    boolean result = false;
    for (String i : acceptedParameters.keySet()) {
      engine.put(i, acceptedParameters.get(i));
    }
    try {
      result = (boolean) engine.eval(equation);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return result;
  }
}
