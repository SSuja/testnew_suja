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
import com.tokyo.supermix.data.dto.TableFormatDto;
import com.tokyo.supermix.data.entities.TableFormat;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.TableFormatService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class TableFormatController {
  @Autowired
  TableFormatService tableFormatService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  Mapper mapper;
  private static final Logger logger = Logger.getLogger(TableFormatController.class);

  @PostMapping(value = EndpointURI.TABLE_FORMAT)
  public ResponseEntity<Object> createTableFormat(
      @Valid @RequestBody TableFormatDto tableFormatDto) {
    tableFormatService.saveTableFormat(mapper.map(tableFormatDto, TableFormat.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_TABLE_FORMAT_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.TABLE_FORMATS)
  public ResponseEntity<Object> getAllTableFormats() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.TABLE_FORMATS,
        mapper.map(tableFormatService.getAllTableFormats(), TableFormatDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @DeleteMapping(value = EndpointURI.TABLE_FORMAT_BY_ID)
  public ResponseEntity<Object> deleteUnit(@PathVariable Long id) {
      tableFormatService.deleteTableFormat(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.TABLE_FORMAT_DELETED),
          HttpStatus.OK);
    
  }

  @GetMapping(value = EndpointURI.TABLE_FORMAT_BY_ID)
  public ResponseEntity<Object> getTableFormatById(@PathVariable Long id) {
    if (tableFormatService.isTableFormatExist(id)) {
      logger.debug("Id found");
      return new ResponseEntity<>(new ContentResponse<>(Constants.TABLE_FORMAT,
          mapper.map(tableFormatService.getTableFormatById(id), TableFormatDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    logger.debug("Invalid id");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TABLE_FORMAT,
        validationFailureStatusCodes.getUnitNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.TABLE_FORMAT)
  public ResponseEntity<Object> updateTableFormat(@Valid @RequestBody TableFormatDto tableFormatDto) {
    if (tableFormatService.isTableFormatExist(tableFormatDto.getId())) {
      tableFormatService.saveTableFormat(mapper.map(tableFormatDto, TableFormat.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.TABLE_FORMAT_UPDATED_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.TABLE_FORMAT,
        validationFailureStatusCodes.getUnitNotExist()), HttpStatus.BAD_REQUEST);
  }
}
