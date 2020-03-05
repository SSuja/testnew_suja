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
import com.tokyo.supermix.data.dto.PlantEquipmentRequestDto;
import com.tokyo.supermix.data.dto.PlantEquipmentResponseDto;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantEquipmentService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class PlantEquipmentController {

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantEquipmentService plantEquipmentService;
  private static final Logger logger = Logger.getLogger(PlantEquipmentController.class);

  // Add EquipmentPlant
  @PostMapping(value = EndpointURI.PLANTEQUIPMENT)
  public ResponseEntity<Object> createEquipmentPlant(
      @Valid @RequestBody PlantEquipmentRequestDto plantequipmentRequestDto) {
    if (plantEquipmentService.isPlantEquipmentExist(plantequipmentRequestDto.getSerialNo())) {
      logger.debug("PlantEquipment SerailNumber already exists: ");
      return new ResponseEntity<>(
          new ValidationFailureResponse(Constants.PLANTEQUIPMENT_SERIALNO,
              validationFailureStatusCodes.getPlantEquipmentAlreadyExist()),
          HttpStatus.BAD_REQUEST);
    }
    plantEquipmentService
        .savePlantEquipment(mapper.map(plantequipmentRequestDto, PlantEquipment.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PLANTEQUIPMENT_SUCCESS),
        HttpStatus.OK);
  }

  // Get All EquipmentPlants
  @GetMapping(value = EndpointURI.PLANTEQUIPMENTS)
  public ResponseEntity<Object> getAllPlantEquipments() {
    List<PlantEquipmentResponseDto> plantEquipmentResponseDtoList =
        mapper.map(plantEquipmentService.getAllPlantEquipments(), PlantEquipmentResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
        plantEquipmentResponseDtoList, RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Delete EquipmentPlant
  @DeleteMapping(value = EndpointURI.DELETE_PLANTEQUIPMENT)
  public ResponseEntity<Object> deletePlantEquipment(@PathVariable String serialNo) {
    if (plantEquipmentService.isPlantEquipmentExist(serialNo)) {
      logger.debug("delete Planteuipment by serialNo");
      plantEquipmentService.deletePlantEquipment(serialNo);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PLANTEQUIPMENT_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTEQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getPlantEquipmentNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get By SerialNo
  @GetMapping(value = EndpointURI.GET_PLANTEQUIPMENT_BY_SERIALNO)
  public ResponseEntity<Object> getPlantEquipmentByserialNo(@PathVariable String serialNo) {
    if (plantEquipmentService.isPlantEquipmentExist(serialNo)) {
      logger.debug("Get PlantEquipment by PlantEquipment Serial number");
      PlantEquipmentResponseDto PlantequipmentResponseDto =
          mapper.map(plantEquipmentService.getPlantEquipmentBySerialNo(serialNo),
              PlantEquipmentResponseDto.class);
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENT_SERIALNO,
          mapper.map(PlantequipmentResponseDto, PlantEquipmentResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTEQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getPlantEquipmentNotExist()), HttpStatus.BAD_REQUEST);

  }

  // Update EquipmentPlant
  @PutMapping(value = EndpointURI.PLANTEQUIPMENT)
  public ResponseEntity<Object> updatePlantEquipment(
      @Valid @RequestBody PlantEquipmentRequestDto plantequipmentRequestDto) {
    if (plantEquipmentService.isPlantEquipmentExist(plantequipmentRequestDto.getSerialNo())) {
      PlantEquipment plantequipment = mapper.map(plantequipmentRequestDto, PlantEquipment.class);
      plantEquipmentService.savePlantEquipment(plantequipment);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANTEQUIPMENT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTEQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getPlantEquipmentNotExist()), HttpStatus.BAD_REQUEST);

  }
}