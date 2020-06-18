package com.tokyo.supermix.server.services.auth;

import com.tokyo.supermix.data.dto.auth.LoginRequestDto;
import com.tokyo.supermix.data.entities.auth.User;

public interface AuthService {
  String generateUserToken(LoginRequestDto loginRequestDto);
  boolean checkIsValidOldPassword(User user, String currentPassword);
  void createForgotPasswordToken(String token,User user);
  String validatePasswordResetToken(String token);
  User getUserByPasswordResetToken(String token);
}
