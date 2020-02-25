package com.tokyo.supermix.server.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.EquipmentPlantCalibrationRequestDto;
import com.tokyo.supermix.data.dto.EquipmentPlantCalibrationResponceDto;
import com.tokyo.supermix.data.entities.EquipmentPlantCalibration;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquipmentPlantCalibrationService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class EquipmentPlantCalibrationController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private EquipmentPlantCalibrationService equipmentPlantCalibrationService;
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  private static final Logger logger = Logger.getLogger(EquipmentPlantCalibrationController.class);

  // post API for equipmentPlantCalibration
  @PostMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATION)
  public ResponseEntity<Object> createEquipmentPlantCalibration(
      @Valid @RequestBody EquipmentPlantCalibrationRequestDto equipmentPlantCalibrationDto) {
    equipmentPlantCalibrationService.createEquipmentPlantCalibration(
        mapper.map(equipmentPlantCalibrationDto, EquipmentPlantCalibration.class));
    return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
        Constants.ADD_EQUIPMENT_PLANT_CALIBRATION_SUCCESS), HttpStatus.OK);
  }

  // get all equipmentPlantCalibration
  @GetMapping(value = EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS)
  public ResponseEntity<Object> getAllEquipmentPlantCalibrations() {
    List<EquipmentPlantCalibration> equipmentPlantCalibrationList =
        equipmentPlantCalibrationService.getAllEquipmentPlantCalibration();
    return new ResponseEntity<Object>(new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATIONS,
        mapper.map(equipmentPlantCalibrationList, EquipmentPlantCalibrationResponceDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  // get equipmentPlantCalibration by id
  @GetMapping(value = EndpointURI.GET_EQUIPMENT_PLANT_CALIBRATION_BY_ID)
  public ResponseEntity<Object> getEquipmentPlantCalibrationById(@PathVariable Long id) {
    if (equipmentPlantCalibrationService.isEquipmentPlantCalibrationExit(id)) {
      logger.debug("Get EquipmentPlantCalibration by id ");
      EquipmentPlantCalibration equipmentPlantCalibration =
          equipmentPlantCalibrationService.getEquipmentPlantCalibrationById(id);
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENT_PLANT_CALIBRATION,
          mapper.map(equipmentPlantCalibration, EquipmentPlantCalibrationResponceDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("invalid");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EQUIPMENT_PLANT_CALIBRATION,
            validationFailureStatusCodes.getEquipmentPlantCalibrationNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // DELETE API for equipmentPlantCalibration
  @DeleteMapping(value = EndpointURI.DELETE_EQUIPMENT_PLANT_CALIBRATION)
  public ResponseEntity<Object> deleteEquipmentPlantCalibration(@PathVariable Long id) {
    if (equipmentPlantCalibrationService.isEquipmentPlantCalibrationExit(id)) {
      equipmentPlantCalibrationService.deleteEquipmentPlantCalibration(id);
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.EQUIPMENT_PLANT_CALIBRATION_DELETED), HttpStatus.OK);
    }
    logger.debug("invalid equipmentPlantCalibration");
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EQUIPMENT_PLANT_CALIBRATION,
            validationFailureStatusCodes.getEquipmentPlantCalibrationNotExist()),
        HttpStatus.BAD_REQUEST);
  }

  // update API for equipmentPlantCalibration
  @PutMapping(value = EndpointURI.UPDATE_EQUIPMENT_PLANT_CALIBRATION)
  public ResponseEntity<Object> updateEquipmentPlantCalibration(
      @Valid @RequestBody EquipmentPlantCalibrationRequestDto equipmentPlantCalibrationDto) {
    if (equipmentPlantCalibrationService
        .isEquipmentPlantCalibrationExit(equipmentPlantCalibrationDto.getId())) {
      equipmentPlantCalibrationService.createEquipmentPlantCalibration(
          mapper.map(equipmentPlantCalibrationDto, EquipmentPlantCalibration.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_EQUIPMENT_PLANT_CALIBRATION_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        new ValidationFailureResponse(Constants.EQUIPMENT_PLANT_CALIBRATION,
            validationFailureStatusCodes.getEquipmentPlantCalibrationNotExist()),
        HttpStatus.BAD_REQUEST);
  }
}
