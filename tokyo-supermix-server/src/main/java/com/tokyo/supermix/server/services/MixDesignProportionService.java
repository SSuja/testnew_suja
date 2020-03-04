package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;

public interface MixDesignProportionService {
  public List<MixDesignProportion> getAllMixDesignProportions();
  public MixDesignProportion saveMixDesignProportion(MixDesignProportion mixDesignProportion);
  public void deleteById(Long id);
  public MixDesignProportion getMixDesignProportionById(Long id);
  public boolean isIdExist(Long id);
  public boolean isMixDesignProportionExist(Long id);
  public List<MixDesignProportion> findByMixDesign(MixDesign mixDesign);
}
