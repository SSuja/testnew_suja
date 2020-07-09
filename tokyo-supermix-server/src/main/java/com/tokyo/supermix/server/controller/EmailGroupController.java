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
import com.tokyo.supermix.data.dto.EmailGroupDto;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailGroupService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class EmailGroupController {
	@Autowired
	private Mapper mapper;
	@Autowired
	private EmailGroupService emailGroupService;
	@Autowired
	private ValidationFailureStatusCodes validationFailureStatusCodes;

	@GetMapping(value = EndpointURI.EMAIL_GROUPS)
	public ResponseEntity<Object> getAllEmailGroups() {
		return new ResponseEntity<>(
				new ContentResponse<>(Constants.EMAIL_GROUPS,
						mapper.map(emailGroupService.getAllEmailGroups(), EmailGroup.class), RestApiResponseStatus.OK),
				null, HttpStatus.OK);
	}

	@GetMapping(value = EndpointURI.EMAIL_GROUP_BY_SHEDULE)
	public ResponseEntity<Object> getAllEmailGroupsBySchedule(@PathVariable Boolean schedule) {
		return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_GROUPS,
				mapper.map(emailGroupService.getAllEmailGroupsBySchedule(schedule), EmailGroup.class),
				RestApiResponseStatus.OK), null, HttpStatus.OK);
	}

	@PostMapping(value = EndpointURI.EMAIL_GROUP)
	public ResponseEntity<Object> createGroup(@Valid @RequestBody EmailGroupDto emailGroupDto) {
		if (emailGroupService.isEmailGroupNameExist(emailGroupDto.getEmailNotifications())) {
			return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_GROUP,
					validationFailureStatusCodes.getEmailRecipientAlreadyExist()), HttpStatus.BAD_REQUEST);
		}
		emailGroupService.saveEmailGroup(mapper.map(emailGroupDto, EmailGroup.class));

		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMAIL_GROUP_SUCCESS),
				HttpStatus.OK);
	}

	@DeleteMapping(value = EndpointURI.EMAIL_GROUP_BY_ID)
	public ResponseEntity<Object> deleteEmailGroup(@PathVariable Long id) {
		if (emailGroupService.isEmailGroupExist(id)) {
			emailGroupService.deleteEmailGroup(id);
			;
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.EMAIL_GROUP_DELETED),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_GROUP,
				validationFailureStatusCodes.getEmailGroupNotExist()), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = EndpointURI.EMAIL_GROUP)
	public ResponseEntity<Object> updateEmailGroup(@Valid @RequestBody EmailGroupDto emailGroupDto) {
		if (emailGroupService.isEmailGroupExist(emailGroupDto.getId())) {
			if (emailGroupService.isEmailGroupNameExist(emailGroupDto.getEmailNotifications())) {
				return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_GROUP,
						validationFailureStatusCodes.getEmailRecipientAlreadyExist()), HttpStatus.BAD_REQUEST);
			}
			emailGroupService.saveEmailGroup(mapper.map(emailGroupDto, EmailGroup.class));
			return new ResponseEntity<>(
					new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EMAIL_GROUP_SUCCESS), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_GROUP,
				validationFailureStatusCodes.getEmailGroupNotExist()), HttpStatus.BAD_REQUEST);
	}

}
