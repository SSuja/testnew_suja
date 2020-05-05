package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.PlantEquipmentCalibrationRequestDto;
import com.tokyo.supermix.data.dto.PlantEquipmentCalibrationResponseDto;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.enums.CalibrationType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantEquipmentCalibrationService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class PlantEquipmentCalibrationController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantEquipmentCalibrationService plantEquipmentCalibrationService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(PlantEquipmentCalibrationController.class);

  // post API for PlantEquipmentCalibration
  @PostMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION)
  public ResponseEntity<Object> createPlantEquipmentCalibration(
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
        Constants.ADD_EQUIPMENT_PLANT_CALIBRATION_SUCCESS), HttpStatus.OK);

  }

  // get all PlantEquipmentCalibration
  @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS)
  public ResponseEntity<Object> getAllPlantEquipmentCalibrations() {
    List<PlantEquipmentCalibration> PlantEquipmentCalibrationList =
        plantEquipmentCalibrationService.getAllPlantEquipmentCalibration();
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
        mapper.map(PlantEquipmentCalibrationList, PlantEquipmentCalibrationResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  // get PlantEquipmentCalibration by id
  @GetMapping(value = EndpointURI.GET_EQUIPMENT_PLANT_CALIBRATION_BY_ID)
  public ResponseEntity<Object> getPlantEquipmentCalibrationById(@PathVariable Long id) {
    if (plantEquipmentCalibrationService.isPlantEquipmentCalibrationExit(id)) {
      logger.debug("Get PlantEquipmentCalibration by id ");
      PlantEquipmentCalibration plantEquipmentCalibration =
          plantEquipmentCalibrationService.getPlantEquipmentCalibrationById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATION,
          mapper.map(plantEquipmentCalibration, PlantEquipmentCalibrationResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("invalid");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EQUIPMENT_PLANT_CALIBRATION,
            validationFailureStatusCodes.getPlantEquipmentCalibrationNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // DELETE API for PlantEquipmentCalibration
  @DeleteMapping(value = EndpointURI.DELETE_EQUIPMENT_PLANT_CALIBRATION)
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
  public ResponseEntity<Object> getPlantEquipmentCalibrationSearch(
      @QuerydslPredicate(root = PlantEquipmentCalibration.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
        plantEquipmentCalibrationService.searchPlantEquipmentCalibration(predicate, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
