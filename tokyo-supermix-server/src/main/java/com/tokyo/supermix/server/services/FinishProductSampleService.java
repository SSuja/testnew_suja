package com.tokyo.supermix.server.services;


import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.Status;

public interface FinishProductSampleService {
  public boolean isFinishProductCodeExist(Long code);

  public void saveFinishProductSample(FinishProductSample finishProductSample);

  public List<FinishProductSample> getAllFinishProductSamples();

  boolean isFinishProductSampleExist(Long id);

  public FinishProductSample getFinishProductSampleById(Long id);

  public void deleteFinishProductSample(Long id);

  public boolean isUpdatedFinishProductCodeExist(Long id, Long code);

  boolean isMixDesignCodeExist(String code);

  public List<FinishProductSample> getFinishProductSampleByMixDesignCode(String code);

  boolean isConcreteMixerExist(Long id);

  public List<FinishProductSample> getFinishProductSampleByEquipmentId(Long id);

  public Page<FinishProductSample> searchFinishProductSample(Predicate predicate, int page,
      int size);

  public List<FinishProductSample> getFinishProductSampleByPlantCode(String plantCode);

  public List<FinishProductSample> getFinishProductSampleByStatus(Status status);

  public boolean isFinishProductSampleStatusExist(Status status);
}
