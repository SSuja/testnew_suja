package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.StrengthCubeTestFinding;
import com.tokyo.supermix.data.repositories.StrengthCubeTestFindingRepository;

@Service
public class StrengthCubeTestFindingServiceImpl implements StrengthCubeTestFindingService {
  @Autowired
  private StrengthCubeTestFindingRepository strengthCubeTestFindingRepository;

  @Transactional(readOnly = true)
  public boolean checkAge(Long age) {
    if (!(age == 1 || age == 3 || age == 5 || age == 7 || age == 14 || age == 21 || age == 28
        || age == 56 || age == 128)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<StrengthCubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId) {
    return strengthCubeTestFindingRepository.findByFinishProductSampleId(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public List<StrengthCubeTestFinding> getAllStrengthCubeTestFindings() {
    return strengthCubeTestFindingRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isStrengthCubeTestFindingExist(Long id) {
    return strengthCubeTestFindingRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public StrengthCubeTestFinding getStrengthCubeTestFindingById(Long id) {
    return strengthCubeTestFindingRepository.findById(id).get();
  }

  @Transactional
  public void saveStrengthCubeTestFindingCubeTestFinding(
      StrengthCubeTestFinding strengthCubeTestFinding) {
    strengthCubeTestFindingRepository.save(strengthCubeTestFinding);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteStrengthCubeTestFinding(Long id) {
    strengthCubeTestFindingRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean existsByFinishProductSampleId(Long finishProductSampleId) {
    return strengthCubeTestFindingRepository.existsByFinishProductSampleId(finishProductSampleId);
  }
}
