package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @Transactional(readOnly = true)
  public Page<MixDesignProportion> searchMixDesignProportion(String rawMaterialName1,
      String rawMaterialName2, String rawMaterialName3, String rawMaterialName4,
      String rawMaterialName5, int page, int size, String mixDesignCode, Double targetGrade,
      Double targetSlump) {
   // BooleanBuilder booleanBuilder = new BooleanBuilder();
    return null;

    // if (rawMaterialName1 != null || rawMaterialName2 != null || rawMaterialName3 != null
    // || rawMaterialName4 != null) {
    // if (targetGrade != null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.mixDesign.targetGrade.eq(targetGrade));
    // }
    // if (targetSlump != null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.mixDesign.targetSlump.eq(targetSlump));
    // }
    // if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.mixDesign.code.eq(mixDesignCode));
    // }
    // }
    // if (rawMaterialName1 != null && rawMaterialName2 == null && rawMaterialName3 == null
    // && rawMaterialName4 == null && rawMaterialName5 == null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName1));
    // }
    // if (rawMaterialName2 != null && rawMaterialName1 != null && rawMaterialName3 == null
    // && rawMaterialName4 == null && rawMaterialName5 == null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName2));
    // }
    // if (rawMaterialName3 != null && rawMaterialName1 != null && rawMaterialName2 != null
    // && rawMaterialName4 == null && rawMaterialName5 == null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName3));
    // }
    // if (rawMaterialName4 != null && rawMaterialName1 != null && rawMaterialName2 != null
    // && rawMaterialName3 != null && rawMaterialName5 == null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName4));
    // }
    // if (rawMaterialName5 != null && rawMaterialName4 != null && rawMaterialName1 != null
    // && rawMaterialName2 != null && rawMaterialName3 != null) {
    // booleanBuilder
    // .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName5));
    // }
    // return mixDesignProportionRepository.findAll(booleanBuilder.getValue(),
    // PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }
}
