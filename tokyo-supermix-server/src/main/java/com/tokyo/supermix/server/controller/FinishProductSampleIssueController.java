package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.FinishProductSampleIssueRequestDto;
import com.tokyo.supermix.data.dto.FinishProductSampleIssueResponseDto;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.FinishProductSampleIssueService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class FinishProductSampleIssueController {

  @Autowired
  FinishProductSampleIssueService finishProductSampleIssueService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
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
  public ResponseEntity<Object> deleteFinishProductSampleIssue(@PathVariable Long id) {
    if (finishProductSampleIssueService.isIdExists(id)) {
      finishProductSampleIssueService.deleteFinishProductSampleIssue(id);
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
  public ResponseEntity<Object> getFinishProductSampleIssueById(@PathVariable Long id) {
    if (finishProductSampleIssueService.isIdExists(id)) {
      logger.debug("Get finish product sample issue by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ISSUE,
              mapper.map(finishProductSampleIssueService.getFinishProductSampleIssueById(id),
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
    if (finishProductSampleIssueService.isIdExists(finishProductSampleIssueRequestDto.getId())) {
      finishProductSampleIssueService.saveFinishProductSampleIssue(
          mapper.map(finishProductSampleIssueRequestDto, FinishProductSampleIssue.class));
      return new ResponseEntity<Object>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_FINISH_PRODUCT_SAMPLE_ISSUE_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.FINISH_PRODUCT_SAMPLE_ISSUE_ID,
            validationFailureStatusCodes.getFinishProductSampleIssueAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE_SEARCH)
  public ResponseEntity<Object> getFinishProductSampleIssueBySearch(
      @QuerydslPredicate(root = FinishProductSampleIssue.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.FINISH_PRODUCT_SAMPLE_ISSUES,
        finishProductSampleIssueService.searchFinishProductSampleIssue(predicate, size, page),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
