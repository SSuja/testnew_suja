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
import com.tokyo.supermix.data.dto.ParameterResultRequestDto;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.EntryLevel;
import com.tokyo.supermix.data.enums.EquationType;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
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
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  MaterialQualityParameterRepository materialQualityParameterRepository;
  @Autowired
  private ParameterEquationRepository parameterEquationRepository;
  @Autowired
  private ParameterEquationElementRepository parameterEquationElementRepository;

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

//  private double calculateTestResult(String equation, List<ParameterResult> parameterResultList) {
//    ScriptEngineManager mgr = new ScriptEngineManager();
//    ScriptEngine engine = mgr.getEngineByName("JavaScript");
//    double result = 0;
//    for (ParameterResult parameterResult : parameterResultList) {
//      TestParameter testParameter =
//          testParameterRepository.findById(parameterResult.getTestParameter().getId()).get();
//      if (!(testParameter.isEquationExists())) {
//        ParameterEquation parameterEquation =
//            parameterEquationRepository.findByTestParameterId(testParameter.getId());
//        List<ParameterEquationElement> parameterEquationElementList =
//            parameterEquationElementRepository.findByParameterEquationId(parameterEquation.getId());
//        for (ParameterEquationElement parameterEquationElement : parameterEquationElementList) {
//          engine.put(parameterEquationElement.getTestParameter().getAbbreviation(),
//              parameterResult.getValue());
//        }
//     } 
//      //else {
////        engine.put(testParameter.getAbbreviation(), testParameter.getValue());
////        if (testParameter.getParameter() != null) {
////          if (testParameter.getEntryLevel().equals(EntryLevel.TEST)) {
////            engine.put(testParameter.getAbbreviation(), parameterResult.getValue());
////          }
////        }
////      }
//    }
//    try {
//      result = (double) engine.eval(equation);
//    } catch (ScriptException e) {
//      e.printStackTrace();
//    }
//    return result;
//  }

  private void updateParameterResult(List<ParameterResult> parameterResultList) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    double result = 0;
    for (ParameterResult parameterResult : parameterResultList) {
      TestParameter testParameter =
          testParameterRepository.findById(parameterResult.getTestParameter().getId()).get();
      if (testParameter.isEquationExists()) {
        System.out.println("bgvbd rbgyebgeb gbe ubguebgu");
        ParameterEquation parameterEquation =
            parameterEquationRepository.findByTestParameterId(testParameter.getId());
        List<ParameterEquationElement> parameterEquationElementList =
            parameterEquationElementRepository.findByParameterEquationId(parameterEquation.getId());
        for (ParameterEquationElement parameterEquationElement : parameterEquationElementList) {
          engine.put(parameterEquationElement.getTestParameter().getAbbreviation(),
              parameterResultList.get(1).getValue());
          System.out.println("abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+parameterEquationElement.getTestParameter().getAbbreviation());
          System.out.println("valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+parameterResultList.get(0).getValue());
//          Double value = calculateTestResult(
//              parameterEquationElement.getParameterEquation().getEquation().getFormula(),
//              parameterResultList);
          try {
            result = (double) engine.eval(parameterEquationElement.getParameterEquation().getEquation().getFormula());
          } catch (ScriptException e) {
            e.printStackTrace();
          }
          parameterResult.setValue(result);
          System.out.println("jhfergedbghetbgebhtbgehgethtreubjhg tbtgjrf bvjr dbvje tdj " + result);
          parameterResultRepository.save(parameterResult);
        }
      }
    }
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode) {
    return parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional
  public void updateMaterialTestTrialResult(MaterialTestTrial materialTestTrial) {
    List<ParameterResult> parameterResultList =
        findByMaterialTestTrialCode(materialTestTrial.getCode());
    if (materialTestTrial.getMaterialTest().getTestConfigure().getEquation() == null) {
      for (ParameterResult parameterResult : parameterResultList) {
        materialTestTrial.setResult(parameterResult.getValue());
      }
    } else {
   
//      if (materialTestTrial.getMaterialTest().getTestConfigure().getEquation().getEquationType()
//          .equals(EquationType.PARAMETER)) {
        updateParameterResult(parameterResultList);
//      } else if (materialTestTrial.getMaterialTest().getTestConfigure().getEquation()
//          .getEquationType().equals(EquationType.TRIAL)) {
//        Double result = roundDoubleValue(calculateTestResult(
//            materialTestTrial.getMaterialTest().getTestConfigure().getEquation().getFormula(),
//            parameterResultList));
//        materialTestTrial.setResult(result);
//        materialTestTrialRepository.save(materialTestTrial);
//      }
    }
  }

  public void isTestParameterValueInConfigureLevel(ParameterResultRequestDto parameterResult) {
    if (testParameterService.getTestParameterById(parameterResult.getTestParameterId()) != null) {
      if (testParameterService.getTestParameterById(parameterResult.getTestParameterId())
          .getEntryLevel().equals(EntryLevel.CONFIGURE)) {
        parameterResult.setValue(testParameterService
            .getTestParameterById(parameterResult.getTestParameterId()).getValue());
      }
    }
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> findParameterResultByPlantCode(String plantCode) {
    return parameterResultRepository
        .findByMaterialTestTrialMaterialTestIncomingSamplePlantCode(plantCode);
  }
}
