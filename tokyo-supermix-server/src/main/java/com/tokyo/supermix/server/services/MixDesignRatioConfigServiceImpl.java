package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

  @Transactional(readOnly = true)
  public boolean isMixDesignRatioConfigExist(Long id) {
    return mixDesignRatioConfigRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MixDesignRatioConfig getMixDesignRatioConfigById(Long id) {
    return mixDesignRatioConfigRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMixDesignRatioConfig(Long id) {
    mixDesignRatioConfigRepository.deleteById(id);
  }
}
