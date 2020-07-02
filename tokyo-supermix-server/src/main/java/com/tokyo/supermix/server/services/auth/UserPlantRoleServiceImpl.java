package com.tokyo.supermix.server.services.auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;

@Service
public class UserPlantRoleServiceImpl implements UserPlantRoleService {
  @Autowired
  private UserPlantRoleRepository userPlantRoleRepository;
  @Autowired
  Mapper mapper;

  @Transactional(readOnly = true)
  public List<PlantRoleDto> getRolesByUserId(Long userId) {
    List<PlantRole> plantroleList = new ArrayList<PlantRole>();
    userPlantRoleRepository.findByUserId(userId).forEach(roles -> {
      plantroleList.add(roles.getPlantRole());
    });
    return mapper.map(plantroleList, PlantRoleDto.class);
  }

  @Transactional(readOnly = true)
  public List<UserResponseDto> getUsersByPlantRoleId(Long PlantRoleId) {
    List<User> userList = new ArrayList<User>();

    userPlantRoleRepository.findByPlantRoleId(PlantRoleId).forEach(users -> {
      userList.add(users.getUser());
    });
    return mapper.map(userList, UserResponseDto.class);
  }

  @Transactional(readOnly = true)
  public boolean existsByUserId(Long userId) {

    return userPlantRoleRepository.existsByUserId(userId);
  }

  @Transactional(readOnly = true)
  public boolean existsByPlantRoleId(Long plantRoleId) {
    return userPlantRoleRepository.existsByPlantRoleId(plantRoleId);
  }

  @Override
  public List<UserResponseDto> getUsersByUserTypeAndPlantRoleId(UserType userType,Long PlantRoleId) {
    List<User> userList = new ArrayList<User>();
    userPlantRoleRepository.findByPlantRoleIdAndUserUserType(PlantRoleId, userType).forEach(userRole -> {
      userList.add(userRole.getUser());
    });
    return mapper.map(userList, UserResponseDto.class);
  }

}
