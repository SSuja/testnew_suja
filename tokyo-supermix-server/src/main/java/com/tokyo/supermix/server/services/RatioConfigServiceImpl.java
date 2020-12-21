package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.RatioConfig;
import com.tokyo.supermix.data.repositories.RatioConfigRepository;

@Service
public class RatioConfigServiceImpl implements RatioConfigService {

  @Autowired
  private RatioConfigRepository ratioConfigRepository;

  @Transactional
  public RatioConfig saveRatioConfig(RatioConfig ratioConfig) {
    ratioConfigRepository.save(ratioConfig);
    return ratioConfig;
  }

  @Transactional(readOnly = true)
  public List<RatioConfig> getAllRatioConfigs() {
    return ratioConfigRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isRatioConfigExist(Long id) {
    return ratioConfigRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public RatioConfig getRatioConfigById(Long id) {
    return ratioConfigRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRatioConfig(Long id) {
    ratioConfigRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isRatioConfigExist(String name) {
    return ratioConfigRepository.existsByName(name);
  }

  public boolean isUpdatedRatioConfigNameExist(Long id, String name) {
    if ((!getRatioConfigById(id).getName().equalsIgnoreCase(name)) && (isRatioConfigExist(name))) {
      return true;
    }
    return false;
  }
}
