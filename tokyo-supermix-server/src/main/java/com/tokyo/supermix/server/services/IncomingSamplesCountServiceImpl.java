package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.entities.MaterialCategory;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialCategoryRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;

@Service
public class IncomingSamplesCountServiceImpl implements IncomingSamplesCountService {
  @Autowired
  IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;

  @Transactional(readOnly = true)
  public Long calculateMaterialSubCategoryCount(String materialSubCategoryName) {
    return incomingSampleRepository.calculateMaterialSubCategoryCount(materialSubCategoryName);
  }

  @Transactional(readOnly = true)
  public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName) {
    return incomingSampleRepository
        .countByTotalMaterialCategoryIncomingSample(materialCategoryName);
  }

  @Transactional(readOnly = true)
  public Long getMaterialSubCategoryStatusCount(String materialSubCategoryName, int status) {
    return incomingSampleRepository.getMaterialSubCategoryStatusCount(materialSubCategoryName,
        status);
  }

  @Transactional(readOnly = true)
  public Long getMaterialCategoryStatusCount(String materialCategoryName, int status) {
    return incomingSampleRepository.getMaterialCategoryStatusCount(materialCategoryName, status);
  }
  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(
      MaterialCategory materialCategory) {
    final LocalDateTime today = LocalDateTime.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate()); 
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<MaterialSubCategory> materialSubCategories =
        materialSubCategoryRepository.findByMaterialCategoryId(
            materialCategory.getId());
    for (MaterialSubCategory materialSubCategory : materialSubCategories) {
      List<RawMaterial> rawMaterials =
          rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategory.getId());
      for (RawMaterial material : rawMaterials) {
        countMaterialDtoList.add(setFieldsCountMaterialDto(material,sqlDate));
      }
    }
    return countMaterialDtoList;
  }
  private CountMaterialDto setFieldsCountMaterialDto(RawMaterial material,Date date){
    CountMaterialDto countMaterialDto = new CountMaterialDto();
    countMaterialDto.setMaterialName(material.getName());
    countMaterialDto.setCount(
        incomingSampleRepository.findByRawMaterialIdAndDate(material.getId(),date).size());
    return countMaterialDto;
  }
}
