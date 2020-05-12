package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.QConcreteTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteTestRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ConcreteTestServiceImpl implements ConcreteTestService {
  @Autowired
  private ConcreteTestRepository concreteTestRepository;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private MixDesignProportionRepository mixDesignProportionRepository;

  @Transactional
  public ConcreteTest saveConcreteTest(ConcreteTest concreteTest) {
    return concreteTestRepository.save(calculateRatio(concreteTest));
  }

  public ConcreteTest calculateRatio(ConcreteTest concreteTest) {
    List<MixDesignProportion> mixDesignProportionList = mixDesignProportionRepository
        .findByMixDesignCode(concreteTest.getFinishProductSample().getMixDesign().getCode());
    Long quantity = null;
    Long binderquantity = 0L;
    for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {
      if (mixDesignProportion.getRawMaterial().getName()
          .equalsIgnoreCase(Constants.RAW_MATERIAL_CEMENT)) {
        quantity = mixDesignProportion.getQuantity();
        concreteTest.setWaterCementRatio(
            calculateWaterCementRatio(concreteTest.getWaterContent(), quantity.doubleValue()));
      }
      if (mixDesignProportion.getRawMaterial().getName()
          .equalsIgnoreCase(Constants.RAW_MATERIAL_FLYASH)) {
        binderquantity = mixDesignProportion.getQuantity();
      }
    }
    concreteTest.setWaterBinderRatio(calculateWaterBinderRatio(concreteTest.getWaterContent(),
        binderquantity.doubleValue(), quantity.doubleValue()));
    concreteTest.setSlumpGradeRatio(calculateSlumpGradeRatio(
        concreteTest.getFinishProductSample().getId(), concreteTest.getSlump()));
    concreteTest.setStatus(calculateConcreteStatus(concreteTest.getFinishProductSample().getId(),
        concreteTest.getSlump()));
    return concreteTest;
  }

  // To find calculateConcreteStatus
  private Status calculateConcreteStatus(Long finishProductSampleId, Double slump) {
    Double targetSlump = finishProductSampleRepository.findById(finishProductSampleId).get()
        .getMixDesign().getTargetSlump();
    if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
      return Status.PASS;
    } else {
      return Status.FAIL;
    }
  }

  // To find WaterCementRatio
  private Double calculateWaterCementRatio(Double waterContent, Double cementQuantity) {
    return roundDoubleValue(100 * waterContent / cementQuantity);
  }

  // To find WaterBinderRatio
  private Double calculateWaterBinderRatio(Double waterContent, Double binderquantity,
      Double quantity) {
    return roundDoubleValue(100 * waterContent / (quantity + binderquantity));
  }

  // To find SlumpGradeRatio
  private Double calculateSlumpGradeRatio(Long finishProductSampleId, Double slump) {
    Double targetSlump = finishProductSampleRepository.findById(finishProductSampleId).get()
        .getMixDesign().getTargetSlump();
    return roundDoubleValue(slump / targetSlump);
  }

  // To Change Four Digit Value
  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional(readOnly = true)
  public List<ConcreteTest> getAllConcreteTests() {
    return concreteTestRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ConcreteTest getConcreteTestById(Long id) {
    return concreteTestRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteTest(Long id) {
    concreteTestRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteTestExists(Long id) {
    return concreteTestRepository.existsById(id);
  }

  public Page<ConcreteTest> searchConcreteTest(Long finishProductSampleId, Status status,
      Double slump, Double slumpMin, Double slumpMax, Double slumpGradeRatio,
      Double slumpGradeRatioMin, Double slumpGradeRatioMax, BooleanBuilder booleanBuilder, int page,
      int size) {
    if (finishProductSampleId != null) {
      booleanBuilder.and(QConcreteTest.concreteTest.id.eq(finishProductSampleId));
    }
    if (status != null) {
      booleanBuilder.and(QConcreteTest.concreteTest.status.eq(status));
    }
    if (slumpMax != null && slumpMax != 0 && slumpMin == null && slump == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slump.lt(slumpMax));
    }
    if (slumpMin != null && slumpMin != 0 && slumpMax == null && slump == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slump.gt(slumpMin));
    }

    if (slumpMin != null && slumpMin != 0 && slumpMax != null && slumpMax != null
        && slump == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slump.between(slumpMin, slumpMax));
    }
    if (slump != null && slump != 0 && slumpMax == null && slumpMin == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slump.eq(slump));
    }
    if (slumpGradeRatioMax != null && slumpGradeRatioMax != 0 && slumpGradeRatioMin == null
        && slumpGradeRatio == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slumpGradeRatio.lt(slumpGradeRatioMax));
    }
    if (slumpGradeRatioMin != null && slumpGradeRatioMin != 0 && slumpGradeRatioMax == null
        && slumpGradeRatio == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slumpGradeRatio.gt(slumpGradeRatioMin));
    }

    if (slumpGradeRatioMin != null && slumpGradeRatioMin != 0 && slumpGradeRatioMax != null
        && slumpGradeRatioMax != null && slumpGradeRatio == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slumpGradeRatio.between(slumpGradeRatioMin,
          slumpGradeRatioMax));
    }
    if (slumpGradeRatio != null && slumpGradeRatio != 0 && slumpGradeRatioMax == null
        && slumpGradeRatioMin == null) {
      booleanBuilder.and(QConcreteTest.concreteTest.slumpGradeRatio.eq(slumpGradeRatio));
    }
    return concreteTestRepository.findAll(booleanBuilder,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
}
