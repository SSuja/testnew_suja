package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MixDesignResponseDto;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class MixDesignServiceImpl implements MixDesignService {
  @Autowired
  public MixDesignRepository mixDesignRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesigns() {
    return mixDesignRepository.findAll();
  }

  @Transactional
  public String saveMixDesign(MixDesign mixDesign) {
    if (mixDesign.getCode() == null) {
      String codePrefix = mixDesign.getPlant().getCode() + "-" + "G" + "-";
      List<MixDesign> mixDesignList = mixDesignRepository.findByCodeContaining(codePrefix);
      if (mixDesignList.size() == 0) {
        mixDesign.setCode(codePrefix + String.format("%03d", 1));
      } else {
        mixDesign.setCode(codePrefix + String.format("%03d", maxNumberFromCode(mixDesignList) + 1));
      }
    }
    MixDesign mixDesignObj = mixDesignRepository.save(mixDesign);
    if (mixDesignObj != null) {
      emailNotification.sendMixDesignCreationEmail(mixDesignObj);
    }
    return mixDesign.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<MixDesign> mixDesignList) {
    List<Integer> list = new ArrayList<Integer>();
    mixDesignList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
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
  public List<MixDesignResponseDto> searchMixDesign(Double targetSlumpMin, Double targetSlumpMax,
      Double targetSlumpEqual, Double targetGradeMin, Double targetGradeMax,
      Double targetGradeEqual, Double waterCementRatioMin, Double waterCementRatioMax,
      Double waterCementRatioEqual, Double waterBinderRatioMin, Double waterBinderRatioMax,
      Double waterBinderRatioEqual, String plantName, int page, int size) {
    return null;
    // BooleanBuilder booleanBuilder = new BooleanBuilder();
    // if (plantName != null && !plantName.isEmpty()) {
    // booleanBuilder.and(QMixDesign.mixDesign.plant.name.eq(plantName));
    // }
    // if (targetSlumpMin != null && targetSlumpMin != 0 && targetSlumpMax == null
    // && targetSlumpEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetSlump.gt(targetSlumpMin));
    // }
    // if (targetSlumpMax != null && targetSlumpMax != 0 && targetSlumpMin == null
    // && targetSlumpEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetSlump.lt(targetSlumpMax));
    // }
    // if (targetSlumpMin != null && targetSlumpMin != 0 && targetSlumpMax != null
    // && targetSlumpMax != null && targetSlumpEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetSlump.between(targetSlumpMin, targetSlumpMax));
    // }
    // if (targetSlumpEqual != null && targetSlumpEqual != 0 && targetSlumpMax == null
    // && targetSlumpMin == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetSlump.eq(targetSlumpEqual));
    // }
    // if (targetGradeMin != null && targetGradeMin != 0 && targetGradeMax == null
    // && targetGradeEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetGrade.gt(targetGradeMin));
    // }
    // if (targetGradeMax != null && targetGradeMax != 0 && targetGradeMin == null
    // && targetGradeEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetGrade.lt(targetGradeMax));
    // }
    // if (targetGradeMin != null && targetGradeMin != 0 && targetGradeMax != null
    // && targetGradeMax != null && targetGradeEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetGrade.between(targetGradeMin, targetGradeMax));
    // }
    // if (targetGradeEqual != null && targetGradeEqual != 0 && targetGradeMax == null
    // && targetGradeMin == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.targetGrade.eq(targetGradeEqual));
    // }
    // if (waterCementRatioMin != null && waterCementRatioMin != 0 && waterCementRatioMax == null
    // && waterCementRatioEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.waterCementRatio.gt(waterCementRatioMin));
    // }
    // if (waterCementRatioMax != null && waterCementRatioMax != 0 && waterCementRatioMin == null
    // && waterCementRatioEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.waterCementRatio.lt(waterCementRatioMax));
    // }
    // if (waterCementRatioMin != null && waterCementRatioMin != 0 && waterCementRatioMax != null
    // && waterCementRatioMax != null && waterCementRatioEqual == null) {
    // booleanBuilder.and(
    // QMixDesign.mixDesign.waterCementRatio.between(waterCementRatioMin, waterCementRatioMax));
    // }
    // if (waterCementRatioEqual != null && waterCementRatioEqual != 0 && waterCementRatioMax ==
    // null
    // && waterCementRatioMin == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.waterCementRatio.eq(waterCementRatioEqual));
    // }
    // if (waterBinderRatioMin != null && waterBinderRatioMin != 0 && waterBinderRatioMax == null
    // && waterBinderRatioEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.waterBinderRatio.gt(waterBinderRatioMin));
    // }
    // if (waterBinderRatioMax != null && waterBinderRatioMax != 0 && waterBinderRatioMin == null
    // && waterBinderRatioEqual == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.waterBinderRatio.lt(waterBinderRatioMax));
    // }
    // if (waterBinderRatioMin != null && waterBinderRatioMin != 0 && waterBinderRatioMax != null
    // && waterBinderRatioMax != null && waterBinderRatioEqual == null) {
    // booleanBuilder.and(
    // QMixDesign.mixDesign.waterBinderRatio.between(waterBinderRatioMin, waterBinderRatioMax));
    // }
    // if (waterBinderRatioEqual != null && waterBinderRatioEqual != 0 && waterBinderRatioMax ==
    // null
    // && waterBinderRatioMin == null) {
    // booleanBuilder.and(QMixDesign.mixDesign.waterBinderRatio.eq(waterBinderRatioEqual));
    // }
    // List<MixDesign> mixDesignList = new ArrayList<>();
    // mixDesignRepository
    // .findAll(booleanBuilder.getValue(),
    // PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")))
    // .stream().filter(mixDesigns -> mixDesignList.add(mixDesigns)).collect(Collectors.toList());
    // return mapper.map(mixDesignList, MixDesignResponseDto.class);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignByPlantCode(String plantCode) {
    return mixDesignRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getMixDesignByStatus(Status status) {
    return mixDesignRepository.findByStatus(status);
  }

  @Transactional(readOnly = true)
  public boolean isMixDesignStatusExist(Status status) {
    return mixDesignRepository.existsByStatus(status);
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesignByPlant(UserPrincipal currentUser) {
    return mixDesignRepository.findByPlantCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MIX_DESIGN));
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllMixDesignByDecending() {
    return mixDesignRepository.findAllByOrderByUpdatedAtDesc();
  }

  @Transactional(readOnly = true)
  public List<MixDesign> getAllPlantCodeOrderByUpdatedAtDesc(String plantCode) {
    return mixDesignRepository.findByPlantCodeOrderByUpdatedAtDesc(plantCode);
  }
}
