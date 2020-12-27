package com.tokyo.supermix.server.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.UploadImage;
import com.tokyo.supermix.data.repositories.UploadImageRepository;

@Service
public class UploadImageServiceImpl implements UploadImageService {

  @Autowired
  private UploadImageRepository uploadImageRepository;

  @Transactional
  public void uploadImage(UploadImage uploadImage) {
    uploadImageRepository.save(uploadImage);
  }
}
