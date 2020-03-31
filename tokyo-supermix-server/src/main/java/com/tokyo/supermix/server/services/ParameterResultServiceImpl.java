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
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
  @Autowired
  private ParameterResultRepository parameterResultRepository;
  @Autowired
  private MaterialTestTrialRepository materialTestTrialRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;

  @Transactional
  public void saveParameterValue(ParameterResult parameterValue) {
    parameterResultRepository.save(parameterValue);
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> getAllParameterResults() {
    return parameterResultRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteParameterResult(Long id) {
    parameterResultRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public ParameterResult getParameterResultById(Long id) {
    return parameterResultRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isParameterResultExist(Long id) {
    return parameterResultRepository.existsById(id);
  }

  private double calculateTestResult(String equation, List<ParameterResult> parameterResultList) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    double result = 0;
    for (ParameterResult parameterResult : parameterResultList) {
      TestParameter testParameter =
          testParameterRepository.findById(parameterResult.getTestParameter().getId()).get();
      engine.put(testParameter.getParameter().getAbbreviation(), parameterResult.getValue());
    }
    try {
      result = (double) engine.eval(equation);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode) {
    return parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  public void updateMaterialTestTrial(MaterialTestTrial materialTestTrial) {
    List<ParameterResult> parameterResultList =
        findByMaterialTestTrialCode(materialTestTrial.getCode());
    String formula = materialTestTrial.getMaterialTest().getTest().getEquation().getFormula();
    Double result = roundDoubleValue(calculateTestResult(formula, parameterResultList));
    materialTestTrial.setResult(result);
    materialTestTrialRepository.save(materialTestTrial);
  }

  @Transactional
  public void updateMaterialTestTrialResult(MaterialTestTrial materialTestTrial) {
    if (materialTestTrial.getMaterialTest().getTest().getEquation() == null) {
      List<ParameterResult> parameterResultList =
          findByMaterialTestTrialCode(materialTestTrial.getCode());
      for (ParameterResult parameterResult : parameterResultList) {
        materialTestTrial.setResult(parameterResult.getValue());
      }
    } else {
      updateMaterialTestTrial(materialTestTrial);
    }
  }
}
