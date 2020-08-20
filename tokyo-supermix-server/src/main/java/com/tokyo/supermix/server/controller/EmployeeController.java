package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.config.export.EmployeeFillManager;
import com.tokyo.supermix.config.export.EmployeeLayouter;
import com.tokyo.supermix.config.export.EnrollWriter;
import com.tokyo.supermix.data.dto.EmployeeRequestDto;
import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.EmployeeService;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.FileStorageConstants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(EmployeeController.class);

  // Add Employee
  @PostMapping(value = EndpointURI.EMPLOYEE)
  public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto,
      HttpServletRequest request) {
    if (employeeService.isEmailExist(employeeDto.getEmail())) {
      logger.debug("email is already exists: createEmployee(), isEmailAlreadyExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
          validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    employeeService.createEmployee(mapper.map(employeeDto, Employee.class), request);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMPLOYEE_SUCCESS),
        HttpStatus.OK);
  }

  // Delete Employee
  @DeleteMapping(value = EndpointURI.EMPLOYEE_BY_ID)
  public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
    if (employeeService.isEmployeeExist(id)) {
      logger.debug("delete employee by id");
      employeeService.deleteEmployee(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EMPLOYEE_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get Employee By Id
  @GetMapping(value = EndpointURI.EMPLOYEE_BY_ID)
  public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
    if (employeeService.isEmployeeExist(id)) {
      logger.debug("Get Employee By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEE,
          mapper.map(employeeService.getEmployeeById(id), EmployeeResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update Employee
  @PutMapping(value = EndpointURI.EMPLOYEE)
  public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto,
      HttpServletRequest request) {
    if (employeeService.isEmployeeExist(employeeDto.getId())) {
      if (employeeService.isUpdatedEmployeeEmailExist(employeeDto.getId(),
          employeeDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      employeeService.updateEmployee(mapper.map(employeeDto, Employee.class), request);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EMPLOYEE_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

  /* Get All Employees */
  @GetMapping(value = EndpointURI.EMPLOYEES)
  public ResponseEntity<Object> getAllEmployees() {
    logger.debug("get all employee");
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEES,
        mapper.map(employeeService.getAllEmployees(), EmployeeResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EMPLOYEE_BY_PLANT)
  public ResponseEntity<Object> getAllEmployees(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEES, mapper
          .map(employeeService.getAllEmployeesByPlant(currentUser), EmployeeResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_EMPLOYEE)
        .contains(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEES,
          mapper.map(employeeService.getEmployeeByPlantCode(plantCode), EmployeeResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_EMPLOYEE)
  public ResponseEntity<Object> getEmployeeSearch(
      @QuerydslPredicate(root = Employee.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.EMPLOYEES,
            employeeService.searchEmployee(predicate, size, page), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_EMPLOYEES_BY_PLANT_CODE)
  public ResponseEntity<Object> getEmployeeByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEES,
          mapper.map(employeeService.getEmployeeByPlantCode(plantCode), EmployeeResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.EMPLOYEE_WITH_TOKEN)
  public String getEmployeeBy(@PathVariable String confirmationToken) {
    employeeService.updateEmployeeWithConfirmation(confirmationToken);
    return "<div ><div style='display:flex,flexDirection:row'><div><img src='https://upload.wikimedia.org/wikipedia/commons/a/ac/Green_tick.svg' alt='Verified Image Not Found' width='100' height='100'></div><div style='color:darkblue'><h1>Your Email Successfully Verified</h1></div></div></div>";
  }

  @GetMapping(value = EndpointURI.EXPORT_EMPLOYEE)
  public ResponseEntity<Object> exportEmployee(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.EMPLOYEE_WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    EmployeeLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    EmployeeFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        employeeService.getAllEmployees());
    String fileName = FileStorageConstants.EMPLOYEE_FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.IMPORT_EMPLOYEE)
  public ResponseEntity<Object> uploadCustomer(@RequestParam("file") MultipartFile file,
      HttpServletRequest request) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importEmployee(file, request);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }
}
