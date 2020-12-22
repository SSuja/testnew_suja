package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.RatioConfigEquation;

public interface RatioConfigEquationService {

  public void saveRatioConfigEquation(RatioConfigEquation ratioConfigEquation);

  public List<RatioConfigEquation> getAllRatioConfigEquations();

  public List<RatioConfigEquation> getAllRatioConfigEquationsByRatioConfig(Long ratioConfigId);

  public RatioConfigEquation getRatioConfigEquationById(Long id);

  public boolean isRatioExists(String ratio);

  public boolean isRatioConfigEquationExistsById(Long id);

  public boolean isRatioConfigEquationExistsByRatioConfigId(Long ratioConfigId);

  public void deleteRatioConfigEquation(Long id);
}
