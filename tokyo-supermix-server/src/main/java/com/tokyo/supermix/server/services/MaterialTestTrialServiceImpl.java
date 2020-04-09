package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.AdmixtureAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.AdmixtureAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Service
public class MaterialTestTrialServiceImpl implements MaterialTestTrialService {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  private MaterialTestTrialRepository materialTestTrialRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private MaterialTestRepository materialTestRepository;
  @Autowired
  private AdmixtureAcceptedValueRepository admixtureAcceptedValueRepository;

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
    return materialTestTrialRepository.findByCode(code);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialTestTrial(String code) {
    materialTestTrialRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestTrialExits(String code) {
    return materialTestTrialRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getMaterialTestTrialByMaterialTestCode(String materialTestCode) {
    return materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialTestIdExits(String materialTestCode) {
    return materialTestTrialRepository.existsByMaterialTestCode(materialTestCode);
  }

  @Transactional
  public void getAverageAndStatus(String materialTestCode) {
    compareWithAverage(calculateAverage(materialTestCode), materialTestCode);
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    emailService.sendMailWithFormat(mailConstants.getMailUpdateMaterialTestStatus(),
        Constants.SUBJECT_NEW_MATERIAL_TEST,
        "<p>The Incoming Sample Code is <b>" + materialTest.getIncomingSample().getCode()
            + ".</b> for Material test. The test is <b>" + materialTest.getStatus()
            + "</b>.</p> <p>Test Details : </p><ul>" + "<li>Test Name : "
            + materialTest.getTestConfigure().getTest().getName() + "</li><li> Average : "
            + materialTest.getAverage() + "</li><li>Material : "
            + materialTest.getIncomingSample().getRawMaterial().getName() + "</li><li> Date : "
            + materialTest.getDate() + "</li></ul>");
  }

  private void compareWithAverage(Double average, String materialTestCode) {
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    Long testId = materialTest.getTestConfigure().getId();
    if (materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory()
        .getMaterialCategory().getName().equalsIgnoreCase(Constants.ADMIXTURE)) {
      AdmixtureAcceptedValue admixtureAcceptedValue =
          admixtureAcceptedValueRepository.findByTestConfigureIdAndRawMaterialId(testId,
              materialTest.getIncomingSample().getRawMaterial().getId());
      calculateStatus(average, admixtureAcceptedValue.getMinValue(),
          admixtureAcceptedValue.getMaxValue(), materialTestCode);
    } else {
      calculateStatus(average, acceptedValueRepository.findByTestConfigureId(testId).getMinValue(),
          acceptedValueRepository.findByTestConfigureId(testId).getMaxValue(), materialTestCode);
    }
  }

  private void calculateStatus(Double average, Double minValue, Double maxValue,
      String materialTestCode) {
    if (minValue <= average && average <= maxValue) {
      updateAverage(average, materialTestCode, Status.PASS);
    } else {
      updateAverage(average, materialTestCode, Status.FAIL);
    }

  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  private Double calculateAverage(String materialTestCode) {
    Double totalResult = 0.0;
    int trialTotal = 0;
    List<MaterialTestTrial> listMaterialTestTrial =
        materialTestTrialRepository.findByMaterialTestCode(materialTestCode);
    for (MaterialTestTrial materialTestTrial : listMaterialTestTrial) {
      totalResult = totalResult + materialTestTrial.getResult();
      trialTotal = listMaterialTestTrial.size();
    }
    return roundDoubleValue(totalResult / trialTotal);
  }

  @Transactional
  public MaterialTest updateAverage(Double average, String code, Status status) {
    MaterialTest materialTest = materialTestRepository.findByCode(code);
    materialTest.setAverage(roundDoubleValue(average));
    materialTest.setStatus(status);
    return materialTestRepository.save(materialTest);
  }
}
