package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.LoginRequestDto;
import com.tokyo.supermix.data.entities.User;

public interface AuthService {
  String generateUserToken(LoginRequestDto loginRequestDto);
  boolean checkIsValidOldPassword(User user, String currentPassword);
  void createForgotPasswordToken(String token,User user);
  String validatePasswordResetToken(String token);
  User getUserByPasswordResetToken(String token);
}
