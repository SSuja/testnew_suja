package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.MixDesignProportionRequestDto;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.entities.MixDesignRatioConfig;
import com.tokyo.supermix.data.entities.QMixDesignProportion;
import com.tokyo.supermix.data.entities.RatioConfigParameter;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.data.repositories.MixDesignRatioConfigRepository;
import com.tokyo.supermix.data.repositories.RatioConfigParameterRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class MixDesignProportionServiceImpl implements MixDesignProportionService {

  @Autowired
  public MixDesignProportionRepository mixDesignProportionRepository;
  @Autowired
  public MixDesignRatioConfigRepository mixDesignRatioConfigRepository;
  @Autowired
  public RatioConfigParameterRepository ratioConfigParameterRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Transactional(readOnly = true)
  public List<MixDesignProportion> getAllMixDesignProportions() {
    return mixDesignProportionRepository.findAll();
  }

  @Transactional
  public String saveMixDesignProportion(List<MixDesignProportion> mixDesignProportion) {
    mixDesignProportionRepository.saveAll(mixDesignProportion);
    return mixDesignProportion.get(0).getMixDesign().getCode();
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
      String rawMaterialName5, int page, int size, String mixDesignCode) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    if (rawMaterialName1 != null || rawMaterialName2 != null || rawMaterialName3 != null
        || rawMaterialName4 != null) {

      if (mixDesignCode != null && !mixDesignCode.isEmpty()) {
        booleanBuilder
            .and(QMixDesignProportion.mixDesignProportion.mixDesign.code.eq(mixDesignCode));
      }
    }
    if (rawMaterialName1 != null && rawMaterialName2 == null && rawMaterialName3 == null
        && rawMaterialName4 == null && rawMaterialName5 == null) {
      booleanBuilder
          .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName1));
    }
    if (rawMaterialName2 != null && rawMaterialName1 != null && rawMaterialName3 == null
        && rawMaterialName4 == null && rawMaterialName5 == null) {
      booleanBuilder
          .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName2));
    }
    if (rawMaterialName3 != null && rawMaterialName1 != null && rawMaterialName2 != null
        && rawMaterialName4 == null && rawMaterialName5 == null) {
      booleanBuilder
          .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName3));
    }
    if (rawMaterialName4 != null && rawMaterialName1 != null && rawMaterialName2 != null
        && rawMaterialName3 != null && rawMaterialName5 == null) {
      booleanBuilder
          .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName4));
    }
    if (rawMaterialName5 != null && rawMaterialName4 != null && rawMaterialName1 != null
        && rawMaterialName2 != null && rawMaterialName3 != null) {
      booleanBuilder
          .and(QMixDesignProportion.mixDesignProportion.rawMaterial.name.eq(rawMaterialName5));
    }
    return mixDesignProportionRepository.findAll(booleanBuilder.getValue(),
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional
  public void updateMixDesignProportion(MixDesignProportion mixDesignProportion) {
    mixDesignProportionRepository.save(mixDesignProportion);
  }

  @Transactional(readOnly = true)
  public boolean deleteProportionCheck(Long id, String mixDesignCode) {
    boolean deleteCheck = false;
    for (MixDesignRatioConfig mixDesignRatioConfig : mixDesignRatioConfigRepository
        .findByMixDesignCode(mixDesignCode)) {
      for (RatioConfigParameter ratioConfigParameter : ratioConfigParameterRepository
          .findByRatioConfigId(mixDesignRatioConfig.getRatioConfig().getId())) {
        if (ratioConfigParameter.getRawMaterial().getId() == mixDesignProportionRepository
            .findById(id).get().getRawMaterial().getId()) {
          return deleteCheck = true;
        }
      }
    }
    return deleteCheck;
  }

  @Transactional(readOnly = true)
  public boolean checkMixDesignProportionQuantityHasZeroValues(
      List<MixDesignProportionRequestDto> mixDesignProportionRequestDtoList) {
    for (MixDesignProportionRequestDto mixDesignProportionRequestDto : mixDesignProportionRequestDtoList) {
      if (mixDesignProportionRequestDto.getQuantity() != null
          || mixDesignProportionRequestDto.getQuantity() == 0) {
        return true;
      }
    }
    return false;

  }

  @Transactional(readOnly = true)
  public List<MixDesignProportion> getAllMixDesignProportionsByPlant(UserPrincipal currentUser) {
    return mixDesignProportionRepository.findByMixDesignPlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_MIX_DESIGN_PROPORTION));
  }
}
