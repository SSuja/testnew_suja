package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;

public interface MixDesignRatioConfigService {

  public void saveMixDesignRatioConfig(List<MixDesignRatioConfig> mixDesignRatioConfigList);

  public List<MixDesignRatioConfig> getAllMixDesignRatioConfigs();

  public boolean isMixDesignRatioConfigExist(Long id);

  public MixDesignRatioConfig getMixDesignRatioConfigById(Long id);

  public void deleteMixDesignRatioConfig(Long id);
}
