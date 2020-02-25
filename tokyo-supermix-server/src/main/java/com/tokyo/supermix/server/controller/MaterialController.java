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
import com.tokyo.supermix.data.dto.MaterialRequestDto;
import com.tokyo.supermix.data.dto.MaterialResponseDto;
import com.tokyo.supermix.data.entities.Material;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.server.services.MaterialService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin
public class MaterialController {
  @Autowired
  private MaterialService materialService;

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;

  @Autowired
  private Mapper mapper;

  private static final Logger logger = Logger.getLogger(MaterialController.class);

  @PostMapping(value = EndpointURI.MATERIAL)
  public ResponseEntity<Object> createMaterial(
      @Valid @RequestBody MaterialRequestDto materialRequestDto) {
    if (materialService.isMaterialNameExist(materialRequestDto.getName())) {
      logger.debug("Material already exists: createMaterial(), materialName: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_NAME,
          validationFailureStatusCodes.getMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    if (materialService.isMaterialExist(materialRequestDto.getCode())) {
      logger.debug("Material already exists: createMaterial(), materialCode: {}");
      return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CODE,
          validationFailureStatusCodes.getMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
    }
    Material material = mapper.map(materialRequestDto, Material.class);
    materialService.createMaterial(material);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.ADD_MATERIAL_SUCCESS),
        HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.MATERIALS)
  public ResponseEntity<Object> getAllMaterials() {
    List<Material> materialList = materialService.getAllMaterials();
    List<MaterialResponseDto> materialResponseDtoList =
        mapper.map(materialList, MaterialResponseDto.class);
    return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL, materialResponseDtoList,
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_MATERIAL_BY_CODE)
  public ResponseEntity<Object> getMaterialByCode(@PathVariable String code) {
    if (materialService.isMaterialExist(code)) {
      Material material = materialService.getMaterialByCode(code);
      return new ResponseEntity<>(new ContentResponse<>(Constants.MATERIAL,
          mapper.map(material, MaterialResponseDto.class), RestApiResponseStatus.OK),
          HttpStatus.OK);
    }
    logger.debug("No Material record exist for given code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CODE,
        validationFailureStatusCodes.getMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @PutMapping(value = EndpointURI.MATERIAL)
  public ResponseEntity<Object> updateMaterial(
      @Valid @RequestBody MaterialRequestDto materialRequestDto) {
    if (materialService.isMaterialExist(materialRequestDto.getCode())) {
      if (materialService.isUpdatedNameExist(materialRequestDto.getCode(),
          materialRequestDto.getName())) {
        return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_NAME,
            validationFailureStatusCodes.getMaterialAlreadyExist()), HttpStatus.BAD_REQUEST);
      }
      Material material = mapper.map(materialRequestDto, Material.class);
      materialService.updateMaterial(material);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_MATERIAL_SUCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Material record exist for given code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CODE,
        validationFailureStatusCodes.getMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping(EndpointURI.DELETE_MATERIAL)
  public ResponseEntity<Object> deleteMaterial(@PathVariable String code) {
    if (materialService.isMaterialExist(code)) {
      materialService.deleteMaterial(code);
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.DELETE_MATERIAL_SCCESS),
          HttpStatus.OK);
    }
    logger.debug("No Material record exist for given code");
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.MATERIAL_CODE,
        validationFailureStatusCodes.getMaterialNotExist()), HttpStatus.BAD_REQUEST);
  }

}
