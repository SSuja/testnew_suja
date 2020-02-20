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
import com.tokyo.supermix.data.dto.EmployeeRequestDto;
import com.tokyo.supermix.data.dto.EmployeeResponseDto;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmployeeService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin("*")
@RestController
public class EmployeeController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmployeeService employeeService;
  private static final Logger logger = Logger.getLogger(EmployeeController.class);

  // Add Employee
  @PostMapping(value = EndpointURI.EMPLOYEE)
  public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
    if (employeeService.isEmailExist(employeeDto.getEmail())) {
      logger.debug("email is already exists: createEmployee(), isEmailAlreadyExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
          validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Employee employee = mapper.map(employeeDto, Employee.class);
    employeeService.createEmployee(employee);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMPLOYEE_SUCCESS),
        HttpStatus.OK);
  }

  // Delete Employee
  @DeleteMapping(value = EndpointURI.DELETE_EMPLOYEE)
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
  @GetMapping(value = EndpointURI.GET_EMPLOYEE_BY_ID)
  public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
    if (employeeService.isEmployeeExist(id)) {
      logger.debug("Get Employee By Id");
      Employee employee = employeeService.getEmployeeById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.EMPLOYEE,
          mapper.map(employee, EmployeeResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
        validationFailureStatusCodes.getEmployeeNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update Employee
  @PutMapping(value = EndpointURI.UPDATE_EMPLOYEE)
  public ResponseEntity<Object> updateEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
    if (employeeService.isEmployeeExist(employeeDto.getId())) {
      if (employeeService.isUpdatedEmployeeEmailExist(employeeDto.getId(),
          employeeDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getEmployeeAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      Employee employee = mapper.map(employeeDto, Employee.class);
      employeeService.updateEmployee(employee);
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
    List<Employee> employeeList = employeeService.getAllEmployees();
    List<EmployeeResponseDto> employeeDtoList = mapper.map(employeeList, EmployeeResponseDto.class);
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.EMPLOYEES, employeeDtoList, RestApiResponseStatus.OK), null,
        HttpStatus.OK);
  }

}
