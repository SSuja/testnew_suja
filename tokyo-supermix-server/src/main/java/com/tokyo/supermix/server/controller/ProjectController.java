package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ProjectRequestDto;
import com.tokyo.supermix.data.dto.ProjectResponseDto;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.ProjectService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ProjectService projectService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(ProjectController.class);

  @PostMapping(value = EndpointURI.PROJECT)
  public ResponseEntity<Object> createProject(
      @Valid @RequestBody ProjectRequestDto projectRequestDto) {
    if (projectService.isNameExist(projectRequestDto.getName())) {
      logger.debug("name is already exists: createProject(), isNameExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT_NAME,
          validationFailureStatusCodes.getProjectAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    projectService.saveProject(mapper.map(projectRequestDto, Project.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PROJECT_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PROJECTS)
  public ResponseEntity<Object> getProjects() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
        mapper.map(projectService.getAllProjects(), ProjectResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.PROJECT_BY_PLANT)
  public ResponseEntity<Object> getProjects(@CurrentUser UserPrincipal currentUser,
      HttpSession session) {
    String plantCode = (String) session.getAttribute(Constants.SESSION_PLANT);
    if (plantCode == null) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
          mapper.map(projectService.getAllProjectsByPlant(currentUser), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PROJECT)
        .contains(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
          mapper.map(projectService.getProjectByPlantCode(plantCode), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);

  }

  @DeleteMapping(value = EndpointURI.PROJECT_BY_ID)
  public ResponseEntity<Object> deleteProject(@PathVariable String code) {
    if (projectService.isProjectExist(code)) {
      logger.debug("delete project by code");
      projectService.deleteProject(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PROJECT_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT_CODE,
        validationFailureStatusCodes.getProjectNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PROJECT_BY_ID)
  public ResponseEntity<Object> getProjectByCode(@PathVariable String code) {
    if (projectService.isProjectExist(code)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECT,
          mapper.map(projectService.getProjectByCode(code), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Project record exist for given code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT_CODE,
        validationFailureStatusCodes.getProjectNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PROJECT)
  public ResponseEntity<Object> updateProject(
      @Valid @RequestBody ProjectRequestDto projectRequestDto) {
    if (projectService.isProjectExist(projectRequestDto.getCode())) {
      if (projectService.isUpdatedProjectExist(projectRequestDto.getCode(),
          projectRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT_NAME,
            validationFailureStatusCodes.getProjectAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      projectService.saveProject(mapper.map(projectRequestDto, Project.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PROJECT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT_CODE,
        validationFailureStatusCodes.getProjectNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_PROJECT)
  public ResponseEntity<Object> getProjectSearch(
      @QuerydslPredicate(root = Project.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.PROJECTS,
            projectService.searchProject(predicate, size, page), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_PROJECTS_BY_PLANT_CODE)
  public ResponseEntity<Object> getProjectByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
          mapper.map(projectService.getProjectByPlantCode(plantCode), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}
