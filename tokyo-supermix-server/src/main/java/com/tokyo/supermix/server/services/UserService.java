package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.User;

public interface UserService {
  User saveUser(User user);

  public boolean isUsernameExist(String username);

  public boolean isEmployeeExist(Long employeeId);

  public boolean isUserExist(Long id);

  public List<User> getAllUsers();

  public void deleteUser(Long id);

  public User getUserById(Long id);

  public boolean isUpdatedUserExist(Long id, String username);
}
