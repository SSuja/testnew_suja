package com.tokyo.supermix.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ProjectRequestDto;
import com.tokyo.supermix.data.dto.ProjectResponseDto;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ProjectService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ProjectService projectService;
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
    List<Project> projectList = projectService.getAllProjects();
    return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
        mapper.map(projectList, ProjectResponseDto.class), RestApiResponseStatus.OK),
        HttpStatus.OK);
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
      return new ResponseEntity<>(new ContentResponse<>(Constants.TEST,
          mapper.map(projectService.getProjectByCode(code), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Project record exist for given code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TEST_ID,
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

}
