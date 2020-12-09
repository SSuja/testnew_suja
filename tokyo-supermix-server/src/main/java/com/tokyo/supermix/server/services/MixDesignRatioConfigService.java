package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;

public interface MixDesignRatioConfigService {

  public void saveMixDesignRatioConfig(MixDesignRatioConfig mixDesignRatioConfig);

  public List<MixDesignRatioConfig> getAllMixDesignRatioConfigs();
}
