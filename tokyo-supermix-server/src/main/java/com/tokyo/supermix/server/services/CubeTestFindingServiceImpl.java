package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.CubeTestFinding;
import com.tokyo.supermix.data.repositories.CubeTestFindingRepository;

@Service
public class CubeTestFindingServiceImpl implements CubeTestFindingService {

  @Autowired
  private CubeTestFindingRepository cubeTestFindingRepository;

  @Transactional
  public void saveCubeTestFinding(CubeTestFinding cubeTestFinding) {
    cubeTestFindingRepository.save(cubeTestFinding);
  }

  @Transactional(readOnly = true)
  public List<CubeTestFinding> getAllCubeTestFindings() {
    return cubeTestFindingRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isCubeTestFindingExist(Long id) {
    return cubeTestFindingRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public CubeTestFinding getCubeTestFindingById(Long id) {
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
  public List<CubeTestFinding> findByFinishProductSampleId(Long finishProductSampleId) {
    return cubeTestFindingRepository.findByFinishProductSampleId(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public List<CubeTestFinding> findByConcreteTestElementId(Long concreteTestElementId) {
    return cubeTestFindingRepository.findByConcreteTestElementId(concreteTestElementId);
  }
}
