package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.QMixDesign;
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

  @Override
  public BooleanBuilder searchTargetSlump(Double targetSlumpMin, Double targetSlumpMax,
      Double targetSlumpEqual, String plantCode, BooleanBuilder booleanBuilder) {
    if (plantCode != null && !plantCode.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.plant.code.eq(plantCode));
    }
    if (targetSlumpMin != null && targetSlumpMin != 0 && targetSlumpMax == null
        && targetSlumpEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.goe(targetSlumpMin));
    }
    if (targetSlumpMax != null && targetSlumpMax != 0 && targetSlumpMin == null
        && targetSlumpEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.loe(targetSlumpMax));
    }
    if (targetSlumpMin != null && targetSlumpMin != 0 && targetSlumpMax != null
        && targetSlumpMax != null && targetSlumpEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.between(targetSlumpMin, targetSlumpMax));
    }
    if (targetSlumpEqual != null && targetSlumpEqual != 0 && targetSlumpMax == null
        && targetSlumpMin == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.eq(targetSlumpEqual));
    }
    return booleanBuilder;
  }
}
