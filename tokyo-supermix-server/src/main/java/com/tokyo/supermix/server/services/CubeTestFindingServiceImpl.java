package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteStrengthTest;
import com.tokyo.supermix.data.entities.CubeTestFinding;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.repositories.ConcreteStrengthTestRepository;
import com.tokyo.supermix.data.repositories.CubeTestFindingRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class CubeTestFindingServiceImpl implements CubeTestFindingService {

  @Autowired
  private CubeTestFindingRepository cubeTestFindingRepository;
  @Autowired
  private ConcreteStrengthTestRepository concreteStrengthTestRepository;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;

  @Transactional
  public void saveCubeTestFinding(CubeTestFinding cubeTestFinding) {
    cubeTestFindingRepository.save(cubeTestFinding);
  }

  @Transactional
  public void updateCubeTestFinding(CubeTestFinding cubeTestFinding) {
    cubeTestFindingRepository.save(setCubConcreteStrengthRatio(cubeTestFinding));
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

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  public Double calculateAverageCubStrength(Long finishProductSampleId) {
    List<CubeTestFinding> CubeTestFindingList = findByFinishProductSampleId(finishProductSampleId);
    double sum = 0;
    double count = 0;
    for (CubeTestFinding cubeTestFinding : CubeTestFindingList) {
      ConcreteStrengthTest concreteStrengthTest = concreteStrengthTestRepository
          .findByFinishProductSampleId(cubeTestFinding.getFinishProductSample().getId());
      if (cubeTestFinding.getAge() == concreteStrengthTest.getConcreteAge()) {
        sum = sum + cubeTestFinding.getValue();
        count++;
      }
    }
    return (sum / count);
  }

  public Double getTargetGradre(Long finishProductSampleId) {
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findById(finishProductSampleId).get();
    return finishProductSample.getMixDesign().getTargetGrade();
  }

  public Double calculateCubStrengthRatio(Long finishProductSampleId) {
    return calculateAverageCubStrength(finishProductSampleId)
        / getTargetGradre(finishProductSampleId);
  }

  public CubeTestFinding setCubConcreteStrengthRatio(CubeTestFinding cubeTestFinding) {
    ConcreteStrengthTest concreteStrengthTest = concreteStrengthTestRepository
        .findByFinishProductSampleId(cubeTestFinding.getFinishProductSample().getId());
    concreteStrengthTest.setStrength(roundDoubleValue(
        calculateAverageCubStrength(cubeTestFinding.getFinishProductSample().getId())));
    concreteStrengthTest.setStrengthGradeRatio(roundDoubleValue(
        calculateCubStrengthRatio(cubeTestFinding.getFinishProductSample().getId())));
    return cubeTestFinding;
  }
}
