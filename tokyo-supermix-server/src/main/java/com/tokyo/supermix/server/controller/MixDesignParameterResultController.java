package com.tokyo.supermix.server.controller;

import javax.validation.Valid;

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
import com.tokyo.supermix.data.dto.MixDesignParameterResultRequestDto;
import com.tokyo.supermix.data.dto.MixDesignParameterResultResponseDto;
import com.tokyo.supermix.data.entities.MixDesignParameterResult;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MixDesignParameterResultService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignParameterResultController {
	@Autowired
	private Mapper mapper;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;
	@Autowired
	private MixDesignParameterResultService mixDesignParameterResultService;
	private static final Logger logger = Logger.getLogger(MixDesignParameterResultController.class);

	@GetMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_RESULTS)
	public ResponseEntity<Object> getAllMixDesignParameterResults() {
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.MIX_DESIGN_PARAMETER_RESULTS,
						mapper.map(mixDesignParameterResultService.getAllMixDesignParamResults(),
								MixDesignParameterResultResponseDto.class),
						RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@PostMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_RESULT)
	public ResponseEntity<Object> saveMixDesignParameterResult(
			@Valid @RequestBody MixDesignParameterResultRequestDto mixDesignParameterResultRequestDto) {
		mixDesignParameterResultService.saveMixDesignParameterResult(
				mapper.map(mixDesignParameterResultRequestDto, MixDesignParameterResult.class));
		return new ResponseEntity<>(
				new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIX_DESIGN_PARAMETER_RESULT_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.GET_MIX_DESIGN_PARAMETER_RESULT_BY_ID)
	public ResponseEntity<Object> getMixDesignParameterResultById(@PathVariable Long id) {
		if (mixDesignParameterResultService.isMixDesignParameterResultExists(id)) {
			logger.debug("Get Mix Design Parameter Result By Id");
			return new ResponseEntity<>(
					new ContentResponse<>(Constants.MIX_DESIGN_PARAMETER_RESULT,
							mapper.map(mixDesignParameterResultService.getMixDesignParamResultById(id),
									MixDesignParameterResultResponseDto.class),
							RestApiResponseStatus.OK),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_RESULT_ID,
				validationFailureStatusCodes.getMixDesignparameterResultNotExist()), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = EndpointURI.DELETE_MIX_DESIGN_PARAMETER_RESULT_BY_ID)
	public ResponseEntity<Object> deleteMixDesignParameterResult(@PathVariable Long id) {
		if (mixDesignParameterResultService.isMixDesignParameterResultExists(id)) {
			logger.debug("delete Mix Design Parameter Result by id");
			mixDesignParameterResultService.deleteMixDesignParameterResult(id);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIX_DESIGN_PARAMETER_RESULT_DELETED),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_RESULT_ID,
				validationFailureStatusCodes.getMixDesignparameterResultNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.MIX_DESIGN_PARAMETER_RESULT)
	public ResponseEntity<Object> updateMixDesignParameterResult(
			@Valid @RequestBody MixDesignParameterResultRequestDto mixDesignParameterResultRequestDto) {
		if (mixDesignParameterResultService
				.isMixDesignParameterResultExists(mixDesignParameterResultRequestDto.getId())) {
			mixDesignParameterResultService.saveMixDesignParameterResult(
					mapper.map(mixDesignParameterResultRequestDto, MixDesignParameterResult.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIX_DESIGN_UPDATE_PARAMETER_RESULT_SUCCESS),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_PARAMETER_RESULT_ID,
				validationFailureStatusCodes.getMixDesignparameterResultNotExist()), HttpStatus.BAD_REQUEST);
	}
}
