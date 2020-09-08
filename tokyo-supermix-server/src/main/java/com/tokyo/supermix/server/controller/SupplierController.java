package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.config.export.SupplierFillManager;
import com.tokyo.supermix.config.export.SupplierLayouter;
import com.tokyo.supermix.data.dto.SupplierRequestDto;
import com.tokyo.supermix.data.dto.SupplierResponseDto;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.SupplierCategoryService;
import com.tokyo.supermix.server.services.SupplierService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.FileStorageConstants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin(origins = "*")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;
  @Autowired
  private SupplierCategoryService supplierCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(SupplierController.class);

  @GetMapping(value = EndpointURI.SUPPLIERS)
  public ResponseEntity<Object> getSuppliers() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
        mapper.map(supplierService.getSuppliers(), SupplierResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SUPPLIER_BY_PLANT)
  public ResponseEntity<Object> getSuppliersByPlant(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(supplierService.getCountSupplier());
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplierService.getSuppliersByPlant(currentUser, pageable),
              SupplierResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_SUPPLIER)
        .contains(plantCode)) {
      pagination.setTotalRecords(supplierService.getCountSupplierByPlantCode(plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplierService.getSupplierByPlantCode(plantCode, pageable),
              SupplierResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PostMapping(value = EndpointURI.SUPPLIER)
  public ResponseEntity<Object> createSupplier(@Valid @RequestBody SupplierRequestDto supplierDto) {
    if (supplierService.isPhoneNumberExist(supplierDto.getPhoneNumber())) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PHONE_NUMBER,
          validationFailureStatusCodes.getEmailAlreadyExists()), HttpStatus.BAD_REQUEST);
    }
    supplierService.createSupplier(mapper.map(supplierDto, Supplier.class),
        supplierDto.getSuppilerCategoryIds());
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_SUPPLIER_SUCCESS),
        HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.SUPPLIER)
  public ResponseEntity<Object> updateSupplier(@Valid @RequestBody SupplierRequestDto supplierDto) {
    if (supplierService.isSupplierExist(supplierDto.getId())) {
      if (supplierService.isUpdatedEmailExist(supplierDto.getId(), supplierDto.getEmail())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL,
            validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      if (supplierService.isUpdatedPhoneNumberExist(supplierDto.getId(),
          supplierDto.getPhoneNumber())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.PHONE_NUMBER,
            validationFailureStatusCodes.getSupplierAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      supplierService.updateSupplier(mapper.map(supplierDto, Supplier.class),
          supplierDto.getSuppilerCategoryIds());
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_SUPPLIER_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.SUPPLIER_BY_ID)
  public ResponseEntity<Object> deleteSupplierById(@PathVariable Long id) {
    if (supplierService.isSupplierExist(id)) {
      supplierService.deleteSupplierById(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_SUPPLIER_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("Supplier doesn't exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SUPPLIER_BY_ID)
  public ResponseEntity<Object> getSupplierById(@PathVariable Long id) {
    if (supplierService.isSupplierExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplierService.getSupplierById(id), SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("No Supplier record exist for given id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
        validationFailureStatusCodes.getSupplierNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_SUPPLIER_BY_SUPPLIER_CATEGORY_ID)
  public ResponseEntity<Object> getSupplierBySupplierCategoryId(
      @PathVariable Long suppilerCategoryId, @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
          mapper.map(supplierService.findBySupplierCategoryId(suppilerCategoryId),
              SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    if (supplierCategoryService.isSupplierCategoryExist(suppilerCategoryId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER_CATEGORY,
          mapper.map(
              supplierService.findBySupplierCategoryIdAndPlantCode(suppilerCategoryId, plantCode),
              SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No Supplier record exist for given Supplier Category id");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
          validationFailureStatusCodes.getSupplierCategoryNotExit()), HttpStatus.BAD_REQUEST);
    }
  }


  // @GetMapping(value = EndpointURI.GET_SUPPLIERS_BY_PLANT_CODE)
  // public ResponseEntity<Object> getSupplierByPlantCode(@PathVariable String plantCode) {
  // if (plantService.isPlantExist(plantCode)) {
  // return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
  // mapper.map(supplierService.getSupplierByPlantCode(plantCode), SupplierResponseDto.class),
  // RestApiResponseStatus.OK), HttpStatus.OK);
  // }
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
  // validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  // }

  @GetMapping(value = EndpointURI.GET_SUPPLIERS_BY_PLANT_CODE_AND_SUPPLIER_CATEGORY)
  public ResponseEntity<Object> getSupplierByPlantCodeAndSupplierCategoryId(
      @PathVariable String plantCode, @PathVariable Long supplierCategoryId) {
    if (supplierService.isPlantCodeAndSupplierCategoryIdExist(plantCode, supplierCategoryId)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.SUPPLIER,
              mapper.map(supplierService.getByPlantCodeAndSupplierCategoryId(plantCode,
                  supplierCategoryId), SupplierResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER_CATEGORY,
        validationFailureStatusCodes.getSupplierCategoryNotExit()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.EXPORT_SUPPLIER)
  public ResponseEntity<Object> exportSupplier(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.SUPPLIER_WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    SupplierLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    SupplierFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        supplierService.getSuppliers());
    String fileName = FileStorageConstants.SUPPLIER_FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.IMPORT_SUPPLIER)
  public ResponseEntity<Object> uploadSupplier(@RequestParam("file") MultipartFile file) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importSupplier(file);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_SUPPLIER_BY_PLANT_CODE)
  public ResponseEntity<Object> getCustomerNameSearch(@PathVariable String plantCode,
      @RequestParam(name = "name") String name) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
          mapper.map(supplierService.getSupplierName(name), SupplierResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUPPLIER,
        mapper.map(supplierService.getSupplierNameByPlantCode(plantCode, name),
            SupplierResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.SUPPLIER_SEARCH)
  public ResponseEntity<Object> getSupplierSearch(@PathVariable String plantCode,
      @RequestParam(name = "name") String name,
      @RequestParam(name = "address", required = false) String address,
      @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
      @RequestParam(name = "email", required = false) String email,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination= new Pagination(0, 0, 0, 0l); 
    BooleanBuilder booleanBuilder = new BooleanBuilder(); 
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
    pagination.setTotalRecords(supplierService.getCountSupplier());
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.SUPPLIER,
            mapper.map(supplierService.searchSupplier(name, address, phoneNumber, email, plantName,
                booleanBuilder,pageable,plantCode), SupplierResponseDto.class),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
    }
    pagination.setTotalRecords(supplierService.getCountSupplierByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.SUPPLIER,
              mapper.map(supplierService.searchSupplier(name, address, phoneNumber, email, plantName,
                  booleanBuilder,pageable,plantCode), SupplierResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          null, HttpStatus.OK); 
    
  }
}
