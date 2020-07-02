package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.dto.MaterialQualityParameterResponseDto;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialQualityParameterService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MaterialQualityParameterController {
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;
	@Autowired
	private Mapper mapper;
	@Autowired
	private MaterialQualityParameterService materialQualityParameterService;
	private static final Logger logger = Logger.getLogger(MaterialQualityParameterController.class);

	@PostMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER)
	public ResponseEntity<Object> createMaterialQualityParameter(
			@RequestBody MaterialQualityParameterRequestDto materialQualityParameterRequestDto) {
		if (materialQualityParameterService.isDuplicateRowExists(
				materialQualityParameterRequestDto.getQualityParameterId(),
				materialQualityParameterRequestDto.getRawMaterialId(), materialQualityParameterRequestDto.getValue(),
				materialQualityParameterRequestDto.getUnitId())) {
			logger.debug("row is already exists: createMaterialQualityParameter(), isDuplicateRowExists: {}");
			return new ResponseEntity<>(
					new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
							validationFailureStatusCodes.getMaterialQualityParameterAlreadyExist()),
					HttpStatus.BAD_REQUEST);
		}
		materialQualityParameterService.saveMaterialQualityParameter(
				mapper.map(materialQualityParameterRequestDto, MaterialQualityParameter.class));
		return new ResponseEntity<>(
				new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_QUALITY_PARAMETER_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETERS)
	public ResponseEntity<Object> getAllMaterialQualityParameters() {
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.MATERIAL_QUALITY_PARAMETERS,
						mapper.map(materialQualityParameterService.getAllMaterialQualityParameters(),
								MaterialQualityParameterResponseDto.class),
						RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@DeleteMapping(EndpointURI.MATERIAL_QUALITY_PARAMETER_BY_ID)
	public ResponseEntity<Object> deleteMaterialQualityParameterById(@PathVariable Long id) {
		if (materialQualityParameterService.isMaterialQualityParameterIdExist(id)) {
			materialQualityParameterService.deleteMaterialQualityParameter(id);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETED_MATERIAL_QUALITY_PARAMETER),
					HttpStatus.OK);
		}
		logger.debug("No material quality Parameter record exist for given id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_ID,
				validationFailureStatusCodes.getMaterialQualityParameterNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER_BY_ID)
	public ResponseEntity<Object> getQualityParameterById(@PathVariable Long id) {
		if (materialQualityParameterService.isMaterialQualityParameterIdExist(id)) {
			return new ResponseEntity<>(
					new ContentResponse<>(Constants.MATERIAL_QUALITY_PARAMETER,
							mapper.map(materialQualityParameterService.getMaterialQualityParameterById(id),
									MaterialQualityParameterResponseDto.class),
							RestApiResponseStatus.OK),
					HttpStatus.OK);
		}
		logger.debug("No Material Quality Parameter record exist for given id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_ID,
				validationFailureStatusCodes.getMaterialQualityParameterNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER)
	public ResponseEntity<Object> updateMaterialQualityParameter(
			@RequestBody MaterialQualityParameterRequestDto materialQualityParameterRequestDto) {
		if (materialQualityParameterService
				.isMaterialQualityParameterIdExist(materialQualityParameterRequestDto.getId())) {
			if (materialQualityParameterService.isDuplicateRowExists(
					materialQualityParameterRequestDto.getQualityParameterId(),
					materialQualityParameterRequestDto.getRawMaterialId(),
					materialQualityParameterRequestDto.getValue(), materialQualityParameterRequestDto.getUnitId())) {
				logger.debug("row is already exists: updateQualityParameter(), isDuplicateRowExists: {}");
				return new ResponseEntity<>(
						new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER,
								validationFailureStatusCodes.getMaterialQualityParameterAlreadyExist()),
						HttpStatus.BAD_REQUEST);
			}
			materialQualityParameterService.saveMaterialQualityParameter(
					mapper.map(materialQualityParameterRequestDto, MaterialQualityParameter.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MATERIAL_QUALITY_PARAMETER_SUCCESS),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_ID,
				validationFailureStatusCodes.getMaterialQualityParameterNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.MATERIAL_QUALITY_PARAMETER_BY_MATERIAL)
	public ResponseEntity<Object> getQualityParameterByMaterials(@PathVariable Long rawMaterialId) {
		if (materialQualityParameterService.isMaterialIdExists(rawMaterialId)) {
			logger.debug("No Material Quality Parameter record exist for given id");
			return new ResponseEntity<>(
					new ValidationFailureResponse(Constants.MATERIAL_QUALITY_PARAMETER_ID,
							validationFailureStatusCodes.getMaterialQualityParameterNotExist()),
					HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL_QUALITY_PARAMETER,
				mapper.map(materialQualityParameterService.getMaterialQualityParameterById(rawMaterialId),
						MaterialQualityParameterResponseDto.class),
				RestApiResponseStatus.OK), HttpStatus.OK);
	}
}
