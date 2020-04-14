package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.ConcreteMixer;

public interface ConcreteMixerService {
  public void saveConcreteMixer(ConcreteMixer concreteMixer);

  public List<ConcreteMixer> getAllConcreteMixers();

  public void deleteConcreteMixer(Long id);

  public boolean isConcreteMixerExist(Long id);

  public ConcreteMixer getConcreteMixerById(Long id);

  public List<ConcreteMixer> findByPlantCode(String plantCode);

  public boolean isDuplicateEntryExist(String name, String plantCode);
}
