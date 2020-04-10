package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;

public interface IncomingSamplesCountService {
	public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName);

	public Long calculateMaterialSubCategoryCount(String materialSubCategoryName);

	public Long getMaterialSubCategoryStatusCount(String materialSubCategoryName, int status);

	public Long getMaterialCategoryStatusCount(String materialCategoryName, int status);

	public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(MaterialSubCategory materialSubCategory);

	public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(MaterialCategory materialCategory);
}
