package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;

public interface FinishProductSampleService {
  public boolean isFinishProductCodeExist(String code);

  public void saveFinishProductSample(FinishProductSample finishProductSample);

  public List<FinishProductSample> getAllFinishProductSamples();

  public List<FinishProductSample> getAllFinishProductSamplesByPlant(UserPrincipal currentUser,
      Pageable pageable);

  boolean isFinishProductSampleExist(String code);

  public FinishProductSample getFinishProductSampleById(String code);

  public void deleteFinishProductSample(String code);

  public boolean isUpdatedFinishProductCodeExist(String code, String finishProductCode);

  boolean isMixDesignCodeExist(String code);

  public List<FinishProductSample> getFinishProductSampleByMixDesignCode(String code);

  public Page<FinishProductSample> searchFinishProductSample(Predicate predicate, int page,
      int size);

  public List<FinishProductSample> getFinishProductSampleByPlantCode(String plantCode,
      Pageable pageable);

  public List<FinishProductSample> getFinishProductSampleByStatus(Status status);

  public boolean isFinishProductSampleStatusExist(Status status);

  public void updateFinishProductSample(FinishProductSample finishProductSample);

  public Long getCountFinishProductSample();

  public Long getCountFinishProductSampleByPlantCode(String plantCode);

  public List<FinishProductSample> searchFinishProductSample(BooleanBuilder booleanBuilder,
      String finishProductCode, String equipmentName, String mixDesignCode, String plantName,
      String plantCode, Status status, String date, String code, String rawMaterialName,
      String workOrderNumber, String customer, Pageable pageable, Pagination pagination);

  public List<FinishProductSample> getFinishProductSamplesBySubCategoryIdAndPlantCode(
      Long subCategoryId, String plantCode);

  public Long getSubCategoryCountFinishProductSample(Long materialSubCategoryId);

  public Long getCountSubCategoryFinishProductSampleByPlantCode(String plantCode,
      Long materialSubCategoryId);

  public Long getCategoryCountFinishProductSample(Long materialCategoryId);

  public List<FinishProductSample> getFinishProductSamplesByCategoryId(Long materialCategoryId);

  public Long getCountCategoryFinishProductSampleByPlantCode(String plantCode,
      Long materialCategoryId);

  public List<FinishProductSample> getFinishProductSamplesByCategoryIdAndPlantCode(
      Long materialCategoryId, String plantCode);

  public Long getRawMaterialCountFinishProductSample(Long rawMaterialId);

  public List<FinishProductSample> getFinishProductSamplesByRawMaterialId(Long rawMaterialId);

  public Long getCountRawMaterialFinishProductSampleByPlantCode(String plantCode,
      Long rawMaterialId);

  public List<FinishProductSample> getFinishProductSamplesByRawMaterialIdAndPlantCode(
      Long rawMaterialId, String plantCode);

  public List<FinishProductSample> getFinishProductSamplesBySubCategoryId(Long subCategoryId);

  public List<FinishProductSample> searchFinishProductSampleIssue(BooleanBuilder booleanBuilder,
      String finishProductCode, String equipmentName, String mixDesignCode, String plantName,
      String plantCode, Status status, String date, String code, String rawMaterialName,
      String workOrderNumber, String customer, Pageable pageable, Pagination pagination);
}
