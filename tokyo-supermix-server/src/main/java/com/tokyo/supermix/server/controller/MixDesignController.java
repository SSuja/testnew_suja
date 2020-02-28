package com.tokyo.supermix.server.controller;

import java.util.List;

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
import com.tokyo.supermix.data.dto.MixDesignRequestDto;
import com.tokyo.supermix.data.dto.MixDesignResponseDto;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MixDesignService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MixDesignController {

	@Autowired
	MixDesignService mixDesignService;
	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;
	@Autowired
	private Mapper mapper;
	private static final Logger logger = Logger.getLogger(MixDesignController.class);

	@PostMapping(value = EndpointURI.MIXDESIGN)
	public ResponseEntity<Object> saveMixDesign(@Valid @RequestBody MixDesignRequestDto mixDesignRequestDto) {
		mixDesignService.saveMixDesign(mapper.map(mixDesignRequestDto, MixDesign.class));
		return new ResponseEntity<Object>(
				new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIXDESIGN_SUCCESS), HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.MIXDESIGNS)
	public ResponseEntity<Object> getAllMixDesigns() {
		List<MixDesign> MixDesignList = mixDesignService.getAllMixDesigns();
		return new ResponseEntity<>(new ContentResponse<>(Constants.MIXDESIGNS,
				mapper.map(MixDesignList, MixDesignResponseDto.class), RestApiResponseStatus.OK), null, HttpStatus.OK);
	}

	@DeleteMapping(value = EndpointURI.DELETE_MIXDESIGN_BY_ID)
	public ResponseEntity<Object> deleteMixDesign(@PathVariable String code) {
		if (mixDesignService.isMixDesignExist(code)) {
			mixDesignService.deleteMixDesign(code);
			return new ResponseEntity<Object>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIXDESIGN_DELETED), HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIXDESIGN_DELETED,
				validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.GET_MIXDESIGN_BY_ID)
	public ResponseEntity<Object> getMixDesignById(@PathVariable String code) {
		if (mixDesignService.isMixDesignExist(code)) {
			logger.debug("Get mix design by id ");
			return new ResponseEntity<>(new ContentResponse<>(Constants.MIXDESIGN,
					mapper.map(mixDesignService.getMixDesignById(code), MixDesignResponseDto.class), RestApiResponseStatus.OK),
					HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIXDESIGN_ID,
				validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.MIXDESIGN)
	public ResponseEntity<Object> updateMixDesign(@Valid @RequestBody MixDesignRequestDto mixDesignRequestDto) {
		if (mixDesignService.isMixDesignExist(mixDesignRequestDto.getCode())) {
			if (mixDesignService.isUpdatedMixDesignCodeExist(mixDesignRequestDto.getCode())) {
				return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIXDESIGN,
						validationFailureStatusCodes.getMixDesignAlreadyExist()), HttpStatus.BAD_REQUEST);
			}

		}
		mixDesignService.saveMixDesign(mapper.map(mixDesignRequestDto, MixDesign.class));
		return new ResponseEntity<Object>(
				new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MIXDESIGN_SUCCESS), HttpStatus.OK);
	}

}
