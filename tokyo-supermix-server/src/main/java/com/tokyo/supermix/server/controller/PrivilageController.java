package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PrivilageRequestDto;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.server.services.PrivilageService;
import com.tokyo.supermix.util.Constants;


@CrossOrigin
@RestController
public class PrivilageController {
  @Autowired
  private PrivilageService privilageService;
  private static final Logger logger = Logger.getLogger(PrivilageController.class);

  @PostMapping(value = EndpointURI.PRIVILAGE)
  public ResponseEntity<Object> createPrivilage(
      @Valid @RequestBody List<PrivilageRequestDto> privilageRequestDtos) {
    privilageService.addDeleteRolePermissions(privilageRequestDtos);    
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.PERMISSIONS_SUCCESS),
        HttpStatus.OK);
  }
}
