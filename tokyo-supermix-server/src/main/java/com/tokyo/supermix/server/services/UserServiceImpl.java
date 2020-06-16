package com.tokyo.supermix.server.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.enums.UserType;
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
  public UserCredentialDto saveUser(User user,List<Long> roles) {
    user.setUserName(user.getEmail());
    String password = generateRandomPassword();
  if(user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
    Set<Role> roleList = new HashSet<Role>();
    roles.forEach(roleId->{
      Role role = new Role();
      role.setId(roleId);
      roleList.add(role);
    });
    user.setRoles(roleList);
  }else {
    Set<PlantRole> plantRoleList = new HashSet<PlantRole>();
    roles.forEach(roleId->{
      PlantRole plantrole = new PlantRole();
      plantrole.setId(roleId);
      plantRoleList.add(plantrole);
    });
    user.setPlantRoles(plantRoleList);
  }
    saveUserPassword(user, password);
    if(user.getEmployee()!=null) {  
      Employee employee = employeeRepository.findById(user.getEmployee().getId()).get();
      employee.setHasUser(true);
      employeeRepository.save(employee);
    }
    UserCredentialDto dto = new UserCredentialDto();
    dto.setUserName(user.getUserName());
    dto.setPassword(password);
    dto.setEmail(user.getEmail());
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
