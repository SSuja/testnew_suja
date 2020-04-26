package com.tokyo.supermix.server.services;

import java.util.List;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.MixDesign;

public interface MixDesignService {
  public List<MixDesign> getAllMixDesigns();

  public MixDesign saveMixDesign(MixDesign mixDesign);

  public void deleteMixDesign(String code);

  public MixDesign getMixDesignByCode(String code);

  public boolean isCodeExist(String code);

  public BooleanBuilder searchTargetSlump(Double targetSlumpMin, Double targetSlumpMax,
      Double targetSlumpEqual, String plantCode, BooleanBuilder booleanBuilder);
}
