package com.tokyo.supermix.server.controller;

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
import com.tokyo.supermix.data.dto.EquipmentDto;
import com.tokyo.supermix.data.entities.Equipment;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.EquipmentService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import org.springframework.security.access.prepost.PreAuthorize;


@CrossOrigin(origins = "*")
@RestController
public class EquipmentController {
  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EquipmentService equipmentService;
  private static final Logger logger = Logger.getLogger(EquipmentController.class);

  // Add Equipment
  @PostMapping(value = EndpointURI.EQUIPMENT)
  @PreAuthorize("hasAuthority('add_equipment')")
  public ResponseEntity<Object> createEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {
    if (equipmentService.isNameExist(equipmentDto.getName())) {
      logger.debug("name is already exists: createEquipment(), isNameExist: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENT_NAME,
          validationFailureStatusCodes.getEquipmentAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    equipmentService.saveEquipment(mapper.map(equipmentDto, Equipment.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_EQUIPMENT_SUCCESS),
        HttpStatus.OK);
  }

  // Get All Equipments
  @GetMapping(value = EndpointURI.EQUIPMENTS)
  @PreAuthorize("hasAuthority('get_equipment')")
  public ResponseEntity<Object> getAllEquipments() {
    logger.debug("get all equipments");
    return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENTS,
        mapper.map(equipmentService.getAllEquipments(), EquipmentDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Get Equipment By Id
  @GetMapping(value = EndpointURI.GET_EQUIPMENT_BY_ID)
  public ResponseEntity<Object> getEquipmentById(@PathVariable Long id) {
    if (equipmentService.isEquipmentExist(id)) {
      logger.debug("Get Equipment By Id");
      return new ResponseEntity<>(new ContentResponse<>(Constants.EQUIPMENT,
          mapper.map(equipmentService.getEquipmentById(id), EquipmentDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENMT_ID,
        validationFailureStatusCodes.getEquipmentNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Delete Equipment
  @DeleteMapping(value = EndpointURI.DELETE_EQUIPMENT)
  @PreAuthorize("hasAuthority('delete_equipment')")
  public ResponseEntity<Object> deleteEquipment(@PathVariable Long id) {
    if (equipmentService.isEquipmentExist(id)) {
      logger.debug("delete equipment by id");
      equipmentService.deleteEquipment(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.EQUIPMENT_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENMT_ID,
        validationFailureStatusCodes.getEquipmentNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update Equipment
  @PutMapping(value = EndpointURI.EQUIPMENT)
  @PreAuthorize("hasAuthority('edit_equipment')")
  public ResponseEntity<Object> updateEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {
    if (equipmentService.isEquipmentExist(equipmentDto.getId())) {
      if (equipmentService.isUpdatedNameExist(equipmentDto.getId(), equipmentDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENT_NAME,
            validationFailureStatusCodes.getEquipmentAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      equipmentService.saveEquipment(mapper.map(equipmentDto, Equipment.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_EQUIPMENT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.EQUIPMENMT_ID,
        validationFailureStatusCodes.getEquipmentNotExist()), HttpStatus.BAD_REQUEST);
  }
}
