package com.tokyo.supermix.server.controller;

import javax.validation.Valid;
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
import com.tokyo.supermix.data.dto.ProcessSampleLoadRequestDto;
import com.tokyo.supermix.data.dto.ProcessSampleLoadResponseDto;
import com.tokyo.supermix.data.entities.ProcessSampleLoad;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.ProcessSampleLoadService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class ProcessSampleLoadController {
  @Autowired
  private ProcessSampleLoadService processSampleLoadService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantService plantService;

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_LOADS)
   public ResponseEntity<Object> getProcessSampleLoads() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLE_LOADS,
        mapper.map(processSampleLoadService.getAllProcessSampleLoads(),
            ProcessSampleLoadResponseDto.class),
        RestApiResponseStatus.OK), HttpStatus.OK);
  }

  @PostMapping(value = EndpointURI.PROCESS_SAMPLE_LOAD)
   public ResponseEntity<Object> createProcessSampleLoad(
      @Valid @RequestBody ProcessSampleLoadRequestDto processSampleLoadRequestDto) {
    processSampleLoadService
        .saveProcessSampleLoad(mapper.map(processSampleLoadRequestDto, ProcessSampleLoad.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_PROCESS_SAMPLE_LOAD_SUCCESS),
        HttpStatus.OK);

  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_LOAD_BY_ID)
  public ResponseEntity<Object> getProcessSampleLoadById(@PathVariable Long id) {
    if (processSampleLoadService.isProcessSampleLoadExist(id)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLE_LOAD,
          mapper.map(processSampleLoadService.getProcessSampleLoadById(id),
              ProcessSampleLoadResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_LOAD_ID,
        validationFailureStatusCodes.getProcessSampleLoadNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.PROCESS_SAMPLE_LOAD)
   public ResponseEntity<Object> updateProcessSampleLoad(
      @Valid @RequestBody ProcessSampleLoadRequestDto processSampleLoadRequestDto) {
    if (processSampleLoadService.isProcessSampleLoadExist(processSampleLoadRequestDto.getId())) {
      processSampleLoadService
          .saveProcessSampleLoad(mapper.map(processSampleLoadRequestDto, ProcessSampleLoad.class));
      return new ResponseEntity<>(new BasicResponse<>(RestApiResponseStatus.OK,
          Constants.UPDATE_PROCESS_SAMPLE_LOAD_SUCCESS), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_LOAD,
        validationFailureStatusCodes.getProcessSampleLoadNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(value = EndpointURI.PROCESS_SAMPLE_LOAD_BY_ID)
  public ResponseEntity<Object> deleteProcessSampleLoadById(@PathVariable Long id) {
    if (processSampleLoadService.isProcessSampleLoadExist(id)) {
      processSampleLoadService.deleteProcessSampleLoad(id);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.PROCESS_SAMPLE_LOAD_DELETED),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PROCESS_SAMPLE_LOAD_ID,
        validationFailureStatusCodes.getProcessSampleLoadNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PROCESS_SAMPLE_LOADS_BY_PLANT_CODE)
  public ResponseEntity<Object> getProcessSampleLoadByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PROCESS_SAMPLE_LOAD,
          mapper.map(processSampleLoadService.getProcessSampleLoadByPlantCode(plantCode),
              ProcessSampleLoadResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
}
