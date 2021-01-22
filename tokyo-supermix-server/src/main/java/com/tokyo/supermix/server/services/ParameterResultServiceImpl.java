package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MaterialParameterResultDto;
import com.tokyo.supermix.data.dto.ParameterResultDetails;
import com.tokyo.supermix.data.dto.ParameterResultViewResponseDto;
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
import com.tokyo.supermix.data.enums.ParameterDataType;
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

  public String getEquation(Long paramterEquationId) {
    return parameterEquationRepository.findById(paramterEquationId).get().getEquation()
        .getFormula();
  }

  public double findResult(HashMap<String, Double> abb, String equation) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
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
      if (testParameterRepository.getOne(parameterResultDto.getTestParameterId()).getParameter()
          .getParameterDataType().equals(ParameterDataType.DATETIME)) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(parameterResultDto.getDateValue(), formatter);
        parameterResult.setDateValue(dateTime);
      } else {
        parameterResult.setValue(parameterResultDto.getValue());
      }
      parameterResultRepository.save(parameterResult);
    });
    getValueCalculate(materialParameterResultDto.getMaterialTestTrialCode());
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  public List<ParameterResultViewResponseDto> findResultsByMaterialTestCode(
      String materialTestCode) {
    List<ParameterResultViewResponseDto> parameterResultViewResponseDtoList = new ArrayList<>();
    materialTestTrialRepository.findByMaterialTestCode(materialTestCode)
        .forEach(materialTestTrial -> {
          ParameterResultViewResponseDto parameterResultViewResponseDto =
              new ParameterResultViewResponseDto();
          parameterResultViewResponseDto.setMaterialTestTrialCode(materialTestTrial.getCode());
          parameterResultViewResponseDto
              .setMaterialTestTrialTrialNo(materialTestTrial.getTrialNo());
          List<ParameterResultDetails> parameterResultList = new ArrayList<>();
          parameterResultRepository.findByMaterialTestTrialCode(materialTestTrial.getCode())
              .forEach(parameterResult -> {
                ParameterResultDetails parameterResultDto = new ParameterResultDetails();
                parameterResultDto.setTestParameterId(parameterResult.getId());
                parameterResultDto.setTestParameterAbbrebation(
                    parameterResult.getTestParameter().getAbbreviation());
                parameterResultDto.setTestParameterInputMethod(
                    parameterResult.getTestParameter().getInputMethods().toString());
                parameterResultDto
                    .setTestParameterType(parameterResult.getTestParameter().getType().toString());
                parameterResultDto.setTestParameterName(
                    parameterResult.getTestParameter().getParameter().getName());
                if (parameterResult.getTestParameter().getParameter().getParameterDataType()
                    .equals(ParameterDataType.DATETIME)) {
                  parameterResultDto.setValue(parameterResult.getValue());
                } else {
                  parameterResultDto.setValue(parameterResult.getValue());
                }
                parameterResultList.add(parameterResultDto);
              });
          parameterResultViewResponseDto.setParameterResult(parameterResultList);
          parameterResultViewResponseDtoList.add(parameterResultViewResponseDto);
        });
    return parameterResultViewResponseDtoList;
  }

  public void getValueCalculate(String materialTestTrialCode) {
    MaterialTestTrial materialTestTrial = materialTestTrialRepository.getOne(materialTestTrialCode);
    List<TestParameter> testparameters = testParameterRepository
        .findByTestConfigureId(materialTestTrial.getMaterialTest().getTestConfigure().getId());
    List<TestParameter> calculationTestParameterList =
        testParameterRepository.findByTestConfigureIdAndInputMethods(
            materialTestTrial.getMaterialTest().getTestConfigure().getId(),
            InputMethod.CALCULATION);
       if (calculationTestParameterList.isEmpty()) {
           testparameters.forEach(testparameter -> {
        getResults(testparameter, materialTestTrial);
      });
    }
   
    if (!calculationTestParameterList.isEmpty()) {
      calculationTestParameterList.forEach(testparameter -> {
        calculateEquation(materialTestTrialCode, testparameter);
      });
    }
  
    if (testparameters.stream()
        .anyMatch(tespa -> tespa.getInputMethods().equals(InputMethod.OBSERVE)
            && tespa.getType().equals(TestParameterType.RESULT))) {
      testparameters.forEach(testparameter -> {
        if (testparameter.getInputMethods().equals(InputMethod.OBSERVE)
            && testparameter.getType().equals(TestParameterType.RESULT)) {
          getResults(testparameter, materialTestTrial);
        }
      });
    }
  }

  private void calculateEquation(String materialTestTrialCode, TestParameter testparameter) {
    if (testparameter.getType().equals(TestParameterType.RESULT)) {
      TestEquation testEquation =
          testEquationRepository.findByTestParameterId(testparameter.getId());

      String testTrialEquation = "";
      List<TestEquationParameter> testEquationParameterlist =
          testEquationParameterRepository.findByTestEquationId(testEquation.getId());
      testTrialEquation = testEquation.getEquation().getFormula();
      HashMap<String, Double> testResultTot = new HashMap<String, Double>();
      List<LocalDateTime> dateResultlist = new ArrayList<>();
      for (TestEquationParameter testEquationElement : testEquationParameterlist) {
        if (testEquationElement.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && parameterResultRepository
                .findByTestParameterIdAndMaterialTestTrialCode(
                    testEquationElement.getTestParameter().getId(), materialTestTrialCode)
                .getValue() == null) {
          calculateEquation(materialTestTrialCode, testparameter);
        }
        if (testEquationElement.getTestParameter().getParameter().getParameterDataType()
            .equals(ParameterDataType.DATETIME)) {
          ParameterResult parameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  testEquationElement.getTestParameter().getId(), materialTestTrialCode);
          dateResultlist.add(parameterResult.getDateValue());
        } else {

          ParameterResult parameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  testEquationElement.getTestParameter().getId(), materialTestTrialCode);

          testResultTot.put(testEquationElement.getTestParameter().getAbbreviation(),
              parameterResult.getValue());
        }
      }
      if (!dateResultlist.isEmpty()) {
        ParameterResult parameterResultsum =
            parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testparameter.getId(), materialTestTrialCode);
        Double val = findTimeDifference(dateResultlist);
        parameterResultsum.setValue(val);
        parameterResultRepository.save(parameterResultsum);
      }
      if (!testResultTot.isEmpty()) {
        ParameterResult parameterResultsum =
            parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testparameter.getId(), materialTestTrialCode);
        parameterResultsum.setValue(findResult(testResultTot, testTrialEquation));
        parameterResultRepository.save(parameterResultsum);
      }
      MaterialTestTrial materialTestTrial =
          materialTestTrialRepository.getOne(materialTestTrialCode);
      List<ParameterResult> paramResultList =
          parameterResultRepository.findByTestParameterIdAndMaterialTestCode(testparameter.getId(),
              materialTestTrial.getMaterialTest().getCode());
      Double average =
          paramResultList.stream().collect(Collectors.averagingDouble(p -> p.getValue()));
      MaterialTestResult materialTestResults = new MaterialTestResult();
      if (materialTestResultRepository.findByTestParameterAndMaterialTestCode(testparameter,
          materialTestTrial.getMaterialTest().getCode()) == null) {
        materialTestResults.setMaterialTest(materialTestTrial.getMaterialTest());
      } else {
        materialTestResults = materialTestResultRepository.findByTestParameterAndMaterialTestCode(
            testparameter, materialTestTrial.getMaterialTest().getCode());
      }
      materialTestResults.setResult(average);
      materialTestResults.setTestEquation(testEquation);
      materialTestResults.setTestParameter(testparameter);
      materialTestResultRepository.save(materialTestResults);
    } else if (!testparameter.getType().equals(TestParameterType.RESULT)) {
      ParameterEquation parameterEquation =
          parameterEquationRepository.findByTestParameterId(testparameter.getId());
      String paraEq = "";
      List<ParameterEquationElement> parameterEquationElementlist =
          parameterEquationElementRepository.findByParameterEquationId(parameterEquation.getId());
      paraEq = parameterEquation.getEquation().getFormula();
      HashMap<String, Double> sum = new HashMap<String, Double>();
      List<LocalDateTime> datelist = new ArrayList<>();
      for (ParameterEquationElement paramEquationEle : parameterEquationElementlist) {
        Long testParameterId = paramEquationEle.getTestParameter().getId();
        if (paramEquationEle.getTestParameter().getInputMethods().equals(InputMethod.CALCULATION)
            && parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testParameterId, materialTestTrialCode).getValue() == null) {
          calculateEquation(materialTestTrialCode, paramEquationEle.getTestParameter());
        }
        if (paramEquationEle.getTestParameter().getParameter().getParameterDataType()
            .equals(ParameterDataType.DATETIME)) {
          ParameterResult parameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  paramEquationEle.getTestParameter().getId(), materialTestTrialCode);
          datelist.add(parameterResult.getDateValue());
        } else {
          ParameterResult parameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  testParameterId, materialTestTrialCode);
          sum.put(paramEquationEle.getTestParameter().getAbbreviation(),
              parameterResult.getValue());
        }
      }

      if (!datelist.isEmpty()) {
        ParameterResult parameterResultsum =
            parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testparameter.getId(), materialTestTrialCode);
        Double val = findTimeDifference(datelist);
        parameterResultsum.setValue(val);
        parameterResultRepository.save(parameterResultsum);
      }

      if (!sum.isEmpty()) {
        ParameterResult parameterResultsum =
            parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testparameter.getId(), materialTestTrialCode);
        parameterResultsum.setValue(findResult(sum, paraEq));
        parameterResultRepository.save(parameterResultsum);
      }
    }
  }

  private Double findTimeDifference(List<LocalDateTime> lis) {
    Duration duration = Duration.between(lis.get(0), lis.get(1));
    Double value = Math.abs((double) duration.toMinutes());
    return value;
  }

  private void getResults(TestParameter testparameter, MaterialTestTrial materialTestTrial) {
    Double average = parameterResultRepository
        .findByTestParameterIdAndMaterialTestCode(testparameter.getId(),
            materialTestTrial.getMaterialTest().getCode())
        .stream().collect(Collectors.averagingDouble(p -> p.getValue()));
    if (materialTestResultRepository.findByTestParameterAndMaterialTestCode(testparameter,
        materialTestTrial.getMaterialTest().getCode()) == null) {
      MaterialTestResult materialTestResults = new MaterialTestResult();
      materialTestResults.setMaterialTest(materialTestTrial.getMaterialTest());
      materialTestResults.setResult(average);
      materialTestResults.setTestParameter(testparameter);
      materialTestResultRepository.save(materialTestResults);
    } else {
      MaterialTestResult materialResults =
          materialTestResultRepository.findByTestParameterAndMaterialTestCode(testparameter,
              materialTestTrial.getMaterialTest().getCode());
      materialResults.setResult(average);
      materialResults.setTestParameter(testparameter);
      materialTestResultRepository.save(materialResults);
    }
  }
}
