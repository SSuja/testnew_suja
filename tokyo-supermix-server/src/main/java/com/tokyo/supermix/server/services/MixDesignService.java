package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.security.UserPrincipal;

public interface MixDesignService {
  public List<MixDesign> getAllMixDesigns();

  public String saveMixDesign(MixDesign mixDesign);

  public void deleteMixDesign(String code);

  public MixDesign getMixDesignByCode(String code);

  public boolean isCodeExist(String code);

  public Page<MixDesign> searchMixDesign(Double targetSlumpMin, Double targetSlumpMax,
      Double targetSlumpEqual, Double targetGradeMin, Double targetGradeMax,
      Double targetGradeEqual, Double targetWaterCementRetioMin, Double targetWaterCementRetioMax,
      Double targetWaterCementRetioEqual, Double waterBinderRatioMin, Double waterBinderRatioMax,
      Double waterBinderRatioEqual, String plantCode, int page, int size);

  public List<MixDesign> getMixDesignByPlantCode(String plantCode);

  public List<MixDesign> getMixDesignByStatus(Status status);

  public boolean isMixDesignStatusExist(Status status);

  public List<MixDesign> getAllMixDesignByPlant(UserPrincipal currentUser);

  public List<MixDesign> getAllMixDesignByDecending();

  public List<MixDesign> getAllMixDesignByMaterialCategory(Long materialCategoryId);

  public List<MixDesign> getAllPlantCodeOrderByUpdatedAtDesc(String plantCode);
}
