package com.tokyo.supermix.server.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.UploadImageRequestDto;
import com.tokyo.supermix.data.entities.UploadImage;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.exception.TokyoSupermixFileStorageException;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.UploadImageService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@CrossOrigin(origins = "*")
@RestController
public class UploadImageController {

  @Autowired
  private ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  private Mapper mapper;
  @Autowired
  private UploadImageService uploadImageService;
  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping(value = EndpointURI.UPLOAD_IMAGE)
  public ResponseEntity<Object> updateImages(
      @Valid @RequestBody UploadImageRequestDto uploadImageRequestDto,
      @RequestParam(value = "image", required = false) MultipartFile file)
      throws TokyoSupermixFileStorageException, IOException {
    uploadImageRequestDto.setTestImage(fileStorageService.storeFile(file));
    uploadImageService.uploadImage(mapper.map(uploadImageRequestDto, UploadImage.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPLOAD_IMAGE), HttpStatus.OK);
  }
}
