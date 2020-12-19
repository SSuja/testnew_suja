package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.entities.RatioConfig;
import com.tokyo.supermix.data.repositories.RatioConfigRepository;

@Service
public class RatioConfigServiceImpl implements RatioConfigService {

  @Autowired
  private RatioConfigRepository ratioConfigRepository;

  @Transactional
  public void saveRatioConfig(RatioConfig ratioConfig) {
    ratioConfigRepository.save(ratioConfig);
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
}
