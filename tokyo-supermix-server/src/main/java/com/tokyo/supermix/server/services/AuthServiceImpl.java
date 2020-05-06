package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.LoginRequestDto;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.security.JwtTokenProvider;
@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  JwtTokenProvider tokenProvider;
  @Autowired
  AuthUserDetailsService authUserDetailsService;
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  PasswordEncoder passwordEncoder;
  
  @Override
  public String generateUserToken(LoginRequestDto loginRequestDto) {
    UserDetails userDetails =
        authUserDetailsService.loadUserByUsername(loginRequestDto.getUsernameOrEmail());
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, loginRequestDto.getPassword(),
            userDetails.getAuthorities());
    if (authenticationManager.authenticate(usernamePasswordAuthenticationToken).isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      return tokenProvider.generateToken(usernamePasswordAuthenticationToken);
    }
    return null;
  }
  @Override
  public boolean checkIsValidOldPassword(User user, String currentPassword) {
     return passwordEncoder.matches(currentPassword, user.getPassword());
  }
}
