package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.MainModuleService;
import com.tokyo.supermix.util.Constants;

@CrossOrigin
@RestController
public class MainModuleController {
  @Autowired
  private MainModuleService mainModuleService;

  @GetMapping(value = EndpointURI.MAIN_MODULES)
  public ResponseEntity<Object> getallMainModules() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MAIN_MODULES,
        mainModuleService.getMainModules(), RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MODULE_ROLE_PERMISSIONS)
  public ResponseEntity<Object> getAllModulePermissions() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSIONS,
        mainModuleService.getModulePermissions(), RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
