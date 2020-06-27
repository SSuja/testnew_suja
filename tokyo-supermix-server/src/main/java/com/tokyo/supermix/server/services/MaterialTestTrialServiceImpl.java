package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

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
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  @Transactional
  public String saveMaterialTestTrial(MaterialTestTrial materialTestTrial) {
    String codePrefix = materialTestTrial.getMaterialTest().getCode();
       //String subPrefix=codePrefix + "-";
       String subPrefix = codePrefix + "-T-";
//       String code=subPrefix + String.format("%04d", 1);
       List<MaterialTestTrial> materialTestTrialList = materialTestTrialRepository.findByCodeContaining(subPrefix);
       if (materialTestTrialList.size() == 0) {
         materialTestTrial.setCode(subPrefix + String.format("%04d", 1));
       } else {
         materialTestTrial
             .setCode(subPrefix + String.format("%04d",maxNumberFromCode(materialTestTrialList) + 1));
       }
  
   materialTestTrialRepository.save(materialTestTrial);
   return materialTestTrial.getCode();
  }
  private Integer getNumberFromCode(String code) {
    String numberOnly = code.replaceAll("[^0-9]", "");
    return Integer.parseInt(numberOnly);
  }
  private Integer maxNumberFromCode(List<MaterialTestTrial> materialTestTrialList) {
    List<Integer> list = new ArrayList<Integer>();
    materialTestTrialList.forEach(obj -> {
      String code = obj.getCode();
      list.add(getNumberFromCode(code.substring(code.length() - code.indexOf("-"))));
    });
    return Collections.max(list);
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
  }

  private void compareWithAverage(Double average, String materialTestCode) {
    MaterialTest materialTest = materialTestRepository.findByCode(materialTestCode);
    Long testConfigureId = materialTest.getTestConfigure().getId();
    if (materialTest.getIncomingSample().getRawMaterial().getMaterialSubCategory()
        .getMaterialCategory().getName().equalsIgnoreCase(Constants.ADMIXTURE)) {
      MaterialAcceptedValue materialAcceptedValue =
          materialAcceptedValueRepository.findByTestConfigureIdAndRawMaterialId(testConfigureId,
              materialTest.getIncomingSample().getRawMaterial().getId());
      calculateStatus(average, materialAcceptedValue.getMinValue(),
          materialAcceptedValue.getMaxValue(), materialTestCode);
    } else {
      calculateStatus(average,
          acceptedValueRepository.findByTestConfigureId(testConfigureId).getMinValue(),
          acceptedValueRepository.findByTestConfigureId(testConfigureId).getMaxValue(),
          materialTestCode);
    }
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

  private void calculateStatus(Double average, Double minValue, Double maxValue,
      String materialTestCode) {
    if (maxValue == null && minValue <= average || minValue == null && average <= maxValue) {
      updateAverage(average, materialTestCode, Status.PASS);
    } else if (minValue != null && minValue <= average && maxValue != null && average <= maxValue) {
      updateAverage(average, materialTestCode, Status.PASS);
    } else {
      updateAverage(average, materialTestCode, Status.FAIL);
    }
  }

  @Transactional
  public MaterialTest updateAverage(Double average, String code, Status status) {
    MaterialTest materialTest = materialTestRepository.findByCode(code);
    materialTest.setAverage(roundDoubleValue(average));
    materialTest.setStatus(status);
    return materialTestRepository.save(materialTest);
  }

  private Double roundDoubleValue(Double value) {
    DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
    return Double.valueOf(decimalFormat.format(value));
  }

  @Transactional(readOnly = true)
  public List<MaterialTestTrial> getMaterialTestTrialByPlantCode(String plantCode) {
    return materialTestTrialRepository.findByMaterialTestIncomingSamplePlantCode(plantCode);
  }

  @Override
  public List<MaterialTestTrial> getAllMaterialTestTrialByplant(UserPrincipal currentUser) {
    return materialTestTrialRepository.findByMaterialTestIncomingSamplePlantCodeIn(
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_MATERIAL_TEST_TRIAL));
  }
}
