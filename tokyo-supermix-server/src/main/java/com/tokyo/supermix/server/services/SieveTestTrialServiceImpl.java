package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.SieveTestTrial;
import com.tokyo.supermix.data.repositories.SieveTestTrialRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class SieveTestTrialServiceImpl implements SieveTestTrialService {
  @Autowired
  SieveTestTrialRepository sieveTestTrialRepository;
  @Autowired
  SieveTestService sieveTestService;

  @Transactional
  public void saveSieveTestTrial(List<SieveTestTrial> sieveTestTrials) {
    Double totalRetainedWight = totalWeight(sieveTestTrials);

    System.out.println("total " + totalRetainedWight);
    for (SieveTestTrial sieveTestTrial : sieveTestTrials) {
      sieveTestTrial
          .setPercentageRetained(findPercentageRetainedWeight(sieveTestTrial, totalRetainedWight));
      sieveTestTrial.setPassing(findPassing(sieveTestTrial, totalRetainedWight));
      sieveTestTrialRepository.save(sieveTestTrial);
    }
  }

  // find total weight of cummalative weight retained
  private Double totalWeight(List<SieveTestTrial> sieveTestTrials) {
    double total = sieveTestTrials.get(sieveTestTrials.size() - 1).getCummalativeRetained();
    return total;
  }

  // find percentage weight retained
  private Double findPercentageRetainedWeight(SieveTestTrial sieveTestTrial,
      double totalRetainedWight) {
    double petained =
        roundDoubleValue(sieveTestTrial.getCummalativeRetained() * 100 / totalRetainedWight);
    System.out.println("petained" + petained);
    return petained;
  }

  // fing percentage weight passing
  private Double findPassing(SieveTestTrial sieveTestTrial, Double totalRetainedWight) {
    System.out
        .println("passing " + findPercentageRetainedWeight(sieveTestTrial, totalRetainedWight));
    double passing =
        roundDoubleValue(100 - findPercentageRetainedWeight(sieveTestTrial, totalRetainedWight));
    return passing;
  }

  // To Change Four Digit Value
  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.TWO_DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

}
