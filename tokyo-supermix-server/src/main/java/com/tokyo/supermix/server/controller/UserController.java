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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.auth.GenerateUserDto;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailService;
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
  @Autowired
  private EmailService emailService;
  private static final Logger logger = Logger.getLogger(UserController.class);

  @PostMapping(value = EndpointURI.USER)
  public ResponseEntity<Object> createUser(@Valid @RequestBody GenerateUserDto generateUserDto) {
    if (generateUserDto.getUserType().name().equalsIgnoreCase(UserType.PLANT_USER.name())) {
      if (generateUserDto.getEmployeeId() == null) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE,
            validationFailureStatusCodes.getEmployeeIdIsNull()), HttpStatus.BAD_REQUEST);
      } else if (userService.isEmployeeExist(generateUserDto.getEmployeeId())) {
        logger.debug("Employee already exists: createUser(), employee: {}");
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE,
            validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
    }
    UserCredentialDto userDto =
        userService.saveUser(mapper.map(generateUserDto, User.class), generateUserDto.getRoleIds());
    if (userDto != null) {
      String message = "Your Account sucessfully created. Your Username is " + userDto.getUserName()
          + ". Password is " + userDto.getPassword();
      emailService.sendMail(userDto.getEmail(), Constants.SUBJECT_NEW_USER, message);
    }
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

  // @PutMapping(value = EndpointURI.USER)
  // public ResponseEntity<Object> updateUser(@Valid @RequestBody UserRequestDto userRequestDto) {
  // if (userService.isUserExist(userRequestDto.getId())) {
  // userService.saveUser(mapper.map(userRequestDto, User.class),);
  // return new ResponseEntity<>(
  // new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_USER_SUCCESS),
  // HttpStatus.OK);
  // }
  // logger.debug("No User record exist for given id");
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_ID,
  // validationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  // }
}
