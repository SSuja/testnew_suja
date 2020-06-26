package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.TestDetailForSampleDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.dto.report.TestReportDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.IncomingSampleService;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.server.services.TestReportService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class TestReportController {
	@Autowired
	MaterialTestService materialTestService;
	@Autowired
	private TestReportService testReportService;
	@Autowired
	private IncomingSampleService incomingSampleService;

	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;
	@Autowired
	private Mapper mapper;

	@GetMapping(value = EndpointURI.MATERIAL_TEST_REPORT)
	public ResponseEntity<Object> getMaterialTestReport(@PathVariable String materialTestCode) {
		if (materialTestService.isMaterialTestExists(materialTestCode)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
					mapper.map(testReportService.getMaterialTestReport(materialTestCode), TestReportDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
				validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.MATERIAL_TEST_DETAIL_REPORT)
	public ResponseEntity<Object> getTestDetailsReportBySample(@PathVariable String icomingSampleCode,
			@PathVariable String classification) {
		if (materialTestService.findByIncomingSampleCode(icomingSampleCode).size() > 0) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_DETAIL_REPORT,
					mapper.map(testReportService.getTestDetails(icomingSampleCode, classification),
							TestDetailForSampleDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
				validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.MATERIAL_TEST_REPORT_DETAIL)
	public ResponseEntity<Object> getMaterialTestReportDetails(@PathVariable String materialTestCode) {
		if (materialTestService.isMaterialTestExists(materialTestCode)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT, mapper
					.map(testReportService.getMaterialTestDetailReport(materialTestCode), TestReportDetailDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
				validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
	}

//	@GetMapping(value = EndpointURI.SIEVE_TEST_REPORT)
//	public ResponseEntity<Object> getSieveTestReportBySieveTestCode(@PathVariable String sieveTestCode) {
//		if (sieveTestService.isSieveTestExists(sieveTestCode)) {
//			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
//					mapper.map(sieveTestReportService.getSeiveTestReport(sieveTestCode), SieveTestReportDto.class),
//					RestApiResponseStatus.OK), HttpStatus.OK);
//		}
//		return new ResponseEntity<>(new ValidationFailureResponse(Constants.SIEVE_TEST_CODE,
//				validationFailureStatusCodes.getSieveTestNotExist()), HttpStatus.BAD_REQUEST);
//	}

	@GetMapping(value = EndpointURI.CEMENT_REPORT_DETAIL)
	public ResponseEntity<Object> getAdmixtureReportDetails(@PathVariable String materialTestCode) {
		if (materialTestService.isMaterialTestExists(materialTestCode)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
					mapper.map(testReportService.getCementDetailReport(materialTestCode), TestReportDetailDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_TEST,
				validationFailureStatusCodes.getMaterialTestNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.INCOMING_SAMPLE_REPORT_DETAIL)
	public ResponseEntity<Object> getIncomingSampleDeliveryReportDetails(@PathVariable String incomingSampleCode) {
		if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
					mapper.map(testReportService.getIncomingSampleDeliveryReport(incomingSampleCode),
							IncomingSampleDeliveryReportDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE,
				validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndpointURI.INCOMING_SAMPLE_REPORT_DETAILS)
	public ResponseEntity<Object> getIncomingSampleReportDetails(@PathVariable String incomingSampleCode,
			@PathVariable String testName) {
		if (incomingSampleService.isIncomingSampleExist(incomingSampleCode)) {
			return new ResponseEntity<>(new ContentResponse<>(Constants.TEST_REPORT,
					mapper.map(testReportService.getIncomingSampleDeliveryReports(incomingSampleCode, testName),
							IncomingSampleDeliveryReportDto.class),
					RestApiResponseStatus.OK), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE,
				validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
	}
}
