package com.tokyo.supermix.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.EmailGroupService;
import com.tokyo.supermix.util.Constants;


@CrossOrigin(origins = "*")
@RestController
public class EmailGroupController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmailGroupService emailGroupService;
  
  @GetMapping(value = EndpointURI.EMAIL_QROUPS)
  public ResponseEntity<Object> getAllEmailGroups() {
       return new ResponseEntity<>(new ContentResponse<>(Constants.EMAIL_QROUPS,
        mapper.map(emailGroupService.getAllEmailGroups(), EmailGroup.class), RestApiResponseStatus.OK),
        null, HttpStatus.OK);
  }

}
