package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tokyo.supermix.data.entities.privilege.Permission;

@Service
public interface PrivilageService {

  public List<Permission> getBySubRouteName(String subRouteName);

  public List<Permission> getBySubRouteMainRouteName(String mainRouteName);

}
