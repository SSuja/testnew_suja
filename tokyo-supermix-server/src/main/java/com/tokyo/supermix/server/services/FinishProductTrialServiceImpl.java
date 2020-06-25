package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
            .setCode(prefix + String.format("%04d", maxNumberFromCode(FinishProductTrialList) + 1));
      }
    }
    finishProductTrialRepository.save(finishProductTrial);
    return finishProductTrial.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTrial> finishProductTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductTrialList.forEach(obj -> {
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
    saveFinishProductTrial(finishProductTrial);
    FinishProductTest finishProductTest =
        finishProductTestRepository.getOne(finishProductTrial.getFinishProductTest().getId());
    FinishProductSample finishProductSample = finishProductSampleRepository
        .findById(finishProductTest.getFinishProductSample().getId()).get();
    if (finishProductTrial.getTestParameter() != null) {
      FinishProductParameterResult finishProductParameterResult =
          new FinishProductParameterResult();
      finishProductParameterResult.setFinishProductSample(finishProductSample);
      finishProductParameterResult.setTestParameter(finishProductTrial.getTestParameter());
      if (finishProductTest.getTestConfigure().getTest().getName().equalsIgnoreCase("slump")) {
        finishProductParameterResult.setResult(finishProductTrial.getValue());
      } else {

      }
      finishProductParameterResultRepository.save(finishProductParameterResult);
      finishProductTest.setResult(finishProductParameterResult.getResult());
      finishProductTestRepository.save(finishProductTest);

    } else {
      finishProductTest.setResult(finishProductTrial.getValue());
      finishProductTestRepository.save(finishProductTrial.getFinishProductTest());
    }
  }

  public String getEquation(Long finidhProductTestId) {
    return finishProductTestRepository.getOne(finidhProductTestId).getTestConfigure().getEquation()
        .getFormula();
  }

  public double averageStrength(FinishProductTrial finishProductTrial) {
    FinishProductTest finishProductTest = finishProductTestRepository
        .findById(finishProductTrial.getFinishProductTest().getId()).get();
    List<FinishProductTrial> finishProductTrialList = finishProductTrialRepository
        .findByFinishProductTestId(finishProductTrial.getFinishProductTest().getId());
    double strengthResult = 0;
    double trialNo = 0;
    for (FinishProductTrial finishProductTrial1 : finishProductTrialList) {
      if (finishProductTest.getTestConfigure().getDays() != 0) {
        strengthResult = strengthResult + finishProductTrial1.getValue();
        trialNo++;
      } else {
      }
    }
    return (strengthResult / trialNo);

  }

  public void calculateSlumpGradeRatio(FinishProductTrial finishProductTrial, double slump) {
    HashMap<String, Double> map = new HashMap<>();
    FinishProductTest finishProductTest = finishProductTestRepository
        .findById(finishProductTrial.getFinishProductTest().getId()).get();
    List<TestParameter> testParameterList = testParameterRepository.findByTestConfigureId(
        finishProductTrial.getFinishProductTest().getTestConfigure().getId());

    for (TestParameter testParameter : testParameterList) {

    }

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

  private double findResult(HashMap<String, Double> abbrevation, String equation) {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine engine = mgr.getEngineByName("JavaScript");
    double result = 0;
    for (String i : abbrevation.keySet()) {
      engine.put(i, abbrevation.get(i));
    }
    try {
      result = (double) engine.eval(equation);
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public void deleteFinishProductTrial(Long id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isFinishProductTrialExists(Long id) {
    // TODO Auto-generated method stub
    return false;
  }
}
