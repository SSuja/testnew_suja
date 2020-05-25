package com.auth.security.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.repositories.auth.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired(required = false)
  private UserRepository userRepository;
  @Autowired(required = false)
  PasswordEncoder passwordEncoder;

  @Transactional
  public User saveUser(User user) {
    return saveUserPassword(user, user.getPassword());
  }

  private User saveUserPassword(User user, String password) {
    user.setPassword(passwordEncoder.encode(password));
    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public boolean isUserNameExist(String userName) {
    System.out.println(userName);
    return userRepository.existsByUserName(userName);
  }

  @Transactional(readOnly = true)
  public boolean isEmployeeExist(Long employeeId) {
    // return userRepository.existsByEmployeeId(employeeId);
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isUserExist(Long id) {
    return userRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public User getUserById(Long id) {
    return userRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isUpdatedUserExist(Long id, String userName) {
    if ((!getUserById(id).getUserName().equalsIgnoreCase(userName))
        && (isUserNameExist(userName))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public void changeUserPassword(User user, String newPassword) {
    saveUserPassword(user, newPassword);
  }

  @Override
  public User findUserByEmail(String userEmail) {
    return userRepository.findByEmail(userEmail);
  }
}
