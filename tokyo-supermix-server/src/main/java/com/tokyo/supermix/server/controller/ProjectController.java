package com.tokyo.supermix.server.controller;

import java.sql.Date;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.config.export.EnrollWriter;
import com.tokyo.supermix.config.export.ProjectFillManager;
import com.tokyo.supermix.config.export.ProjectLayouter;
import com.tokyo.supermix.data.dto.ProjectRequestDto;
import com.tokyo.supermix.data.dto.ProjectResponseDto;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.ProjectService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.FileStorageConstants;
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
  private PlantRepository plantRepository;
  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;

  private static final Logger logger = Logger.getLogger(ProjectController.class);

  @PostMapping(value = EndpointURI.PROJECT)
  public ResponseEntity<Object> createProject(
      @Valid @RequestBody ProjectRequestDto projectRequestDto) {
    if (projectService.isNameAndCustomerIdAndProjectExist(projectRequestDto.getName(),
        projectRequestDto.getCustomerId(), projectRequestDto.getPlantCode())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT,
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
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(projectService.getCountProject());
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PROJECTS,
          mapper.map(projectService.getAllProjectsByPlant(currentUser, pageable),
              ProjectResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PROJECT)
        .contains(plantCode)) {
      pagination.setTotalRecords(projectService.getCountProjectByPlantCode(plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PROJECTS, mapper
          .map(projectService.getProjectByPlantCode(plantCode, pageable), ProjectResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
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
      projectService.saveProject(mapper.map(projectRequestDto, Project.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PROJECT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROJECT_CODE,
        validationFailureStatusCodes.getProjectNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_PROJECT)
  public ResponseEntity<Object> getProjectSearch(@PathVariable String plantCode,
      @RequestParam(name = "code", required = false) String code,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "name", required = false) String name,
      @RequestParam(name = "customerName", required = false) String customerName,
      @RequestParam(name = "contactPerson", required = false) String contactPerson,
      @RequestParam(name = "contactNumber", required = false) String contactNumber,
      @RequestParam(name = "startDate", required = false) Date startDate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.PROJECTS,
            projectService.searchProject(booleanBuilder, code, plantName, name, customerName, contactPerson, startDate , plantCode, pageable, pagination, contactNumber), RestApiResponseStatus.OK ,pagination),
         HttpStatus.OK);
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


  @GetMapping(value = EndpointURI.GET_PROJECTS_BY_CUSTOMER)
  public ResponseEntity<Object> getProjectByCustomer(@PathVariable Long customerId) {
    if (projectService.isCustomerExistsByProject(customerId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
          mapper.map(projectService.getAllProjectsByCustomer(customerId), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_PROJECTS_BY_CUSTOMER_PLANT_WISE)
  public ResponseEntity<Object> getProjectsByCustomerIdAndPlant(@PathVariable Long customerId,
      @PathVariable String plantCode) {
    if (projectService.isCustomerExistsByProject(customerId)) {
      if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS, mapper
            .map(projectService.getAllProjectsByCustomer(customerId), ProjectResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      if (plantRepository.existsByCode(plantCode)) {
        return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
            mapper.map(projectService.getAllProjectsByCustomerAndPlant(customerId, plantCode),
                ProjectResponseDto.class),
            RestApiResponseStatus.OK), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.INCOMING_SAMPLE_CODE,
        validationFailureStatusCodes.getIncomingSampleNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.EXPORT_PROJECT)
  public ResponseEntity<Object> exportProject(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.PROJECT_WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    ProjectLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    ProjectFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        projectService.getAllProjects());
    String fileName = FileStorageConstants.PROJECT_FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.IMPORT_PROJECT)
  public ResponseEntity<Object> uploadCustomer(@RequestParam("file") MultipartFile file) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importProject(file);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_PROJECT_BY_NAME)
  public ResponseEntity<Object> getProjectNameSearch(@PathVariable String plantCode,
      @RequestParam(name = "name") String name) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS,
          mapper.map(projectService.getProjectName(name), ProjectResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.PROJECTS, mapper
        .map(projectService.getProjectNameByPlantCode(plantCode, name), ProjectResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
}

