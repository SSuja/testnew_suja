package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.StatusCountResponseDto;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.IncomingSamplesCountService;
import com.tokyo.supermix.server.services.MaterialCategoryService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class IncomingSamplesCountController {
	@Autowired
	private IncomingSamplesCountService incomingSamplesCountService;
	@Autowired
	private MaterialCategoryService materialCategoryService;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;

	@GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_TOTAL_COUNT)
	public Long getIncomingSampleBySubCategory(@PathVariable String materialSubCategoryName) {
		return incomingSamplesCountService.calculateMaterialSubCategoryCount(materialSubCategoryName);
	}

	@GetMapping(value = EndpointURI.MATERIAL_CATEGORY_TOTAL_COUNT)
	public Long getIncomingSampleByMaterialCategory(@PathVariable String materialCategoryName) {
		return incomingSamplesCountService.countByTotalMaterialCategoryIncomingSample(materialCategoryName);
	}

	@GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_STATUS_TOTAL_COUNT)
	public StatusCountResponseDto getIncomingSampleStatusBySubCategory(@PathVariable String materialSubCategoryName) {
		StatusCountResponseDto statusResponseDto = new StatusCountResponseDto();
		statusResponseDto.setPassCount(
				incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 0));
		statusResponseDto.setProcessCount(
				incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 1));
		statusResponseDto.setFailCount(
				incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 2));
		statusResponseDto
				.setNewCount(incomingSamplesCountService.getMaterialSubCategoryStatusCount(materialSubCategoryName, 3));
		return statusResponseDto;
	}

	@GetMapping(value = EndpointURI.MATERIAL_CATEGORY_STATUS_TOTAL_COUNT)
	public StatusCountResponseDto getIncomingSampleStatusByMaterialCategory(@PathVariable String materialCategoryName) {
		StatusCountResponseDto statusResponseDto = new StatusCountResponseDto();
		statusResponseDto
				.setPassCount(incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 0));
		statusResponseDto
				.setProcessCount(incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 1));
		statusResponseDto
				.setFailCount(incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 2));
		statusResponseDto
				.setNewCount(incomingSamplesCountService.getMaterialCategoryStatusCount(materialCategoryName, 3));
		return statusResponseDto;
	}

	@GetMapping(value = EndpointURI.MATERIAL_SAMPLE_COUNT_BY_MATERIAL_SUB_CATEGORY)
	public ResponseEntity<Object> getincomingSampleCountByMaterialSubCategory(
			@PathVariable String materialSubCategoryName) {
		return new ResponseEntity<>(new ContentResponse<>(Constants.SAMPLE_COUNTS,
				incomingSamplesCountService.getmaterialSampleCountByMaterialSubCategory(materialSubCategoryName),
				RestApiResponseStatus.OK), HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.MATERIAL_SAMPLE_COUNT_BY_MATERIAL_CATEGORY)
	public ResponseEntity<Object> getincomingSampleCountByMaterialCategory(@PathVariable String materialCategoryName) {
		if (materialCategoryService.isNameExist(materialCategoryName)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.SAMPLE_COUNTS,
					incomingSamplesCountService.getmaterialSampleCountByMaterialCategory(
							materialCategoryService.getMaterialCategoryByName(materialCategoryName)),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_NAME,
				validationFailureStatusCodes.getMaterialCategoryAlreadyExist()), HttpStatus.BAD_REQUEST);
	}
}
