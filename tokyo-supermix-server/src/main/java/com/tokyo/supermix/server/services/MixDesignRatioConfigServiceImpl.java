package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;
import com.tokyo.supermix.data.repositories.MixDesignRatioConfigRepository;

@Service
public class MixDesignRatioConfigServiceImpl implements MixDesignRatioConfigService {

  @Autowired
  private MixDesignRatioConfigRepository mixDesignRatioConfigRepository;

  @Transactional
  public void saveMixDesignRatioConfig(MixDesignRatioConfig mixDesignRatioConfig) {
    mixDesignRatioConfigRepository.save(mixDesignRatioConfig);
  }

  @Transactional(readOnly = true)
  public List<MixDesignRatioConfig> getAllMixDesignRatioConfigs() {
    return mixDesignRatioConfigRepository.findAll();
  }
}
