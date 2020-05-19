package com.tokyo.supermix.server.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ConcreteTestResponseDto;
import com.tokyo.supermix.data.dto.ConcreteTestResultRequestDto;
import com.tokyo.supermix.data.dto.ConcreteTestResultResponseDto;
import com.tokyo.supermix.data.entities.ConcreteTestResult;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestResultService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
public class ConcreteTestResultController {
	@Autowired
	private Mapper mapper;
	@Autowired
	private ConcreteTestResultService concreteTestResultService;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;
	private static final Logger logger = Logger.getLogger(ConcreteTestResultController.class);

	@PostMapping(value = EndpointURI.CONCRETE_TEST_RESULT)
	public ResponseEntity<Object> createConcreteTestResult(
			@Valid @RequestBody ConcreteTestResultRequestDto concreteTestResultRequestDto) {
		concreteTestResultService
				.saveConcreteTestResult(mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
		return new ResponseEntity<>(
				new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_RESULT_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.CONCRETE_TEST_RESULTS)
	public ResponseEntity<Object> getAllConcreteTestResult() {
		return new ResponseEntity<Object>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
				mapper.map(concreteTestResultService.getAllConcreteTestResults(), ConcreteTestResponseDto.class),
				RestApiResponseStatus.OK), HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_ID)
	public ResponseEntity<Object> getConcreteTestResultById(@PathVariable Long id) {
		if (concreteTestResultService.isConcreteTestResultExists(id)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST_RESULT, mapper
					.map(concreteTestResultService.getConcreteTestResultById(id), ConcreteTestResultResponseDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		logger.debug("Invalid Id");
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
				validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = EndpointURI.CONCRETE_TEST_RESULT_BY_ID)
	public ResponseEntity<Object> deleteConcreteTestResult(@PathVariable Long id) {
		if (concreteTestResultService.isConcreteTestResultExists(id)) {
			concreteTestResultService.deleteConcreteTestResult(id);
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_RESULT_DELETED),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
				validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.CONCRETE_TEST_RESULT)
	public ResponseEntity<Object> updateConcreteTestResult(
			@Valid @RequestBody ConcreteTestResultRequestDto concreteTestResultRequestDto) {
		if (concreteTestResultService.isConcreteTestResultExists(concreteTestResultRequestDto.getId())) {
			concreteTestResultService
					.saveConcreteTestResult(mapper.map(concreteTestResultRequestDto, ConcreteTestResult.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CONCRETE_TEST_RESULT_SUCCESS),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST_RESULT,
				validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.SEARCH_CONCRETE_TEST_RESULT)
	public ResponseEntity<Object> searchConcreteStrengthTest(@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size,
			@RequestParam(name = "finishProductSampleId", required = false) Long finishProductSampleId,
			@RequestParam(name = "ConcreteTestId", required = false) Long ConcreteTestId,
			@RequestParam(name = "status", required = false) Status status,
			@RequestParam(name = "result", required = false) Double result,
			@RequestParam(name = "resultMin", required = false) Double resultMin,
			@RequestParam(name = "resultMax", required = false) Double resultMax,
			@RequestParam(name = "strenghGradeRatio", required = false) Double strenghGradeRatio,
			@RequestParam(name = "strenghGradeRatioMin", required = false) Double strenghGradeRatioMin,
			@RequestParam(name = "strenghGradeRatioMax", required = false) Double strenghGradeRatioMax,
			@RequestParam(name = "slump", required = false) Double slump,
			@RequestParam(name = "slumpMin", required = false) Double slumpMin,
			@RequestParam(name = "slumpMax", required = false) Double slumpMax,
			@RequestParam(name = "slumpGradeRatio", required = false) Double slumpGradeRatio,
			@RequestParam(name = "slumpGradeRatioMin", required = false) Double slumpGradeRatioMin,
			@RequestParam(name = "slumpGradeRatioMax", required = false) Double slumpGradeRatioMax) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.CONCRETE_TEST_RESULTS,
						concreteTestResultService.searchConcreteTestResult(finishProductSampleId, ConcreteTestId,
								status, result, resultMin, resultMax, strenghGradeRatioMax, strenghGradeRatioMin,
								strenghGradeRatioMax, slump, slumpMin, slumpMax, slumpGradeRatio, slumpGradeRatioMin,
								slumpGradeRatioMax, booleanBuilder, page, size),
						RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}
}
