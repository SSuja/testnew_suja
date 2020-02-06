package com.tokyo.supermix.rest.exception.handler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.exception.TokyoSupermixServerException;
import com.tokyo.supermix.rest.response.ApiResponse;



@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger logger = Logger.getLogger(RestResponseEntityExceptionHandler.class);

  @ExceptionHandler
  protected ResponseEntity<ApiResponse> handleServerException(TokyoSupermixServerException ex, WebRequest request) {
	  logger.error("Defect Tracker Exception occured", ex);
    return new ResponseEntity<ApiResponse>(new ApiResponse(RestApiResponseStatus.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  
}
