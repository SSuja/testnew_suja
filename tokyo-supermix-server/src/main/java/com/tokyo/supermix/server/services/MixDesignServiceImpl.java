package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.repositories.MixDesignRepository;

@Service
public class MixDesignServiceImpl implements MixDesignService {
  @Autowired
  public MixDesignRepository mixDesignRepository;

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesigns() {
    return mixDesignRepository.findAll();
  }

  @Transactional
  public MixDesign saveMixDesign(MixDesign mixDesign) {
    return mixDesignRepository.save(mixDesign);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMixDesign(String code) {
    mixDesignRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public MixDesign getMixDesignByCode(String code) {
    return mixDesignRepository.findById(code).get();
  }

  @Transactional(readOnly = true)
  public boolean isCodeExist(String code) {
    return mixDesignRepository.existsByCode(code);
  }
}
