package com.tokyo.supermix.server.controller;

import javax.validation.Valid;

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
import com.tokyo.supermix.data.dto.MacAddressDto;
import com.tokyo.supermix.data.entities.MacAddress;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MacAddressService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class MacAddressController {
	@Autowired
	MacAddressService macAddressService;
	@Autowired
	  ValidationFailureStatusCodes validationFailureStatusCodes;
	@Autowired
	Mapper mapper;

	@PostMapping(value = EndpointURI.MAC_ADDRESS)
	public ResponseEntity<Object> createUnit(@Valid @RequestBody MacAddressDto macAddressDto) {
	if (macAddressService.isMacAddressExist(macAddressDto.getMacAddress())) {					
		return new ResponseEntity<>(
					new ValidationFailureResponse(Constants.MAC_ADDRESS, validationFailureStatusCodes.getMacAddressAlreadyExist()),
					HttpStatus.BAD_REQUEST);
		}
		macAddressService.saveMacAddress(mapper.map(macAddressDto, MacAddress.class));
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MAC_ADDRESS_SUCCESS),
				HttpStatus.OK);
	}
	
	@GetMapping(value = EndpointURI.MAC_ADDRESSES)
	  public ResponseEntity<Object> getAllMacAddress() {
	    return new ResponseEntity<>(new ContentResponse<>(Constants.MAC_ADDRESS,
	        mapper.map(macAddressService.getAllMacAddress(), MacAddressDto.class), RestApiResponseStatus.OK), null,
	        HttpStatus.OK);
	  }

	  @DeleteMapping(value = EndpointURI.MAC_ADDRESS_BY_ID)
	  public ResponseEntity<Object> deleteMacAddress(@PathVariable Long id) {
	    if (macAddressService.isMacAddressExist(id)) {
	    	macAddressService.deleteMacAddress(id);
	      return new ResponseEntity<>(
	          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MAC_ADDRESS_DELETED), HttpStatus.OK);
	    }
	     return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAC_ADDRESS,
	        validationFailureStatusCodes.getMacAddressNotExist()), HttpStatus.BAD_REQUEST);
	  }

	  @GetMapping(value = EndpointURI.MAC_ADDRESS_BY_ID)
	  public ResponseEntity<Object> getUnitById(@PathVariable Long id) {
	    if (macAddressService.isMacAddressExist(id)) {
	  	      return new ResponseEntity<>(
	          new ContentResponse<>(Constants.MAC_ADDRESS,
	              mapper.map(macAddressService.getMacAddressById(id), MacAddressDto.class), RestApiResponseStatus.OK),
	          HttpStatus.OK);
	    }
	    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAC_ADDRESS,
	        validationFailureStatusCodes.getMacAddressNotExist()), HttpStatus.BAD_REQUEST);
	  }

	  @PutMapping(value = EndpointURI.MAC_ADDRESS)
	  public ResponseEntity<Object> updateMacAddress(@Valid @RequestBody MacAddressDto macAddressDto) {
	    if (macAddressService.isMacAddressExist(macAddressDto.getId())) {
	      if (macAddressService.isMacAddressExist(macAddressDto.getMacAddress())) {
	        return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAC_ADDRESS,
	            validationFailureStatusCodes.getMacAddressAlreadyExist()), HttpStatus.BAD_REQUEST);
	      }
	      macAddressService.saveMacAddress(mapper.map(macAddressDto, MacAddress.class));
	
	      return new ResponseEntity<>(
	          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MAC_ADDRESS_UPDATED_SUCCESS),
	          HttpStatus.OK);
	    }
	    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MAC_ADDRESS,
	        validationFailureStatusCodes.getMacAddressNotExist()), HttpStatus.BAD_REQUEST);
	  }

}
