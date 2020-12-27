package com.tokyo.supermix.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.data.dto.UploadImageRequestDto;
import com.tokyo.supermix.data.dto.UploadImageResponseDto;
import com.tokyo.supermix.data.entities.UploadImage;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.rest.enums.RestApiResponseStatus;
import com.tokyo.supermix.rest.exception.TokyoSupermixFileStorageException;
import com.tokyo.supermix.rest.response.BasicResponse;
import com.tokyo.supermix.rest.response.ContentResponse;
import com.tokyo.supermix.server.services.FileStorageService;
import com.tokyo.supermix.server.services.UploadImageService;
import com.tokyo.supermix.util.Constants;

@CrossOrigin(origins = "*")
@RestController
public class UploadImageController {
  @Autowired
  private Mapper mapper;
  @Autowired
  private UploadImageService uploadImageService;
  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  private ObjectMapper objectMapper;

  @PostMapping(value = EndpointURI.UPLOAD_IMAGE)
  public ResponseEntity<Object> updateImages(
      @RequestParam(value = "uploadImage", required = true) String uploadImageRequest,
      @RequestParam(value = "image", required = false) MultipartFile[] file)
      throws TokyoSupermixFileStorageException, IOException {
    UploadImageRequestDto uploadImageRequestDto =
        objectMapper.readValue(uploadImageRequest, UploadImageRequestDto.class);
    List<String> testImage = new ArrayList<>();
    Arrays.asList(file).stream().forEach(files -> {
      try {
        uploadImageRequestDto.setTestImage(fileStorageService.storeFile(files));
      } catch (TokyoSupermixFileStorageException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      testImage.add(files.getOriginalFilename());
    });
    uploadImageService.uploadImage(mapper.map(uploadImageRequestDto, UploadImage.class));
    return new ResponseEntity<>(
        new BasicResponse<>(RestApiResponseStatus.OK, Constants.UPLOAD_IMAGE), HttpStatus.OK);
  }

  @GetMapping(value = EndpointURI.UPLOAD_IMAGES)
  public ResponseEntity<Object> getAllImages() {
    return new ResponseEntity<>(new ContentResponse<>(Constants.UPLOAD_IMAGES,
        mapper.map(uploadImageService.getAllImages(), UploadImageResponseDto.class),
        RestApiResponseStatus.OK), null, HttpStatus.OK);
  }
}
