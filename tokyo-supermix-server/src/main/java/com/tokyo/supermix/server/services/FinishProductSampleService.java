package com.tokyo.supermix.server.services;


import java.util.List;
import com.tokyo.supermix.data.entities.FinishProductSample;

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

  public List<FinishProductSample> getFinishProductSampleByConcreteMixerId(Long id);

  public boolean isFinishProductSampleCodeNull(Long code);

}
