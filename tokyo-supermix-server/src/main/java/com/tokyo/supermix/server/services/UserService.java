package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.entities.auth.User;

public interface UserService {
  UserCredentialDto saveUser(User user);

  public boolean isUserNameExist(String userName);

  public boolean isEmployeeExist(Long employeeId);

  public boolean isUserExist(Long id);

  public List<User> getAllUsers();

  public void deleteUser(Long id);

  public User getUserById(Long id);

  public boolean isUpdatedUserExist(Long id, String userName);
  
  public boolean existsByEmail(String email);
  
  void changeUserPassword(User user, String password);

  User findUserByEmail(String userEmail);
}
