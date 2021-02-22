package com.tokyo.supermix.server.controller.auth;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.auth.RoleDto;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.RoleService;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class RoleController {
  @Autowired
  private RoleService roleService;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(RoleController.class);

  @PostMapping(value = PrivilegeEndpointURI.ROLE)
  public ResponseEntity<Object> createRole(@Valid @RequestBody RoleDto roleDto) {
    if (roleService.existsByRoleName(roleDto.getName())) {
      return new ResponseEntity<>(
          new ValidationFailureResponse(PrivilegeConstants.ROLE,
              privilegeValidationFailureStatusCodes.getRoleNameAlreadyExists()),
          HttpStatus.BAD_REQUEST);
    }
    roleService.createRole(mapper.map(roleDto, Role.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.ADD_ROLE_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.ROLES)
  public ResponseEntity<Object> getAllRoll() {
    List<RoleDto> roleDtoList = mapper.map(roleService.getAllRoles(), RoleDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(PrivilegeConstants.ROLES, roleDtoList, RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @DeleteMapping(value = PrivilegeEndpointURI.ROLE_BY_ID)
  public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
    if (roleService.isRoleExists(id)) {
      roleService.deleteRole(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.ROLE_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.ROLE_ID,
        privilegeValidationFailureStatusCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = PrivilegeEndpointURI.ROLE_BY_ID)
  public ResponseEntity<Object> getRoleById(@PathVariable Long id) {
    if (roleService.isRoleExists(id)) {
      return new ResponseEntity<>(
          new ContentResponse<>(PrivilegeConstants.ROLE,
              mapper.map(roleService.findRoleById(id), RoleDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.ROLE_ID,
        privilegeValidationFailureStatusCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = PrivilegeEndpointURI.ROLE)
  public ResponseEntity<Object> updateRole(@Valid @RequestBody RoleDto roleDto) {
    if (roleService.isRoleExists(roleDto.getId())) {
      if (roleService.isUpdatedRoleExists(roleDto.getId(), roleDto.getName())) {
        return new ResponseEntity<>(
            new ValidationFailureResponse(PrivilegeConstants.ROLE,
                privilegeValidationFailureStatusCodes.getRoleAlreadyExists()),
            HttpStatus.BAD_REQUEST);
      }
      roleService.updateRole(mapper.map(roleDto, Role.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, PrivilegeConstants.ROLE_UPDATED_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.ROLE,
        privilegeValidationFailureStatusCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }


  @GetMapping(value = PrivilegeEndpointURI.ROLES_PAGE)
  public ResponseEntity<Object> getAllRolesWithPagination(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    pagination.setTotalRecords(roleService.getAllRolesCount());
    return new ResponseEntity<>(new PaginatedContentResponse<>(PrivilegeConstants.ROLES,
        mapper.map(roleService.getAllWithPagination(pageable), RoleDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.ROLES_SEARCH)
  public ResponseEntity<Object> searchRoles(
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(PrivilegeConstants.ROLES, mapper
        .map(roleService.searchRoles(name, booleanBuilder, pageable, pagination), RoleDto.class),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }
}
