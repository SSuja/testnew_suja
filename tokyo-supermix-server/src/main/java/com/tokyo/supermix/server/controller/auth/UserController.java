package com.tokyo.supermix.server.controller.auth;

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
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.auth.GenerateUserDto;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.dto.auth.UserRoleDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.server.services.auth.UserService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;
  @Autowired
  private EmailService emailService;
  private static final Logger logger = Logger.getLogger(UserController.class);

  @PostMapping(value = PrivilegeEndpointURI.USER)
  public ResponseEntity<Object> createUser(@Valid @RequestBody GenerateUserDto generateUserDto) {
    if (userService.existsByEmail(generateUserDto.getEmail())) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
          privilegeValidationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    } else if (generateUserDto.getEmployeeId() == null) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.EMPLOYEE,
          privilegeValidationFailureStatusCodes.getEmployeeIdIsNull()), HttpStatus.BAD_REQUEST);
    } else if (userService.isEmployeeExist(generateUserDto.getEmployeeId())) {
      logger.debug("Employee already exists: createUser(), employee: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
          privilegeValidationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }

    UserCredentialDto userDto =
        userService.saveUser(mapper.map(generateUserDto, User.class), generateUserDto.getRoleIds());
    if (userDto != null) {
      String message = "Your Account sucessfully created. Your Username is " + userDto.getUserName()
          + ". Password is " + userDto.getPassword();
      emailService.sendMail(userDto.getEmail(), Constants.SUBJECT_NEW_USER, message);
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.ADD_USER_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.USERS)
  public ResponseEntity<Object> getAllUsers() {
    return new ResponseEntity<>(
        new ContentResponse<>(PrivilegeConstants.USER,
            mapper.map(userService.getAllUsers(), UserResponseDto.class), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.USER_BY_ID)
  public ResponseEntity<Object> getUserById(@PathVariable Long id) {
    if (userService.isUserExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.USER,
          mapper.map(userService.getUserById(id), UserResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No User record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER_ID,
        privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(PrivilegeEndpointURI.USER_BY_ID)
  public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
    if (userService.isUserExist(id)) {
      userService.deleteUser(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.DELETE_USER_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No User record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER_ID,
        privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = PrivilegeEndpointURI.UPDATE_USER_STATUS_BY_ID)
  public ResponseEntity<Object> updateUserStatus(@PathVariable Long userId,
      @PathVariable Boolean status) {
    if (userService.isUserExist(userId)) {
      userService.updateUserStatus(userId, status);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.UPDATE_USER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER_ID,
        privilegeValidationFailureStatusCodes.getUserNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = PrivilegeEndpointURI.UPDATE_USER_ROLE)
  public ResponseEntity<Object> updateUserRole(@RequestBody UserRoleDto userRoleDto) {
    userService.updateUserRoles(userRoleDto);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.UPDATE_USER_SUCCESS),
        HttpStatus.OK);
  }
}
