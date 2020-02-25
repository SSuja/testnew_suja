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
import com.tokyo.supermix.data.dto.EquipmentPlantRequestDto;
import com.tokyo.supermix.data.dto.EquipmentPlantResponseDto;
import com.tokyo.supermix.data.entities.EquipmentPlant;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquipmentPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class EquipmentPlantController {

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EquipmentPlantService equipmentPlantService;
  private static final Logger logger = Logger.getLogger(EquipmentPlantController.class);

  // Add EquipmentPlant
  @PostMapping(value = EndpointURI.EQUIPMENTPLANT)
  public ResponseEntity<Object> createEquipmentPlant(
      @Valid @RequestBody EquipmentPlantRequestDto equipmentPlantRequestDto) {
    if (equipmentPlantService.isEquipmentPlantExist(equipmentPlantRequestDto.getSerialNo())) {
      logger.debug("Equipment SerailNumber already exists: ");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENTPLANT,
          validationFailureStatusCodes.getEuipmentPlantAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    equipmentPlantService
        .saveEquipmentPlant(mapper.map(equipmentPlantRequestDto, EquipmentPlant.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EQUIPMENTPLANT_SUCCESS),
        HttpStatus.OK);
  }

  // Get All EquipmentPlants
  @GetMapping(value = EndpointURI.EQUIPMENTPLANTS)
  public ResponseEntity<Object> getAllEquipmentPlants() {
    List<EquipmentPlant> EquipmentPlantList = equipmentPlantService.getAllEquipmentPlants();
    List<EquipmentPlantResponseDto> EquipmentPlantResponseDtoList =
        mapper.map(EquipmentPlantList, EquipmentPlantResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENTPLANTS,
        EquipmentPlantResponseDtoList, RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Delete EquipmentPlant
  @DeleteMapping(value = EndpointURI.DELETE_EQUIPMENTPLANT)
  public ResponseEntity<Object> deleteEquipment(@PathVariable String serialNo) {
    if (equipmentPlantService.isEquipmentPlantExist(serialNo)) {
      logger.debug("delete euipment by serialNo");
      equipmentPlantService.deleteEquipmentPlant(serialNo);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EQUIPMENT_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getEuipmentPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Get By SerialNo
  @GetMapping(value = EndpointURI.GET_EQUIPMENTPLANT_BY_SERIALNO)
  public ResponseEntity<Object> getEquipmentPlantByserialNo(@PathVariable String serialNo) {
    if (equipmentPlantService.isEquipmentPlantExist(serialNo)) {
      logger.debug("Get EquipmentPlants by EquipmentPlant Serial number");
      EquipmentPlantResponseDto equipmentPlantResponseDto =
          mapper.map(equipmentPlantService.getEquipmentPlantBySerialNo(serialNo),
              EquipmentPlantResponseDto.class);
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENTPLANT,
          mapper.map(equipmentPlantResponseDto, EquipmentPlantResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getEuipmentPlantNotExist()), HttpStatus.BAD_REQUEST);


  }

  // Update EquipmentPlant
  @PutMapping(value = EndpointURI.EQUIPMENTPLANT)
  public ResponseEntity<Object> updateEquipmentPlant(
      @Valid @RequestBody EquipmentPlantRequestDto equipmentPlantRequestDto) {
    if (equipmentPlantService.isEquipmentPlantExist(equipmentPlantRequestDto.getSerialNo())) {
      EquipmentPlant equipmentPlant = mapper.map(equipmentPlantRequestDto, EquipmentPlant.class);
      equipmentPlantService.saveEquipmentPlant(equipmentPlant);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EQUIPMENTPLANT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getEuipmentPlantNotExist()), HttpStatus.BAD_REQUEST);

  }
}