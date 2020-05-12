package com.tokyo.supermix.auth.controller;

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
import com.tokyo.supermix.auth.AuthEndpointURI;
import com.tokyo.supermix.auth.service.UserService;
import com.tokyo.supermix.auth.util.AuthConstants;
import com.tokyo.supermix.auth.util.AuthValidationFailureCodes;
import com.tokyo.supermix.data.dto.UserRequestDto;
import com.tokyo.supermix.data.dto.UserResponseDto;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Mapper mapper;
	@Autowired
	private AuthValidationFailureCodes authValidationFailureCodes;
//	@Autowired
//	private EmailService emailService;
//	@Autowired
//	private MailConstants mailConstants;
	private static final Logger logger = Logger.getLogger(UserController.class);

	@PostMapping(value = AuthEndpointURI.USER)
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		if (userService.isUserNameExist(userRequestDto.getUserName())) {
			logger.debug("User already exists: createUser(), username: {}");
			return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.USER_NAME,
					authValidationFailureCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
		}
		User user = userService.saveUser(mapper.map(userRequestDto, User.class));
		if (user != null) {
			String message = "Your Account sucessfully created. Your Username is " + userRequestDto.getUserName()
					+ ". Password is " + userRequestDto.getPassword();
//			emailService.sendMail(mailConstants.getMailNewUser(), AuthConstants.SUBJECT_NEW_USER, message);
		}
		return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.ADD_USER_SUCCESS),
				HttpStatus.OK);
	}

	@GetMapping(value = AuthEndpointURI.USERS)
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(new ContentResponse<>(AuthConstants.USER,
				mapper.map(userService.getAllUsers(), UserResponseDto.class), RestApiResponseStatus.OK), null,
				HttpStatus.OK);
	}

	@GetMapping(value = AuthEndpointURI.USER_BY_ID)
	public ResponseEntity<Object> getUserById(@PathVariable Long id) {
		if (userService.isUserExist(id)) {
			return new ResponseEntity<>(
					new ContentResponse<>(AuthConstants.USER,
							mapper.map(userService.getUserById(id), UserResponseDto.class), RestApiResponseStatus.OK),
					HttpStatus.OK);
		}
		logger.debug("No User record exist for given id");
		return new ResponseEntity<>(
				new ValidationFailureResponse(AuthConstants.USER_ID, authValidationFailureCodes.getUserNotExist()),
				HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(AuthEndpointURI.USER_BY_ID)
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		if (userService.isUserExist(id)) {
			userService.deleteUser(id);
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.DELETE_USER_SCCESS),
					HttpStatus.OK);
		}
		logger.debug("No User record exist for given id");
		return new ResponseEntity<>(
				new ValidationFailureResponse(AuthConstants.USER_ID, authValidationFailureCodes.getUserNotExist()),
				HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = AuthEndpointURI.USER)
	public ResponseEntity<Object> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		if (userService.isUserExist(userRequestDto.getId())) {
			if (userService.isUpdatedUserExist(userRequestDto.getId(), userRequestDto.getUserName())) {
				return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.USER_NAME,
						authValidationFailureCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
			}
			userService.saveUser(mapper.map(userRequestDto, User.class));
			return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.UPDATE_USER_SUCCESS),
					HttpStatus.OK);
		}
		logger.debug("No User record exist for given id");
		return new ResponseEntity<>(
				new ValidationFailureResponse(AuthConstants.USER_ID, authValidationFailureCodes.getUserNotExist()),
				HttpStatus.BAD_REQUEST);
	}
}
