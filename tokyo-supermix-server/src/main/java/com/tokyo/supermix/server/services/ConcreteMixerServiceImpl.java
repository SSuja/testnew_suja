package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteMixer;
import com.tokyo.supermix.data.repositories.ConcreteMixerRepository;

@Service
public class ConcreteMixerServiceImpl implements ConcreteMixerService {
  @Autowired
  private ConcreteMixerRepository concreteMixerRepository;

  @Transactional
  public void saveConcreteMixer(ConcreteMixer concreteMixer) {
    concreteMixerRepository.save(concreteMixer);
  }

  @Transactional(readOnly = true)
  public List<ConcreteMixer> getAllConcreteMixers() {
    return concreteMixerRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteConcreteMixer(Long id) {
    concreteMixerRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isConcreteMixerExist(Long id) {
    return concreteMixerRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public ConcreteMixer getConcreteMixerById(Long id) {
    return concreteMixerRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public List<ConcreteMixer> findByPlantCode(String plantCode) {
    return concreteMixerRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(String name, String plantCode) {
    if (concreteMixerRepository.existsByNameAndPlantCode(name, plantCode)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isNameNull(String name) {
    if (name == null) {
      return true;
    }
    return false;
  }
}
