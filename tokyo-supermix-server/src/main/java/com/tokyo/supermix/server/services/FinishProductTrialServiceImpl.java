package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
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

import com.tokyo.supermix.data.dto.FinishProductTrialDto;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class FinishProductTrialServiceImpl implements FinishProductTrialService {
  @Autowired
  private FinishProductTrialRepository finishProductTrialRepository;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private FinishProductTestRepository finishProductTestRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private FinishProductParameterResultRepository finishProductParameterResultRepository;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(String code) {
    return finishProductTrialRepository.findFinishProductTrialByCode(code);
  }

  @Transactional
  public String saveFinishProductTrial(FinishProductTrial finishProductTrial) {
    if (finishProductTrial.getCode() == null) {
      String prefix = finishProductTrial.getFinishProductTest().getCode();
      List<FinishProductTrial> FinishProductTrialList =
          finishProductTrialRepository.findByCodeContaining(prefix);
      if (FinishProductTrialList.size() == 0) {
        finishProductTrial.setCode(prefix + String.format("%03d", 1));
      } else {
        finishProductTrial
            .setCode(prefix + String.format("%03d", maxNumberFromCode(FinishProductTrialList) + 1));
      }
    }
    finishProductTrialRepository.save(finishProductTrial);
    updateFinishProductResult(finishProductTrial);
    return finishProductTrial.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTrial> FinishProductTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    FinishProductTrialList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode()));
    });
    return Collections.max(list);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTrial(String code) {
    finishProductTrialRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTrialExists(String code) {
    return finishProductTrialRepository.existsByCode(code);
  }

  public void updateFinishProductResult(FinishProductTrial finishProductTrial) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.getOne(finishProductTrial.getFinishProductTest().getCode());
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishProductTest.getFinishProductSample().getId()).get();
    FinishProductParameterResult finishProductParameterResult = new FinishProductParameterResult();
    if (finishProductTrial.getTestParameter() != null) {
      finishProductParameterResult.setFinishProductSample(finishProductSample);
      finishProductParameterResult.setTestParameter(finishProductTrial.getTestParameter());
      if (finishProductTest.getTestConfigure().getDays() == null) {
        finishProductParameterResult.setResult(finishProductTrial.getValue());
        finishProductTest.setStatus(Status.PROCESS);
        finishProductSample.setStatus(
            getSlumpTestStatus(finishProductTest.getFinishProductSample().getMixDesign().getCode(),
                finishProductTrial.getValue()));
        finishProductSampleRepository.save(finishProductSample);
      } else {
        finishProductParameterResult
            .setResult(roundDoubleValue(averageStrength(finishProductTest.getCode())));
        finishProductTest.setStatus(Status.COMPLETED);
      }
      finishProductParameterResultRepository.save(finishProductParameterResult);
      finishProductTest.setResult(finishProductParameterResult.getResult());
    } else {
      finishProductTest.setResult(finishProductTrial.getValue());
      finishProductTestRepository.save(finishProductTrial.getFinishProductTest());
      if (finishProductTest.getTestConfigure().getEquation() != null) {
        if (finishProductTest.getTestConfigure().getDays() != null) {
          finishProductTest.setResult(calculateFinishProductGradeRatio(finishProductTrial,
              finishProductParameterResult.getResult(),
              getTargetSlump(finishProductSample.getMixDesign().getCode())));
        } else {
          finishProductTest.setResult(calculateFinishProductGradeRatio(finishProductTrial,
              finishProductParameterResult.getResult(),
              getTargetGrade(finishProductSample.getMixDesign().getCode())));
        }
      }
    }
  }

  public String getEquation(String finidhProductTestCode) {
    return finishProductTestRepository.getOne(finidhProductTestCode).getTestConfigure()
        .getEquation().getFormula();
  }

  public double averageStrength(String finidhProductTestCode) {
    FinishProductTest finishProductTest =
        finishProductTestRepository.findById(finidhProductTestCode).get();
    List<FinishProductTrial> finishProductTrialList =
        finishProductTrialRepository.findByFinishProductTestCode(finidhProductTestCode);
    double strengthResult = 0;
    double trialNo = 0;
    for (FinishProductTrial finishProductTrial : finishProductTrialList) {
      if (finishProductTest.getTestConfigure().getDays() != 0) {
        {
          if (finishProductTrial.getTestParameter().getParameter().getDays() == finishProductTest
              .getTestConfigure().getDays()) {
            strengthResult = strengthResult + finishProductTrial.getValue();
            trialNo++;
          }
        }
      }
    }
    return roundDoubleValue(strengthResult / trialNo);
  }

  public double calculateFinishProductGradeRatio(FinishProductTrial finishProductTrial,
      double resultValue, double targetValue) {
    HashMap<String, Double> map = new HashMap<>();
    FinishProductTest finishProductTest = finishProductTestRepository
        .findById(finishProductTrial.getFinishProductTest().getCode()).get();
    ArrayList<FinishProductTrialDto> finishProductTrialDtoList =
        new ArrayList<FinishProductTrialDto>();
    List<TestParameter> testParameterList = testParameterRepository.findByTestConfigureId(
        finishProductTrial.getFinishProductTest().getTestConfigure().getId());
    for (TestParameter testParameter : testParameterList) {
      FinishProductTrialDto finishProductTrialDto = new FinishProductTrialDto();
      finishProductTrialDto.setAbb(testParameter.getAbbreviation());
      finishProductTrialDto.setValue(resultValue);
      finishProductTrialDto.setValue(targetValue);
      finishProductTrialDtoList.add(finishProductTrialDto);
      map.put(finishProductTrialDto.getAbb(), finishProductTrialDto.getValue());
    }
    return calculateRatio(map, finishProductTest.getTestConfigure().getEquation().getFormula());
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  private double getTargetSlump(String mixDesignCode) {
    return mixDesignRepository.findByCode(mixDesignCode).getTargetSlump();
  }

  private double getTargetGrade(String mixDesignCode) {
    return mixDesignRepository.findByCode(mixDesignCode).getTargetGrade();
  }

  private Status getSlumpTestStatus(String mixDesignCode, Double slump) {
    Double targetSlump = getTargetSlump(mixDesignCode);
    if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
      return Status.PASS;
    } else {
      return Status.FAIL;
    }
  }

  public double calculateRatio(HashMap<String, Double> abbreviation, String equation) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
    double result = 0;
    for (String i : abbreviation.keySet()) {
      engine.put(i, abbreviation.get(i));
    }
    try {
      result = (double) engine.eval(equation);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getFinishProductTrialsByFinishProductTestCode(
      String finishProductTestCode) {
    return finishProductTrialRepository.findByFinishProductTestCode(finishProductTestCode);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTestExists(String finishProductTestCode) {
    return finishProductTrialRepository.existsByFinishProductTestCode(finishProductTestCode);
  }

  @Transactional
  public Status upadateFinishProductStatusByFinishProductCode(String finishProductTestCode) {
    FinishProductTest finishProductTest = finishProductTestRepository.getOne(finishProductTestCode);
    finishProductTest.setStatus(Status.COMPLETED);
    finishProductTestRepository.save(finishProductTest);
    return finishProductTest.getStatus();
  }
}
