package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.RatioConfigParameter;

public interface RatioConfigParameterService {

  public void saveRatioConfigParameters(List<RatioConfigParameter> ratioConfigParameter);

  public List<RatioConfigParameter> getAllRatioConfigParameters();

  public boolean isRatioConfigParameterExist(Long id);

  public boolean isRatioConfigParameterExistByRatioConfig(Long ratioConfigId);

  public List<RatioConfigParameter> getAllRatioParametersByRatioConfig(Long ratioConfigId);
}
