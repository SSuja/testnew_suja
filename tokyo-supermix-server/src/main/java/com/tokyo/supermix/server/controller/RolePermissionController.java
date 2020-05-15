package com.tokyo.supermix.server.controller;

import java.util.List;
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
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.RolePermissionRequestDto;
import com.tokyo.supermix.data.dto.RolePermissionResponseDto;
import com.tokyo.supermix.data.entities.RolePermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
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

  @PutMapping(value = EndpointURI.ROLE_PERMISSION)
  public ResponseEntity<Object> updateRolePermission(
      @Valid @RequestBody RolePermissionRequestDto rolePermissionRequestDto) {
    if (rolePermissionService.isRolePermissionExist(rolePermissionRequestDto.getId())) {
      if (rolePermissionService.isDuplicateRowExists(rolePermissionRequestDto.getRoleId(),
          rolePermissionRequestDto.getPermissionId())) {
        logger.debug("row is already exists");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.ROLE_PERMISSION,
                validationFailureStatusCodes.getRolePermissionAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
      rolePermissionService
          .updateRolePermission(mapper.map(rolePermissionRequestDto, RolePermission.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ROLE_PERMISSION_UPDATED_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ROLE_PERMISSION,
        validationFailureStatusCodes.getRolePermissionNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.ROLE_PERMISSION_BY_ID)
  public ResponseEntity<Object> deleteRolePermissionById(@PathVariable Long id) {
    if (rolePermissionService.isRolePermissionExist(id)) {
      rolePermissionService.deleteRolePermission(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.ROLE_PERMISSION_DELETED),
          HttpStatus.OK);
    }
    logger.debug("No role permission record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ROLE_PERMISSION,
        validationFailureStatusCodes.getRolePermissionNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.ROLE_PERMISSION)
  public ResponseEntity<Object> createRolePermission(
      @RequestBody List<RolePermissionRequestDto> rolePermissionRequestDtoList) {
    for (RolePermissionRequestDto rolePermissionRequestDto : rolePermissionRequestDtoList) {
      if (rolePermissionService.isDuplicateRowExists(rolePermissionRequestDto.getRoleId(),
          rolePermissionRequestDto.getPermissionId())) {
        logger.debug("row is already exists: RolePermission create(), isDuplicateRowExists: {}");
        return new ResponseEntity<>(
            new ValidationFailureResponse(Constants.ROLE_PERMISSION,
                validationFailureStatusCodes.getRolePermissionAlreadyExist()),
            HttpStatus.BAD_REQUEST);
      }
    }
    rolePermissionService
        .saveRolePermission(mapper.map(rolePermissionRequestDtoList, RolePermission.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EQUATION_PARAMETER_SUCCESS),
        HttpStatus.OK);
  }
}
