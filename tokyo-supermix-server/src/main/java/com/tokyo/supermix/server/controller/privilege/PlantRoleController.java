package com.tokyo.supermix.server.controller.privilege;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;
import com.tokyo.supermix.data.dto.privilege.PlantRoleResponseDto;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.RoleService;
import com.tokyo.supermix.server.services.privilege.PlantRolePlantPermissionServices;
import com.tokyo.supermix.server.services.privilege.PlantRoleService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;
import com.tokyo.supermix.util.privilege.PrivilegeValidationFailureStatusCodes;

@CrossOrigin
@RestController
public class PlantRoleController {
  @Autowired
  private PlantRoleService plantRoleService;
  @Autowired
  private PlantRolePlantPermissionServices plantRolePlantPermissionServices;
  @Autowired
  private RoleService roleService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PrivilegeValidationFailureStatusCodes privilegeValidationFailureStatusCodes;

  @PostMapping(value = PrivilegeEndpointURI.PLANT_ROLE)
  public ResponseEntity<Object> createPlantRole(@Valid @RequestBody PlantRoleDto plantRoleDto) {
    if (roleService.existsByRole(plantRoleDto.getRoleId())) {
      if (plantService.isPlantExist(plantRoleDto.getPlantCode())) {
        if (plantRoleService.existsByPlantCodeAndRoleId(plantRoleDto.getPlantCode(),
            plantRoleDto.getRoleId())) {
          return new ResponseEntity<>(
              new ValidationFailureResponse(PrivilegeConstants.ROLE,
                  privilegeValidationFailureStatusCodes.getRoleAlreadyExists()),
              HttpStatus.BAD_REQUEST);
        }
        PlantRole plantRole =
            plantRoleService.savePlantRole(plantRoleDto.getPlantCode(), plantRoleDto.getRoleId());
        plantRolePlantPermissionServices.createPlantRolePlantPermission(plantRole);
        return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
            PrivilegeConstants.ADD_PLANT_ROLE_SUCCESS), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_CODE,
          privilegeValidationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.ROLE_ID,
        privilegeValidationFailureStatusCodes.getRoleNotExists()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLES)
  public ResponseEntity<Object> getPlantRoles() {
    return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_ROLES,
        mapper.map(plantRoleService.getAllPlantRole(), PlantRoleDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLE_BY_ROLE_NAME)
  public ResponseEntity<Object> getPlantRolesByRoleName(@PathVariable String roleName) {
    if (roleService.existsByRoleName(roleName)) {
      return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_ROLES,
          mapper.map(plantRoleService.getPlantRolesByRoleName(roleName), PlantRoleDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.ROLE,
        privilegeValidationFailureStatusCodes.getRoleAlreadyExists()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLES_BY_PLANT_CODE)
  public ResponseEntity<Object> getPlantRolesByPlantCode(@PathVariable String plantCode) {
    if (plantRoleService.existsByPlantCode(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(PrivilegeConstants.PLANT_ROLES, mapper
          .map(plantRoleService.getAllPlantRolesByPlantCode(plantCode), PlantRoleResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_CODE,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLES_BY_PLANT_WISE)
  public ResponseEntity<Object> getAllPlantRoles(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(plantRoleService.getCountPlantRole());
      return new ResponseEntity<>(new PaginatedContentResponse<>(PrivilegeConstants.PLANT_ROLES,
          mapper.map(plantRoleService.getAllPlantRole(pageable), PlantRoleDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (plantService.isPlantExist(plantCode)) {
      pagination.setTotalRecords(plantRoleService.getCountPlantRoleByPlantCode(plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(PrivilegeConstants.PLANT_ROLES,
          mapper.map(plantRoleService.getPlantRoleByPlantCode(plantCode, pageable),
              PlantRoleDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_CODE,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.SEARCH_PLANT_ROLE)
  public ResponseEntity<Object> getPlantRoleSearch(@PathVariable String plantCode,
      @RequestParam(name = "roleName", required = false) String roleName,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(
        PrivilegeConstants.PLANT_ROLES, plantRoleService.searchPlantRole(booleanBuilder, roleName,
            name, plantCode, plantName, pageable, pagination),
        RestApiResponseStatus.OK, pagination), HttpStatus.OK);
  }

  @GetMapping(value = PrivilegeEndpointURI.PLANT_ROLES_BY_PLANT_WISE_PAGE)
  public ResponseEntity<Object> getAllPlantRolesWIhPagination(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(plantRoleService.getCountPlantRole());
      return new ResponseEntity<>(new PaginatedContentResponse<>(PrivilegeConstants.PLANT_ROLES,
          mapper.map(plantRoleService.getAllPlantRole(pageable), PlantRoleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (plantService.isPlantExist(plantCode)) {
      pagination.setTotalRecords(plantRoleService.getCountPlantRoleByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(PrivilegeConstants.PLANT_ROLES,
              mapper.map(plantRoleService.getPlantRoleByPlantCode(plantCode, pageable),
                  PlantRoleResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(PrivilegeConstants.PLANT_CODE,
        privilegeValidationFailureStatusCodes.getPlantRoleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = PrivilegeEndpointURI.SEARCH_PLANT_ROLE_PAGE)
  public ResponseEntity<Object> getPlantRoleSearchWithPagination(@PathVariable String plantCode,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(PrivilegeConstants.PLANT_ROLES,
            plantRoleService.searchPlantRoleWithPagination(booleanBuilder, name, plantCode,
                plantName, pageable, pagination),
            RestApiResponseStatus.OK, pagination),
        HttpStatus.OK);
  }
}

