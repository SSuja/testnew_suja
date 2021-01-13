package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.RatioConfigParameterRequestDto;
import com.tokyo.supermix.data.entities.RatioConfigParameter;

public interface RatioConfigParameterService {

  public void saveRatioConfigParameters(List<RatioConfigParameter> ratioConfigParameter);

  public void UpdateRatioConfigParameters(RatioConfigParameter ratioConfigParameter);

  public List<RatioConfigParameter> getAllRatioConfigParameters();

  public boolean isRatioConfigParameterExist(Long id);

  public boolean isRatioConfigParameterExistByRatioConfig(Long ratioConfigId);

  public List<RatioConfigParameter> getAllRatioParametersByRatioConfig(Long ratioConfigId);

  public void deleteRatioConfigParameter(Long id);

  public List<RatioConfigParameter> getRatioConfigParametersByRatioConfigIds(Long[] ids);

  public boolean checkAleadyExistValidation(
      List<RatioConfigParameterRequestDto> ratioConfigParameter);

  public boolean checkAleadyExistValidationAgain(
      List<RatioConfigParameterRequestDto> ratioConfigParameterList);

  public boolean checkAleadyRawmaterial(
      List<RatioConfigParameterRequestDto> ratioConfigParameterList);


  public boolean checkValidationForRawMaterialAndAbbre(Long id, Long ratioConfigId,
      Long rawMaterialId, String abbre);

  public boolean deleteCheck(Long ratioParameter);

  public boolean editCheck(Long ratioParameter, String abbreViation);
}
