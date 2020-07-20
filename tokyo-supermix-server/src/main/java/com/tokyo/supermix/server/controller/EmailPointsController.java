package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.EmailPointsRequestDto;
import com.tokyo.supermix.data.dto.EmailPointsResponseDto;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EmailPointsService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class EmailPointsController {
  @Autowired
  private EmailPointsService emailPointsService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  private static final Logger logger = Logger.getLogger(EmailPointsController.class);

  // Add API
  @PostMapping(value = EndpointURI.EMAIL_POINT)
  public ResponseEntity<Object> createEmailPoints(
      @RequestBody EmailPointsRequestDto emailPointsRequestDto) {
    if (emailPointsService.isEmailPointsExist(emailPointsRequestDto)) {
      logger.debug("email points is already exists: createEmailPoints(), isEmailPointsExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_POINT,
          validationFailureStatusCodes.getEmailPointsAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    emailPointsService.createEmailPoints(mapper.map(emailPointsRequestDto, EmailPoints.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EMAIL_POINT_SUCCESS),
        HttpStatus.OK);
  }

  // Get API
  @GetMapping(value = EndpointURI.EMAIL_POINTS)
  public ResponseEntity<Object> getAllEmailPoints() {
    logger.debug("get all email points");
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_POINTS,
        mapper.map(emailPointsService.getAllEmailPoints(), EmailPointsResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Get By Status
  @GetMapping(value = EndpointURI.EMAIL_POINTS_BY_STATUS)
  public ResponseEntity<Object> getAllEmailPointsByStatus(@PathVariable boolean status) {
    logger.debug("get all email points");
    return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_POINTS, mapper
        .map(emailPointsService.getAllEmailPointsByStatus(status), EmailPointsResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @PutMapping(value = EndpointURI.EMAIL_POINT)
  public ResponseEntity<Object> updateEmailPointsStatus(
      @RequestBody EmailPointsRequestDto emailPointsRequestDto) {
    if (emailPointsService.isEmailPointIdExists(emailPointsRequestDto.getId())) {
      emailPointsService
          .updateEmailPointStatus(mapper.map(emailPointsRequestDto, EmailPoints.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EMAIL_POINT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMAIL_POINT_ID,
        validationFailureStatusCodes.getEmailPointsNotExist()), HttpStatus.BAD_REQUEST);

  }
}
