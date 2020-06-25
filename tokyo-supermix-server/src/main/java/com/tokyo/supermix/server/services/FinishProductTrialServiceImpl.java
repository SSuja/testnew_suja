package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.FinishProductTrial;
import com.tokyo.supermix.data.repositories.FinishProductTrialRepository;

@Service
public class FinishProductTrialServiceImpl implements FinishProductTrialService {

  @Autowired
  private FinishProductTrialRepository finishProductTrialRepository;

  @Transactional(readOnly = true)
  public List<FinishProductTrial> getAllFinishProductTrials() {
    return finishProductTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public FinishProductTrial getFinishProductTrialByCode(String code) {
    return finishProductTrialRepository.findFinishProductTrialByCode(code);
  }

  @Transactional
  public String saveFinishProductTrial(FinishProductTrial finishProductTrial) {
    if (finishProductTrial.getCode() == null) {
      String prefix = finishProductTrial.getFinishProductTest().getCode();
      List<FinishProductTrial> FinishProductTrialList =
          finishProductTrialRepository.findByCodeContaining(prefix);
      if (FinishProductTrialList.size() == 0) {
        finishProductTrial.setCode(prefix + String.format("%03d", 1));
      } else {
        finishProductTrial
            .setCode(prefix + String.format("%04d", maxNumberFromCode(FinishProductTrialList) + 1));
      }
    }
    finishProductTrialRepository.save(finishProductTrial);
    return finishProductTrial.getCode();
  }

  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }

  private Integer maxNumberFromCode(List<FinishProductTrial> finishProductTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    finishProductTrialList.forEach(obj -> {
      list.add(getNumberFromCode(obj.getCode()));
    });
    return Collections.max(list);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteFinishProductTrial(String code) {
    finishProductTrialRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isFinishProductTrialExists(String code) {
    return finishProductTrialRepository.existsByCode(code);
  }
}
