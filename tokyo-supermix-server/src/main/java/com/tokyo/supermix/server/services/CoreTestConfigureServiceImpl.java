package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.QCoreTestConfigure;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.Origin;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
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
	public void createCoreTestConfigureInTestConfig(Long Mainid, Long subId, Long RawMatId, Long testId) {

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
	public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdAndCoreTestTrue(Long rawMaterialId) {
		return coreTestConfigureRepository.findByrawMaterialIdAndCoreTestTrue(rawMaterialId);
	}

	@Transactional
	public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialId(Long rawMaterialId) {
		return coreTestConfigureRepository.findByrawMaterialIdAndCoreTestTrue(rawMaterialId);
	}

	@Override
	public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestConfigureId(Long testConfigureId) {
		CoreTestConfigureResponseDto coreTestConfigureResponseDto = new CoreTestConfigureResponseDto();
		coreTestConfigureResponseDto.setTestConfigureId(testConfigureId);

		TestConfigure testConfigure = testConfigureRepository.getOne(testConfigureId);

		testConfigure.getMaterialCategory();

		List<CoreTestConfigure> coreTestConfigurelist = coreTestConfigureRepository
				.findByTestConfigureIdAndMaterialCategoryId(testConfigureId,
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
				CoreTestConfigureMaterialDto coreTestConfigureMaterialDto = new CoreTestConfigureMaterialDto();
				coreTestConfigureMaterialDto.setRawMaterialId(coreSub.getRawMaterial().getId());
				coreTestConfigureMaterialDto.setRawMaterialName(coreSub.getRawMaterial().getName());
				coreTestConfigureMaterialDto.setCoreTestConfigureId(coreSub.getId());
				coreTestConfigureMaterialDto.setMaterialSubCategoryId(coreSub.getMaterialSubCategory().getId());
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
		coreTestConfigureResponseDto.setMaterialCategoryName(testConfigure.getMaterialCategory().getName());
		coreTestConfigureResponseDto.setCoreTestConfigureSubCatDto(coreTestConfigureSubCatDtoList);

		return coreTestConfigureResponseDto;
	}

	@Override
	public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestId(Long testId) {
		return getAllCoreTestConfigureByTestConfigureId(testConfigureRepository.findByTestId(testId).getId());
	}

	@Override
	public List<TestOriginDto> getAllCoreTestConfigureByMainCategoryId(Long mainCategoryId) {
		List<TestOriginDto> testOriginDtoList = new ArrayList<>();
		// testconfigureBymatrialCatId
		List<TestConfigure> testConfigureList = testConfigureRepository
				.findByMaterialCategoryIdAndMaterialSubCategoryNull(mainCategoryId);

		for (TestConfigure testConfigure : testConfigureList) {
			TestOriginDto testOriginDto = new TestOriginDto();
			testOriginDto.setTestConfigureId(testConfigure.getId());
			testOriginDto.setCoreTest(
					coreTestConfigureRepository.existsBytestConfigureIdAndMaterialCategoryIdAndCoreTestTrue(
							testConfigure.getId(), mainCategoryId));
			testOriginDto.setTestOrigin(Origin.OWN);
			testOriginDto.setTestId(testConfigure.getTest().getId());
			testOriginDto.setTestName(testConfigure.getTest().getName());
			testOriginDtoList.add(testOriginDto);
		}
		return testOriginDtoList;
	}

	@Override
	public List<TestOriginDto> getAllCoreTestConfigureByMaterialSubCategoryId(Long materialSubCategoryId) {
		MaterialSubCategory materialSubCategory = materialSubCategoryRepository.getOne(materialSubCategoryId);
		List<TestOriginDto> testOriginDtoList = getAllCoreTestConfigureByMainCategoryId(
				materialSubCategory.getMaterialCategory().getId());
		testOriginDtoList.forEach(testOrigin -> {
			testOrigin.setTestOrigin(Origin.MAIN);
			testOrigin.setCoreTest(
					coreTestConfigureRepository.existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrue(
							testOrigin.getTestConfigureId(), materialSubCategoryId));
		});
		List<TestConfigure> testConfigureList = testConfigureRepository
				.findByMaterialSubCategoryIdAndRawMaterialNull(materialSubCategoryId);

		for (TestConfigure testConfigure : testConfigureList) {
			TestOriginDto testOriginDto = new TestOriginDto();
			testOriginDto.setTestConfigureId(testConfigure.getId());
			testOriginDto.setCoreTest(
					coreTestConfigureRepository.existsBytestConfigureIdAndMaterialSubCategoryIdAndCoreTestTrue(
							testConfigure.getId(), materialSubCategoryId));
			testOriginDto.setTestOrigin(Origin.OWN);
			testOriginDto.setTestId(testConfigure.getTest().getId());
			testOriginDto.setTestName(testConfigure.getTest().getName());
			testOriginDtoList.add(testOriginDto);
		}

		return testOriginDtoList;
	}

	@Override
	public List<TestOriginDto> getAllCoreTestConfigureByRawMaterialId(Long rawMaterialId) {

		RawMaterial rawMaterial = rawMaterialRepository.getOne(rawMaterialId);

		List<TestOriginDto> testOriginDtoList = getAllCoreTestConfigureByMaterialSubCategoryId(
				rawMaterial.getMaterialSubCategory().getId());
		testOriginDtoList.forEach(testOrigin -> {
			if (testOrigin.getTestOrigin().equals(Origin.OWN)) {
				testOrigin.setTestOrigin(Origin.SUB);
			}
			testOrigin.setCoreTest(coreTestConfigureRepository.existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrue(
					testOrigin.getTestConfigureId(), rawMaterialId));
		});

		List<TestConfigure> testConfigureList = testConfigureRepository.findByRawMaterialId(rawMaterial.getId());
		for (TestConfigure testConfigure : testConfigureList) {
			TestOriginDto testOriginDto = new TestOriginDto();
			testOriginDto.setTestConfigureId(testConfigure.getId());
			testOriginDto
					.setCoreTest(coreTestConfigureRepository.existsBytestConfigureIdAndRawMaterialIdAndCoreTestTrue(
							testConfigure.getId(), rawMaterial.getId()));
			testOriginDto.setTestOrigin(Origin.OWN);
			testOriginDto.setTestId(testConfigure.getTest().getId());
			testOriginDto.setTestName(testConfigure.getTest().getName());
			testOriginDtoList.add(testOriginDto);
		}

		return testOriginDtoList;
	}

	public void testOriginChangeStatus(List<TestOriginRequestDto> testOriginRequestDtolist) {

		for (TestOriginRequestDto testOriginRequestDto : testOriginRequestDtolist) {

			if (testOriginRequestDto.getMaterialCategoryId() != null) {
				coreTestConfigureRepository
						.findByTestConfigureIdAndMaterialCategoryId(testOriginRequestDto.getTestConfigureId(),
								testOriginRequestDto.getMaterialCategoryId())
						.stream().forEach(coreTest -> {
							coreTest.setCoreTest(testOriginRequestDto.isCoreTest());
							coreTestConfigureRepository.save(coreTest);
						});
			}
			if (testOriginRequestDto.getMaterialSubCategoryId() != null) {

				coreTestConfigureRepository
						.findByTestConfigureIdAndMaterialSubCategoryId(testOriginRequestDto.getTestConfigureId(),
								testOriginRequestDto.getMaterialSubCategoryId())
						.stream().forEach(coreTest -> {
							coreTest.setCoreTest(testOriginRequestDto.isCoreTest());
							coreTestConfigureRepository.save(coreTest);
						});
			}
			if (testOriginRequestDto.getRawMaterialId() != null) {

				coreTestConfigureRepository
						.findByTestConfigureIdAndRawMaterialId(testOriginRequestDto.getTestConfigureId(),
								testOriginRequestDto.getRawMaterialId())
						.stream().forEach(coreTest -> {
							coreTest.setCoreTest(testOriginRequestDto.isCoreTest());
							coreTestConfigureRepository.save(coreTest);
						});
			}
		}
	}

	public List<CoreTestConfigureDto> searchRawMaterial(BooleanBuilder booleanBuilder, Long testConfigureId,
			String rawMaterialName, Long materialSubCategoryId) {
		List<CoreTestConfigure> coretestlist = new ArrayList<>();
		if (testConfigureId != null) {
			booleanBuilder.and(QCoreTestConfigure.coreTestConfigure.testConfigure.id.eq(testConfigureId));
		}
		if (materialSubCategoryId != null) {
			booleanBuilder.and(QCoreTestConfigure.coreTestConfigure.materialSubCategory.id.eq(materialSubCategoryId));
		}
		if (rawMaterialName != null && !rawMaterialName.isEmpty()) {
			booleanBuilder
					.and(QCoreTestConfigure.coreTestConfigure.rawMaterial.name.containsIgnoreCase(rawMaterialName));
		}
		coreTestConfigureRepository.findAll(booleanBuilder).forEach(coreTest -> coretestlist.add(coreTest));
		return mapper.map(coretestlist, CoreTestConfigureDto.class);
	}

	@Override
	public List<CoreTestConfigure> updateCoreTestConfigure(List<CoreTestConfigure> CoreTestConfigurelist) {
		List<CoreTestConfigure> CoreTestConfigurelistnew=new ArrayList<>();
		for(CoreTestConfigure coreTestConfigure:CoreTestConfigurelist) {
			CoreTestConfigure coreTestConfigure2=coreTestConfigureRepository.getOne(coreTestConfigure.getId());
			coreTestConfigure2.setCoreTest(coreTestConfigure.isCoreTest());
			CoreTestConfigurelistnew.add(coreTestConfigure2);
		}
		return coreTestConfigureRepository.saveAll(CoreTestConfigurelistnew);
	}
}