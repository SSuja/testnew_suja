package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.tokyo.supermix.config.export.CustomerFillManager;
import com.tokyo.supermix.config.export.CustomerLayouter;
import com.tokyo.supermix.config.export.EnrollWriter;
import com.tokyo.supermix.data.dto.CustomerRequestDto;
import com.tokyo.supermix.data.dto.CustomerResponseDto;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.CustomerService;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.FileStorageConstants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin
public class CustomerController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private CustomerService customerService;
  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(CustomerController.class);

  @GetMapping(value = EndpointURI.CUSTOMER_BY_PLANT)
  public ResponseEntity<Object> getAllCustomersByCurrentUserPermission(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(customerService.getCountCustomer());
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.CUSTOMERS,
              customerService.getAllCustomer(pageable), RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_CUSTOMER)
        .contains(plantCode)) {
      pagination.setTotalRecords(customerService.getCountCustomerByPlantCode(plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.CUSTOMERS,
          customerService.getCustomerByPlantCode(plantCode, pageable), RestApiResponseStatus.OK,
          pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  // @GetMapping(value = EndpointURI.CUSTOMERS)
  // public ResponseEntity<Object> getAllCustomers() {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMERS,
  // mapper.map(customerService.getAllCustomer(), CustomerResponseDto.class),
  // RestApiResponseStatus.OK), null, HttpStatus.OK);
  // }

  @PostMapping(value = EndpointURI.CUSTOMER)
  public ResponseEntity<Object> saveCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    if (customerService.isNameExist(customerRequestDto.getName())) {
      logger.debug("Name is already exists:saveCustomer(), isNameAlreadyExist:{}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER,
          validationFailureStatusCodes.getCustomerAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    customerService.saveCustomer(mapper.map(customerRequestDto, Customer.class),
        customerRequestDto.getPlantCodes());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CUSTOMER_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CUSTOMER_BY_ID)
  public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
    if (customerService.isCustomerExist(id)) {
      logger.debug("Get Customer By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMER,
          mapper.map(customerService.getCustomerById(id), CustomerResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.CUSTOMER_BY_ID)
  public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
    if (customerService.isCustomerExist(id)) {
      logger.debug("delete customer by id");
      customerService.deleteCustomer(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CUSTOMER_DELETED), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CUSTOMER)
  public ResponseEntity<Object> updateCustomer(
      @Valid @RequestBody CustomerRequestDto customerRequestDto) {
    if (customerService.isCustomerExist(customerRequestDto.getId())) {
      if (customerService.isUpdatedCustomerEmailExist(customerRequestDto.getId(),
          customerRequestDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getEmailAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      if (customerService.isUpdatedCustomerNameExist(customerRequestDto.getId(),
          customerRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER,
            validationFailureStatusCodes.getCustomerAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      customerService.saveCustomer(mapper.map(customerRequestDto, Customer.class),
          customerRequestDto.getPlantCodes());
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CUSTOMER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CUSTOMER_ID,
        validationFailureStatusCodes.getCustomerNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.CUSTOMER_SEARCH)
  public ResponseEntity<Object> getCustomerSearch(
      @QuerydslPredicate(root = Customer.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.CUSTOMERS,
            customerService.searchCustomer(predicate, page, size), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  // @GetMapping(value = EndpointURI.GET_CUSTOMERS_BY_PLANT_CODE)
  // public ResponseEntity<Object> getCustomerByPlantCode(@PathVariable String plantCode) {
  // if (plantService.isPlantExist(plantCode)) {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.CUSTOMERS,
  // mapper.map(customerService.getCustomerByPlantCode(plantCode), CustomerResponseDto.class),
  // RestApiResponseStatus.OK), HttpStatus.OK);
  // }
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
  // validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  // }

  @GetMapping(value = EndpointURI.EXPORT_CUSTOMER)
  public ResponseEntity<Object> exportCustomer(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.CUSTOMER_WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    CustomerLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    CustomerFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        customerService.getAllCustomers());
    String fileName = FileStorageConstants.CUSTOMER_FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.IMPORT_CUSTOMER)
  public ResponseEntity<Object> uploadCustomer(@RequestParam("file") MultipartFile file) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importCustomer(file);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }
}
