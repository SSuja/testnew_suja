package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.UploadImage;

public interface UploadImageService {
  public void uploadImage(UploadImage uploadImage);

  public List<UploadImage> getAllImages();
}
