package com.tokyo.supermix.rest.response;

import java.util.Collections;
import java.util.List;

import com.tokyo.supermix.rest.enums.RestApiResponseStatus;


public class ValidationFailureResponse extends ApiResponse {

  public ValidationFailureResponse(List<ValidationFailure> validationFailures) {
    super(RestApiResponseStatus.VALIDATION_FAILURE);
    this.validationFailures = validationFailures;
  }

  public ValidationFailureResponse(String field, String code) {
    super(RestApiResponseStatus.VALIDATION_FAILURE);
    ValidationFailure validationFailure = new ValidationFailure(field, code);
    this.validationFailures = Collections.singletonList(validationFailure);
  }

  private List<ValidationFailure> validationFailures;

  public List<ValidationFailure> getValidationFailures() {
    return validationFailures;
  }

  public void setValidationFailures(List<ValidationFailure> validationFailures) {
    this.validationFailures = validationFailures;
  }

}
