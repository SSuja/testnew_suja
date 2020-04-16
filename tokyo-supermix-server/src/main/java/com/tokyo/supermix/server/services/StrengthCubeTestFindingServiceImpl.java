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
  private StrengthCubeTestFindingRepository cubeTestFindingRepository;

  @Transactional
  public void saveCubeTestFinding(StrengthCubeTestFinding cubeTestFinding) {
    cubeTestFindingRepository.save(cubeTestFinding);
  }

  @Transactional(readOnly = true)
  public List<StrengthCubeTestFinding> getAllCubeTestFindings() {
    return cubeTestFindingRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isCubeTestFindingExist(Long id) {
    return cubeTestFindingRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public StrengthCubeTestFinding getCubeTestFindingById(Long id) {
    return cubeTestFindingRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteCubeTestFinding(Long id) {
    cubeTestFindingRepository.deleteById(id);
  }

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
    return cubeTestFindingRepository.findByFinishProductSampleId(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public List<StrengthCubeTestFinding> findByConcreteTestId(Long concreteTestId) {
    return cubeTestFindingRepository.findByConcreteTestId(concreteTestId);
  }

  @Transactional(readOnly = true)
  public boolean existsByFinishProductSampleId(Long finishProductSampleId) {
    return cubeTestFindingRepository.existsByFinishProductSampleId(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public boolean existsByConcreteTestId(Long concreteTestId) {
    return cubeTestFindingRepository.existsByConcreteTestId(concreteTestId);
  }
}
