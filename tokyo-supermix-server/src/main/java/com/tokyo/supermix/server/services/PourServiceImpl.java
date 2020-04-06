package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Pour;
import com.tokyo.supermix.data.repositories.PourRepository;

@Service
public class PourServiceImpl implements PourService {
  @Autowired
  private PourRepository pourRepository;

  @Transactional()
  public Pour savePour(Pour pour) {
    return pourRepository.save(pour);
  }

  @Transactional()
  public List<Pour> getAllPour() {
    return pourRepository.findAll();
  }

  @Transactional()
  public void deletePour(Long id) {
    pourRepository.deleteById(id);
  }

  @Transactional
  public Pour getPourById(Long id) {
    return pourRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isPourExit(Long id) {
    return pourRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isPourNameExistPerProject(String name, String projectCode) {
    return pourRepository.existsByNameAndProjectCode(name, projectCode);
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedPourExists(Long id, String name, String projectCode) {
    if (!getPourById(id).getName().equalsIgnoreCase(name)
        && isPourNameExistPerProject(name, projectCode)) {
      return true;
    }
    return false;
  }
}
