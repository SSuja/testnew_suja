package com.tokyo.supermix.server.services;

import java.util.List;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.CoreTestConfigureDto;
import com.tokyo.supermix.data.dto.CoreTestConfigureResponseDto;
import com.tokyo.supermix.data.dto.TestOriginDto;
import com.tokyo.supermix.data.dto.TestOriginRequestDto;
import com.tokyo.supermix.data.entities.CoreTestConfigure;

public interface CoreTestConfigureService {
	public List<CoreTestConfigure> saveCoreTestConfigure(List<CoreTestConfigure> CoreTestConfigure);

	public List<CoreTestConfigure> getAllCoreTestConfigure();

	public void createCoreTestConfigure(Long Id);

	public void createCoreTestConfigureInTestConfig(Long Mainid, Long subId, Long RawMatId, Long testId);

	public List<CoreTestConfigure> getCoreTestConfigureByTestConfigureId(Long testConfigureId);

	public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdAndCoreTestTrue(Long rawMaterialId);

	public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialId(Long rawMaterialId);

	public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestConfigureId(Long testConfigureId);

	public CoreTestConfigureResponseDto getAllCoreTestConfigureByTestId(Long testId);

	public List<TestOriginDto> getAllCoreTestConfigureByMainCategoryId(Long mainCategoryId);

	public List<TestOriginDto> getAllCoreTestConfigureByMaterialSubCategoryId(Long materialSubCategoryId);

	public List<TestOriginDto> getAllCoreTestConfigureByRawMaterialId(Long rawMaterialId);

	public void testOriginChangeStatus(List<TestOriginRequestDto> testOriginRequestDtolist);

	public List<CoreTestConfigureDto> searchRawMaterial(BooleanBuilder booleanBuilder, Long testConfigureId,
			String rawMaterialName, Long materialSubCategoryId);
	
	public List<CoreTestConfigure> updateCoreTestConfigure(List<CoreTestConfigure> CoreTestConfigure);
	
	public List<CoreTestConfigure> getCoreTestConfigureByRawMaterialIdAndApplicableTest(Long rawMaterialId);
	
	public void updateCoreTestByNewRawMaterial(Long rawMaterialId);
}
