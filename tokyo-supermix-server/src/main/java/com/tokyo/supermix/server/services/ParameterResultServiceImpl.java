package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.tokyo.supermix.data.dto.ParameterResultDto;
import com.tokyo.supermix.data.dto.SieveParameterResultDto;
import com.tokyo.supermix.data.dto.SieveTestResultsDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.SieveSize;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.SieveSizeRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
  @Autowired
  private ParameterResultRepository parameterResultRepository;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  MaterialQualityParameterRepository materialQualityParameterRepository;
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
  SieveSizeRepository sieveSizeRepository;

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

  // private Double roundDoubleValue(Double value) {
  // DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
  // return Double.valueOf(decimalFormat.format(value));
  // }

  public void isTestParameterValueInConfigLevel(ParameterResult parameterResult) {
    if (testParameterService
        .getTestParameterById(parameterResult.getTestParameter().getId()) != null) {
      if (testParameterService.getTestParameterById(parameterResult.getTestParameter().getId())
          .getEntryLevel().equals(TestParameterType.CONFIGURE)) {
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
    return parameterResultRepository
        .findByMaterialTestTrialCodeAndTestParameterEquationExistsTrue(materialTestTrialCode);
  }

  // set values to equation less parameters
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
    int no = materialParameterResultDtolist.size();
    Double du = 0.0;
    if (no > 1) {
      List<ParameterResultDto> paralist =
          materialParameterResultDtolist.get(no - 1).getParameterResults();
      for (ParameterResultDto paraRe : paralist) {
        TestParameter testParameter = testParameterRepository.getOne(paraRe.getTestParameterId());
        if (testParameter.getEntryLevel().equals(TestParameterType.TEST)) {
          du = paraRe.getValue();
        }

      }

    }
    for (MaterialParameterResultDto materialParameterResultDto : materialParameterResultDtolist) {
      MaterialTest materialTest = materialTestService
          .getMaterialTestByCode(materialParameterResultDto.getMaterialTestCode());
      MaterialTestTrial materialTestTrial = new MaterialTestTrial();
      materialTestTrial.setMaterialTest(materialTest);
      // materialParameterResultDto.parameterResults.forEach(par ->{
      // par.getTestParameterId().
      // });
      if (materialParameterResultDto.getSieveSizeId() != null) {
        SieveSize sieveSize =
            sieveSizeRepository.getOne(materialParameterResultDto.getSieveSizeId());
        materialTestTrial.setSieveSize(sieveSize);
      }

      materialParameterResultDto.setMaterialTestTrialCode(
          materialTestTrialService.saveMaterialTestTrial(materialTestTrial));
      setParameterResult(materialParameterResultDto, du);
    }
  }

  public void setParameterResult(MaterialParameterResultDto materialParameterResultDto, Double du) {
    ArrayList<ParameterResult> parameterResultList = new ArrayList<ParameterResult>();
    for (int i = 0; i < materialParameterResultDto.parameterResults.size(); i++) {
      ParameterResultDto parameterResultDto = materialParameterResultDto.parameterResults.get(i);
      if (du != 0.0) {
        TestParameter testParameter =
            testParameterRepository.getOne(parameterResultDto.getTestParameterId());
        if (!testParameter.isEquationExists()) {
          if (testParameter.getEntryLevel().equals(TestParameterType.GENERATE)) {
            ParameterResult parameterResult = new ParameterResult();
            parameterResult.setMaterialTest(materialTestRepository
                .findByCode(materialParameterResultDto.getMaterialTestCode()));
            parameterResult.setMaterialTestTrial(materialTestTrialRepository
                .findByCode(materialParameterResultDto.getMaterialTestTrialCode()));
            parameterResult.setTestParameter(
                testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
            parameterResult.setValue(du);
            parameterResultList.add(parameterResult);
            parameterResultRepository.save(parameterResult);
          } else {
            ParameterResult parameterResult = new ParameterResult();
            parameterResult.setMaterialTest(materialTestRepository
                .findByCode(materialParameterResultDto.getMaterialTestCode()));
            parameterResult.setMaterialTestTrial(materialTestTrialRepository
                .findByCode(materialParameterResultDto.getMaterialTestTrialCode()));
            parameterResult.setTestParameter(
                testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
            parameterResult.setValue(parameterResultDto.getValue());
            parameterResultList.add(parameterResult);
            parameterResultRepository.save(parameterResult);
          }
        } else {

          ParameterResult parameterResult = new ParameterResult();
          parameterResult.setMaterialTest(
              materialTestRepository.findByCode(materialParameterResultDto.getMaterialTestCode()));
          parameterResult.setMaterialTestTrial(materialTestTrialRepository
              .findByCode(materialParameterResultDto.getMaterialTestTrialCode()));
          parameterResult.setTestParameter(
              testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
          parameterResult.setValue(parameterResultDto.getValue());
          parameterResultList.add(parameterResult);
          parameterResultRepository.save(parameterResult);
        }
      } else {
        ParameterResult parameterResult = new ParameterResult();
        parameterResult.setMaterialTest(
            materialTestRepository.findByCode(materialParameterResultDto.getMaterialTestCode()));
        parameterResult.setMaterialTestTrial(materialTestTrialRepository
            .findByCode(materialParameterResultDto.getMaterialTestTrialCode()));



        parameterResult.setTestParameter(
            testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
        parameterResult.setValue(parameterResultDto.getValue());
        parameterResultList.add(parameterResult);
        parameterResultRepository.save(parameterResult);
      }
    }
    getvalueCalculate(materialParameterResultDto.getMaterialTestTrialCode());
  }

  public void getvalueCalculate(String materialTestTrialCode) {
    MaterialTestTrial materialTestTrial = materialTestTrialRepository.getOne(materialTestTrialCode);
    List<TestParameter> testparameters = testParameterRepository
        .findByTestConfigureId(materialTestTrial.getMaterialTest().getTestConfigure().getId());
    // List<TestParameter> testParametershasEqu =
    // testParameterRepository.findByTestConfigureIdAndEquationExistsTrue(
    // materialTestTrial.getMaterialTest().getTestConfigure().getId());
    String mainEquation = "";
    if (!materialTestTrial.getMaterialTest().getTestConfigure().isEquationExists()) {
      testparameters.forEach(tes -> {
        ParameterResult parameterResultsum = parameterResultRepository
            .findByTestParameterIdAndMaterialTestTrialCode(tes.getId(), materialTestTrialCode);
        materialTestTrial.setResult(parameterResultsum.getValue());
        materialTestTrialRepository.save(materialTestTrial);
      });
    } else {
      List<TestParameter> testParametershasEqu =
          testParameterRepository.findByTestConfigureIdAndEquationExistsTrue(
              materialTestTrial.getMaterialTest().getTestConfigure().getId());


      // if (materialTestTrial.getMaterialTest().getTestConfigure().getEquation().getName().) {
      // mainEquation =
      // materialTestTrial.getMaterialTest().getTestConfigure().getEquation().getFormula();
      // }
      if (!(materialTestTrial.getMaterialTest().getTestConfigure().getEquation() == null)) {
        mainEquation =
            materialTestTrial.getMaterialTest().getTestConfigure().getEquation().getFormula();
      }
      // List<ParameterEquation> parameterEquations = new ArrayList<>();
      for (TestParameter testparameter : testParametershasEqu) {
        ParameterEquation parameterEquation =
            parameterEquationRepository.findByTestParameterId(testparameter.getId());
        // parameterEquations.add(parameterEquation);
        String paraEq = "";
        List<ParameterEquationElement> parameterEquationElementlist =
            parameterEquationElementRepository.findByParameterEquationId(parameterEquation.getId());
        paraEq = parameterEquation.getEquation().getFormula();



        HashMap<String, Double> sum = new HashMap<String, Double>();
        for (ParameterEquationElement paramEquationEle : parameterEquationElementlist) {
          Long testParameterId = paramEquationEle.getTestParameter().getId();
          paramEquationEle.getTestParameter().getValue();
          paramEquationEle.getTestParameter().getAbbreviation();
          ParameterResult parameterResult =
              parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                  testParameterId, materialTestTrialCode);
          sum.put(paramEquationEle.getTestParameter().getAbbreviation(),
              parameterResult.getValue());
        }
        ParameterResult parameterResultsum =
            parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(
                testparameter.getId(), materialTestTrialCode);
        parameterResultsum.setValue(findResult(sum, paraEq));
        parameterResultRepository.save(parameterResultsum);
      }
      HashMap<String, Double> main = new HashMap<String, Double>();
      for (TestParameter tepa : testparameters) {
        tepa.getAbbreviation();
        ParameterResult parameterResultmain = parameterResultRepository
            .findByTestParameterIdAndMaterialTestTrialCode(tepa.getId(), materialTestTrialCode);
        main.put(tepa.getAbbreviation(), parameterResultmain.getValue());
      }
      // System.out.println("****AM***" + findResult(main, mainEquation));
      if (!mainEquation.isEmpty()) {
        materialTestTrial.setResult(findResult(main, mainEquation));
        materialTestTrialRepository.save(materialTestTrial);
      }
    }
  }

  // }
  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  public List<SieveTestResultsDto> getSieveTestResultsByMaterialTestCode(String materialTestCode) {
    List<MaterialTestTrial> materialTestTriallist =
        materialTestTrialService.getMaterialTestTrialByMaterialTestCode(materialTestCode);
    List<SieveTestResultsDto> sieveTestResultsDtolist = new ArrayList<>();

    for (MaterialTestTrial materialTestTrial : materialTestTriallist) {
      SieveTestResultsDto sieveTestResultsDto = new SieveTestResultsDto();

      sieveTestResultsDto.setSieveSize(materialTestTrial.getSieveSize().getSize());
      List<ParameterResult> ParameterResultlist =
          parameterResultRepository.findByMaterialTestTrialCode(materialTestTrial.getCode());
      List<SieveParameterResultDto> SieveParameterResultDtolist = new ArrayList<>();
      for (ParameterResult parRes : ParameterResultlist) {
        SieveParameterResultDto sieveParameterResultDto = new SieveParameterResultDto();
        sieveParameterResultDto
            .setParameterName(parRes.getTestParameter().getParameter().getName());
        sieveParameterResultDto.setValue(parRes.getValue());
        SieveParameterResultDtolist.add(sieveParameterResultDto);
      }
      sieveTestResultsDto.setSieveParameters(SieveParameterResultDtolist);
      sieveTestResultsDtolist.add(sieveTestResultsDto);
    }
    return sieveTestResultsDtolist;
  }

}
