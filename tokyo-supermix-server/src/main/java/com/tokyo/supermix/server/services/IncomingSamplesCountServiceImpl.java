package com.tokyo.supermix.server.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;

@Service
public class IncomingSamplesCountServiceImpl implements IncomingSamplesCountService {
	@Autowired
	IncomingSampleRepository incomingSampleRepository;
	@Autowired
	RawMaterialRepository rawMaterialRepository;
	@Autowired
	MaterialSubCategoryRepository materialSubCategoryRepository;

	@Transactional(readOnly = true)
	public Long calculateMaterialSubCategoryCount(String materialSubCategoryName) {
		return incomingSampleRepository.calculateMaterialSubCategoryCount(materialSubCategoryName);
	}

	@Transactional(readOnly = true)
	public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName) {
		return incomingSampleRepository.countByTotalMaterialCategoryIncomingSample(materialCategoryName);
	}

	@Transactional(readOnly = true)
	public Long getMaterialSubCategoryStatusCount(String materialSubCategoryName, int status) {
		return incomingSampleRepository.getMaterialSubCategoryStatusCount(materialSubCategoryName, status);
	}

	@Transactional(readOnly = true)
	public Long getMaterialCategoryStatusCount(String materialCategoryName, int status) {
		return incomingSampleRepository.getMaterialCategoryStatusCount(materialCategoryName, status);
	}

	 @Transactional(readOnly = true)
	  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
	      String materialSubCategoryName) {
		 final LocalDateTime today = LocalDateTime.now();
		    java.sql.Date date = java.sql.Date.valueOf(today.toLocalDate()); 
	    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
	    List<RawMaterial> rawMaterials = rawMaterialRepository.findByMaterialSubCategoryId(
	        materialSubCategoryRepository.findByName(materialSubCategoryName).getId());
	    for (RawMaterial material : rawMaterials) {
	      CountMaterialDto countMaterialDto = new CountMaterialDto();
	      countMaterialDto.setMaterialName(material.getName());
	      countMaterialDto.setCount(
	          incomingSampleRepository.findByRawMaterialIdAndDate(material.getId(), date).size());
	      countMaterialDtoList.add(countMaterialDto);
	    }
	    return countMaterialDtoList;
	  }

}
