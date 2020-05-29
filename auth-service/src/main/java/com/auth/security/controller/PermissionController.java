package com.auth.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth.security.AuthEndpointURI;
import com.auth.security.mapper.Mapper;
import com.auth.security.service.PermissionService;
import com.auth.security.util.AuthConstants;
import com.tokyo.supermix.data.dto.auth.PermissionDto;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;

@RestController
@CrossOrigin(origins = "*")
public class PermissionController {
  @Autowired
  private PermissionService permissionService;
  @Autowired
  private Mapper mapper;

  @GetMapping(value = AuthEndpointURI.PERMISSIONS)
  public ResponseEntity<Object> getAllPermissions() {
    return new ResponseEntity<>(new ContentResponse<>(AuthConstants.PERMISSIONS,
        mapper.map(permissionService.getAllPermissions(), PermissionDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
