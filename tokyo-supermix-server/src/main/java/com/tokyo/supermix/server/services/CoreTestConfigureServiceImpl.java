package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.CoreTestConfigureMaterialDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureResponseDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureSubCatDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;

@Service
public class CoreTestConfigureServiceImpl implements CoreTestConfigureService {

  @Autowired
  private CoreTestConfigureRepository coreTestConfigureRepository;

  @Autowired
  private RawMaterialRepository rawMaterialRepository;

  @Autowired
  private TestConfigureRepository testConfigureRepository;


  @Transactional
  public List<CoreTestConfigure> saveCoreTestConfigure(List<CoreTestConfigure> CoreTestConfigure) {
    return coreTestConfigureRepository.saveAll(CoreTestConfigure);
  }

  public List<CoreTestConfigure> getAllCoreTestConfigure() {
    return coreTestConfigureRepository.findAll();
  }

  @Transactional
  public void createCoreTestConfigure(Long Id) {
    List<CoreTestConfigure> coreTestConfigurelist = new ArrayList<>();
    TestConfigure testConfigure = testConfigureRepository.findById(Id).get();
    if (testConfigure.getRawMaterial() == null && testConfigure.getMaterialSubCategory() != null) {
      List<RawMaterial> rawMaterialslist = rawMaterialRepository
          .findByMaterialSubCategoryId(testConfigure.getMaterialSubCategory().getId());
      rawMaterialslist.forEach(rawMaterial -> {
        CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
        coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
        coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
        coreTestConfigure.setRawMaterial(rawMaterial);
        coreTestConfigure.setTestConfigure(testConfigure);
        coreTestConfigure.setCoreTest(testConfigure.isCoreTest());
        coreTestConfigure.setApplicableTest(true);

        coreTestConfigurelist.add(coreTestConfigure);
      });
      coreTestConfigureRepository.saveAll(coreTestConfigurelist);
    } else if (testConfigure.getMaterialSubCategory() == null) {

      List<RawMaterial> rawMaterialslist = rawMaterialRepository
          .findByMaterialSubCategoryMaterialCategoryId(testConfigure.getMaterialCategory().getId());
      rawMaterialslist.forEach(rawMaterial -> {
        CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
        coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
        coreTestConfigure.setMaterialSubCategory(rawMaterial.getMaterialSubCategory());
        coreTestConfigure.setRawMaterial(rawMaterial);
        coreTestConfigure.setTestConfigure(testConfigure);
        coreTestConfigure.setCoreTest(testConfigure.isCoreTest());
        coreTestConfigure.setApplicableTest(true);

        coreTestConfigurelist.add(coreTestConfigure);
      });
      coreTestConfigureRepository.saveAll(coreTestConfigurelist);
    } else {
      CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
      coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
      coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
      coreTestConfigure.setRawMaterial(testConfigure.getRawMaterial());
      coreTestConfigure.setTestConfigure(testConfigure);
      coreTestConfigure.setCoreTest(testConfigure.isCoreTest());
      coreTestConfigure.setApplicableTest(true);
      coreTestConfigureRepository.save(coreTestConfigure);
    }
  }

  @Override
  public void createCoreTestConfigureInTestConfig(Long Mainid, Long subId, Long RawMatId,
      Long testId) {

    List<CoreTestConfigure> coreTestConfigurelist = new ArrayList<>();
    TestConfigure testConfigure = testConfigureRepository.findById(470l).get();

    if (RawMatId == null) {
      System.out.println("rawMatId");
      List<RawMaterial> rawMaterialslist = rawMaterialRepository.findByMaterialSubCategoryId(subId);

      rawMaterialslist.forEach(rawMaterial -> {
        CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
        coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
        coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
        coreTestConfigure.setRawMaterial(rawMaterial);
        coreTestConfigure.setTestConfigure(testConfigure);
        coreTestConfigure.setCoreTest(true);
        coreTestConfigure.setApplicableTest(true);

        coreTestConfigurelist.add(coreTestConfigure);
      });
      coreTestConfigureRepository.saveAll(coreTestConfigurelist);
    } else if (testConfigure.getMaterialSubCategory() == null) {
      System.out.println("subId");
      List<RawMaterial> rawMaterialslist = rawMaterialRepository
          .findByMaterialSubCategoryMaterialCategoryId(testConfigure.getMaterialCategory().getId());
      rawMaterialslist.forEach(rawMaterial -> {
        CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
        coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
        coreTestConfigure.setMaterialSubCategory(rawMaterial.getMaterialSubCategory());
        coreTestConfigure.setRawMaterial(rawMaterial);
        coreTestConfigure.setTestConfigure(testConfigure);
        coreTestConfigure.setCoreTest(true);
        coreTestConfigure.setApplicableTest(true);

        coreTestConfigurelist.add(coreTestConfigure);
      });
      coreTestConfigureRepository.saveAll(coreTestConfigurelist);
    } else {
      CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
      coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
      coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
      coreTestConfigure.setRawMaterial(testConfigure.getRawMaterial());
      coreTestConfigure.setTestConfigure(testConfigure);
      coreTestConfigure.setCoreTest(true);

      coreTestConfigurelist.add(coreTestConfigure);
      coreTestConfigure.setApplicableTest(true);

      coreTestConfigureRepository.save(coreTestConfigure);
    }
  }

