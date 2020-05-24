package com.gateway.auth.controller;

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
import com.gateway.auth.AuthEndpointURI;
import com.gateway.auth.mapper.Mapper;
import com.gateway.auth.service.AuthService;
import com.gateway.auth.service.UserService;
import com.gateway.auth.util.AuthConstants;
import com.gateway.auth.util.AuthValidationFailureCodes;
import com.tokyo.supermix.data.dto.auth.JwtAuthenticationDtoResponse;
import com.tokyo.supermix.data.dto.auth.LoginRequestDto;
import com.tokyo.supermix.data.dto.auth.PasswordDto;
import com.tokyo.supermix.data.dto.auth.ResetPasswordDto;
import com.tokyo.supermix.data.dto.auth.UserRequestDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
  // @Autowired
  // private EmailService emailService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private UserService userService;
  @Autowired
  private AuthService authService;
  @Autowired
  private AuthValidationFailureCodes authValidationFailureCodes;

  @PostMapping(value = AuthEndpointURI.SIGNIN)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    try {
      String jwt = authService.generateUserToken(loginRequestDto);
      if (jwt != null) {
        return ResponseEntity.ok(new JwtAuthenticationDtoResponse(jwt));
      } else {
        return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.PASSWORD,
            authValidationFailureCodes.getPassword()), HttpStatus.BAD_REQUEST);
      }
    } catch (UsernameNotFoundException ex) {
      return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.EMAIL_OR_USERNAME,
          authValidationFailureCodes.getEmailOrUserName()), HttpStatus.BAD_REQUEST);
    } catch (BadCredentialsException ex) {
      return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.CREDENCIALS,
          authValidationFailureCodes.getCredentials()), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = AuthEndpointURI.SIGNUP)
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    if (userService.isUserNameExist(userRequestDto.getUserName())) {
      return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.USER_NAME,
          authValidationFailureCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (userService.existsByEmail(userRequestDto.getEmail())) {
      return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.EMAIL,
          authValidationFailureCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    userService.saveUser(mapper.map(userRequestDto, User.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.ADD_USER_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = AuthEndpointURI.CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDto passwordDto) {
    User user = userService.getUserById(passwordDto.getUserId());
    if (!authService.checkIsValidOldPassword(user, passwordDto.getCurrentPassword())) {
      return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.PASSWORD,
          authValidationFailureCodes.getIsMatchPassword()), HttpStatus.BAD_REQUEST);
    }
    userService.changeUserPassword(user, passwordDto.getNewPassword());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.UPDATE_PASSWORD_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = AuthEndpointURI.FORGOT_PASSWORD)
  public ResponseEntity<?> forgotPassword(@RequestParam("userEmail") String userEmail) {
    if (userService.existsByEmail(userEmail)) {
      final User user = userService.findUserByEmail(userEmail);
      if (user != null) {
        final String token = UUID.randomUUID().toString();
        authService.createForgotPasswordToken(token, user);
        // emailService.sendMail(userEmail,AuthConstants.SUBJECT_FORGOT_PASSWORD,AuthConstants.MESSAGE_OF_FORGOT_PASSWORD
        // + token);
        return new ResponseEntity<>(
            new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.GENERATE_PASSWORD_SUCCESS),
            HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.EMAIL,
        authValidationFailureCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = AuthEndpointURI.RESET_PASSWORD)
  public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
      @RequestBody ResetPasswordDto passwordDto) {
    String result = authService.validatePasswordResetToken(token);
    if (result != null) {
      return new ResponseEntity<>(new ValidationFailureResponse(result,
          authValidationFailureCodes.getIsPasswordTokenFailed()), HttpStatus.BAD_REQUEST);
    }
    User user = authService.getUserByPasswordResetToken(token);
    userService.changeUserPassword(user, passwordDto.getPassword());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.UPDATE_PASSWORD_SUCCESS),
        HttpStatus.OK);
  }
}
