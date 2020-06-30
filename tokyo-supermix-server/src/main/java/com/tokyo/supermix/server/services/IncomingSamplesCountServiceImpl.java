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
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class IncomingSamplesCountServiceImpl implements IncomingSamplesCountService {
  @Autowired
  IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  
  private LocalDateTime today = LocalDateTime.now();
  java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());

  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialCategory(Long materialCategoryId, UserPrincipal currentUser) {
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<MaterialSubCategory> materialSubCategories =
        materialSubCategoryRepository.findByMaterialCategoryId(materialCategoryId);
    for (MaterialSubCategory materialSubCategory : materialSubCategories) {
      List<RawMaterial> rawMaterials =
          rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategory.getId());
      for (RawMaterial rawMaterial : rawMaterials) {
        Status status = null;
        countMaterialDtoList.add(setFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status, currentUser));
      }
    }
    return countMaterialDtoList;
  }

  @Transactional(readOnly = true)
  public List<CountMaterialDto> getmaterialSampleCountByMaterialSubCategory(
      Long materialSubCategoryId, UserPrincipal currentUser) {
    List<CountMaterialDto> countMaterialDtoList = new ArrayList<CountMaterialDto>();
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    for (RawMaterial rawMaterial : rawMaterialList) {
      Status status = null;
      countMaterialDtoList.add(setFieldsStatusCountMaterialDto(rawMaterial, sqlDate, status, currentUser));
    }
    return countMaterialDtoList;
  }

  private CountMaterialDto setFieldsStatusCountMaterialDto(RawMaterial rawMaterial, Date date,
      Status status,UserPrincipal currentUser) {
    CountMaterialDto countMaterialDto = new CountMaterialDto();
    List<String> plantCodes = currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS);
    countMaterialDto.setMaterialName(rawMaterial.getName());
    countMaterialDto.setTotal(
        incomingSampleRepository.findByRawMaterialIdAndDateAndPlantCodeIn(rawMaterial.getId(), date, plantCodes).size());
    countMaterialDto.setNewCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCodeIn(Status.NEW, rawMaterial.getId(), date, plantCodes).size());
    countMaterialDto.setPassCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCodeIn(Status.PASS, rawMaterial.getId(), date, plantCodes).size());
    countMaterialDto.setFailCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCodeIn(Status.FAIL, rawMaterial.getId(), date, plantCodes).size());
    countMaterialDto.setProcessCount(incomingSampleRepository
        .findByStatusAndRawMaterialIdAndDateAndPlantCodeIn(Status.PROCESS, rawMaterial.getId(), date, plantCodes).size());
    return countMaterialDto;
  }

  @Transactional(readOnly = true)
  public List<StatusCountResponseDto> getCountByMaterialSubCategory(Long materialSubCategoryId, UserPrincipal currentUser) {
    List<StatusCountResponseDto> statusCountResponseDtoList =
        new ArrayList<StatusCountResponseDto>();
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategoryId);
    Status status = null;
    statusCountResponseDtoList.add(setFieldsStatusMaterialSubCategory(
        rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status, currentUser));
    return statusCountResponseDtoList;
  }

  private StatusCountResponseDto setFieldsStatusMaterialSubCategory(Long materialSubCategoryId,
      Date sqlDate, Status status, UserPrincipal currentUser) {
    List<String> plantCodes = currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS);
    StatusCountResponseDto statusCountResponseDto = new StatusCountResponseDto();
    statusCountResponseDto.setTotal(incomingSampleRepository
        .findByRawMaterialMaterialSubCategoryIdAndDateAndPlantCodeIn(materialSubCategoryId, sqlDate, plantCodes).size());
    statusCountResponseDto.setNewCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCodeIn(
            materialSubCategoryId, sqlDate, Status.NEW, plantCodes).size());
    statusCountResponseDto.setPassCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCodeIn(
            materialSubCategoryId, sqlDate, Status.PASS, plantCodes).size());
    statusCountResponseDto.setFailCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCodeIn(
            materialSubCategoryId, sqlDate, Status.FAIL, plantCodes).size());
    statusCountResponseDto.setProcessCount(
        incomingSampleRepository.findByRawMaterialMaterialSubCategoryIdAndDateAndStatusAndPlantCodeIn(
            materialSubCategoryId, sqlDate, Status.PROCESS, plantCodes).size());
    return statusCountResponseDto;

  }

  @Override
  public List<StatusCountResponseDto> getCountByMaterialCategory(Long materialCategoryId, UserPrincipal currentUser) {
    List<StatusCountResponseDto> statusCountResponseDtoList =
        new ArrayList<StatusCountResponseDto>();
    List<MaterialSubCategory> materialSubCategories =
        materialSubCategoryRepository.findByMaterialCategoryId(materialCategoryId);
    List<RawMaterial> rawMaterialList =
        rawMaterialRepository.findByMaterialSubCategoryId(materialSubCategories.get(0).getId());
    Status status = null;
    statusCountResponseDtoList.add(setFieldsStatusMaterialSubCategory(
        rawMaterialList.get(0).getMaterialSubCategory().getId(), sqlDate, status, currentUser));
    return statusCountResponseDtoList;
  }

}
