package com.tokyo.supermix.server.services;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.CountMaterialDto;
import com.tokyo.supermix.data.dto.StatusCountResponseDto;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
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
  public Long countByTotalMaterialCategoryIncomingSample(String materialCategoryName) {
    return incomingSampleRepository
        .countByTotalMaterialCategoryIncomingSample(materialCategoryName);
  }

  @Transactional(readOnly = true)
  public Long getMaterialCategoryStatusCount(String materialCategoryName, int status) {
    return incomingSampleRepository.getMaterialCategoryStatusCount(materialCategoryName, status);
  }

  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId) {
    final LocalDateTime today = LocalDateTime.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<MaterialSubCategory> materialSubCategories =
        materialSubCategoryRepository.findByMaterialCategoryId(materialCategoryId);
    for (MaterialSubCategory materialSubCategory : materialSubCategories) {
      List<RawMaterial> rawMaterials =
          rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategory.getId());
      for (RawMaterial rawMaterial : rawMaterials) {
        Status status = null;
        countMaterialDtoList.add(setFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status));
      }
    }
    return countMaterialDtoList;
  }

  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId) {
    final LocalDateTime today = LocalDateTime.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    for (RawMaterial rawMaterial : rawMaterialList) {
      Status status = null;
      countMaterialDtoList.add(setFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status));
    }
    return countMaterialDtoList;
  }

  private CountMaterialDto setFieldsStatusCountMaterialDto(RawMaterial rawMaterial, Date date,
      Status status) {
    CountMaterialDto countMaterialDto = new CountMaterialDto();
    countMaterialDto.setMaterialName(rawMaterial.getName());
    countMaterialDto.setTotal(
        incomingSampleRepository.findByRawMaterialIdAndDate(rawMaterial.getId(), date).size());
    countMaterialDto.setNewCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDate(Status.NEW, rawMaterial.getId(), date).size());
    countMaterialDto.setPassCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDate(Status.PASS, rawMaterial.getId(), date).size());
    countMaterialDto.setFailCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDate(Status.FAIL, rawMaterial.getId(), date).size());
    countMaterialDto.setProcessCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDate(Status.PROCESS, rawMaterial.getId(), date).size());
    return countMaterialDto;
  }

  @Transactional(readOnly = true)
  public List<StatusCountResponseDto> getCountByMaterialSubCategory(Long materialSubCategoryId) {
    final LocalDateTime today = LocalDateTime.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());
    List<StatusCountResponseDto> statusCountResponseDtoList =
        new ArrayList<StatusCountResponseDto>();
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    Status status = null;
    statusCountResponseDtoList.add(setFieldsStatusMaterialSubCategory(
        rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status));
    return statusCountResponseDtoList;
  }

  private StatusCountResponseDto setFieldsStatusMaterialSubCategory(Long materialSubCategoryId,
      Date sqlDate, Status status) {
    StatusCountResponseDto statusCountResponseDto = new StatusCountResponseDto();
    statusCountResponseDto.setTotal(incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryIdAndDate(materialSubCategoryId, sqlDate).size());
    statusCountResponseDto.setNewCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(
            materialSubCategoryId, sqlDate, Status.NEW).size());
    statusCountResponseDto.setPassCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(
            materialSubCategoryId, sqlDate, Status.PASS).size());
    statusCountResponseDto.setFailCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(
            materialSubCategoryId, sqlDate, Status.FAIL).size());
    statusCountResponseDto.setProcessCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatus(
            materialSubCategoryId, sqlDate, Status.PROCESS).size());
    return statusCountResponseDto;

  }

}
