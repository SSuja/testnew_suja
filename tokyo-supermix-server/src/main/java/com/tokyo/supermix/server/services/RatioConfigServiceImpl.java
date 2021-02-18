package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MixDesignTestConfigDetailsDto;
import com.tokyo.supermix.data.dto.RatioConfigEquationResponseDto;
import com.tokyo.supermix.data.dto.RatioConfigParameterResponseDto;
import com.tokyo.supermix.data.dto.RatioConfigResponseDto;
import com.tokyo.supermix.data.entities.RatioConfig;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.MixDesignRatioConfigRepository;
import com.tokyo.supermix.data.repositories.RatioConfigEquationRepository;
import com.tokyo.supermix.data.repositories.RatioConfigParameterRepository;
import com.tokyo.supermix.data.repositories.RatioConfigRepository;

@Service
public class RatioConfigServiceImpl implements RatioConfigService {

  @Autowired
  private RatioConfigRepository ratioConfigRepository;
  @Autowired
  private Mapper mapper;
  @Autowired
  private RatioConfigParameterRepository ratioConfigParameterRepository;
  @Autowired
  private RatioConfigEquationRepository ratioConfigEquationRepository;
  @Autowired
  private MixDesignRatioConfigRepository mixDesignRatioConfigRepository;

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

  @Transactional(readOnly = true)
  public boolean isUpdatedRatioConfigNameExist(Long id, String name) {
    if ((!getRatioConfigById(id).getName().equalsIgnoreCase(name)) && (isRatioConfigExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public MixDesignTestConfigDetailsDto getMixDesignTestConfigDetails(Long mixDesignConfigId) {
    MixDesignTestConfigDetailsDto mixDesignTestConfigDetailsDto =
        new MixDesignTestConfigDetailsDto();
    mixDesignTestConfigDetailsDto.setRatioConfigResponseDto(
        mapper.map(getRatioConfigById(mixDesignConfigId), RatioConfigResponseDto.class));
    mixDesignTestConfigDetailsDto.setRatioConfigParameterResponseDto(
        mapper.map(ratioConfigParameterRepository.findByRatioConfigId(mixDesignConfigId),
            RatioConfigParameterResponseDto.class));
    mixDesignTestConfigDetailsDto.setRatioConfigEquationResponseDto(
        mapper.map(ratioConfigEquationRepository.findByRatioConfigId(mixDesignConfigId),
            RatioConfigEquationResponseDto.class));
    return mixDesignTestConfigDetailsDto;
  }

  @Transactional(readOnly = true)
  public boolean checkRatioConfigDepend(Long ratioConfigId) {
    if (mixDesignRatioConfigRepository.existsByRatioConfigId(ratioConfigId)) {
      return true;
    }
    return false;
  }

  @Transactional
  public void deleteRatioConfigReset(Long ratioConfigId) {
    if (ratioConfigParameterRepository.existsByRatioConfigId(ratioConfigId)) {
      ratioConfigParameterRepository.deleteByRatioConfigId(ratioConfigId);
    }
    if (ratioConfigEquationRepository.existsByRatioConfigId(ratioConfigId)) {
      ratioConfigEquationRepository.deleteByRatioConfigId(ratioConfigId);
    }
    if (ratioConfigRepository.existsById(ratioConfigId)) {
      ratioConfigRepository.deleteById(ratioConfigId);
    }
  }
}
