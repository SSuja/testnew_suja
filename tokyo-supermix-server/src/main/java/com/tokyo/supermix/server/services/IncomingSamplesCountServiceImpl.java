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
import com.tokyo.supermix.util.Constants;

@Service
public class IncomingSamplesCountServiceImpl implements IncomingSamplesCountService {
  @Autowired
  IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
   
  private LocalDateTime today = LocalDateTime.now();
  java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());

  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId,String plantCode) {
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<MaterialSubCategory> materialSubCategories =
        materialSubCategoryRepository.findByMaterialCategoryId(materialCategoryId);
    for (MaterialSubCategory materialSubCategory : materialSubCategories) {
      List<RawMaterial> rawMaterials =
          rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategory.getId());
      for (RawMaterial rawMaterial : rawMaterials) {
        Status status = null;
        if(plantCode.equalsIgnoreCase(Constants.ADMIN)) {
          countMaterialDtoList.add(setAdminFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status));     
        }else {
        countMaterialDtoList.add(setFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status, plantCode));
      }
      }
    }
    return countMaterialDtoList;
  }

  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId, String plantCode) {
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    for (RawMaterial rawMaterial : rawMaterialList) {
      Status status = null;
      if(plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        countMaterialDtoList.add(setAdminFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status));
      }else {
      countMaterialDtoList.add(setFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status, plantCode));
    }
    }
    return countMaterialDtoList;
  }

  private CountMaterialDto setFieldsStatusCountMaterialDto(RawMaterial rawMaterial, Date date,
      Status status, String plantCode) {
    CountMaterialDto countMaterialDto = new CountMaterialDto();
    countMaterialDto.setMaterialName(rawMaterial.getName());
    countMaterialDto.setTotal(
        incomingSampleRepository.findByRawMaterialIdAndDateAndPlantCode(rawMaterial.getId(), date, plantCode).size());
    countMaterialDto.setNewCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCode(Status.NEW, rawMaterial.getId(), date, plantCode).size());
    countMaterialDto.setPassCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCode(Status.PASS, rawMaterial.getId(), date, plantCode).size());
    countMaterialDto.setFailCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCode(Status.FAIL, rawMaterial.getId(), date, plantCode).size());
    countMaterialDto.setProcessCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCode(Status.PROCESS, rawMaterial.getId(), date, plantCode).size());
    return countMaterialDto;
  }
  private CountMaterialDto setAdminFieldsStatusCountMaterialDto(RawMaterial rawMaterial, Date date,
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
  public List<StatusCountResponseDto> getCountByMaterialSubCategory(Long materialSubCategoryId, String plantCode) {
    List<StatusCountResponseDto> statusCountResponseDtoList =
        new ArrayList<StatusCountResponseDto>();
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    Status status = null;
    if(plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      statusCountResponseDtoList.add(setAdminFieldsStatusMaterialSubCategory(
          rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status));
       
    }else {
    statusCountResponseDtoList.add(setFieldsStatusMaterialSubCategory(
        rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status, plantCode));
    }
    return statusCountResponseDtoList;
  }

  private StatusCountResponseDto setFieldsStatusMaterialSubCategory(Long materialSubCategoryId,
      Date sqlDate, Status status, String plantCode) {
    StatusCountResponseDto statusCountResponseDto = new StatusCountResponseDto();
    statusCountResponseDto.setTotal(incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryIdAndDateAndPlantCode(materialSubCategoryId, sqlDate, plantCode).size());
    statusCountResponseDto.setNewCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCode(
            materialSubCategoryId, sqlDate, Status.NEW, plantCode).size());
    statusCountResponseDto.setPassCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCode(
            materialSubCategoryId, sqlDate, Status.PASS, plantCode).size());
    statusCountResponseDto.setFailCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCode(
            materialSubCategoryId, sqlDate, Status.FAIL, plantCode).size());
    statusCountResponseDto.setProcessCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCode(
            materialSubCategoryId, sqlDate, Status.PROCESS, plantCode).size());
    return statusCountResponseDto;

  }
  
  private StatusCountResponseDto setAdminFieldsStatusMaterialSubCategory(Long materialSubCategoryId,
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

  @Override
  public List<StatusCountResponseDto> getCountByMaterialCategory(Long materialCategoryId,String plantCode) {
    List<StatusCountResponseDto> statusCountResponseDtoList =
        new ArrayList<StatusCountResponseDto>();
    List<MaterialSubCategory> materialSubCategories =
        materialSubCategoryRepository.findByMaterialCategoryId(materialCategoryId);
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategories.get(0).getId());
    Status status = null;
    if(plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      statusCountResponseDtoList.add(setAdminFieldsStatusMaterialSubCategory(
          rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status));
       
    }else {
    statusCountResponseDtoList.add(setFieldsStatusMaterialSubCategory(
        rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status, plantCode));
    }
    return statusCountResponseDtoList;
  }

}
