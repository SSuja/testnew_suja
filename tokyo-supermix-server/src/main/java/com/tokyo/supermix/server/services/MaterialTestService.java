package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.enums.Status;

public interface MaterialTestService {

  public void saveMaterialTest(MaterialTest materialTest);

  public boolean isMaterialTestExists(String code);

  public MaterialTest getMaterialTestByCode(String code);

  public List<MaterialTest> getAllMaterialTests();

  public void deleteMaterialTest(String code);

  public boolean isMaterialTestStatusExists(String status);

  public boolean isMaterialTestByTestConfigureExists(Long testConfigureId);

  public List<MaterialTest> getMaterialTestByStatus(String status);

  public List<MaterialTest> getMaterialTestByTestConfigure(Long testConfigureId);

  public void updateIncomingSampleStatusByIncomingSample(IncomingSample incomingSample);

  public List<MaterialTest> findByIncomingSampleCode(String incomingSampleCode);

  public Page<MaterialTest> searchMaterialTest(String incomingSampleCode, Status status,
      Double average,Double averageMin,Double averageMax, BooleanBuilder booleanBuilder, int page, int size);
}
