package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;

@Service
public class MaterialTestTrialServiceImpl implements MaterialTestTrialService {
  @Autowired
  private MaterialTestTrialRepository materialTestTrialRepository;
  @Autowired
  private MaterialTestService materialTestService;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private MaterialTestRepository materialTestRepository;

  @Transactional
  public MaterialTestTrial saveMaterialTestTrial(MaterialTestTrial materialTestTrial) {
    return materialTestTrialRepository.save(materialTestTrial);
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getAllMaterialTestTrial() {
    return materialTestTrialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public MaterialTestTrial getMaterialTestTrialByCode(String code) {
    return materialTestTrialRepository.findById(code).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialTestTrial(String code) {
    materialTestTrialRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestTrialExit(String code) {
    return materialTestTrialRepository.existsById(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getMaterialTestTrialByMaterialTestId(String materialTestCode) {
    return materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestIdExit(String materialTestCode) {
    return materialTestTrialRepository.existsByMaterialTestCode(materialTestCode);
  }

  @Transactional
  public void getAverage(String materialTestCode) {
    compareWithAvarage(calculateAvarage(materialTestCode), materialTestCode);

  }

  private void compareWithAvarage(Double avarage, String materialTestCode) {
    Long testId = materialTestRepository.findById(materialTestCode).get().getTest().getId();
    if (acceptedValueRepository.findByTestId(testId).getMinValue() <= avarage
        && acceptedValueRepository.findByTestId(testId).getMaxValue() >= avarage) {
      materialTestService.updateAverage(avarage, materialTestCode, Status.PASS);
    } else {
      materialTestService.updateAverage(avarage, materialTestCode, Status.FAIL);

    }
  }

  private Double calculateAvarage(String materialTestCode) {
    Double totalResult = 0.0;
    int trialTotal = 0;

    List<MaterialTestTrial> listMaterialTestTrial =
        materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
    for (MaterialTestTrial materialTestTrial : listMaterialTestTrial) {
      totalResult = totalResult + materialTestTrial.getResult();
      trialTotal = listMaterialTestTrial.size();
    }
    return (totalResult / trialTotal);
  }
}
