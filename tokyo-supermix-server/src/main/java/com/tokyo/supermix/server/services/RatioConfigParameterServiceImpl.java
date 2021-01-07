package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.RatioConfigParameterRequestDto;
import com.tokyo.supermix.data.entities.RatioConfigParameter;
import com.tokyo.supermix.data.repositories.RatioConfigParameterRepository;

@Service
public class RatioConfigParameterServiceImpl implements RatioConfigParameterService {

  @Autowired
  private RatioConfigParameterRepository ratioConfigParameterRepository;

  @Transactional
  public void saveRatioConfigParameters(List<RatioConfigParameter> ratioConfigParameter) {
    ratioConfigParameterRepository.saveAll(ratioConfigParameter);
  }

  @Transactional(readOnly = true)
  public List<RatioConfigParameter> getAllRatioConfigParameters() {
    return ratioConfigParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isRatioConfigParameterExist(Long id) {
    return ratioConfigParameterRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<RatioConfigParameter> getAllRatioParametersByRatioConfig(Long ratioConfigId) {
    return ratioConfigParameterRepository.findByRatioConfigId(ratioConfigId);
  }

  @Transactional(readOnly = true)
  public boolean isRatioConfigParameterExistByRatioConfig(Long ratioConfigId) {
    return ratioConfigParameterRepository.existsByRatioConfigId(ratioConfigId);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRatioConfigParameter(Long id) {
    ratioConfigParameterRepository.deleteById(id);
  }

  @Transactional
  public void UpdateRatioConfigParameters(RatioConfigParameter ratioConfigParameter) {
    ratioConfigParameterRepository.save(ratioConfigParameter);
  }

  @Transactional(readOnly = true)
  public List<RatioConfigParameter> getRatioConfigParametersByRatioConfigIds(Long[] ids) {
    return ratioConfigParameterRepository.findByRatioConfigIdIn(ids);
  }

  @Transactional(readOnly = true)
  public boolean checkAleadyExistValidation(
      List<RatioConfigParameterRequestDto> ratioConfigParameter) {
    ArrayList<String> abb = new ArrayList<String>();
    for (RatioConfigParameterRequestDto ratioConfigParameterRequestDto : ratioConfigParameter) {
      abb.add(ratioConfigParameterRequestDto.getAbbreviation());
    }
    if (abb.size() != abb.stream().distinct().collect(Collectors.toList()).size()) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean checkAleadyExistValidationAgain(
      List<RatioConfigParameterRequestDto> ratioConfigParameterList) {
    for (RatioConfigParameterRequestDto ratioConfigParameterRequestDto : ratioConfigParameterList) {
      if (ratioConfigParameterRepository.existsByRatioConfigIdAndAbbreviation(
          ratioConfigParameterRequestDto.getRatioConfigId(),
          ratioConfigParameterRequestDto.getAbbreviation())
          || ratioConfigParameterRepository.existsByRatioConfigIdAndRawMaterialId(
              ratioConfigParameterRequestDto.getRatioConfigId(),
              ratioConfigParameterRequestDto.getRawMaterialId())) {
        return true;
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean checkAleadyRawmaterial(
      List<RatioConfigParameterRequestDto> ratioConfigParameterList) {
    for (RatioConfigParameterRequestDto ratioConfigParameterRequestDto : ratioConfigParameterList) {
      if (ratioConfigParameterRepository.existsByRatioConfigIdAndRawMaterialId(
          ratioConfigParameterRequestDto.getRatioConfigId(),
          ratioConfigParameterRequestDto.getRawMaterialId())) {
        return true;
      }
    }
    return false;
  }
}
