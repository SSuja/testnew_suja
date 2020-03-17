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
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
  @Autowired
  private ParameterResultRepository parameterResultRepository;
  @Autowired
  private MaterialTestTrialRepository materialTestTrialRepository;

  @Transactional(readOnly = true)
  public List<ParameterResult> getAllParameterResults() {
    return parameterResultRepository.findAll();
  }

  @Transactional
  public ParameterResult saveParameterResult(ParameterResult parameterResult) {
    return parameterResultRepository.save(parameterResult);
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

  public void setResult(MaterialTestTrial materialTestTrial) {
    List<ParameterResult> parameterResultList =
        findByMaterialTestTrialCode(materialTestTrial.getCode());
    String formula = materialTestTrial.getMaterialTest().getTest().getEquation().getFormula();
    Double result = roundDoubleValue(calculateTestResult(formula, parameterResultList));
    materialTestTrial.setResult(result);
    materialTestTrialRepository.save(materialTestTrial);
  }

  private double calculateTestResult(String formula, List<ParameterResult> paramResult) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    double result = 0;
    paramResult.forEach(paramObj -> {
      engine.put(paramObj.getTestParameter().getParameter().getAbbreviation(), paramObj.getValue());
    });
    try {
      result = (double) engine.eval(formula);
      System.out.println(result);
    } catch (ScriptException e) {
      e.printStackTrace();
      System.out.println("exception");
    }
    return result;
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode) {
    return parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
  }
}
