package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.CoreTestConfigureDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureMaterialDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureResponseDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureSubCatDto;
import com.tokyo.supermix.data.dto.TestOriginDto;
import com.tokyo.supermix.data.dto.TestOriginRequestDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.QCoreTestConfigure;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.Origin;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialCategoryRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
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

  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;

  @Autowired
  Mapper mapper;

  @Autowired
  MaterialCategoryRepository materialCategoryRepository;

  @Autowired
  MaterialAcceptedValueRepository MaterialAcceptedValueRepository;

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
        coreTestConfigure.setCoreTest(false);
        coreTestConfigure.setApplicableTest(false);

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
        coreTestConfigure.setCoreTest(false);
        coreTestConfigure.setApplicableTest(false);

        coreTestConfigurelist.add(coreTestConfigure);
      });
      coreTestConfigureRepository.saveAll(coreTestConfigurelist);
    } else {
      CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
      coreTestConfigure.setMaterialCategory(testConfigure.getMaterialCategory());
      coreTestConfigure.setMaterialSubCategory(testConfigure.getMaterialSubCategory());
      coreTestConfigure.setRawMaterial(testConfigure.getRawMaterial());
      coreTestConfigure.setTestConfigure(testConfigure);
      coreTestConfigure.setCoreTest(false);
      coreTestConfigure.setApplicableTest(false);
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
  public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdCoreTestTrueAndApplicableTestTrue(
      Long rawMaterialId) {
    return coreTestConfigureRepository
        .findByrawMaterialIdAndCoreTestTrueAndApplicableTestTrue(rawMaterialId);
  }

  @Transactional
  public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestConfigureId(
      Long testConfigureId) {
    CoreTestConfigureResponseDto coreTestConfigureResponseDto = new CoreTestConfigureResponseDto();
    coreTestConfigureResponseDto.setTestConfigureId(testConfigureId);

    TestConfigure testConfigure = testConfigureRepository.getOne(testConfigureId);

    testConfigure.getMaterialCategory();

    List<CoreTestConfigure> coreTestConfigurelist = coreTestConfigureRepository

        .findByTestConfigureIdAndMaterialCategoryIdAndApplicableTestTrue(testConfigureId,
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

          .findByTestConfigureIdAndMaterialSubCategoryIdAndApplicableTestTrue(testConfigureId,
              c.getId());

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

  @Transactional
  public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestId(Long testId) {
    return getAllCoreTestConfigureByTestConfigureId(
        testConfigureRepository.findByTestId(testId).getId());
  }

  @Transactional
  public List<TestOriginDto> getAllCoreTestConfigureByMainCategoryId(Long mainCategoryId) {
    List<TestOriginDto> testOriginDtoList = new ArrayList<>();

    List<TestConfigure> testConfigureList =
        testConfigureRepository.findByMaterialCategoryIdAndMaterialSubCategoryNull(mainCategoryId);

    for (TestConfigure testConfigure : testConfigureList) {
      TestOriginDto testOriginDto = new TestOriginDto();
      testOriginDto.setTestConfigureId(testConfigure.getId());
      testOriginDto.setCoreTest(

          coreTestConfigureRepository
              .existsBytestConfigureIdAndMaterialCategoryIdAndCoreTestTrueAndApplicableTestTrue(
                  testConfigure.getId(), mainCategoryId));
      testOriginDto.setTestOrigin(Origin.OWN);
      testOriginDto.setTestId(testConfigure.getTest().getId());
      testOriginDto.setTestName(testConfigure.getTest().getName());
      if (coreTestConfigureRepository
          .existsBytestConfigureIdAndMaterialCategoryIdAndApplicableTestTrue(testConfigure.getId(),
              mainCategoryId)) {
        testOriginDtoList.add(testOriginDto);
      }
    }
    return testOriginDtoList;
  }

  @Transactional
  public List<TestOriginDto> getAllCoreTestConfigureByMaterialSubCategoryId(
      Long materialSubCategoryId) {
    MaterialSubCategory materialSubCategory =
        materialSubCategoryRepository.getOne(materialSubCategoryId);
    List<TestOriginDto> lis =
        getAllCoreTestConfigureByMainCategoryId(materialSubCategory.getMaterialCategory().getId());
    lis.forEach(testOrigin -> {
      testOrigin.setTestOrigin(Origin.MAIN);
      testOrigin.setCoreTest(coreTestConfigureRepository

          .existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrueAndApplicableTestTrue(
              testOrigin.getTestConfigureId(), materialSubCategoryId));
    });

    List<TestOriginDto> testOriginDtoList = lis.stream()
        .filter(testOriginDto -> coreTestConfigureRepository
            .existsBytestConfigureIdAndMaterialSubCategoryIdAndApplicableTestTrue(
                testOriginDto.getTestConfigureId(), materialSubCategoryId) == true)
        .collect(Collectors.toList());
    List<TestConfigure> testConfigureList = testConfigureRepository
        .findByMaterialSubCategoryIdAndRawMaterialNull(materialSubCategoryId);

    for (TestConfigure testConfigure : testConfigureList) {
      TestOriginDto testOriginDto = new TestOriginDto();
      testOriginDto.setTestConfigureId(testConfigure.getId());
      testOriginDto.setCoreTest(coreTestConfigureRepository

          .existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrueAndApplicableTestTrue(
              testConfigure.getId(), materialSubCategoryId));
      testOriginDto.setTestOrigin(Origin.OWN);
      testOriginDto.setTestId(testConfigure.getTest().getId());
      testOriginDto.setTestName(testConfigure.getTest().getName());
      if (coreTestConfigureRepository
          .existsBytestConfigureIdAndMaterialSubCategoryIdAndApplicableTestTrue(
              testConfigure.getId(), materialSubCategoryId)) {
        testOriginDtoList.add(testOriginDto);
      }
    }
    return testOriginDtoList;
  }

  @Transactional
  public List<TestOriginDto> getAllCoreTestConfigureByRawMaterialId(Long rawMaterialId) {
    RawMaterial rawMaterial = rawMaterialRepository.getOne(rawMaterialId);
    List<TestOriginDto> testOriginDtoList = getAllCoreTestConfigureByMaterialSubCategoryId(
        rawMaterial.getMaterialSubCategory().getId());
    testOriginDtoList.forEach(testOrigin -> {
      if (testOrigin.getTestOrigin().equals(Origin.OWN)) {
        testOrigin.setTestOrigin(Origin.SUB);
      }
      testOrigin.setCoreTest(coreTestConfigureRepository
          .existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrueAndApplicableTestTrue(
              testOrigin.getTestConfigureId(), rawMaterialId));
    });

    List<TestOriginDto> lisSUbCat = testOriginDtoList.stream()
        .filter(testOriginDto -> coreTestConfigureRepository
            .existsBytestConfigureIdAndRawMaterialIdAndApplicableTestTrue(
                testOriginDto.getTestConfigureId(), rawMaterialId) == true)
        .collect(Collectors.toList());

    List<TestConfigure> testConfigureList =
        testConfigureRepository.findByRawMaterialId(rawMaterial.getId());
    for (TestConfigure testConfigure : testConfigureList) {
      TestOriginDto testOriginDto = new TestOriginDto();
      testOriginDto.setTestConfigureId(testConfigure.getId());
      testOriginDto.setCoreTest(coreTestConfigureRepository
          .existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrueAndApplicableTestTrue(
              testConfigure.getId(), rawMaterial.getId()));
      testOriginDto.setTestOrigin(Origin.OWN);
      testOriginDto.setTestId(testConfigure.getTest().getId());
      testOriginDto.setTestName(testConfigure.getTest().getName());
      if (coreTestConfigureRepository.existsBytestConfigureIdAndRawMaterialIdAndApplicableTestTrue(
          testConfigure.getId(), rawMaterial.getId())) {
        lisSUbCat.add(testOriginDto);
      }
    }

    return lisSUbCat;
  }

  @Transactional
  public void testOriginChangeStatus(List<TestOriginRequestDto> testOriginRequestDtolist) {

    for (TestOriginRequestDto testOriginRequestDto : testOriginRequestDtolist) {

      if (testOriginRequestDto.getMaterialCategoryId() != null) {
        coreTestConfigureRepository
            .findByTestConfigureIdAndMaterialCategoryIdAndApplicableTestTrue(
                testOriginRequestDto.getTestConfigureId(),
                testOriginRequestDto.getMaterialCategoryId())

            .stream().forEach(coreTest -> {
              coreTest.setCoreTest(testOriginRequestDto.isCoreTest());
              coreTestConfigureRepository.save(coreTest);
            });
      }
      if (testOriginRequestDto.getMaterialSubCategoryId() != null) {
        coreTestConfigureRepository
            .findByTestConfigureIdAndMaterialSubCategoryIdAndApplicableTestTrue(
                testOriginRequestDto.getTestConfigureId(),
                testOriginRequestDto.getMaterialSubCategoryId())
            .stream().forEach(coreTest -> {
              coreTest.setCoreTest(testOriginRequestDto.isCoreTest());
              coreTestConfigureRepository.save(coreTest);
            });
      }
      if (testOriginRequestDto.getRawMaterialId() != null) {

        coreTestConfigureRepository
            .findByTestConfigureIdAndRawMaterialIdAndApplicableTestTrue(
                testOriginRequestDto.getTestConfigureId(), testOriginRequestDto.getRawMaterialId())
            .stream().forEach(coreTest -> {
              coreTest.setCoreTest(testOriginRequestDto.isCoreTest());
              coreTestConfigureRepository.save(coreTest);
            });
      }
    }
  }

  @Transactional
  public List<CoreTestConfigureDto> searchRawMaterial(BooleanBuilder booleanBuilder,
      Long testConfigureId, String rawMaterialName, Long materialSubCategoryId) {
    List<CoreTestConfigure> coretestlist = new ArrayList<>();
    if (testConfigureId != null) {
      booleanBuilder.and(QCoreTestConfigure.coreTestConfigure.testConfigure.id.eq(testConfigureId));
    }
    if (materialSubCategoryId != null) {
      booleanBuilder.and(
          QCoreTestConfigure.coreTestConfigure.materialSubCategory.id.eq(materialSubCategoryId));
    }
    if (rawMaterialName != null && !rawMaterialName.isEmpty()) {
      booleanBuilder.and(QCoreTestConfigure.coreTestConfigure.rawMaterial.name
          .containsIgnoreCase(rawMaterialName));
    }
    coreTestConfigureRepository.findAll(booleanBuilder)
        .forEach(coreTest -> coretestlist.add(coreTest));
    return mapper.map(
        coretestlist.stream().filter(core -> core.isApplicableTest()).collect(Collectors.toList()),
        CoreTestConfigureDto.class);
  }

  @Transactional
  public List<CoreTestConfigure> updateCoreTestConfigure(
      List<CoreTestConfigure> CoreTestConfigurelist) {
    List<CoreTestConfigure> CoreTestConfigurelistnew = new ArrayList<>();
    for (CoreTestConfigure coreTestConfigure : CoreTestConfigurelist) {
      CoreTestConfigure coreTestConfigure2 =
          coreTestConfigureRepository.getOne(coreTestConfigure.getId());
      coreTestConfigure2.setCoreTest(coreTestConfigure.isCoreTest());
      CoreTestConfigurelistnew.add(coreTestConfigure2);
    }
    return coreTestConfigureRepository.saveAll(CoreTestConfigurelistnew);
  }

  @Transactional
  public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdAndApplicableTest(
      Long rawMaterialId) {

    return coreTestConfigureRepository.findByrawMaterialIdAndApplicableTestTrue(rawMaterialId);
  }

  @Transactional
  public void updateCoreTestByNewRawMaterial(Long rawMaterialId, Long materialSubCategoryId) {
    RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId).get();

    MaterialSubCategory materialSubCategory =
        materialSubCategoryRepository.findById(materialSubCategoryId).get();

    MaterialCategory materialCategory = materialSubCategory.getMaterialCategory();

    List<TestConfigure> testConfigures = testConfigureRepository
        .findByMaterialCategoryIdAndMaterialSubCategoryNull(materialCategory.getId());

    if (!testConfigures.isEmpty()) {
      testConfigures.forEach(testConfigure -> {
        CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
        coreTestConfigure.setMaterialCategory(materialCategory);
        coreTestConfigure.setMaterialSubCategory(materialSubCategory);
        coreTestConfigure.setTestConfigure(testConfigure);
        if (testConfigure.getAcceptedType() != null
            && testConfigure.getAcceptedType().equals(AcceptedType.MATERIAL)) {
          coreTestConfigure.setApplicableTest(false);
        } else if (testConfigure.getAcceptedType() != null
            && testConfigure.getAcceptedType().equals(AcceptedType.SUB_CATEGORY)) {
          coreTestConfigure.setApplicableTest(
              MaterialAcceptedValueRepository.existsByTestConfigureIdAndMaterialSubCategoryId(
                  testConfigure.getId(), materialSubCategoryId));
        } else {
          coreTestConfigure.setApplicableTest(true);
        }
        coreTestConfigure.setRawMaterial(rawMaterial);
        coreTestConfigure.setCoreTest(coreTestConfigureRepository
            .existsBytestConfigureIdAndMaterialCategoryIdAndCoreTestTrueAndApplicableTestTrue(
                testConfigure.getId(), materialCategory.getId()));

        coreTestConfigureRepository.save(coreTestConfigure);
      });
    }

    List<TestConfigure> testConfiguresSub = testConfigureRepository
        .findByMaterialSubCategoryIdAndRawMaterialNull(materialSubCategory.getId());

    if (!testConfiguresSub.isEmpty()) {
      testConfiguresSub.forEach(testConfigure -> {
        CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
        coreTestConfigure.setMaterialCategory(materialCategory);
        coreTestConfigure.setMaterialSubCategory(materialSubCategory);
        coreTestConfigure.setTestConfigure(testConfigure);
        if (testConfigure.getAcceptedType() != null
            && testConfigure.getAcceptedType().equals(AcceptedType.MATERIAL)) {
          coreTestConfigure.setApplicableTest(false);
        } else {
          coreTestConfigure.setApplicableTest(true);
        }
        coreTestConfigure.setRawMaterial(rawMaterial);
        coreTestConfigure.setCoreTest(coreTestConfigureRepository
            .existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrueAndApplicableTestTrue(
                testConfigure.getId(), materialSubCategory.getId()));
        coreTestConfigureRepository.save(coreTestConfigure);
      });
    }
  }

  @Transactional
  public void updatetestConfigureByTestConfigureId(Long testConfigureId) {
    List<CoreTestConfigure> coreTestConfigures =
        coreTestConfigureRepository.findBytestConfigureId(testConfigureId);
    coreTestConfigures.forEach(coreTest -> {
      coreTestConfigureRepository.deleteById(coreTest.getId());
    });
  }

  @Transactional
  public void updateApplicableTestByMaterialSubCategoryId(Long testConfigureId,
      Long materialSubCategoryId, boolean applicable) {
    List<CoreTestConfigure> coreTestConfigureList = coreTestConfigureRepository
        .findBytestConfigureIdAndMaterialSubCategoryId(testConfigureId, materialSubCategoryId);
    coreTestConfigureList.forEach(testConfig -> testConfig.setApplicableTest(applicable));
    coreTestConfigureRepository.saveAll(coreTestConfigureList);
  }

  @Transactional
  public void updateApplicableTestByRawMaterialId(Long testConfigureId, Long rawMaterialId,
      boolean applicable) {
    try {
      CoreTestConfigure coreTestConfigure = coreTestConfigureRepository
          .findBytestConfigureIdAndRawMaterialId(testConfigureId, rawMaterialId);
      coreTestConfigure.setApplicableTest(applicable);
      coreTestConfigureRepository.save(coreTestConfigure);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void updateApplicableTestByTestConfigureId(Long testConfigureId, boolean applicable) {
    try {
      List<CoreTestConfigure> coreTestConfigureList =
          coreTestConfigureRepository.findBytestConfigureId(testConfigureId);
      coreTestConfigureList.forEach(testConfig -> testConfig.setApplicableTest(applicable));
      coreTestConfigureRepository.saveAll(coreTestConfigureList);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Transactional
  public void getAllCoreTestConfigureByrawMaterialId(Long rawMaterialId) {
    coreTestConfigureRepository.findByrawMaterialId(rawMaterialId);
  }

  @Transactional
  public void deleteAllCoreTestConfigureByrawMaterialId(Long rawMaterialId) {
    coreTestConfigureRepository
        .deleteAll(coreTestConfigureRepository.findByrawMaterialId(rawMaterialId));
  }

  @Transactional
  public void deleteAllCoreTestConfigureByMaterialSubCategoryId(Long materialSubCategoryId) {
    coreTestConfigureRepository
        .deleteAll(coreTestConfigureRepository.findByMaterialSubCategoryId(materialSubCategoryId));
  }

  @Transactional
  public void updateCoreTestByNewMaterialSubCategoryId(Long materialSubCategoryId,
      Long materialCategoryId) {
    MaterialSubCategory materialSubCategory =
        materialSubCategoryRepository.findById(materialSubCategoryId).get();
    MaterialCategory materialCategory =
        materialCategoryRepository.findById(materialCategoryId).get();
    List<TestConfigure> testConfigures = testConfigureRepository
        .findByMaterialCategoryIdAndMaterialSubCategoryNull(materialCategoryId);
    List<CoreTestConfigure> configures = new ArrayList<>();
    if (!testConfigures.isEmpty()) {
      testConfigures.forEach(testConfigure -> {
        List<RawMaterial> rawMateriallist =
            rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
        for (RawMaterial rawMaterial : rawMateriallist) {
          CoreTestConfigure coreTestConfigure = new CoreTestConfigure();
          coreTestConfigure.setMaterialCategory(materialCategory);
          coreTestConfigure.setMaterialSubCategory(materialSubCategory);
          coreTestConfigure.setTestConfigure(testConfigure);
          if (testConfigure.getAcceptedType() != null
              && testConfigure.getAcceptedType().equals(AcceptedType.TEST)) {
            coreTestConfigure.setApplicableTest(true);
          } else {
            coreTestConfigure.setApplicableTest(false);
          }
          coreTestConfigure.setRawMaterial(rawMaterial);
          coreTestConfigure.setCoreTest(coreTestConfigureRepository
              .existsBytestConfigureIdAndMaterialCategoryIdAndCoreTestTrueAndApplicableTestTrue(
                  testConfigure.getId(), materialCategory.getId()));
          configures.add(coreTestConfigure);
        }
      });
      coreTestConfigureRepository.saveAll(configures);
    }
  }
}
