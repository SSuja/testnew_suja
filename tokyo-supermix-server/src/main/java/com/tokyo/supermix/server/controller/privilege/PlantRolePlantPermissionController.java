package com.tokyo.supermix.server.controller.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.privilege.PlantRolePlantPermissionServices;
import com.tokyo.supermix.util.Constants;

public class PlantRolePlantPermissionController {
	
	@Autowired
	private PlantRolePlantPermissionServices plantRolePlantPermissionServices;
	
//	 @GetMapping(value = EndpointURI.ROLE_PLANT_PERMISSIONS)
//	  public ResponseEntity<Object> getAllRolePlantPermission() {
//	    return new ResponseEntity<>(new ContentResponse<>(Constants.MAIN_MODULES,
//	    		plantRolePlantPermissionServices.getAllRolePlantPermissions(), RestApiResponseStatus.OK), null, HttpStatus.OK);
//	  }

}
