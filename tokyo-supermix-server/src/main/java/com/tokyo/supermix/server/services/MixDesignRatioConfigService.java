package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;

public interface MixDesignRatioConfigService {

  public void saveMixDesignRatioConfig(List<MixDesignRatioConfig> mixDesignRatioConfig);

  public List<MixDesignRatioConfig> getAllMixDesignRatioConfigs();

  public List<MixDesignRatioConfig> getAllRatiosByMixDesignCode(String mixDesignCode);

  public boolean isMixDesignRatioConfigExist(Long id);

  public MixDesignRatioConfig getMixDesignRatioConfigById(Long id);

  public void deleteMixDesignRatioConfig(Long id);

  public boolean isExistByMixDesignCode(String mixDesignCode);

  public void saveRatioResult(String mixDesignCode, Long ratioConfigId);

  public void updateRatioResult(String mixDesignCode);
}
