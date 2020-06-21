package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.ParameterResultRequestDto;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.enums.EntryLevel;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
  @Autowired
  private ParameterResultRepository parameterResultRepository;
  @Autowired
  private TestParameterService testParameterService;
  @Autowired
  MaterialQualityParameterRepository materialQualityParameterRepository;

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

  @Transactional(readOnly = true)
  public List<ParameterResult> findByMaterialTestCode(String materialTestCode) {
    return parameterResultRepository.findByMaterialTestCode(materialTestCode);
  }
}
