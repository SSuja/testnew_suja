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
import com.tokyo.supermix.data.dto.FinishProductRequestDto;
import com.tokyo.supermix.data.dto.FinishProductResponseDto;
import com.tokyo.supermix.data.entities.FinishProduct;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinishProductService;
import com.tokyo.supermix.util.Constants;
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
		if (finishProductService.isPourIdExists(finishProductDto.getPourId())) {
			logger.debug("Pour already exists");
			return new ResponseEntity<>(
					new ValidationFailureResponse(Constants.POUR, validationFailureStatusCodes.getPourAlreadyExist()),
					HttpStatus.BAD_REQUEST);
		}
		FinishProduct finishProduct = mapper.map(finishProductDto, FinishProduct.class);
		finishProductService.saveFinishProduct(finishProduct);
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_FINISH_PRODUCT_SUCCESS),
				HttpStatus.OK);
	}

	// get all finish products
	@GetMapping(value = EndpointURI.FINISH_PRODUCTS)
	public ResponseEntity<Object> getAllFinishProduct() {
		List<FinishProductResponseDto> finishProductList = mapper.map(finishProductService.getAllFinishProducts(),
				FinishProductResponseDto.class);
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.FINISH_PRODUCTS, finishProductList, RestApiResponseStatus.OK), null,
				HttpStatus.OK);
	}

	// get finish product by id- APIs
	@GetMapping(value = EndpointURI.FINISH_PRODUCT_BY_ID)
	public ResponseEntity<Object> getFinishProductById(@PathVariable Long id) {
		if (finishProductService.isFinishProductExists(id)) {
			logger.debug("Id Exists");
			return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT,
					mapper.map(finishProductService.getById(id), FinishProductResponseDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT,
				validationFailureStatusCodes.getFinishProductNotExist()), HttpStatus.BAD_REQUEST);
	}

	// delete finish product by id
	@DeleteMapping(value = EndpointURI.FINISH_PRODUCT_BY_ID)
	public ResponseEntity<Object> deleteFinishProduct(@PathVariable Long id) {
		if (finishProductService.isFinishProductExists(id)) {
			finishProductService.deleteFinishProduct(id);
			logger.debug("Finish Product deleted");
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.FINISH_PRODUCT_DELETED),
					HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT,
				validationFailureStatusCodes.getFinishProductNotExist()), HttpStatus.BAD_REQUEST);
	}

	// update finish product
	@PutMapping(value = EndpointURI.FINISH_PRODUCT)
	public ResponseEntity<Object> updateFinishProduct(@Valid @RequestBody FinishProductRequestDto finishProductDto) {
		if (finishProductService.isFinishProductExists(finishProductDto.getId())) {
			logger.debug("Id exists");

			if (finishProductService.isUpdatedPourExist(finishProductDto.getId(), finishProductDto.getPourId())) {
				return new ResponseEntity<>(new ValidationFailureResponse(Constants.POUR,
						validationFailureStatusCodes.getPourAlreadyExist()), HttpStatus.BAD_REQUEST);
			}
			FinishProduct finishProduct = mapper.map(finishProductDto, FinishProduct.class);
			finishProductService.saveFinishProduct(finishProduct);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.FINISH_PRODUCT_UPDATED_SUCCESS),
					HttpStatus.OK);
		}
		logger.debug("Id not found");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.FINISH_PRODUCT,
				validationFailureStatusCodes.getFinishProductNotExist()), HttpStatus.BAD_REQUEST);

	}
}
