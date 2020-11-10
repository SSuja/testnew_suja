package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.FinishProductSampleIssueRequestDto;
import com.tokyo.supermix.data.dto.FinishProductSampleIssueResponseDto;
import com.tokyo.supermix.data.dto.FinishProductSampleResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FinishProductSampleIssueService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin(origins = "*")
public class FinishProductSampleIssueController {
  @Autowired
  FinishProductSampleIssueService finishProductSampleIssueService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantService plantService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(FinishProductSampleIssueController.class);

  @PostMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE)
  public ResponseEntity<Object> saveFinishProductSampleIssue(
      @Valid @RequestBody FinishProductSampleIssueRequestDto finishProductSampleIssueRequestDto) {
    finishProductSampleIssueService.saveFinishProductSampleIssue(
        mapper.map(finishProductSampleIssueRequestDto, FinishProductSampleIssue.class));
    return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_FINISH_PRODUCT_SAMPLE_ISSUE_SUCCESS), HttpStatus.OK);
  }


  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUES)
  public ResponseEntity<Object> getAllFinishProductSampleIssues() {
    return new ResponseEntity<>(
        new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ISSUES,
            mapper.map(finishProductSampleIssueService.getAllFinishProductSampleIssues(),
                FinishProductSampleIssueResponseDto.class),
            RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE_BY_ID)
  public ResponseEntity<Object> deleteFinishProductSampleIssue(@PathVariable String code) {
    if (finishProductSampleIssueService.isCodeExists(code)) {
      finishProductSampleIssueService.deleteFinishProductSampleIssue(code);
      return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.FINISH_PRODUCT_SAMPLE_ISSUE_DELETED), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ISSUE_ID,
            validationFailureStatusCodes.getFinishProductSampleIssueNotExists()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE_BY_ID)
  public ResponseEntity<Object> getFinishProductSampleIssueById(@PathVariable String code) {
    if (finishProductSampleIssueService.isCodeExists(code)) {
      logger.debug("Get finish product sample issue by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ISSUE,
              mapper.map(finishProductSampleIssueService.getFinishProductSampleIssueById(code),
                  FinishProductSampleIssueResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ISSUE_ID,
            validationFailureStatusCodes.getFinishProductSampleIssueNotExists()),
        HttpStatus.BAD_REQUEST);
  }


  @PutMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE)
  public ResponseEntity<Object> updateFinishProductSampleIssue(
      @Valid @RequestBody FinishProductSampleIssueRequestDto finishProductSampleIssueRequestDto) {
    if (finishProductSampleIssueService
        .isCodeExists(finishProductSampleIssueRequestDto.getCode())) {
      finishProductSampleIssueService.saveFinishProductSampleIssue(
          mapper.map(finishProductSampleIssueRequestDto, FinishProductSampleIssue.class));
      return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_FINISH_PRODUCT_SAMPLE_ISSUE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ISSUE_ID,
            validationFailureStatusCodes.getFinishProductSampleIssueNotExists()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUES_BY_PLANT_CODE)
  public ResponseEntity<Object> getFinishProductSampleIssueByPlantCode(
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantService.isPlantExist(plantCode)) {
      pagination.setTotalRecords(
          finishProductSampleIssueService.countFinishProductSampleIssueByPlant(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ISSUES,
              mapper.map(finishProductSampleIssueService.getFinishProductSampleIssueByPlantCode(
                  plantCode, pageable), FinishProductSampleIssueResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUES_BY_PLANT)
  public ResponseEntity<Object> getAllFinishProductSampleIssuesByPlant(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(finishProductSampleIssueService.countFinishProductSampleIssue());
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleIssueService.getAllFinishProductSampleIssueByPlant(
              currentUser, pageable), FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
        PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE_ISSUE).contains(plantCode)) {
      pagination.setTotalRecords(
          finishProductSampleIssueService.countFinishProductSampleIssueByPlant(plantCode));
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLES,
          mapper.map(finishProductSampleIssueService.getFinishProductSampleIssueByPlantCode(
              plantCode, pageable), FinishProductSampleResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE_SEARCH)
  public ResponseEntity<Object> getFinishProductSampleIssueSearch(@PathVariable String plantCode,
      @RequestParam(name = "workOrderNumber", required = false) String workOrderNumber,
      @RequestParam(name = "materialName", required = false) String materialName,
      @RequestParam(name = "mixDesignCode", required = false) String mixDesignCode,
      @RequestParam(name = "pourName", required = false) String pourName,
      @RequestParam(name = "projectName", required = false) String projectName,
      @RequestParam(name = "customerName", required = false) String customerName,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(
        new PaginatedContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ISSUES,
            finishProductSampleIssueService.searchFinishProductSampleIssue(booleanBuilder,
                workOrderNumber, materialName, mixDesignCode, pourName, projectName, customerName,
                plantCode, pageable, pagination),
            RestApiResponseStatus.OK, pagination),
        null, HttpStatus.OK);
  }
}
