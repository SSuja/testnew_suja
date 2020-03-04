package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.UserRequestDto;
import com.tokyo.supermix.data.dto.UserResponseDto;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.UserService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(UserController.class);

  @PostMapping(value = EndpointURI.USER)
  public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    if (userService.isUsernameExist(userRequestDto.getUsername())) {
      logger.debug("User already exists: createUser(), username: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_NAME,
          validationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (userService.isEmployeeExist(userRequestDto.getEmployeeId())) {
      logger.debug("Employee already exists: createUser(), employee: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE,
          validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    userService.saveUser(mapper.map(userRequestDto, User.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_USER_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.USERS)
  public ResponseEntity<Object> getAllUsers() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.USER,
            mapper.map(userService.getAllUsers(), UserResponseDto.class), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.USER_BY_ID)
  public ResponseEntity<Object> getUserById(@PathVariable Long id) {
    if (userService.isUserExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.USER,
          mapper.map(userService.getUserById(id), UserResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No User record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID,
        validationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.USER_BY_ID)
  public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
    if (userService.isUserExist(id)) {
      userService.deleteUser(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_USER_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No User record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID,
        validationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.USER)
  public ResponseEntity<Object> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    if (userService.isUserExist(userRequestDto.getId())) {
      if (userService.isUpdatedUserExist(userRequestDto.getId(), userRequestDto.getUsername())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_NAME,
            validationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      userService.saveUser(mapper.map(userRequestDto, User.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_USER_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No User record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID,
        validationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }
}
