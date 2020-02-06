package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PlantDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ApiResponse;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailure;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationConstance;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
public class PlantController {
	@Autowired
	PlantService plantService;


	

	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	private Mapper mapper;

	private static final Logger logger = Logger.getLogger(PlantController.class);

	@PostMapping(value = EndpointURI.PLANT)
	public ResponseEntity<Object> createPlant(@RequestBody PlantDto plantDto) {
		if (plantService.isPlantAlreadyExist(plantDto.getName())) {
			logger.debug("Plant already exists: createPlant(), plantName: {}");
				 return new ResponseEntity<>(new BasicResponse<>(
			          new ValidationFailure(Constants.PLANT_NAME, validationFailureStatusCodes.getPlantAlreadyExist()),
			          RestApiResponseStatus.VALIDATION_FAILURE,ValidationConstance.PLANT_EXIST), HttpStatus.BAD_REQUEST);
		}
		Plant plant = mapper.map(plantDto, Plant.class);
		plantService.createPlant(plant);
		return new ResponseEntity<>(new ApiResponse(RestApiResponseStatus.OK), HttpStatus.OK);

	}

}
