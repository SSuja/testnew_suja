package com.auth.security.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.auth.security.AuthEndpointURI;
import com.auth.security.mapper.Mapper;
import com.auth.security.service.RoleService;
import com.auth.security.util.AuthConstants;
import com.auth.security.util.AuthValidationFailureCodes;
import com.tokyo.supermix.data.dto.auth.RoleRequestDto;
import com.tokyo.supermix.data.dto.auth.RoleResponsedto;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;

@RestController
@CrossOrigin(origins = "*")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @Autowired
  private AuthValidationFailureCodes authValidationFailureCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(RoleController.class);

  @PostMapping(value = AuthEndpointURI.ROLE)
  public ResponseEntity<Object> createRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
    if (roleService.existsByRoleName(roleRequestDto.getRoleName())) {
      return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.ROLE,
          authValidationFailureCodes.getRoleNameAlreadyExists()), HttpStatus.BAD_REQUEST);
    }

    roleService.saveRole(mapper.map(roleRequestDto, Role.class), roleRequestDto.getPermissionIds());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.ADD_ROLE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = AuthEndpointURI.ROLES)
  public ResponseEntity<Object> getAllRoles() {
    return new ResponseEntity<>(
        new ContentResponse<>(AuthConstants.ROLES,
            mapper.map(roleService.getAllRoles(), RoleResponsedto.class), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }


  @DeleteMapping(value = AuthEndpointURI.DELETE_ROLE_BY_ID)
  public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
    if (roleService.isRoleExists(id)) {
      roleService.deleteRole(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.ROLE_DELETED), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.ROLE_ID,
        authValidationFailureCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = AuthEndpointURI.GET_ROLE_BY_ID)
  public ResponseEntity<Object> getRoleById(@PathVariable Long id) {
    if (roleService.isRoleExists(id)) {
      return new ResponseEntity<>(new ContentResponse<>(AuthConstants.ROLE,
          mapper.map(roleService.findRoleById(id), RoleResponsedto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.ROLE_ID,
        authValidationFailureCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = AuthEndpointURI.ROLE)
  public ResponseEntity<Object> updateRole(@Valid @RequestBody RoleRequestDto roleRequestDto) {
    if (roleService.isRoleExists(roleRequestDto.getId())) {
      if (roleService.isUpdatedRoleExists(roleRequestDto.getId(), roleRequestDto.getRoleName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.ROLE,
            authValidationFailureCodes.getRoleAlreadyExists()), HttpStatus.BAD_REQUEST);
      }
      roleService.saveRole(mapper.map(roleRequestDto, Role.class), roleRequestDto.getPermissionIds());
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, AuthConstants.ROLE_UPDATED_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(AuthConstants.ROLE,
        authValidationFailureCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }
}
