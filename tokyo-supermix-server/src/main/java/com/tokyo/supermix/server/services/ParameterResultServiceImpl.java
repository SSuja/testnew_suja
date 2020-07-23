package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MaterialParameterResultDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.entities.TestEquationParameter;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestResultRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestEquationParameterRepository;
import com.tokyo.supermix.data.repositories.TestEquationRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
  @Autowired
  private ParameterResultRepository parameterResultRepository;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  MaterialTestTrialRepository materialTestTrialRepository;
  @Autowired
  MaterialTestRepository materialTestRepository;
  @Autowired
  TestParameterRepository testParameterRepository;
  @Autowired
  ParameterEquationRepository parameterEquationRepository;
  @Autowired
  ParameterEquationElementRepository parameterEquationElementRepository;
  @Autowired
  MaterialTestService materialTestService;
  @Autowired
  MaterialTestTrialService materialTestTrialService;
  @Autowired
  TestEquationRepository testEquationRepository;
  @Autowired
  TestEquationParameterRepository testEquationParameterRepository;
  @Autowired
  MaterialTestResultRepository materialTestResultRepository;

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

  @Transactional(readOnly = true)
  public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode) {
    return parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
  }

  public void isTestParameterValueInConfigLevel(ParameterResult parameterResult) {
    if (testParameterService
        .getTestParameterById(parameterResult.getTestParameter().getId()) != null) {
      if (testParameterService.getTestParameterById(parameterResult.getTestParameter().getId())
          .getType().equals(TestParameterType.CONFIG)) {
        parameterResult.setValue(testParameterService
            .getTestParameterById(parameterResult.getTestParameter().getId()).getValue());
      }
    }
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> findParameterResultByPlantCode(String plantCode) {
    return parameterResultRepository
        .findByMaterialTestTrialMaterialTestIncomingSamplePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> getParameterResultWithConfigValue(String materialTestTrialCode,
      String materialTestCode) {
    List<ParameterResult> paramterResultList = parameterResultRepository
        .findByMaterialTestTrialCodeAndMaterialTestCode(materialTestTrialCode, materialTestCode);
    for (ParameterResult parameterResult : paramterResultList) {
      isTestParameterValueInConfigLevel(parameterResult);
    }
    return paramterResultList;
  }

  public List<ParameterResult> findByMaterialTestCode(String materialTestCode) {
    return parameterResultRepository.findByMaterialTestCode(materialTestCode);
  }

  @Transactional(readOnly = true)
  public List<ParameterResult> getTestParamWithEquationByTestTrial(String materialTestTrialCode) {
    return null;
  }

  public String getEquation(Long paramterEquationId) {
    return parameterEquationRepository.findById(paramterEquationId).get().getEquation()
        .getFormula();
  }

  public double findResult(HashMap<String, Double> abb, String equation) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    double result = 0.0;
    for (String i : abb.keySet()) {
      engine.put(i, abb.get(i));
    }
    try {
      result = (double) engine.eval(equation);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return roundDoubleValue(result);
  }

  @Override
  public void saveParameterResults(
      List<MaterialParameterResultDto> materialParameterResultDtolist) {
    for (MaterialParameterResultDto materialParameterResultDto : materialParameterResultDtolist) {
      MaterialTest materialTest = materialTestService
          .getMaterialTestByCode(materialParameterResultDto.getMaterialTestCode());
      MaterialTestTrial materialTestTrial = new MaterialTestTrial();
      materialTestTrial.setMaterialTest(materialTest);
      materialParameterResultDto.setMaterialTestTrialCode(
          materialTestTrialService.saveMaterialTestTrial(materialTestTrial));
      setParameterResult(materialParameterResultDto);
    }
  }

  public void setParameterResult(MaterialParameterResultDto materialParameterResultDto) {
    materialParameterResultDto.getParameterResults().forEach(parameterResultDto -> {
      ParameterResult parameterResult = new ParameterResult();
      parameterResult.setMaterialTest(
          materialTestRepository.findByCode(materialParameterResultDto.getMaterialTestCode()));
      parameterResult.setMaterialTestTrial(materialTestTrialRepository
          .findByCode(materialParameterResultDto.getMaterialTestTrialCode()));
      parameterResult.setTestParameter(
          testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
      parameterResult.setValue(parameterResultDto.getValue());
      parameterResultRepository.save(parameterResult);
    });
    getvalueCalculate(materialParameterResultDto.getMaterialTestTrialCode());
  }


  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  public void getvalueCalculate(String materialTestTrialCode) {
    MaterialTestTrial materialTestTrial = materialTestTrialRepository.getOne(materialTestTrialCode);
    List<TestParameter> testparameters = testParameterRepository
        .findByTestConfigureId(materialTestTrial.getMaterialTest().getTestConfigure().getId());

    List<TestParameter> calculationTestParameterList =
        testParameterRepository.findByTestConfigureIdAndInputMethods(
            materialTestTrial.getMaterialTest().getTestConfigure().getId(),
            InputMethod.CALCULATION);
    calculationTestParameterList.forEach(testparameter -> {
      if (!testparameter.getType().equals(TestParameterType.RESULT)) {
        ParameterEquation parameterEquation =
            parameterEquationRepository.findByTestParameterId(testparameter.getId());
        String paraEq = "";
        List<ParameterEquationElement> parameterEquationElementlist =
            parameterEquationElementRepository.findByParameterEquationId(parameterEquation.getId());
        paraEq = parameterEquation.getEquation().getFormula();
        HashMap<String, Double> sum = new HashMap<String, Double>();
        for (ParameterEquationElement paramEquationEle : parameterEquationElementlist) {

          ParameterResult parameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  paramEquationEle.getTestParameter().getId(), materialTestTrialCode);
          sum.put(paramEquationEle.getTestParameter().getAbbreviation(),
              parameterResult.getValue());
        }
        ParameterResult parameterResultsum =
            parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testparameter.getId(), materialTestTrialCode);
        parameterResultsum.setValue(findResult(sum, paraEq));
        parameterResultRepository.save(parameterResultsum);

      }
    });
    for (TestParameter tp : testparameters) {
      if (tp.getType().equals(TestParameterType.RESULT)
          && tp.getInputMethods().equals(InputMethod.OBSERVE)) {
        parameterResultRepository.findByMaterialTestTrialCodeAndMaterialTestCode(
            materialTestTrialCode, materialTestTrial.getMaterialTest().getCode()).forEach(par -> {
              if (par.getTestParameter().getType().equals(TestParameterType.INPUT)) {
                ParameterResult pr =
                    parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                        tp.getId(), materialTestTrialCode);
                pr.setValue(par.getValue());
                parameterResultRepository.save(pr);
                List<ParameterResult> parrelist2 =
                    parameterResultRepository.findByTestParameterIdAndMaterialTestCode(tp.getId(),
                        materialTestTrial.getMaterialTest().getCode());
                Double val = 0.0;
                for (ParameterResult paralist1 : parrelist2) {
                  val = val + paralist1.getValue();
                }
                Double avg = val.doubleValue() / parrelist2.size();

                if (materialTestResultRepository
                    .findByMaterialTestCode(materialTestTrial.getMaterialTest().getCode())
                    .size() == 0) {
                  MaterialTestResult mtr = new MaterialTestResult();
                  mtr.setMaterialTest(materialTestTrial.getMaterialTest());
                  mtr.setResult(avg);
                  materialTestResultRepository.save(mtr);
                } else {
                  List<MaterialTestResult> mtr = materialTestResultRepository
                      .findByMaterialTestCode(materialTestTrial.getMaterialTest().getCode());
                  for (MaterialTestResult MaterialTestResult : mtr) {
                    MaterialTestResult.setResult(avg);
                    materialTestResultRepository.save(MaterialTestResult);
                  }
                }
              }
            });;
      }
      if (tp.getType().equals(TestParameterType.RESULT)
          && tp.getInputMethods().equals(InputMethod.CALCULATION)) {
        TestEquation testEquation = testEquationRepository.findByTestParameterId(tp.getId());
        String testEq = "";
        List<TestEquationParameter> testEquationParameterlist =
            testEquationParameterRepository.findByTestEquationId(testEquation.getId());
        testEq = testEquation.getEquation().getFormula();
        HashMap<String, Double> testsum = new HashMap<String, Double>();
        for (TestEquationParameter testEquationElement : testEquationParameterlist) {
          ParameterResult testparameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  testEquationElement.getTestParameter().getId(), materialTestTrialCode);
          testsum.put(testEquationElement.getTestParameter().getAbbreviation(),
              testparameterResult.getValue());
        }
        ParameterResult testparameterResultsum = parameterResultRepository
            .findByTestParameterIdAndMaterialTestTrialCode(tp.getId(), materialTestTrialCode);
        testparameterResultsum.setValue(findResult(testsum, testEq));
        testparameterResultsum.setTestEquation(testEquation);
        parameterResultRepository.save(testparameterResultsum);
        List<ParameterResult> parrelist =
            parameterResultRepository.findByTestParameterIdAndMaterialTestCode(tp.getId(),
                materialTestTrial.getMaterialTest().getCode());
        Double val = 0.0;
        for (ParameterResult paralist1 : parrelist) {
          val = val + paralist1.getValue();
        }
        Double avg = val.doubleValue() / parrelist.size();

        if (materialTestResultRepository.findByTestEquationAndMaterialTestCode(testEquation,
            materialTestTrial.getMaterialTest().getCode()) == null) {
          MaterialTestResult mtr = new MaterialTestResult();
          mtr.setMaterialTest(materialTestTrial.getMaterialTest());
          mtr.setTestEquation(testEquation);
          mtr.setResult(avg);
          materialTestResultRepository.save(mtr);
        }
        MaterialTestResult mtr = materialTestResultRepository.findByTestEquationAndMaterialTestCode(
            testEquation, materialTestTrial.getMaterialTest().getCode());
        mtr.setResult(avg);
        materialTestResultRepository.save(mtr);
      }

    }

  }

}
