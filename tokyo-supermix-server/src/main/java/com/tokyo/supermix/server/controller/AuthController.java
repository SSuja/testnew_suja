package com.tokyo.supermix.server.controller;
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
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.JwtAuthenticationDtoResponse;
import com.tokyo.supermix.data.dto.LoginRequestDto;
import com.tokyo.supermix.data.dto.PasswordDto;
import com.tokyo.supermix.data.dto.UserRequestDto;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.AuthService;
import com.tokyo.supermix.server.services.UserService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private UserService userService;
  @Autowired
  private AuthService authService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @PostMapping(value = EndpointURI.SIGNIN)
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
    try {
      String jwt = authService.generateUserToken(loginRequestDto);
      if (jwt != null) {
        return ResponseEntity.ok(new JwtAuthenticationDtoResponse(jwt));
      } else {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PASSWORD,
            validationFailureStatusCodes.getPassword()), HttpStatus.BAD_REQUEST);
      }
    } catch (UsernameNotFoundException ex) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_OR_USERNAME,
          validationFailureStatusCodes.getEmailOrUserName()), HttpStatus.BAD_REQUEST);
    } catch (BadCredentialsException ex) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.CREDENCIALS,
          validationFailureStatusCodes.getCredentials()), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping(value = EndpointURI.SIGNUP)
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
    if (userService.isUserNameExist(userRequestDto.getUserName())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.USER_NAME,
              validationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
  }
    if (userService.existsByEmail(userRequestDto.getEmail())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
              validationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
  }
    userService.saveUser(mapper.map(userRequestDto, User.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_USER_SUCCESS),
        HttpStatus.OK);
  }
  
  @PutMapping(value = EndpointURI.CHANGE_PASSWORD)
  public ResponseEntity<?> changePassword(@Valid @RequestBody PasswordDto passwordDto) {
    User user = userService.getUserById(passwordDto.getUserId());
    if(!authService.checkIsValidOldPassword(user,passwordDto.getCurrentPassword())){
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PASSWORD,
          validationFailureStatusCodes.getUserAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    userService.changeUserPassword(user,passwordDto.getNewPassword());
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PASSWORD_SUCCESS),
        HttpStatus.OK);
  }
}