  @Transactional
  public List<CoreTestConfigure> getCoreTestConfigureByTestConfigureId(Long testConfigureId) {
    return coreTestConfigureRepository.findBytestConfigureId(testConfigureId);
  }

  @Transactional
  public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdAndCoreTestTrue(
      Long rawMaterialId) {
    return coreTestConfigureRepository.findByrawMaterialIdAndCoreTestTrue(rawMaterialId);
  }

  @Transactional
  public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialId(Long rawMaterialId) {
    return coreTestConfigureRepository.findByrawMaterialIdAndCoreTestTrue(rawMaterialId);
  }

  @Override
  public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestConfigureId(
      Long testConfigureId) {
    CoreTestConfigureResponseDto coreTestConfigureResponseDto = new CoreTestConfigureResponseDto();
    coreTestConfigureResponseDto.setTestConfigureId(testConfigureId);

    TestConfigure testConfigure = testConfigureRepository.getOne(testConfigureId);

    testConfigure.getMaterialCategory();

    List<CoreTestConfigure> coreTestConfigurelist =
        coreTestConfigureRepository.findByTestConfigureIdAndMaterialCategoryId(testConfigureId,
            testConfigure.getMaterialCategory().getId());

    List<CoreTestConfigureSubCatDto> coreTestConfigureSubCatDtoList = new ArrayList<>();
    Set<MaterialSubCategory> cc = new HashSet<>();

    for (CoreTestConfigure coreTestConfigure : coreTestConfigurelist) {
      if (coreTestConfigure.isCoreTest()) {
        coreTestConfigureResponseDto.setCoreTest(true);
      }
      cc.add(coreTestConfigure.getMaterialSubCategory());
    }

    cc.forEach(c -> {
      c.getName();
      CoreTestConfigureSubCatDto coreTestConfigureSubCatDto = new CoreTestConfigureSubCatDto();
      coreTestConfigureSubCatDto.setMaterialSubCategoryId(c.getId());
      coreTestConfigureSubCatDto.setMaterialSubCategoryName(c.getName());
      coreTestConfigureSubCatDto.setMaterialCategoryId(c.getMaterialCategory().getId());

      List<CoreTestConfigure> coreTestConfigureSublist = coreTestConfigureRepository
          .findByTestConfigureIdAndMaterialSubCategoryId(testConfigureId, c.getId());
      List<CoreTestConfigureMaterialDto> CoreTestConfigureMaterialDtoList = new ArrayList<>();
      for (CoreTestConfigure coreSub : coreTestConfigureSublist) {
        CoreTestConfigureMaterialDto coreTestConfigureMaterialDto =
            new CoreTestConfigureMaterialDto();
        coreTestConfigureMaterialDto.setRawMaterialId(coreSub.getRawMaterial().getId());
        coreTestConfigureMaterialDto.setRawMaterialName(coreSub.getRawMaterial().getName());
        coreTestConfigureMaterialDto.setCoreTestConfigureId(coreSub.getId());
        coreTestConfigureMaterialDto
            .setMaterialSubCategoryId(coreSub.getMaterialSubCategory().getId());
        coreTestConfigureMaterialDto.setCoreTest(coreSub.isCoreTest());

        if (coreSub.isCoreTest()) {
          coreTestConfigureSubCatDto.setCoretest(true);
        }
        CoreTestConfigureMaterialDtoList.add(coreTestConfigureMaterialDto);
      }

      coreTestConfigureSubCatDto.setCoreTestConfigureMaterialDtos(CoreTestConfigureMaterialDtoList);

      coreTestConfigureSubCatDtoList.add(coreTestConfigureSubCatDto);

    });



    coreTestConfigureResponseDto.setTestConfigureId(testConfigureId);
    coreTestConfigureResponseDto.setMaterialCategoryId(testConfigure.getMaterialCategory().getId());
    coreTestConfigureResponseDto
        .setMaterialCategoryName(testConfigure.getMaterialCategory().getName());
    coreTestConfigureResponseDto.setCoreTestConfigureSubCatDto(coreTestConfigureSubCatDtoList);



    return coreTestConfigureResponseDto;
  }

  @Override
  public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestId(Long testId) {   
    return getAllCoreTestConfigureByTestConfigureId(testConfigureRepository.findByTestId(testId).getId());
  }
}

