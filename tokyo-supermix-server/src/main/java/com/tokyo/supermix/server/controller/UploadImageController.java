package com.tokyo.supermix.server.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.server.services.UploadImageService;
import com.tokyo.supermix.util.ValidationFailureStatusCodes;

@RestController
@CrossOrigin(origins = "*")
public class UploadImageController {

  @Autowired
  UploadImageService uploadImageService;
  @Autowired
  ValidationFailureStatusCodes validationFailureStatusCodes;
  @Autowired
  Mapper mapper;
  private static final Logger logger = Logger.getLogger(UploadImageController.class);

}
