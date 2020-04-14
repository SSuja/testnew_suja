package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.SieveTest;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinenessModulusRepository;
import com.tokyo.supermix.data.repositories.SieveTestRepository;
import com.tokyo.supermix.data.repositories.SieveTestTrialRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class SieveTestTrialServiceImpl implements SieveTestTrialService {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  SieveTestTrialRepository sieveTestTrialRepository;
  @Autowired
  private FinenessModulusRepository finenessModulusRepository;
  @Autowired
  private SieveTestRepository sieveTestRepository;
  @Autowired
  private MaterialTestService materialTestService;

  @Transactional
  public void saveSieveTestTrial(List<SieveTestTrial> sieveTestTrials) {
    Double totalRetainedWight = totalWeight(sieveTestTrials);
    for (SieveTestTrial sieveTestTrial : sieveTestTrials) {
      sieveTestTrial.setPercentageRetained(
          findPercentageRetainedWeight(sieveTestTrial.getCumulativeRetained(), totalRetainedWight));
      sieveTestTrial
          .setPassing(findPassing(sieveTestTrial.getCumulativeRetained(), totalRetainedWight));
      sieveTestTrialRepository.save(sieveTestTrial);
    }
    SieveTest sieveTest =
        sieveTestRepository.findByCode(sieveTestTrials.get(0).getSieveTest().getCode());
    updateTotalWeightAndFinenessModulus(sieveTest, sieveTestTrials);
  }

  // update total weight and fineness module in sieve test
  private void updateTotalWeightAndFinenessModulus(SieveTest sieveTest,
      List<SieveTestTrial> sieveTestTrials) {
    sieveTest.setFinenessModulus(finenessModulus(sieveTestTrials));
    sieveTest.setTotalWeight(totalWeight(sieveTestTrials));
    sieveTestRepository.save(sieveTest);
  }

  // find total weight of cumulative weight retained + pan weight
  private Double totalWeight(List<SieveTestTrial> sieveTestTrials) {
    Double total = sieveTestTrials.get(sieveTestTrials.size() - 1).getCumulativeRetained()
        + sieveTestRepository.findByCode(sieveTestTrials.get(0).getSieveTest().getCode())
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
  private Double findPercentageRetainedWeight(Double cumulativeRetained,
      Double totalRetainedWight) {
    Double percentageRetainedWeight =
        roundDoubleValue(cumulativeRetained * 100 / totalRetainedWight);
    return percentageRetainedWeight;
  }

  // find percentage weight passing
  private Double findPassing(Double cumulativeRetained, Double totalRetainedWight) {
    Double passing = roundDoubleValue(
        100 - findPercentageRetainedWeight(cumulativeRetained, totalRetainedWight));
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
  public List<SieveTestTrial> findSieveTestTrialBySieveTestCode(String sieveTestCode) {
    return sieveTestTrialRepository.findBySieveTestCode(sieveTestCode);
  }

  @Transactional
  public void updateSieveTestStatus(SieveTest sieveTest) {
    Long materialSubCategoryId = sieveTestRepository.findByCode(sieveTest.getCode())
        .getIncomingSample().getRawMaterial().getMaterialSubCategory().getId();
    if (finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId).get(0)
        .getMin() <= sieveTest.getFinenessModulus()
        && finenessModulusRepository.findByMaterialSubCategoryId(materialSubCategoryId).get(0)
            .getMax() >= sieveTest.getFinenessModulus()) {
      sieveTest.setStatus(Status.PASS);
      sieveTestRepository.save(sieveTest);
      notifyMail(sieveTest);
    } else {
      sieveTest.setStatus(Status.FAIL);
      sieveTestRepository.save(sieveTest);
      notifyMail(sieveTest);
    }
  }
  private void notifyMail(SieveTest sieveTest) {
    IncomingSample  incomingSample =sieveTest.getIncomingSample();
    emailService.sendMailWithFormat(mailConstants.getMailUpdateIncomingSampleStatus(),
        Constants.SUBJECT_NEW_SEIVE_TEST,
        "<p>The Seive Test is <b>" + sieveTest.getStatus() + "</b> for the Sample Code is <b>" + incomingSample.getCode()
            + "</b>. This Sample arrived on " + incomingSample.getDate()
            + ". The Sample Material is <b>" + incomingSample.getRawMaterial().getName()
            + "</b>.");
    materialTestService.updateIncomingSampleStatusByIncomingSample(incomingSample);
        
  }
}
