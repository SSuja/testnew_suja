package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;

@Service
public class MixDesignProportionServiceImpl implements MixDesignProportionService {

  @Autowired
  public MixDesignProportionRepository mixDesignProportionRepository;

  @Transactional(readOnly = true)
  public List<MixDesignProportion> getAllMixDesignProportions() {
    return mixDesignProportionRepository.findAll();
  }

  @Transactional
  public MixDesignProportion saveMixDesignProportion(MixDesignProportion mixDesignProportion) {
    return mixDesignProportionRepository.save(mixDesignProportion);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteById(Long id) {
    mixDesignProportionRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public MixDesignProportion getMixDesignProportionById(Long id) {
    return mixDesignProportionRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignProportionExist(Long id) {
    return mixDesignProportionRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<MixDesignProportion> findByMixDesignCode(String mixDesignCode) {
    return mixDesignProportionRepository.findByMixDesignCode(mixDesignCode);
  }

  @Transactional(readOnly = true)
  public boolean existsByMixDesignCode(String mixDesignCode) {
      return mixDesignProportionRepository.existsByMixDesignCode(mixDesignCode);
  }
  

}
