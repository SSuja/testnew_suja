package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.Status;
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

  public List<MaterialTest> getMaterialTestByTestConfigureId(Long testConfigureId);

  public List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);

  public Page<MaterialTest> searchMaterialTest(String incomingSampleCode, Status status,
      Double average, String testName, Double averageMin, Double averageMax,
      BooleanBuilder booleanBuilder, int page, int size);

  List<MaterialTest> getMaterialTestByPlantCode(String plantCode);

	public List<MaterialTest> getMaterialTestByTestConfigureTestType(MainType testType);

  public List<MaterialTest> getAllMaterialTestByPlant(UserPrincipal currentUser);

  public void updateIncomingSampleStatusByIncomingSample(MaterialTest materialTestObj);

  public void updateMaterialTestComment(MaterialTest materialTest);
}
