package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.privilege.UserPlantPermissionRepository;

@Service
public class UserPlantPermissionServiceImpl implements UserPlantPermissionService {

  @Autowired
  private UserPlantPermissionRepository userPlantPermissionRepository;
  @Autowired
  Mapper mapper;

  @Transactional(readOnly = true)
  public List<PermissionResponseDto> getPlantRolePermissionsByUserId(Long userId) {
    List<UserPlantPermission> userPlantPermissionList =
        userPlantPermissionRepository.findByUserId(userId);
    return mapper.map(userPlantPermissionList, PermissionResponseDto.class);
  }

  @Override
  public boolean isUserIdExist(Long userId) {
    return userPlantPermissionRepository.existsByUserId(userId);
  }

}
