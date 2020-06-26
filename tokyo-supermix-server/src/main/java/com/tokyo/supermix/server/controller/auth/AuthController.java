package com.tokyo.supermix.server.controller.auth;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.auth.JwtAuthenticationDtoResponse;
import com.tokyo.supermix.data.dto.auth.LoginRequestDto;
import com.tokyo.supermix.data.dto.auth.PasswordDto;
import com.tokyo.supermix.data.dto.auth.ResetPasswordDto;
import com.tokyo.supermix.data.dto.auth.UserRequestDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.server.services.auth.AuthService;
import com.tokyo.supermix.server.services.auth.UserService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
  @Autowired
  private EmailService emailService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private UserService userService;
  @Autowired
  private AuthService authService;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @PostMapping(value = PrivilegeEndpointURI.SIGNIN)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    try {
      String jwt = authService.generateUserToken(loginRequestDto);
      if (jwt != null) {
        return ResponseEntity.ok(new JwtAuthenticationDtoResponse(jwt));
      } else {
        return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER,
            privilegeValidationFailureStatusCodes.getUserNotActive()), HttpStatus.BAD_REQUEST);
      }
    } catch (UsernameNotFoundException ex) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(PrivilegeConstants.EMAIL_OR_USERNAME,
              privilegeValidationFailureStatusCodes.getEmailOrUserName()),
          HttpStatus.BAD_REQUEST);
    } catch (BadCredentialsException ex) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.CREDENCIALS,
          privilegeValidationFailureStatusCodes.getCredentials()), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = PrivilegeEndpointURI.SIGNUP)
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    if (userService.isUserNameExist(userRequestDto.getUserName())) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.USER_NAME,
          privilegeValidationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (userService.existsByEmail(userRequestDto.getEmail())) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.EMAIL,
          privilegeValidationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    userService.saveUser(mapper.map(userRequestDto, User.class), userRequestDto.getRoleIds());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.ADD_USER_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = PrivilegeEndpointURI.CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDto passwordDto) {
    User user = userService.getUserById(passwordDto.getUserId());
    if (!authService.checkIsValidOldPassword(user, passwordDto.getCurrentPassword())) {
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PASSWORD,
          privilegeValidationFailureStatusCodes.getIsMatchPassword()), HttpStatus.BAD_REQUEST);
    }
    userService.changeUserPassword(user, passwordDto.getNewPassword());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.UPDATE_PASSWORD_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = PrivilegeEndpointURI.FORGOT_PASSWORD)
  public ResponseEntity<?> forgotPassword(@RequestParam("userEmail") String userEmail) {
    if (userService.existsByEmail(userEmail)) {
      final User user = userService.findUserByEmail(userEmail);
      if (user != null) {
        final String token = UUID.randomUUID().toString();
        authService.createForgotPasswordToken(token, user);
        emailService.sendMail(userEmail, Constants.SUBJECT_FORGOT_PASSWORD,
            PrivilegeConstants.MESSAGE_OF_FORGOT_PASSWORD + token);
        return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
            PrivilegeConstants.GENERATE_PASSWORD_SUCCESS), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.EMAIL,
        privilegeValidationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = PrivilegeEndpointURI.RESET_PASSWORD)
  public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
      @RequestBody ResetPasswordDto passwordDto) {
    String result = authService.validatePasswordResetToken(token);
    if (result != null) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(result,
              privilegeValidationFailureStatusCodes.getIsPasswordTokenFailed()),
          HttpStatus.BAD_REQUEST);
    }
    User user = authService.getUserByPasswordResetToken(token);
    userService.changeUserPassword(user, passwordDto.getPassword());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.UPDATE_PASSWORD_SUCCESS),
        HttpStatus.OK);
  }
}