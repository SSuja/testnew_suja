package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.RolePermissionResponseDto;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.RolePermissionService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class RolePermissionController {
  @Autowired
  private RolePermissionService rolePermissionService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(RolePermissionController.class);

  @GetMapping(value = EndpointURI.ROLE_PERMISSIONS)
  public ResponseEntity<Object> getAllRolePermission() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.ROLE_PERMISSIONS,
        mapper.map(rolePermissionService.getAllRolePermissions(), RolePermissionResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
