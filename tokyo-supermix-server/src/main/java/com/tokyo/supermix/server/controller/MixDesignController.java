package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.tokyo.supermix.data.dto.MixDesignRequestDto;
import com.tokyo.supermix.data.dto.MixDesignResponseDto;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.MaterialCategoryService;
import com.tokyo.supermix.server.services.MixDesignService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@RestController
@CrossOrigin(origins = "*")
public class MixDesignController {

  @Autowired
  MixDesignService mixDesignService;
  @Autowired
  private PlantService plantService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  MaterialCategoryService materialCategoryService;
  @Autowired
  private Mapper mapper;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(MixDesignController.class);

  @PostMapping(value = EndpointURI.MIX_DESIGN)
  public String saveMixDesign(@Valid @RequestBody MixDesignRequestDto mixDesignRequestDto) {
    return mixDesignService.saveMixDesign(mapper.map(mixDesignRequestDto, MixDesign.class));
  }

  @GetMapping(value = EndpointURI.MIX_DESIGNS)
  public ResponseEntity<Object> getAllMixDesigns() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
        mapper.map(mixDesignService.getAllMixDesignByDecending(), MixDesignResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.MIX_DESIGN_BY_CODE)
  public ResponseEntity<Object> deleteMixDesign(@PathVariable String code) {
    if (mixDesignService.isCodeExist(code)) {
      mixDesignService.deleteMixDesign(code);
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.MIX_DESIGN_DELETED),
          HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_BY_CODE)
  public ResponseEntity<Object> getMixDesignById(@PathVariable String code) {
    if (mixDesignService.isCodeExist(code)) {
      logger.debug("Get mix design by id ");
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGN,
          mapper.map(mixDesignService.getMixDesignByCode(code), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid Id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN_CODE,
        validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MIX_DESIGN)
  public ResponseEntity<Object> updateMixDesign(
      @Valid @RequestBody MixDesignRequestDto mixDesignRequestDto) {
    if (mixDesignService.isCodeExist(mixDesignRequestDto.getCode())) {
      mixDesignService.saveMixDesign(mapper.map(mixDesignRequestDto, MixDesign.class));
      return new ResponseEntity<Object>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MIX_DESIGN_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN,
        validationFailureStatusCodes.getMixDesignAlreadyExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_BY_PLANT)
  public ResponseEntity<Object> getMixDesignByPlant(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANT_ID, mapper
          .map(mixDesignService.getMixDesignByPlantCode(plantCode), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No MixDesign record exist for given Plant Code");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT_ID,
          validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_BY_STATUS)
  public ResponseEntity<Object> getMixDesignByStatus(@PathVariable Status status) {
    if (mixDesignService.isMixDesignStatusExist(status)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
          mapper.map(mixDesignService.getMixDesignByStatus(status), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No MixDesign record exist for given Status");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN,
          validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_BY_PLANT)
  public ResponseEntity<Object> getAllMixDesignsByPlant(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode, @RequestParam(name = "page") int page,
      @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination.setTotalRecords(mixDesignService.getCountMixDesign());
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.MIX_DESIGNS,
          mapper.map(mixDesignService.getAllMixDesign(pageable), MixDesignResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_MIX_DESIGN)
        .contains(plantCode)) {
      pagination.setTotalRecords(mixDesignService.getCountMixDesignByPlantCode(plantCode));
      return new ResponseEntity<>(
          new PaginatedContentResponse<>(Constants.MIX_DESIGNS,
              mapper.map(mixDesignService.getMixDesignByPlantCode(plantCode, pageable),
                  MixDesignResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGN_BY_RAW_MATERIAL)
  public ResponseEntity<Object> getMixDesignsByRawMaterialId(@PathVariable Long rawMaterialId) {
    if (mixDesignService.isRawMaterialExists(rawMaterialId)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.MIX_DESIGNS,
          mapper.map(mixDesignService.getMixDesignsByRawMaterialId(rawMaterialId),
              MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    } else {
      logger.debug("No MixDesign record exist for given rawMaterialId");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MIX_DESIGN,
          validationFailureStatusCodes.getMixDesignNotExist()), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = EndpointURI.MIX_DESIGN_SEARCH)
  public ResponseEntity<Object> getMixDesign(@PathVariable String plantCode,
      @RequestParam(name = "materialName", required = false) String materialName,
      @RequestParam(name = "subCategoryName", required = false) String subCategoryName,
      @RequestParam(name = "plantName", required = false) String plantName,
      @RequestParam(name = "status", required = false) String status,
      @RequestParam(name = "date", required = false) String date,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(0, 0, totalpage, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.MIX_DESIGN,
        mixDesignService.searchMixDesign(booleanBuilder, materialName, subCategoryName, plantName,
            plantCode, status, date, pageable, pagination),
        RestApiResponseStatus.OK, pagination), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGNS_BY_PLANT)
  public ResponseEntity<Object> getCodeSearch(@PathVariable String plantCode,
      @RequestParam(name = "code") String code) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
          mapper.map(mixDesignService.getCode(code), MixDesignResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL, mapper
        .map(mixDesignService.getCodeByPlantCode(plantCode, code), MixDesignResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_MIX_DESIGNS_BY_RAW_MATERIAL_WITH_STATUS)
  public ResponseEntity<Object> getCodeWithMaterialIdSearch(@PathVariable Long rawMaterialId,
      @PathVariable Status status, @RequestParam(name = "code") String code) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.RAW_MATERIAL,
        mapper.map(mixDesignService.getCodeAndRawMaterialId(rawMaterialId, status, code),
            MixDesignResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }
  
  @GetMapping(value = EndpointURI.MIX_DESIGNS_WITH_TOKEN)
  public String getMixDesignsBy(@PathVariable String confirmationToken) {
	  mixDesignService.updateMixDesignWithConfirmation(confirmationToken);
    return "<div ><div style='display:flex,flexDirection:row'><div><img src='https://upload.wikimedia.org/wikipedia/commons/a/ac/Green_tick.svg' alt='Verified Image Not Found' width='100' height='100'></div><div style='color:darkblue'><h1>This MixDesign Successfully Approved</h1></div></div></div>";
  }
}
