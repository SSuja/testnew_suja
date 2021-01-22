package com.tokyo.supermix.server.controller;

import java.sql.Date;
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
import com.tokyo.supermix.data.dto.PlantEquipmentCalibrationRequestDto;
import com.tokyo.supermix.data.dto.PlantEquipmentCalibrationResponseDto;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.enums.CalibrationType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.PlantEquipmentCalibrationService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;


@CrossOrigin(origins = "*")
@RestController
public class PlantEquipmentCalibrationController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantEquipmentCalibrationService plantEquipmentCalibrationService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private PlantService plantService;
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  private static final Logger logger = Logger.getLogger(PlantEquipmentCalibrationController.class);

  // post API for PlantEquipmentCalibration
  @PostMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION)
  public ResponseEntity<Object> createPlantEquipmentCalibration(
      @Valid @RequestBody PlantEquipmentCalibrationRequestDto plantEquipmentCalibrationRequestDto) {
    if (plantEquipmentCalibrationService.existsByPlantEquipmentSerialNo(
        plantEquipmentCalibrationRequestDto.getPlantEquipmentSerialNo())) {
      Date dueDate = plantEquipmentCalibrationService.getLastDueDateByPlantEquipmentSerialNo(
          plantEquipmentCalibrationRequestDto.getPlantEquipmentSerialNo());
      if (!dueDate.before(plantEquipmentCalibrationRequestDto.getCalibratedDate())
          && dueDate != null) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.DUE_DATE,
            validationFailureStatusCodes.getDueDateExist()), HttpStatus.BAD_REQUEST);
      }
    }
    if (plantEquipmentCalibrationRequestDto.getCalibrationType() == CalibrationType.INTERNAL) {
      if (plantEquipmentCalibrationRequestDto.getEmployeeId() == null) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
            validationFailureStatusCodes.getEmployeeIdIsNull()), HttpStatus.BAD_REQUEST);
      }

    } else if (plantEquipmentCalibrationRequestDto.getSupplierId() == null) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
          validationFailureStatusCodes.getSupplierIdIsNull()), HttpStatus.BAD_REQUEST);
    }
    plantEquipmentCalibrationService.savePlantEquipmentCalibration(
        mapper.map(plantEquipmentCalibrationRequestDto, PlantEquipmentCalibration.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_EQUIPMENT_PLANT_CALIBRATION_SUCCESS), HttpStatus.OK);
  }

  // get all PlantEquipmentCalibration
  @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS)
  public ResponseEntity<Object> getAllPlantEquipmentCalibrations() {
    return new ResponseEntity<Object>(
        new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
            mapper.map(plantEquipmentCalibrationService.getAllPlantEquipmentCalibration(),
                PlantEquipmentCalibrationResponseDto.class),
            RestApiResponseStatus.OK),
        HttpStatus.OK);
  }

  // get PlantEquipmentCalibration by id
  @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION_BY_ID)
  public ResponseEntity<Object> getPlantEquipmentCalibrationById(@PathVariable Long id) {
    if (plantEquipmentCalibrationService.isPlantEquipmentCalibrationExit(id)) {
      logger.debug("Get PlantEquipmentCalibration by id ");
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATION,
              mapper.map(plantEquipmentCalibrationService.getPlantEquipmentCalibrationById(id),
                  PlantEquipmentCalibrationResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("invalid");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EQUIPMENT_PLANT_CALIBRATION,
            validationFailureStatusCodes.getPlantEquipmentCalibrationNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // DELETE API for PlantEquipmentCalibration
  @DeleteMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION_BY_ID)
  public ResponseEntity<Object> deletePlantEquipmentCalibration(@PathVariable Long id) {
    if (plantEquipmentCalibrationService.isPlantEquipmentCalibrationExit(id)) {
      plantEquipmentCalibrationService.deletePlantEquipmentCalibration(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.EQUIPMENT_PLANT_CALIBRATION_DELETED), HttpStatus.OK);
    }
    logger.debug("invalid PlantEquipmentCalibration");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EQUIPMENT_PLANT_CALIBRATION,
            validationFailureStatusCodes.getPlantEquipmentCalibrationNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // update API for PlantEquipmentCalibration
  @PutMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION)
  public ResponseEntity<Object> updatePlantEquipmentCalibration(
      @Valid @RequestBody PlantEquipmentCalibrationRequestDto plantEquipmentCalibrationRequestDto) {
    if (plantEquipmentCalibrationRequestDto.getCalibrationType() == CalibrationType.INTERNAL) {
      if (plantEquipmentCalibrationRequestDto.getEmployeeId() == null) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EMPLOYEE_ID,
            validationFailureStatusCodes.getEmployeeIdIsNull()), HttpStatus.BAD_REQUEST);
      }

    } else if (plantEquipmentCalibrationRequestDto.getSupplierId() == null) {
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.SUPPLIER,
          validationFailureStatusCodes.getSupplierIdIsNull()), HttpStatus.BAD_REQUEST);
    }
    plantEquipmentCalibrationService.savePlantEquipmentCalibration(
        mapper.map(plantEquipmentCalibrationRequestDto, PlantEquipmentCalibration.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.UPDATE_EQUIPMENT_PLANT_CALIBRATION_SUCCESS), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION_SEARCH)
  public ResponseEntity<Object> getPlantEquipmentCalibrationSearch(@PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size,
      @RequestParam(name = "serialNo", required = false) String serialNo,
      @RequestParam(name = "equipmentName", required = false) String equipmentName,
      @RequestParam(name = "calibratedDate", required = false) String calibratedDate,
      @RequestParam(name = "dueDate", required = false) String dueDate,
      @RequestParam(name = "calibrationType", required = false) CalibrationType calibrationType,
      @RequestParam(name = "supplierName", required = false) String supplierName,
      @RequestParam(name = "accuracy", required = false) String accuracy,
      @RequestParam(name = "status", required = false) Status status,
      @RequestParam(name = "employeeName", required = false) String employeeName) {
    Pageable pageable = PageRequest.of(page, size);
    Pagination pagination = new Pagination(0, 0, 0, 0l);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    if (plantCode.equalsIgnoreCase(Constants.ADMIN) || plantRepository.existsByCode(plantCode)) {
      return new ResponseEntity<>(new PaginatedContentResponse<>(Constants.PLANTEQUIPMENT,
          mapper.map(plantEquipmentCalibrationService.searchPlantEquipmentCalibration(serialNo,
              equipmentName, calibratedDate, dueDate, calibrationType, supplierName, accuracy,
              status, employeeName, booleanBuilder, page, size, pageable, plantCode, pagination),
              PlantEquipmentCalibrationResponseDto.class),
          RestApiResponseStatus.OK, pagination), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTS,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.GET_EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT_CODE)
  public ResponseEntity<Object> getCalibrationByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
              mapper.map(plantEquipmentCalibrationService.getPlantEquipmentCalibrationsByPlantCode(
                  plantCode), PlantEquipmentCalibrationResponseDto.class),
              RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  // @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT)
  // public ResponseEntity<Object> getAllPlantEquipmentCalibrationsByplant(
  // @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode) {
  // if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
  // return new ResponseEntity<Object>(
  // new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
  // mapper.map(plantEquipmentCalibrationService.getAllPlantEquipmentCalibrationsByPlant(
  // currentUser), PlantEquipmentCalibrationResponseDto.class),
  // RestApiResponseStatus.OK),
  // HttpStatus.OK);
  // }
  // if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
  // PermissionConstants.VIEW_PLANT_EQUIPMENT_CALIBRATION).contains(plantCode)) {
  // return new ResponseEntity<Object>(
  // new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
  // mapper.map(plantEquipmentCalibrationService.getPlantEquipmentCalibrationsByPlantCode(
  // plantCode), PlantEquipmentCalibrationResponseDto.class),
  // RestApiResponseStatus.OK),
  // HttpStatus.OK);
  // }
  // return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
  // validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  // }
  @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT)
  public ResponseEntity<Object> getAllPlantEquipmentCalibrationsByplant(
      @CurrentUser UserPrincipal currentUser, @PathVariable String plantCode,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    Pageable pageable = PageRequest.of(page, size);
    int totalpage = 0;
    Pagination pagination = new Pagination(page, size, totalpage, 0l);

    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      pagination
          .setTotalRecords(plantEquipmentCalibrationService.getCountPlantEquipmentCalibration());
      return new ResponseEntity<Object>(
          new PaginatedContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
              mapper.map(plantEquipmentCalibrationService.getAllPlantEquipmentCalibration(pageable),
                  PlantEquipmentCalibrationResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    if (currentUserPermissionPlantService.getPermissionPlantCodeByCurrentUser(currentUser,
        PermissionConstants.VIEW_PLANT_EQUIPMENT_CALIBRATION).contains(plantCode)) {
      pagination.setTotalRecords(
          plantEquipmentCalibrationService.getCountPlantEquipmentCalibrationByPlantCode(plantCode));
      return new ResponseEntity<Object>(
          new PaginatedContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
              mapper.map(plantEquipmentCalibrationService.getPlantEquipmentCalibrationByPlantCode(
                  plantCode, pageable), PlantEquipmentCalibrationResponseDto.class),
              RestApiResponseStatus.OK, pagination),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}
