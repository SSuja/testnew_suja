package com.auth.security.service;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth.security.config.JwtTokenProvider;
import com.tokyo.supermix.data.dto.auth.LoginRequestDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.auth.VerificationToken;
import com.tokyo.supermix.data.repositories.auth.VerificationTokenRepository;

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
  @Autowired
  VerificationTokenRepository verificationTokenRepository;

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

  @Override
  public void createForgotPasswordToken(String token, User user) {
    VerificationToken verificationToken = new VerificationToken(token, user);
    verificationTokenRepository.save(verificationToken);
  }

  @Override
  public String validatePasswordResetToken(String token) {
    final VerificationToken passToken = verificationTokenRepository.findByToken(token);
    return !isTokenFound(passToken) ? "tokenString" : isTokenExpired(passToken) ? "expiryDate" : null;
  }

  private boolean isTokenFound(VerificationToken passToken) {
    return passToken != null;
  }

  private boolean isTokenExpired(VerificationToken passToken) {
    final Calendar cal = Calendar.getInstance();
    return passToken.getExpiryDate().before(cal.getTime());
  }
  @Override
  public User getUserByPasswordResetToken(String token) {
    return verificationTokenRepository.findByToken(token).getUser();
  }
}
