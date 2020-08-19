package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.config.export.EnrollWriter;
import com.tokyo.supermix.config.export.PlantEquipmentFillManager;
import com.tokyo.supermix.config.export.PlantEquipmentLayouter;
import com.tokyo.supermix.data.dto.PlantEquipmentRequestDto;
import com.tokyo.supermix.data.dto.PlantEquipmentResponseDto;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.rest.response.ValidationFailureResponse;
import com.tokyo.supermix.security.CurrentUser;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.PlantEquipmentService;
import com.tokyo.supermix.server.services.PlantService;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.FileStorageConstants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@CrossOrigin(origins = "*")
@RestController
public class PlantEquipmentController {

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private PlantEquipmentService plantEquipmentService;
  @Autowired
  private PlantService plantService;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private FileStorageService fileStorageService;
  private static final Logger logger = Logger.getLogger(PlantEquipmentController.class);

  // Add EquipmentPlant
  @PostMapping(value = EndpointURI.PLANT_EQUIPMENT)
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
  @GetMapping(value = EndpointURI.PLANT_EQUIPMENTS)
  public ResponseEntity<Object> getAllPlantEquipments() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
        mapper.map(plantEquipmentService.getAllPlantEquipments(), PlantEquipmentResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  // Delete EquipmentPlant
  @DeleteMapping(value = EndpointURI.PLANTEQUIPMENT_BY_SERIALNO)
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
  @GetMapping(value = EndpointURI.PLANTEQUIPMENT_BY_SERIALNO)
  public ResponseEntity<Object> getPlantEquipmentByserialNo(@PathVariable String serialNo) {
    if (plantEquipmentService.isPlantEquipmentExist(serialNo)) {
      logger.debug("Get PlantEquipment by PlantEquipment Serial number");
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENT_SERIALNO,
          mapper.map(plantEquipmentService.getPlantEquipmentBySerialNo(serialNo),
              PlantEquipmentResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTEQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getPlantEquipmentNotExist()), HttpStatus.BAD_REQUEST);
  }

  // Update EquipmentPlant
  @PutMapping(value = EndpointURI.PLANT_EQUIPMENT)
  public ResponseEntity<Object> updatePlantEquipment(
      @Valid @RequestBody PlantEquipmentRequestDto plantequipmentRequestDto) {
    if (plantEquipmentService.isPlantEquipmentExist(plantequipmentRequestDto.getSerialNo())) {
      plantEquipmentService
          .savePlantEquipment(mapper.map(plantequipmentRequestDto, PlantEquipment.class));
      return new ResponseEntity<>(
          new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPDATE_PLANTEQUIPMENT_SUCCESS),
          HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANTEQUIPMENT_SERIALNO,
        validationFailureStatusCodes.getPlantEquipmentNotExist()), HttpStatus.BAD_REQUEST);

  }

  @GetMapping(value = EndpointURI.PLANTEQUIPMENT_SEARCH)
  public ResponseEntity<Object> getPlantEquipmentSearch(
      @QuerydslPredicate(root = PlantEquipment.class) Predicate predicate,
      @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
        plantEquipmentService.searchPlantEquipment(predicate, page, size),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.GET_PLANTEQUIPMENTS_BY_PLANT_CODE)
  public ResponseEntity<Object> getPlantEquipmentByPlantCode(@PathVariable String plantCode) {
    if (plantService.isPlantExist(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
          mapper.map(plantEquipmentService.getPlantEquipmentByPlantCode(plantCode),
              PlantEquipmentResponseDto.class),
          RestApiResponseStatus.OK), HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PLANT_EQUIPMENTS_BY_PLANT)
  public ResponseEntity<Object> getAllPlantEquipmentsByplant(@CurrentUser UserPrincipal currentUser,
      @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
          mapper.map(plantEquipmentService.getAllPlantEquipmentByPlant(currentUser),
              PlantEquipmentResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PLANT_EQUIPMENT)
        .contains(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
          mapper.map(plantEquipmentService.getPlantEquipmentByPlantCode(plantCode),
              PlantEquipmentResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = EndpointURI.PLANT_EQUIPMENTS_BY_CALIBRATION_TRUE_AND_EQUIPMENTID)
  public ResponseEntity<Object> getAllPlantEquipmentsByCalibrationTrueAndEquipment(
      @CurrentUser UserPrincipal currentUser, @PathVariable Long equipmentId,
      @PathVariable String plantCode) {
    if (plantCode.equalsIgnoreCase(Constants.ADMIN)) {
      return new ResponseEntity<>(
          new ContentResponse<>(Constants.PLANTEQUIPMENTS,
              mapper
                  .map(
                      plantEquipmentService
                          .getAllPlantEquipmentsByCalibrationExistTrueAndEquipmentId(equipmentId),
                      PlantEquipmentResponseDto.class),
              RestApiResponseStatus.OK),
          null, HttpStatus.OK);
    }
    if (currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PLANT_EQUIPMENT)
        .contains(plantCode)) {
      return new ResponseEntity<>(new ContentResponse<>(Constants.PLANTEQUIPMENTS,
          mapper.map(plantEquipmentService
              .getAllPlantEquipmentsByCalibrationExistTrueAndEquipmentIdAndPlantCode(equipmentId,
                  plantCode),
              PlantEquipmentResponseDto.class),
          RestApiResponseStatus.OK), null, HttpStatus.OK);
    }
    return new ResponseEntity<>(new ValidationFailureResponse(Constants.PLANT,
        validationFailureStatusCodes.getPlantNotExist()), HttpStatus.BAD_REQUEST);
  }
  @GetMapping(value = EndpointURI.EXPORT_PLANT_EQUIPMENT)
  public ResponseEntity<Object> exportPlantEquiment(HttpServletResponse response)
      throws ClassNotFoundException {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet worksheet = workbook.createSheet(FileStorageConstants.PLANT_EQUIPMENT_WORK_SHEET);
    int startRowIndex = 0;
    int startColIndex = 0;
    PlantEquipmentLayouter.buildReport(worksheet, startRowIndex, startColIndex);
    PlantEquipmentFillManager.fillReport(worksheet, startRowIndex, startColIndex,
        plantEquipmentService.getAllPlantEquipments());
    String fileName = FileStorageConstants.PLANT_EQUIPMENT_FILE_NAME;
    response.setHeader("Content-Disposition", "inline; filename=" + fileName);
    response.setContentType("application/vnd.ms-excel");
    EnrollWriter.write(response, worksheet);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.EXPORT_SUCCESS),
        HttpStatus.OK);
  }
  
  @PostMapping(value = EndpointURI.UPLOAD_PLANT_EQUIPMENT)
  public ResponseEntity<Object> uploadPlantEquipment(@RequestParam("file") MultipartFile file) {
    fileStorageService.uploadCsv(file);
    fileStorageService.importPlantEquipment(file);
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, FileStorageConstants.UPLOAD_SUCCESS),
        HttpStatus.OK);
  }
}
