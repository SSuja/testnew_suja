package com.tokyo.supermix.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.data.dto.JwtAuthenticationDtoResponse;
import com.tokyo.supermix.data.dto.LoginRequestDto;
import com.tokyo.supermix.data.dto.UserRequestDto;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.JwtTokenProvider;
import com.tokyo.supermix.server.services.UserDetailsServiceImpl;
import com.tokyo.supermix.server.services.UserService;
import com.tokyo.supermix.util.AuthValidationConstants;
import com.tokyo.supermix.util.Constants;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
    AuthValidationConstants authValidationConstants;
    @Autowired
    private Mapper mapper;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		try {
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequestDto.getUsernameOrEmail());
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, loginRequestDto.getPassword(), userDetails.getAuthorities());
			boolean result = authenticationManager.authenticate(usernamePasswordAuthenticationToken).isAuthenticated();
			if (result) {
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				String jwt = tokenProvider.generateToken(usernamePasswordAuthenticationToken);
				return ResponseEntity.ok(new JwtAuthenticationDtoResponse(jwt));
			} else {
//				authErrors.put("message", "Invalid password");
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authErrors);
			  return new ResponseEntity<>(
	                new ValidationFailureResponse(Constants.PASSWORD, authValidationConstants.getPassword()),
	                HttpStatus.BAD_REQUEST);
			}
		} catch (UsernameNotFoundException ex) {
		  return new ResponseEntity<>(
              new ValidationFailureResponse(Constants.EMAIL_OR_USERNAME, authValidationConstants.getEmailOrUserName()),
              HttpStatus.BAD_REQUEST);
		}
		catch (BadCredentialsException ex) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.CREDENCIALS, authValidationConstants.getEmailOrUserName()),
            HttpStatus.BAD_REQUEST);
      }
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
//		userValidation.validationUser(userDtoRequest);
//		if (!userValidation.getErrors().isEmpty()) {
//			return new ResponseEntity<>(userValidation.getErrors(), HttpStatus.BAD_REQUEST);
//		}
		return new ResponseEntity<>(
		    userService.saveUser(mapper.map(userRequestDto, User.class)),
				HttpStatus.CREATED);
	}
}
