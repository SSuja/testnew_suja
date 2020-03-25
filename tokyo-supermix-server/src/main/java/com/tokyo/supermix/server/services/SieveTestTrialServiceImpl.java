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
  private FinenessModulusRepository finenessModulusRepository;
  @Autowired
  private SieveTestRepository sieveTestRepository;

  @Transactional
  public void saveSieveTestTrial(List<SieveTestTrial> sieveTestTrials) {
    Double totalRetainedWight = totalWeight(sieveTestTrials);
    for (SieveTestTrial sieveTestTrial : sieveTestTrials) {
      sieveTestTrial.setPercentageRetained(findPercentageRetainedWeight(
          sieveTestTrial.getCummalativeRetained(), totalRetainedWight));
      sieveTestTrial
          .setPassing(findPassing(sieveTestTrial.getCummalativeRetained(), totalRetainedWight));
      sieveTestTrialRepository.save(sieveTestTrial);
    }
    SieveTest sieveTest =
        sieveTestRepository.findById(sieveTestTrials.get(0).getSieveTest().getId()).get();
    updateTotalWeightAndFinenessModulus(sieveTest, sieveTestTrials);
  }

  // update total weight and fineness module in sieve test
  private void updateTotalWeightAndFinenessModulus(SieveTest sieveTest,
      List<SieveTestTrial> sieveTestTrials) {
    sieveTest.setFinenessModulus(finenessModulus(sieveTestTrials));
    sieveTest.setTotalWeight(totalWeight(sieveTestTrials));
    sieveTestRepository.save(sieveTest);
  }

  // find total weight of cummalative weight retained + pan weight
  private Double totalWeight(List<SieveTestTrial> sieveTestTrials) {
    Double total = sieveTestTrials.get(sieveTestTrials.size() - 1).getCummalativeRetained()
        + sieveTestRepository.findById(sieveTestTrials.get(0).getSieveTest().getId()).get()
            .getPanWeight();
    return total;
  }

  // find fineness Modulus
  private Double finenessModulus(List<SieveTestTrial> sieveTestTrials) {
    Double totalPercentageRetained = 0.0;
    for (SieveTestTrial sieveTestTrial : sieveTestTrials) {
      totalPercentageRetained = totalPercentageRetained + sieveTestTrial.getPercentageRetained();
    }
    Double finenessModulus = roundDoubleValue(totalPercentageRetained / 100);
    return finenessModulus;
  }

  // find percentage weight retained
  private Double findPercentageRetainedWeight(Double cummalativeRetained,
      Double totalRetainedWight) {
    Double percentageRetainedWeight =
        roundDoubleValue(cummalativeRetained * 100 / totalRetainedWight);
    return percentageRetainedWeight;
  }

  // find percentage weight passing
  private Double findPassing(Double cummalativeRetained, Double totalRetainedWight) {
    Double passing = roundDoubleValue(
        100 - findPercentageRetainedWeight(cummalativeRetained, totalRetainedWight));
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

  @Transactional
  public void updateSieveTestStatus(SieveTest sieveTest) {
    Long materialSubCategoryId = sieveTestRepository.findById(sieveTest.getId()).get()
        .getIncomingSample().getRawMaterial().getMaterialSubCategory().getId();
    if (finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId).get(0)
        .getMin() <= sieveTest.getFinenessModulus()
        && finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId).get(0)
            .getMax() >= sieveTest.getFinenessModulus()) {
      updateStatus(sieveTest.getId(), Status.PASS);
    } else {
      updateStatus(sieveTest.getId(), Status.FAIL);
    }
  }

  private SieveTest updateStatus(Long sieveTestId, Status status) {
    SieveTest sieveTest = sieveTestRepository.findById(sieveTestId).get();
    sieveTest.setStatus(status);
    return sieveTestRepository.save(sieveTest);
  }


}
