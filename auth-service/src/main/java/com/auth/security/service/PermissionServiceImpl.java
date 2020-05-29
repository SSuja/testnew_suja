package com.auth.security.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.auth.Permission;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
  @Autowired
  private PermissionRepository permissionRepository;

  @Transactional(readOnly = true)
  public List<Permission> getAllPermissions() {
    return permissionRepository.findAll();
  }
}
