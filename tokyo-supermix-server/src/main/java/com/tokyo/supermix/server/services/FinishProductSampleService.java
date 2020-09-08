package com.tokyo.supermix.server.services;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;
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

  public List<FinishProductSample> getFinishProductSampleByEquipmentId(Long id);

  public Page<FinishProductSample> searchFinishProductSample(Predicate predicate, int page,
      int size);

  public List<FinishProductSample> getFinishProductSampleByPlantCode(String plantCode,
      Pageable pageable);

  public List<FinishProductSample> getFinishProductSampleByStatus(Status status);

  public boolean isFinishProductSampleStatusExist(Status status);

  public void updateFinishProductSample(FinishProductSample finishProductSample);

  public Long getCountFinishProductSample();

  public Long getCountFinishProductSampleByPlantCode(String plantCode);

  public List<FinishProductSample> getFinishProductSamplesBySubCategoryId(Long subCategoryId);

  public List<FinishProductSample> getFinishProductSamplesBySubCategoryIdAndPlantCode(Long subCategoryId,
      String plantCode);
}
