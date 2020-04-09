package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.StatusCountResponseDto;
import com.tokyo.supermix.server.services.IncomingSamplesCountService;

@RestController
@CrossOrigin
public class IncomingSamplesCountController {
  @Autowired
  private IncomingSamplesCountService incomingSamplesCountService;

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_TOTAL_COUNT)
  public Long getIncomingSampleBySubCategory(@PathVariable String materialSubCategoryName) {
    return incomingSamplesCountService.calculateMaterialSubCategoryCount(materialSubCategoryName);
  }

  @GetMapping(value = EndpointURI.MATERIAL_CATEGORY_TOTAL_COUNT)
  public Long getDateAndRawMaterialName(@PathVariable String materialSubCategoryName) {
    return incomingSamplesCountService
        .countByTotalMaterialCategoryIncomingSample(materialSubCategoryName);
  }

  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_STATUS_TOTAL_COUNT)
  public StatusCountResponseDto getIncomingSampleStatusBySubCategory(
      @PathVariable String materialSubCategoryName) {
    StatusCountResponseDto statusResponseDto = new StatusCountResponseDto();
    statusResponseDto.setPassCount(
        incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 0));
    statusResponseDto.setProcessCount(
        incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 1));
    statusResponseDto.setFailCount(
        incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 2));
    statusResponseDto.setNewCount(
        incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 3));
    return statusResponseDto;
  }

  @GetMapping(value = EndpointURI.MATERIAL_CATEGORY_STATUS_TOTAL_COUNT)
  public StatusCountResponseDto getIncomingSampleStatusByMaterialCategory(
      @PathVariable String materialCategoryName) {
    StatusCountResponseDto statusResponseDto = new StatusCountResponseDto();
    statusResponseDto.setPassCount(
        incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 0));
    statusResponseDto.setProcessCount(
        incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 1));
    statusResponseDto.setFailCount(
        incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 2));
    statusResponseDto.setNewCount(
        incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 3));
    return statusResponseDto;
  }
}
