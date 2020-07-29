package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.IncomingSamplesCountService;
import com.tokyo.supermix.server.services.MaterialCategoryService;
import com.tokyo.supermix.server.services.MaterialSubCategoryService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin
public class IncomingSamplesCountController {
  @Autowired
  private IncomingSamplesCountService incomingSamplesCountService;
  @Autowired
  private MaterialCategoryService materialCategoryService;
  @Autowired
  private MaterialSubCategoryService materialSubCategoryService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  
  @GetMapping(value = EndpointURI.MATERIAL_SAMPLE_COUNT_BY_MATERIAL_SUB_CATEGORY)
  public ResponseEntity<Object> getIncomingSampleCountByMaterialSubCategory(
      @PathVariable String materialSubCategoryName,@CurrentUser UserPrincipal currentUser, HttpSession session) {
    String plantCode = (String)session.getAttribute(Constants.SESSION_PLANT);
    if(!currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS).contains(plantCode)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
   }
    if (materialSubCategoryService.isMaterialSubCategoryNameExist(materialSubCategoryName)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SAMPLE_COUNTS,
          incomingSamplesCountService.getmaterialSampleCountByMaterialSubCategory(
              materialSubCategoryService.getMaterialSubCategoryByName(materialSubCategoryName)
                  .getId(), plantCode),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_SAMPLE_COUNT_BY_MATERIAL_CATEGORY)
  public ResponseEntity<Object> getincomingSampleCountByMaterialCategory(
      @PathVariable String materialCategoryName, @CurrentUser UserPrincipal currentUser, HttpSession session) {
    String plantCode = (String)session.getAttribute(Constants.SESSION_PLANT);
    if(!currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS).contains(plantCode)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
   }
    if (materialCategoryService.isNameExist(materialCategoryName)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SAMPLE_COUNTS,
          incomingSamplesCountService.getmaterialSampleCountByMaterialCategory(
              materialCategoryService.getMaterialCategoryByName(materialCategoryName).getId(),plantCode),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_NAME,
            validationFailureStatusCodes.getMaterialCategoryAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }
  @GetMapping(value = EndpointURI.MATERIAL_SUB_CATEGORY_STATUS_COUNT)
  public ResponseEntity<Object> getCountByMaterialSubCategory(
      @PathVariable String materialSubCategoryName, @CurrentUser UserPrincipal currentUser, HttpSession session) {
    String plantCode = (String)session.getAttribute(Constants.SESSION_PLANT);   
    if(!currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS).contains(plantCode)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
   }
    if (materialSubCategoryService.isMaterialSubCategoryNameExist(materialSubCategoryName)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SAMPLE_COUNTS,
          incomingSamplesCountService.getCountByMaterialSubCategory(materialSubCategoryService
              .getMaterialSubCategoryByName(materialSubCategoryName).getId(), plantCode),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_SUB_CATEGORY,
        validationFailureStatusCodes.getMaterialSubCategoryNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MATERIAL_CATEGORY_STATUS_COUNT)
  public ResponseEntity<Object> getCountByMaterialCategory(
      @PathVariable String materialCategoryName, @CurrentUser UserPrincipal currentUser, HttpSession session) {
    String plantCode = (String)session.getAttribute(Constants.SESSION_PLANT);
    if(!currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS).contains(plantCode)) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
   }
    if (materialCategoryService.isNameExist(materialCategoryName)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.SAMPLE_COUNTS,
          incomingSamplesCountService.getCountByMaterialCategory(
              materialCategoryService.getMaterialCategoryByName(materialCategoryName).getId(),plantCode),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.MATERIAL_CATEGORY_NAME,
            validationFailureStatusCodes.getMaterialCategoryAlreadyExist()),
        HttpStatus.BAD_REQUEST);
  }
}
