package com.tokyo.supermix.server.services.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.dto.auth.UserRoleDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.auth.QUser;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;
import com.tokyo.supermix.data.entities.auth.UserRole;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;
import com.tokyo.supermix.data.repositories.auth.UserRepository;
import com.tokyo.supermix.data.repositories.auth.UserRoleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRoleRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.EmployeeService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PermissionConstants;

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
  @Autowired
  private PlantRoleRepository plantRoleRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private UserRoleService userRoleService;
  @Autowired
  private UserPlantRoleService userPlantRoleService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private EmployeeService employeeService;


  @Transactional
  public UserCredentialDto saveUser(User user, List<Long> roles) {
    user.setUserName(user.getEmail());
    String password = generateRandomPassword();
    User userObj = saveUserPassword(user, password);
    if (user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
      createUserRoles(roles, userObj);
      emailNotification.sendNonPlantUserCreationEmail(userObj, roles);
    } else {
      createUserPlantRoles(roles, userObj);
      emailNotification.sendPlantUserCreationEmail(userObj, roles);
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
    roles.forEach(
        roleId -> userPlantRoles.add(new UserPlantRole(user, plantRoleRepository.getOne(roleId))));
    userPlantRoleRepository.saveAll(userPlantRoles);
  }

  private void createUserRoles(List<Long> roles, User user) {
    List<UserRole> userRoles = new ArrayList<UserRole>();
    roles.forEach(roleId -> userRoles.add(new UserRole(user, roleRepository.getOne(roleId))));
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
  public List<UserResponseDto> getAllUsers() {
    ArrayList<UserResponseDto> UserResponseDtoList = new ArrayList<UserResponseDto>();
    List<User> userList = userRepository.findAll();
    for (User user : userList) {
      UserResponseDto userResponseDto = new UserResponseDto();
      userResponseDto.setId(user.getId());
      userResponseDto.setUserName(user.getUserName());
      userResponseDto.setEmployee(mapper.map(
          employeeService.getEmployeeById(user.getEmployee().getId()), EmployeeResponseDto.class));
      userResponseDto.setUserType(user.getUserType().name());
      userResponseDto.setCreatedAt(user.getCreatedAt().toString());
      userResponseDto.setUpdatedAt(user.getUpdatedAt().toString());
      userResponseDto.setRoles(getUserDetailById(user.getId()).getRoles());
      userResponseDto.setPlantRoles(getUserDetailById(user.getId()).getPlantRoles());
      UserResponseDtoList.add(userResponseDto);
    }
    return UserResponseDtoList;

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

  @Transactional
  public void changeUserPassword(User user, String newPassword) {
    saveUserPassword(user, newPassword);
  }

  @Transactional(readOnly = true)
  public User findUserByEmail(String userEmail) {
    return userRepository.findByEmail(userEmail);
  }

  @Transactional
  public void updateUserStatus(Long userId, Boolean status) {
    User user = userRepository.findById(userId).get();
    user.setIsActive(status);
    userRepository.save(user);
  }

  @Transactional
  public void updateUserRoles(UserRoleDto userRoleDto) {
    User user = userRepository.findById(userRoleDto.getUserId()).get();
    if (user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
      userRoleRepository.findByUserId(userRoleDto.getUserId())
          .forEach(userRole -> userRoleRepository.deleteById(userRole.getId()));
      createUserRoles(userRoleDto.getRoleIds(), user);
    } else {
      userPlantRoleRepository.findByUserId(userRoleDto.getUserId())
          .forEach(userRole -> userPlantRoleRepository.deleteById(userRole.getId()));
      createUserPlantRoles(userRoleDto.getRoleIds(), user);
    }
  }

  @Override
  public List<User> getAllUsersByPlant(UserPrincipal currentUser) {
    return currentUser.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())
        ? userRepository.findAll()
        : userRepository.findByEmployeePlantCodeIn(currentUserPermissionPlantService
            .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_USER));
  }

  @Override
  public List<User> getAllUsersByUserTypeByplant(UserPrincipal currentUser, UserType userType) {
    return userRepository.findByUserTypeAndEmployeePlantCodeIn(userType,
        currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
            PermissionConstants.VIEW_USER));
  }

  @Override
  public List<User> getAllUsersByUserType(UserType userType) {
    return userRepository.findByUserType(userType);
  }

  @Override
  public UserResponseDto getUserDetailById(Long id) {
    UserResponseDto userResponseDto =
        mapper.map(userRepository.findById(id).get(), UserResponseDto.class);
    if (userResponseDto.getUserType().equalsIgnoreCase(UserType.PLANT_USER.name())) {
      userResponseDto.setPlantRoles(userPlantRoleService.getRolesByUserId(id));
    } else {
      userResponseDto.setRoles(userRoleService.getRolesByUserId(id));
    }
    return userResponseDto;
  }

  @Override
  public List<UserResponseDto> getUserByPlantCode(String plantCode, Pageable pageable) {
    ArrayList<UserResponseDto> userResponseDtoList = new ArrayList<UserResponseDto>();
    List<User> userList = userRepository.findAllByEmployeePlantCode(plantCode, pageable);
    userListGetterSetter(userList, userResponseDtoList);
    return userResponseDtoList;
  }

  @Transactional(readOnly = true)
  public Long getCountUser() {
    return userRepository.count();
  }

  @Transactional(readOnly = true)
  public List<UserResponseDto> getAllUsersByPagination(Pageable pageable) {
    ArrayList<UserResponseDto> userResponseDtoList = new ArrayList<UserResponseDto>();
    List<User> userList = userRepository.findAll(pageable).toList();
    userListGetterSetter(userList, userResponseDtoList);
    return userResponseDtoList;
  }

  private void userListGetterSetter(List<User> userList,
      ArrayList<UserResponseDto> userResponseDtoList) {
    for (User user : userList) {
      UserResponseDto userResponseDto = new UserResponseDto();
      userResponseDto.setId(user.getId());
      userResponseDto.setUserName(user.getUserName());
      userResponseDto.setEmployee(mapper.map(
          employeeService.getEmployeeById(user.getEmployee().getId()), EmployeeResponseDto.class));
      userResponseDto.setUserType(user.getUserType().name());
      userResponseDto.setCreatedAt(user.getCreatedAt().toString());
      userResponseDto.setUpdatedAt(user.getUpdatedAt().toString());
      userResponseDto.setRoles(getUserDetailById(user.getId()).getRoles());
      userResponseDto.setPlantRoles(getUserDetailById(user.getId()).getPlantRoles());
      userResponseDtoList.add(userResponseDto);
    }

  }

  @Transactional(readOnly = true)
  public Long getCountUserByPlantCode(String plantCode) {
    return userRepository.countByEmployeePlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<UserResponseDto> searchUserByPlantCode(String userName, String firstName,
      String plantName, String designationName,String phoneNumber, BooleanBuilder booleanBuilder, String plantCode,
      Pageable pageable, Pagination pagination) {
    if (userName != null && !userName.isEmpty()) {
      booleanBuilder.and(QUser.user.userName.startsWithIgnoreCase(userName));
    }
    if (firstName != null && !firstName.isEmpty()) {
      booleanBuilder.and(QUser.user.employee.firstName.startsWithIgnoreCase(firstName));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QUser.user.employee.plant.name.startsWithIgnoreCase(plantName));
    }
    if (designationName != null && !designationName.isEmpty()) {
      booleanBuilder
          .and(QUser.user.employee.designation.name.startsWithIgnoreCase(designationName));
    }
    if (designationName != null && !designationName.isEmpty()) {
      booleanBuilder
          .and(QUser.user.employee.phoneNumber.startsWithIgnoreCase(designationName));
    }
    if (plantCode != null && !plantCode.isEmpty()
        && !(plantCode.equalsIgnoreCase(Constants.ADMIN))) {
      booleanBuilder.and(QUser.user.employee.plant.code.startsWithIgnoreCase(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<User>) userRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(userRepository.findAll(booleanBuilder, pageable).toList(),
        UserResponseDto.class);
  }
}
