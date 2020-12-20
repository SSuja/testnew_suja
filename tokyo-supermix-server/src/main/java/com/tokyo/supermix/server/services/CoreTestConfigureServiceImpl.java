package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.CoreTestConfigureMainDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureMaterialDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureResponseDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureSubCatDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;
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
  
  public List<CoreTestConfigure> getAllCoreTestConfigure(){
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
      // MaterialCategory materialCategory
      // =materialCategoryRepository.findById(testConfigure.getMaterialCategory()).get();
      // MaterialSubCategory
      // materialSubCategory=materialSubCategoryRepository.findById(subId).get();


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
    CoreTestConfigureResponseDto coreTestConfigureResponseDto=new CoreTestConfigureResponseDto();
    coreTestConfigureResponseDto.setTestConfigureId(testConfigureId);
    
    List<CoreTestConfigure> coreTestConfigurelist=coreTestConfigureRepository.findBytestConfigureId(testConfigureId);
    
    List<CoreTestConfigureMainDto> coreTestConfigureMainDtoList=new ArrayList<>();
    Set<CoreTestConfigureMainDto> cc=new HashSet<>();
    for(CoreTestConfigure coreTestConfigure:coreTestConfigurelist) {
      CoreTestConfigureMainDto configureMainDto =new CoreTestConfigureMainDto();
      configureMainDto.setMaterialCategoryId(coreTestConfigure.getMaterialCategory().getId());
//      coreTestConfigureMainDtoList.add(configureMainDto);
      cc.add(configureMainDto);
    }
    coreTestConfigureMainDtoList.addAll(cc);
//    (cc.toArray());
    
//    List<CoreTestConfigureMainDto> coreTestConfigureMainDtoList=new ArrayList<>();
     
//    CoreTestConfigureMainDto configureMainDto =new CoreTestConfigureMainDto();
//      configureMainDto.setMaterialCategoryId(1l);
        
        List<CoreTestConfigureSubCatDto> coreTestConfigureSubCatDtoList=new ArrayList<>();
        
         CoreTestConfigureSubCatDto coreTestConfigureSubCatDto=new CoreTestConfigureSubCatDto();
         coreTestConfigureSubCatDto.setMaterialSubCategoryId(2l);
         coreTestConfigureSubCatDtoList.add(coreTestConfigureSubCatDto);
         List<CoreTestConfigureMaterialDto> coreTestConfigureMaterialDtoList=new ArrayList<>();
         
         CoreTestConfigureMaterialDto coreTestConfigureMaterialDto=new CoreTestConfigureMaterialDto();
         
         coreTestConfigureMaterialDto.setRawMaterialId(3l);
         coreTestConfigureMaterialDto.setCoreTest(true);
         
         coreTestConfigureMaterialDtoList.add(coreTestConfigureMaterialDto);
         
         coreTestConfigureSubCatDto.setCoreTestConfigureMaterialDtos(coreTestConfigureMaterialDtoList);
         
//      configureMainDto.setMaterialSubCategoryId(coreTestConfigureSubCatDtoList);
    
//    coreTestConfigureMainDtoList.add(configureMainDto);
    
    coreTestConfigureResponseDto.setCoreTestConfigureMainDto(coreTestConfigureMainDtoList);
    
    return coreTestConfigureResponseDto;
  }
}
// @Override
// public void createCoreTestConfigure(Long Mainid, Long subId, Long RawMatId) {
//
// List<CoreTestConfigure> coreTestConfigurelist=new ArrayList<>();
// TestConfigure testConfigure=testConfigureRepository.findById(470l).get();
//
// if(testConfigure.getRawMaterial() ==null) {
// System.out.println("rawMatId");
// List<RawMaterial> rawMaterialslist=rawMaterialRepository.findByMaterialSubCategoryId(subId);
//// MaterialCategory materialCategory
// =materialCategoryRepository.findById(testConfigure.getMaterialCategory()).get();
//// MaterialSubCategory materialSubCategory=materialSubCategoryRepository.findById(subId).get();
//
//
// rawMaterialslist.forEach(rawMaterial -> {
// CoreTestConfigure coreTestConfigure=new CoreTestConfigure();
// coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
// coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
// coreTestConfigure.setRawMaterial(rawMaterial);
// coreTestConfigure.setTestConfigure(testConfigure);
// coreTestConfigure.setCoreTest(true);
//
// coreTestConfigurelist.add(coreTestConfigure);
// });
// coreTestConfigureRepository.saveAll(coreTestConfigurelist);
// }
// else if(testConfigure.getMaterialSubCategory() == null) {
// System.out.println("subId");
// List<RawMaterial>
// rawMaterialslist=rawMaterialRepository.findByMaterialSubCategoryMaterialCategoryId
// (testConfigure.getMaterialCategory().getId());
// rawMaterialslist.forEach(rawMaterial -> {
// CoreTestConfigure coreTestConfigure=new CoreTestConfigure();
// coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
// coreTestConfigure.setMaterialSubCategory(rawMaterial.getMaterialSubCategory());
// coreTestConfigure.setRawMaterial(rawMaterial);
// coreTestConfigure.setTestConfigure(testConfigure);
// coreTestConfigure.setCoreTest(true);
//
// coreTestConfigurelist.add(coreTestConfigure);
// });
// coreTestConfigureRepository.saveAll(coreTestConfigurelist);
// }
// else {
// CoreTestConfigure coreTestConfigure=new CoreTestConfigure();
// coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
// coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
// coreTestConfigure.setRawMaterial(testConfigure.getRawMaterial());
// coreTestConfigure.setTestConfigure(testConfigure);
// coreTestConfigure.setCoreTest(true);
//
// coreTestConfigurelist.add(coreTestConfigure);
//
// coreTestConfigureRepository.save(coreTestConfigure);
// }
// }
// }
