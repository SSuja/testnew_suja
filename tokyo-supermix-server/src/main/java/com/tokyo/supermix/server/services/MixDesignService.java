package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.MixDesignResponseDto;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface MixDesignService {
  public List<MixDesign> getAllMixDesigns();

  public String saveMixDesign(MixDesign mixDesign);

  public void deleteMixDesign(String code);

  public MixDesign getMixDesignByCode(String code);

  public boolean isCodeExist(String code);

  public List<MixDesign> getMixDesignByPlantCode(String plantCode);

  public List<MixDesign> getMixDesignByStatus(Status status);

  public boolean isMixDesignStatusExist(Status status);

  public List<MixDesign> getAllMixDesignByPlant(UserPrincipal currentUser);

  public List<MixDesign> getAllMixDesignByDecending();

  public List<MixDesign> getAllPlantCodeOrderByUpdatedAtDesc(String plantCode);

  public List<MixDesign> getMixDesignsByRawMaterialId(Long rawMaterialId);

  public boolean isRawMaterialExists(Long rawMaterialId);

  public Page<MixDesign> searchMixDesign(Predicate predicate, int size, int page);

  public List<MixDesign> getAllMixDesign(Pageable pageable);

  public List<MixDesign> getMixDesignByPlantCode(String plantCode, Pageable pageable);

  public Long getCountMixDesign();

  public Long getCountMixDesignByPlantCode(String plantCode);

  public List<MixDesignResponseDto> searchMixDesign(BooleanBuilder booleanBuilder,
      String materialName, String subCategoryName, String plantName, String plantCode,
      Pageable pageable, Pagination pagination);
  public List<MixDesign> getCodeByPlantCode(String plantCode, String code);

  public List<MixDesign> getCode(String code);

  public List<MixDesign> getCodeAndRawMaterialId(Long rawMaterialId, Status status, String code);
}
