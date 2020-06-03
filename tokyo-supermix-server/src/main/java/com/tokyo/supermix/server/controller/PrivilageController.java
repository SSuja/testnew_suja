package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PrivilageRequestDto;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PrivilageService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;


@CrossOrigin
@RestController
public class PrivilageController {
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PrivilageService privilageService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(PrivilageController.class);

  @PostMapping(value = EndpointURI.PRIVILAGE)
  public ResponseEntity<Object> createPrivilage(
      @Valid @RequestBody List<PrivilageRequestDto> privilageRequestDtos) {
    privilageService.addDeleteRolePermissions(privilageRequestDtos);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.PERMISSIONS_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_PRIVILAGE_BY_ROLE_ID)
  public ResponseEntity<Object> getPermissionByRoleId(@PathVariable Long roleId) {
    if (roleRepository.existsById(roleId)) {

      return new ResponseEntity<>(new ContentResponse<>(Constants.PERMISSION,
          privilageService.getPermission(roleId), RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.ROLE,
        validationFailureStatusCodes.getRoleNotExist()), HttpStatus.BAD_REQUEST);
  }
}
