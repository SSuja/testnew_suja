package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.CoreTestConfigureResponseDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;

public interface CoreTestConfigureService {
  public List<CoreTestConfigure> saveCoreTestConfigure(List<CoreTestConfigure> CoreTestConfigure);
  
  public List<CoreTestConfigure> getAllCoreTestConfigure();

  public void createCoreTestConfigure(Long Id);

  public void createCoreTestConfigureInTestConfig(Long Mainid, Long subId, Long RawMatId,
      Long testId);

  public List<CoreTestConfigure> getCoreTestConfigureByTestConfigureId(Long testConfigureId);

  public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdAndCoreTestTrue(
      Long rawMaterialId);
  public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialId(Long rawMaterialId);
  
  public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestConfigureId(Long testConfigureId);
}
