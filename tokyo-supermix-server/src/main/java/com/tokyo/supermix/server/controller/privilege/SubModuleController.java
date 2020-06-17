package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.PrivilegeEndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.MainModuleService;
import com.tokyo.supermix.server.services.privilege.SubModuleService;
import com.tokyo.supermix.util.Constants;

@CrossOrigin
@RestController
public class SubModuleController {
  @Autowired
  private SubModuleService subModuleService;
  @Autowired
  private MainModuleService mainModuleService;

  @GetMapping(value = PrivilegeEndpointURI.SUB_MODULES_BY_MAIN_MODULE)
  public ResponseEntity<Object> getSubModulesByMainModule(@PathVariable String mainModule) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.SUB_MODULES,
        subModuleService.getSubModulesByMainModule(
            mainModuleService.findByMainModuleName(mainModule).getId()),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
