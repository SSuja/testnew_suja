package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.security.UserPrincipal;

public interface MaterialTestService {

  public String saveMaterialTest(MaterialTest materialTest);

  public boolean isMaterialTestExists(String code);

  public MaterialTest getMaterialTestByCode(String code);

  public List<MaterialTest> getAllMaterialTests();

  public void deleteMaterialTest(String code);

  public boolean isMaterialTestStatusExists(String status);

  public boolean isMaterialTestByTestConfigureExists(Long testConfigureId);

  public List<MaterialTest> getMaterialTestByStatus(String status);

  public List<MaterialTest> getMaterialTestByTestConfigureIdByPlant(Long testConfigureId,
      String plantCode);

  public List<MaterialTest> getMaterialTestByTestConfigureId(Long testConfigureId);

  public List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);

  public List<MaterialTest> searchMaterialTest(String incomingSampleCode, String status, String supplierName,
     String testName,BooleanBuilder booleanBuilder, int page, int size,Pageable pageable,String plantCode);

 List<MaterialTest> getMaterialTestByPlantCode(String plantCode);

  public List<MaterialTest> getMaterialTestByTestConfigureTestType(MainType testType);

  public List<MaterialTest> getAllMaterialTestByPlant(UserPrincipal currentUser);

  public void updateIncomingSampleStatusByIncomingSample(MaterialTest materialTestObj);

  public void updateMaterialTestComment(MaterialTest materialTest);

  public List<MaterialTest> getMaterialTestsByincomingSampleCodeAndPlantCode(
      String incomingSampleCode, String plantCode);

  public List<MaterialTest> getMaterialTestsByIncomingSampleCodeAndTestConfigId(
      String incomingSampleCode, Long testConfigId);

  public List<MaterialTest> getMaterialTestsByIncomingSampleCodeAndTestConfigIdAndPlantCode(
      String incomingSampleCode, Long testConfigId, String plantCode);
  
  public List<MaterialTest> getMaterialTestByPlant(String plantCode);
  
  public List<MaterialTest> getAllMaterialTests(Pageable pageable);
  
  public List<MaterialTest> getMaterialTestByPlant(String plantCode,Pageable pageable);
  
  public Long getCountMaterialTest();

  public Long getCountMaterialTestByPlantCode(String plantCode);
}
