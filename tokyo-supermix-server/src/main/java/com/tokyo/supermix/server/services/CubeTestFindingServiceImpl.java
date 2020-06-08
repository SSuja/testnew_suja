package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.entities.CubeTestFinding;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.enums.ConcreteStatus;
import com.tokyo.supermix.data.repositories.ConcreteTestRepository;
import com.tokyo.supermix.data.repositories.ConcreteTestStatusRepository;
import com.tokyo.supermix.data.repositories.CubeTestFindingRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class CubeTestFindingServiceImpl implements CubeTestFindingService {
  @Autowired
  private CubeTestFindingRepository cubeTestFindingRepository;
  @Autowired
  private ConcreteTestRepository concreteTestRepository;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private ConcreteTestResultService concreteTestResultService;
  @Autowired
  private ConcreteTestStatusRepository concreteTestStatusRepository;

  @Transactional
  public void saveCubeTestFinding(CubeTestFinding cubeTestFinding) {
    cubeTestFindingRepository.save(cubeTestFinding);
  }

  @Transactional(readOnly = true)
  public boolean checkConcreteStatus(Long finishProductSampleId) {
    ConcreteTestStatus concreteTestStatus = concreteTestStatusRepository
        .findByConcreteTestTypeTypeAndFinishProductSampleId(Constants.SLUMP, finishProductSampleId);
    if (!(concreteTestStatus.getConcreteStatus().equals(ConcreteStatus.COMPLETED))) {
      return true;
    }
    return false;
  }

  @Transactional
  public void updateCubTestFinding(List<CubeTestFinding> cubeTestFindinglist) {
    cubeTestFindingRepository.saveAll(cubeTestFindinglist);
  }

  public void saveCubeTestFindinretTestResultStrengthAverage(CubeTestFinding cubeTestFinding) {
    if (cubeTestFinding.getAge() == 7) {

    }
  }

  @Transactional
  public void saveCubeTestFindingConcretTestResultStrengthAverage(Long finishProductSampleId,
      Long concreteAge) {
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.STRENGTH_TEST);
    ConcreteTestResult concreteTestResult = new ConcreteTestResult();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    concreteTestResult.setConcreteTest(concreteTest);
    concreteTestResult.setFinishProductSample(finishProductSample);
    concreteTestResult.setAge(concreteAge);
    concreteTestResultService.saveConcreteStrengthTestAverageStrengthResult(concreteTestResult);
  }

  @Transactional
  public void saveCubeTestFindingConcretTestResultStrengthGradeRatio(Long finishProductSampleId,
      Long concreteAge) {
    ConcreteTest concreteTest = concreteTestRepository.findByName(Constants.STRENGTH_GRADE_RATIO);
    ConcreteTestResult concreteTestResult = new ConcreteTestResult();
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    concreteTestResult.setConcreteTest(concreteTest);
    concreteTestResult.setFinishProductSample(finishProductSample);
    concreteTestResult.setAge(concreteAge);
    concreteTestResultService.saveConcreteStrengthTestStrengthGradeRatioResult(concreteTestResult);
  }

  public Long countCubetestingValue(Long finishProductSampleId, Long concreteAge) {
    List<CubeTestFinding> cubeTestFindingList =
        cubeTestFindingRepository.findByFinishProductSampleId(finishProductSampleId);
    Long cubeValuecount = (long) 0;
    for (CubeTestFinding cubeTestFinding : cubeTestFindingList) {
      if (cubeTestFinding.getAge() == concreteAge && cubeTestFinding.getValue() != 0) {
        cubeValuecount++;
      }
    }
    return cubeValuecount;
  }

  @Transactional(readOnly = true)
  public boolean checkNoOfTimeCubeTestFindValue(Long finishProductSampleId, Long concreteAge) {
    if (countCubetestingValue(finishProductSampleId, concreteAge) == 2) {
      return true;
    }
    return false;

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
  public boolean existsByFinishProductSampleId(Long finishProductSampleId) {
    return cubeTestFindingRepository.existsByFinishProductSampleId(finishProductSampleId);
  }

  @Transactional(readOnly = true)
  public Page<CubeTestFinding> searchCubeTestFinding(Predicate predicate, int size, int page) {
    return cubeTestFindingRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

}
