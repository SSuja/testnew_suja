package com.tokyo.supermix.server.services.auth;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.dto.auth.UserRoleDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.security.UserPrincipal;

public interface UserService {
  UserCredentialDto saveUser(User user, List<Long> roleIds);

  public boolean isUserNameExist(String userName);

  public boolean isEmployeeExist(Long employeeId);

  public boolean isUserExist(Long id);

  public List<UserResponseDto> getAllUsers();

  public List<UserResponseDto> getAllUsersByPagination(Pageable pageable);

  public List<User> getAllUsersByPlant(UserPrincipal currentUser);

  public void deleteUser(Long id);

  public User getUserById(Long id);

  public boolean isUpdatedUserExist(Long id, String userName);

  public boolean existsByEmail(String email);

  void changeUserPassword(User user, String password);

  User findUserByEmail(String userEmail);

  void updateUserStatus(Long userId, Boolean status);

  void updateUserRoles(UserRoleDto userRoleDto);

  List<User> getAllUsersByUserTypeByplant(UserPrincipal currentUser, UserType userType);

  List<User> getAllUsersByUserType(UserType userType);

  public UserResponseDto getUserDetailById(Long id);

  public List<UserResponseDto> getUserByPlantCode(String plantCode, Pageable pageable);

  public Long getCountUser();

  public Long getCountUserByPlantCode(String plantCode);
}
