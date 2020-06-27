package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ConcreteMixer;
import com.tokyo.supermix.security.UserPrincipal;

public interface ConcreteMixerService {
  public void saveConcreteMixer(ConcreteMixer concreteMixer);

  public List<ConcreteMixer> getAllConcreteMixers();

  public void deleteConcreteMixer(Long id);

  public boolean isConcreteMixerExist(Long id);

  public ConcreteMixer getConcreteMixerById(Long id);

  public List<ConcreteMixer> findByPlantCode(String plantCode);

  public boolean isDuplicateEntryExist(String name, String plantCode);

  public boolean isNameNull(String name);

  public Page<ConcreteMixer> searchConcreteMixer(Predicate predicate, int page, int size);
  public List<ConcreteMixer> getAllConcreteMixersByPlant(UserPrincipal currentUser);
}
