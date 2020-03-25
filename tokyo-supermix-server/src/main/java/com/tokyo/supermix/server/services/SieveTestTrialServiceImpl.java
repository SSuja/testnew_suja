package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinenessModulusRepository;
import com.tokyo.supermix.data.repositories.SieveTestRepository;
import com.tokyo.supermix.data.repositories.SieveTestTrialRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class SieveTestTrialServiceImpl implements SieveTestTrialService {
  @Autowired
  SieveTestTrialRepository sieveTestTrialRepository;
  @Autowired
  SieveTestService sieveTestService;
  @Autowired
  private FinenessModulusRepository finenessModulusRepository;
  @Autowired
  private SieveTestRepository sieveTestRepository;

  @Transactional
  public void saveSieveTestTrial(List<SieveTestTrial> sieveTestTrials) {
    Double totalRetainedWight = totalWeight(sieveTestTrials);
    for (SieveTestTrial sieveTestTrial : sieveTestTrials) {
      sieveTestTrial
          .setPercentageRetained(findPercentageRetainedWeight(sieveTestTrial, totalRetainedWight));
      sieveTestTrial.setPassing(findPassing(sieveTestTrial, totalRetainedWight));
      sieveTestTrialRepository.save(sieveTestTrial);
    }
    SieveTest sieveTest =
        sieveTestService.getSieveTestById(sieveTestTrials.get(0).getSieveTest().getId());
    sieveTest.setFinenessModulus(finenessModulus(sieveTestTrials));
    sieveTest.setTotalWeight(totalWeight(sieveTestTrials));
    sieveTestService.saveSieveTest(sieveTest);

  }

  // find total weight of cummalative weight retained
  private Double totalWeight(List<SieveTestTrial> sieveTestTrials) {
    double total = sieveTestTrials.get(sieveTestTrials.size() - 1).getCummalativeRetained();
    return total;
  }

  // find fineness Modulus
  private Double finenessModulus(List<SieveTestTrial> sieveTestTrials) {
    double totalCumWeight = 0;
    for (SieveTestTrial sieveTestTrial : sieveTestTrials) {
      totalCumWeight = totalCumWeight + sieveTestTrial.getCummalativeRetained();
    }
    double finenessModulus = totalCumWeight / 100;
    return finenessModulus;
  }


  // find percentage weight retained
  private Double findPercentageRetainedWeight(SieveTestTrial sieveTestTrial,
      double totalRetainedWight) {
    double petained =
        roundDoubleValue(sieveTestTrial.getCummalativeRetained() * 100 / totalRetainedWight);
    return petained;
  }

  // find percentage weight passing
  private Double findPassing(SieveTestTrial sieveTestTrial, Double totalRetainedWight) {
    double passing =
        roundDoubleValue(100 - findPercentageRetainedWeight(sieveTestTrial, totalRetainedWight));
    return passing;
  }

  // To Change Four Digit Value
  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.TWO_DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional(readOnly = true)
  public List<SieveTestTrial> getAllSieveTestTrials() {
    return sieveTestTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public SieveTestTrial getSieveTestTrialById(Long id) {
    return sieveTestTrialRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveTestTrial(Long id) {
    sieveTestTrialRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isSieveTestTrialExist(Long id) {
    return sieveTestTrialRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<SieveTestTrial> findSieveTestTrialBySieveTestId(Long sieveTestId) {
    return sieveTestTrialRepository.findBySieveTestId(sieveTestId);
  }

  public void compareWithFinenessModulus(Double finenessModulus, Long sieveTestId) {
    Long materialSubCategoryId = sieveTestRepository.findById(sieveTestId).get().getIncomingSample()
        .getRawMaterial().getMaterialSubCategory().getId();
    if (finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId).get(0)
        .getMin() <= finenessModulus
        && finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId).get(0)
            .getMax() >= finenessModulus) {
      updateStatus(finenessModulus, sieveTestId, Status.PASS);
    } else {
      updateStatus(finenessModulus, sieveTestId, Status.FAIL);

    }
  }

  public SieveTest updateStatus(Double finenessModulus, Long sieveTestId, Status status) {
    SieveTest sieveTest = sieveTestRepository.findById(sieveTestId).get();
    sieveTest.setStatus(status);
    sieveTest.setFinenessModulus(finenessModulus);
    return sieveTestRepository.save(sieveTest);
  }

  @Transactional
  public void updateFinenessModulusStatus(SieveTest sieveTest) {
    compareWithFinenessModulus(sieveTest.getFinenessModulus(), sieveTest.getId());
  }

}
