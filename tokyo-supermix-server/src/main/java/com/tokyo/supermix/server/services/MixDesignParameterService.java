package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MixDesignParameter;

public interface MixDesignParameterService {
  public void saveMixDesignParameter(MixDesignParameter mixDesignParameter);

  public List<MixDesignParameter> getAllMixDesignParameters();

  public MixDesignParameter getMixDesignParameterById(Long id);

  public MixDesignParameter getMixDesignParameterByEquationId(Long equationId);

  public boolean isMixDesignParameterExist(Long id);

  public void deleteMixDesignParameter(Long id);
}
