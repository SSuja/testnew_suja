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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.ConcreteTestRequestDto;
import com.tokyo.supermix.data.dto.ConcreteTestResponseDto;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.ConcreteTestService;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ConcreteTestController {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  private Mapper mapper;
  @Autowired
  private ConcreteTestService concreteTestService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(ConcreteTestController.class);

  // post API for
  @PostMapping(value = EndpointURI.CONCRETE_TEST)
  public ResponseEntity<Object> createConcreteTest(
      @Valid @RequestBody ConcreteTestRequestDto concreteTestRequestDto) {
    ConcreteTest concreteTest = concreteTestService
        .saveConcreteTest(mapper.map(concreteTestRequestDto, ConcreteTest.class));
    if (concreteTest != null) {
      String message = "<p>We have got new concrteTest . The Test is " + concreteTest.getStatus()
          + " </p><ul><li> Slump : " + concreteTest.getSlump() + "</li><li> Mixdesign code : "
          + concreteTest.getFinishProductSample().getMixDesign().getCode() + "</li></ul>";
      emailService.sendMailWithFormat(mailConstants.getMailNewConcreteTest(),
          Constants.SUBJECT_NEW_CONGRETE_TEST, message);
    }
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_CONCRETE_TEST_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.CONCRETE_TESTS)
  public ResponseEntity<Object> getAllConcreteTest() {
    List<ConcreteTest> concreteTestList = concreteTestService.getAllConcreteTests();
    return new ResponseEntity<Object>(
        new ContentResponse<>(Constants.CONCRETE_TESTS,
            mapper.map(concreteTestList, ConcreteTestResponseDto.class), RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  // get ConcreteTest by id
  @GetMapping(value = EndpointURI.CONCRETE_TEST_BY_ID)
  public ResponseEntity<Object> getConcreteTestById(@PathVariable Long id) {
    if (concreteTestService.isConcreteTestExists(id)) {
      logger.debug("Get ConcreteTest by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TEST,
          mapper.map(concreteTestService.getConcreteTestById(id), ConcreteTestResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  // get ConcreteTest Delete
  @DeleteMapping(value = EndpointURI.CONCRETE_TEST_BY_ID)
  public ResponseEntity<Object> deleteConcreteTest(@PathVariable Long id) {
    if (concreteTestService.isConcreteTestExists(id)) {
      concreteTestService.deleteConcreteTest(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.CONCRETE_TEST_DELETED),
          HttpStatus.OK);
    }
    logger.debug("invalid ConcreteTestId");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.CONCRETE_TEST)
  public ResponseEntity<Object> updateConcreteTest(
      @Valid @RequestBody ConcreteTestRequestDto concreteTestRequestDto) {
    if (concreteTestService.isConcreteTestExists(concreteTestRequestDto.getId())) {
      concreteTestService.saveConcreteTest(mapper.map(concreteTestRequestDto, ConcreteTest.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_CONCRETE_TEST_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.CONCRETE_TEST,
        validationFailureStatusCodes.getConcreteTestNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.SEARCH_CONCRETE_TEST)
  public ResponseEntity<Object> searchConcreteTest(@RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size,
      @RequestParam(name = "finishProductSampleId", required = false) Long finishProductSampleId,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "slump", required = false) Double slump,
      @RequestParam(name = "slumpMin", required = false) Double slumpMin,
      @RequestParam(name = "slumpMax", required = false) Double slumpMax,
      @RequestParam(name = "slumpGradeRatio", required = false) Double slumpGradeRatio,
      @RequestParam(name = "slumpGradeRatioMin", required = false) Double slumpGradeRatioMin,
      @RequestParam(name = "slumpGradeRatioMax", required = false) Double slumpGradeRatioMax) {
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new ContentResponse<>(Constants.CONCRETE_TESTS,
        concreteTestService.searchConcreteTest(finishProductSampleId, status, slump, slumpMin,
            slumpMax, slumpGradeRatio, slumpGradeRatioMin, slumpGradeRatioMax, booleanBuilder, page,
            size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}

