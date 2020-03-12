package com.tokyo.supermix.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.MaterialTestRequestDto;
import com.tokyo.supermix.data.dto.MaterialTestResponseDto;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.MaterialTestService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MaterialTestController {

	@Autowired
	MaterialTestService materialTestService;

	@Autowired
	ValidationFailureStatusCodes validationFailureStatusCodes;

	@Autowired
	Mapper mapper;

	@PostMapping(value = EndpointURI.MATERIAL_TEST)
	public ResponseEntity<Object> createMaterialTest(@Valid @RequestBody MaterialTestRequestDto materialTestDto) {

		MaterialTest materialTest = mapper.map(materialTestDto, MaterialTest.class);
		materialTestService.saveMaterialTest(materialTest);

		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_TEST_SUCCESS),
				HttpStatus.OK);

	}

	@GetMapping(value = EndpointURI.MATERIAL_TESTS)
	public ResponseEntity<Object> getAllMaterialTests() {
		List<MaterialTestResponseDto> materialTestLists = mapper.map(materialTestService.getAllMaterialTests(),
				MaterialTestResponseDto.class);

		return new ResponseEntity<>(
				new ContentResponse<>(Constants.MATERIAL_TESTS, materialTestLists, RestApiResponseStatus.OK), null,
				HttpStatus.OK);

	}

}
