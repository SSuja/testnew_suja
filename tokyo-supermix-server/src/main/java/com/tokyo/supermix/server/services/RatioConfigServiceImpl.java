package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.repositories.RatioConfigRepository;

@Service
public class RatioConfigServiceImpl implements RatioConfigService {

  @Autowired
  private RatioConfigRepository ratioConfigRepository;

}
