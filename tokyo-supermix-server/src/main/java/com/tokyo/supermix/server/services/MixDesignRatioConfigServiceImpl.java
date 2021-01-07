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
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;
import com.tokyo.supermix.data.entities.RatioConfigParameter;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRatioConfigRepository;
import com.tokyo.supermix.data.repositories.RatioConfigEquationRepository;
import com.tokyo.supermix.data.repositories.RatioConfigParameterRepository;
import com.tokyo.supermix.data.repositories.RatioConfigRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class MixDesignRatioConfigServiceImpl implements MixDesignRatioConfigService {

  @Autowired
  private MixDesignRatioConfigRepository mixDesignRatioConfigRepository;
  @Autowired
  private RatioConfigParameterRepository ratioConfigParameterRepository;
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;
  @Autowired
  private RatioConfigEquationRepository ratioConfigEquationRepository;
  @Autowired
  private RatioConfigRepository ratioConfigRepository;

  @Transactional
  public void saveMixDesignRatioConfig(List<MixDesignRatioConfig> mixDesignRatioConfig) {
    mixDesignRatioConfigRepository.saveAll(mixDesignRatioConfig);
  }

  @Transactional(readOnly = true)
  public List<MixDesignRatioConfig> getAllMixDesignRatioConfigs() {
    return mixDesignRatioConfigRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignRatioConfigExist(Long id) {
    return mixDesignRatioConfigRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MixDesignRatioConfig getMixDesignRatioConfigById(Long id) {
    return mixDesignRatioConfigRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMixDesignRatioConfig(Long id) {
    mixDesignRatioConfigRepository.deleteById(id);
  }

  @Transactional
  public void saveRatioResult(String mixDesignCode, Long ratioConfigId) {
    MixDesignRatioConfig mixDesignRatioConfig = mixDesignRatioConfigRepository
        .findByMixDesignCodeAndRatioConfigId(mixDesignCode, ratioConfigId);
    mixDesignRatioConfig.setValue(getFinishProductResultParameter(mixDesignCode, ratioConfigId));
    mixDesignRatioConfigRepository.save(mixDesignRatioConfig);
  }

  @Transactional
  public void updateRatioResult(String mixDesignCode) {
    List<MixDesignRatioConfig> mixDesignRatioConfigList =
        mixDesignRatioConfigRepository.findByMixDesignCode(mixDesignCode);
    for (MixDesignRatioConfig mixDesignRatioConfig : mixDesignRatioConfigList) {
      mixDesignRatioConfig.setRatioConfig(
          ratioConfigRepository.findById(mixDesignRatioConfig.getRatioConfig().getId()).get());
      mixDesignRatioConfig.setValue(getFinishProductResultParameter(mixDesignCode,
          mixDesignRatioConfig.getRatioConfig().getId()));
      mixDesignRatioConfigRepository.save(mixDesignRatioConfig);
    }
  }

  private HashMap<String, Double> getMixDesignRatioResult(String mixDesignCode,
      Long ratioConfigId) {
    HashMap<String, Double> list = new HashMap<String, Double>();
    for (RatioConfigParameter ratioConfigParameter : ratioConfigParameterRepository
        .findByRatioConfigId(ratioConfigId)) {
      for (MixDesignProportion mixDesignProportion : mixDesignProportionRepository
          .findByMixDesignCode(mixDesignCode)) {
        {
          if (ratioConfigParameter.getRawMaterial().getId() == mixDesignProportion.getRawMaterial()
              .getId()) {
            list.put(ratioConfigParameter.getAbbreviation(),
                mixDesignProportion.getQuantity().doubleValue());
          }
        }
      }
    }
    return list;
  }

  private String getFormula(Long ratioConfigId) {
    return ratioConfigEquationRepository.findByRatioConfigId(ratioConfigId).get(0).getRatio();

  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT_FINISH);
    return Double.valueOf(decimalFormat.format(value));
  }

  public double getFinishProductResultParameter(String mixDesignCode, Long ratioConfigId) {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    ScriptEngine engine = engineManager.getEngineByName("JavaScript");
    double mixDesignRatioValue = 0.0;
    for (String i : getMixDesignRatioResult(mixDesignCode, ratioConfigId).keySet()) {
      engine.put(i, getMixDesignRatioResult(mixDesignCode, ratioConfigId).get(i));
    }
    try {
      mixDesignRatioValue = (double) engine.eval(getFormula(ratioConfigId));
    } catch (ScriptException e) {
      e.printStackTrace();
    }
    return roundDoubleValue(mixDesignRatioValue);
  }

  @Transactional(readOnly = true)
  public List<MixDesignRatioConfig> getAllRatiosByMixDesignCode(String mixDesignCode) {
    return mixDesignRatioConfigRepository.findByMixDesignCode(mixDesignCode);
  }

  @Transactional(readOnly = true)
  public boolean isExistByMixDesignCode(String mixDesignCode) {
    return mixDesignRatioConfigRepository.existsByMixDesignCode(mixDesignCode);
  }
}
