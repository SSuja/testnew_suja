package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.FinishProductRequestDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.server.services.FinishProductService;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class FinishProductController {

	@Autowired
	FinishProductService finishProductService;

	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	Mapper mapper;

	private static final Logger logger = Logger.getLogger(FinishProductController.class);

	// create finish product api
	@PostMapping(value = EndpointURI.FINISH_PRODUCT)
	public ResponseEntity<Object> createFinishProduct(@Valid @RequestBody FinishProductRequestDto finishProductDto) {
		if (finishProductService.isFinishProductExists(finishProductDto.getId())) {
			logger.debug("Already exists");

		}
		return null;

	}

	// get all finish products
	@GetMapping(value = EndpointURI.FINISH_PRODUCTS)
	public ResponseEntity<Object> getAllFinishProduct() {
		return null;

	}
}
