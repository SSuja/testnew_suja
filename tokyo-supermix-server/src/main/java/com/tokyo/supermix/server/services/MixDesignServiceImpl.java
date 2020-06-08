package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

  @Transactional(readOnly = true)
  public Page<MixDesign> searchMixDesign(Double targetSlumpMin, Double targetSlumpMax,
      Double targetSlumpEqual, Double targetGradeMin, Double targetGradeMax,
      Double targetGradeEqual, Double waterCementRatioMin, Double waterCementRatioMax,
      Double waterCementRatioEqual, Double waterBinderRatioMin, Double waterBinderRatioMax,
      Double waterBinderRatioEqual, String plantName, int page, int size) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QMixDesign.mixDesign.plant.name.eq(plantName));
    }
    if (targetSlumpMin != null && targetSlumpMin != 0 && targetSlumpMax == null
        && targetSlumpEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.gt(targetSlumpMin));
    }
    if (targetSlumpMax != null && targetSlumpMax != 0 && targetSlumpMin == null
        && targetSlumpEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.lt(targetSlumpMax));
    }
    if (targetSlumpMin != null && targetSlumpMin != 0 && targetSlumpMax != null
        && targetSlumpMax != null && targetSlumpEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.between(targetSlumpMin, targetSlumpMax));
    }
    if (targetSlumpEqual != null && targetSlumpEqual != 0 && targetSlumpMax == null
        && targetSlumpMin == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetSlump.eq(targetSlumpEqual));
    }
    if (targetGradeMin != null && targetGradeMin != 0 && targetGradeMax == null
        && targetGradeEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetGrade.gt(targetGradeMin));
    }
    if (targetGradeMax != null && targetGradeMax != 0 && targetGradeMin == null
        && targetGradeEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetGrade.lt(targetGradeMax));
    }
    if (targetGradeMin != null && targetGradeMin != 0 && targetGradeMax != null
        && targetGradeMax != null && targetGradeEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetGrade.between(targetGradeMin, targetGradeMax));
    }
    if (targetGradeEqual != null && targetGradeEqual != 0 && targetGradeMax == null
        && targetGradeMin == null) {
      booleanBuilder.and(QMixDesign.mixDesign.targetGrade.eq(targetGradeEqual));
    }
    if (waterCementRatioMin != null && waterCementRatioMin != 0 && waterCementRatioMax == null
        && waterCementRatioEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.waterCementRatio.gt(waterCementRatioMin));
    }
    if (waterCementRatioMax != null && waterCementRatioMax != 0 && waterCementRatioMin == null
        && waterCementRatioEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.waterCementRatio.lt(waterCementRatioMax));
    }
    if (waterCementRatioMin != null && waterCementRatioMin != 0 && waterCementRatioMax != null
        && waterCementRatioMax != null && waterCementRatioEqual == null) {
      booleanBuilder.and(
          QMixDesign.mixDesign.waterCementRatio.between(waterCementRatioMin, waterCementRatioMax));
    }
    if (waterCementRatioEqual != null && waterCementRatioEqual != 0 && waterCementRatioMax == null
        && waterCementRatioMin == null) {
      booleanBuilder.and(QMixDesign.mixDesign.waterCementRatio.eq(waterCementRatioEqual));
    }
    if (waterBinderRatioMin != null && waterBinderRatioMin != 0 && waterBinderRatioMax == null
        && waterBinderRatioEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.waterBinderRatio.gt(waterBinderRatioMin));
    }
    if (waterBinderRatioMax != null && waterBinderRatioMax != 0 && waterBinderRatioMin == null
        && waterBinderRatioEqual == null) {
      booleanBuilder.and(QMixDesign.mixDesign.waterBinderRatio.lt(waterBinderRatioMax));
    }
    if (waterBinderRatioMin != null && waterBinderRatioMin != 0 && waterBinderRatioMax != null
        && waterBinderRatioMax != null && waterBinderRatioEqual == null) {
      booleanBuilder.and(
          QMixDesign.mixDesign.waterBinderRatio.between(waterBinderRatioMin, waterBinderRatioMax));
    }
    if (waterBinderRatioEqual != null && waterBinderRatioEqual != 0 && waterBinderRatioMax == null
        && waterBinderRatioMin == null) {
      booleanBuilder.and(QMixDesign.mixDesign.waterBinderRatio.eq(waterBinderRatioEqual));
    }
    return mixDesignRepository.findAll(booleanBuilder.getValue(),
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignByPlantCode(String plantCode) {
    return mixDesignRepository.findByPlantCode(plantCode);
  }
}
