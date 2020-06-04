package com.tokyo.supermix.server.services;

import java.util.Arrays;
import java.util.List;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.auth.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  private EmployeeRepository employeeRepository;

  @Transactional
  public UserCredentialDto saveUser(User user) {
    String email = user.getEmail();
    String userName = email.substring(0, email.indexOf('@'));
    user.setUserName(userName);
    String password = generateRandomPassword();
    saveUserPassword(user, password);
    Employee employee = employeeRepository.findById(user.getEmployee().getId()).get();
    employee.setHasUser(true);
    employeeRepository.save(employee);
    UserCredentialDto dto = new UserCredentialDto();
    dto.setUserName(userName);
    dto.setPassword(password);
    dto.setEmail(email);
    return dto;
  }

  public String generateRandomPassword() {
    List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 2),
        new CharacterRule(EnglishCharacterData.LowerCase, 2),
        new CharacterRule(EnglishCharacterData.Digit, 4));
    PasswordGenerator generator = new PasswordGenerator();
    String password = generator.generatePassword(8, rules);
    return password;
  }

  private User saveUserPassword(User user, String password) {
    user.setPassword(passwordEncoder.encode(password));
    return userRepository.save(user);
  }

  @Transactional(readOnly = true)
  public boolean isUserNameExist(String userName) {
    return userRepository.existsByUserName(userName);
  }

  @Transactional(readOnly = true)
  public boolean isEmployeeExist(Long employeeId) {
    return userRepository.existsByEmployeeId(employeeId);
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
