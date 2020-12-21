package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.repositories.RatioConfigParameterRepository;

@Service
public class RatioConfigParameterServiceImpl {

  @Autowired
  private RatioConfigParameterRepository ratioConfigParameterRepository;
}
