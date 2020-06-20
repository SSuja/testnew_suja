package com.tokyo.supermix.server.services.auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.auth.RoleDto;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.auth.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {
  @Autowired
  private UserRoleRepository userRoleRepository;
  @Autowired
  private Mapper mapper;

  @Override
  public List<RoleDto> getRolesByUserId(Long userId) {
    List<Role> roleList = new ArrayList<Role>();
    userRoleRepository.findByUserId(userId).forEach(userRole -> roleList.add(userRole.getRole()));
    return mapper.map(roleList, RoleDto.class);
  }

  @Override
  public List<UserResponseDto> getUsersByRoleId(Long roleId) {
    List<User> userList = new ArrayList<User>();
    userRoleRepository.findByRoleId(roleId).forEach(userRole -> userList.add(userRole.getUser()));
    return mapper.map(userList, UserResponseDto.class);
  }

  @Override
  public boolean existsByUserId(Long userId) {
    return userRoleRepository.existsByUserId(userId);
  }

  @Override
  public boolean existsByRoleId(Long roleId) {
    return userRoleRepository.existsByRoleId(roleId);
  }

}