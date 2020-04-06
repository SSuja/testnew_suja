package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ConcreteMixer;

public interface ConcreteMixerService {
  public void saveConcreteMixer(ConcreteMixer concreteMixer);

  public List<ConcreteMixer> getAllConcreteMixers();

  public void deleteConcreteMixer(Long id);

  public boolean isConcreteMixerExist(Long id);

  public ConcreteMixer getConcreteMixerById(Long id);

  public boolean isConcreteMixerExist(String name);

  public boolean isUpdatedConcreteMixerNameExist(Long id, String name);

  public List<ConcreteMixer> findByPlantCode(String plantCode);
}
