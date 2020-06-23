package com.tokyo.supermix.server.services.auth;

import java.util.ArrayList;
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
import com.tokyo.supermix.data.dto.auth.UserRoleDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;
import com.tokyo.supermix.data.entities.auth.UserRole;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;
import com.tokyo.supermix.data.repositories.auth.UserRepository;
import com.tokyo.supermix.data.repositories.auth.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private UserRoleRepository userRoleRepository;
  @Autowired
  private UserPlantRoleRepository userPlantRoleRepository;

  @Transactional
  public UserCredentialDto saveUser(User user, List<Long> roles) {
    user.setUserName(user.getEmail());
    String password = generateRandomPassword();
    User userObj = saveUserPassword(user, password);
    if (user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
      createUserRoles(roles, userObj);
    } else {
      createUserPlantRoles(roles, userObj);
    }
    if (user.getEmployee() != null) {
      updateEmployee(user.getEmployee().getId());
    }
    return createUserCredentialDto(user.getUserName(), password, user.getEmail());
  }

  private UserCredentialDto createUserCredentialDto(String userName, String password,
      String email) {
    UserCredentialDto dto = new UserCredentialDto();
    dto.setUserName(userName);
    dto.setPassword(password);
    dto.setEmail(email);
    return dto;
  }

  private void updateEmployee(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).get();
    employee.setHasUser(true);
    employeeRepository.save(employee);
  }

  private void createUserPlantRoles(List<Long> roles, User user) {
    List<UserPlantRole> userPlantRoles = new ArrayList<UserPlantRole>();
    roles.forEach(roleId -> {
      PlantRole plantrole = new PlantRole();
      plantrole.setId(roleId);
      userPlantRoles.add(new UserPlantRole(user, plantrole));
    });
    userPlantRoleRepository.saveAll(userPlantRoles);
  }

  private void createUserRoles(List<Long> roles, User user) {
    List<UserRole> userRoles = new ArrayList<UserRole>();
    roles.forEach(roleId -> {
      Role role = new Role();
      role.setId(roleId);
      userRoles.add(new UserRole(user, role));
    });
    userRoleRepository.saveAll(userRoles);
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

  @Override
  public void updateUserStatus(Long userId, Boolean status) {
    User user = userRepository.findById(userId).get();
    user.setIsActive(status);
    userRepository.save(user);
  }

  @Override
  public void updateUserRoles(UserRoleDto userRoleDto) {
    User user = userRepository.findById(userRoleDto.getUserId()).get();
    if (user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
      userRoleRepository.findByUserId(userRoleDto.getUserId())
          .forEach(userRole -> userRoleRepository.deleteById(userRole.getId()));
      createUserRoles(userRoleDto.getRoleIds(), user);
    } else {
      userPlantRoleRepository.findRolesByUserId(userRoleDto.getUserId())
          .forEach(userRole -> userPlantRoleRepository.deleteById(userRole.getId()));
      createUserPlantRoles(userRoleDto.getRoleIds(), user);
    }
  }
}
