package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

  @Transactional(readOnly = true)
  public List<UploadImage> getAllImages() {
    return uploadImageRepository.findAll();
  }
}
