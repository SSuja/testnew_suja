package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.UploadImage;

public interface UploadImageService {
  public void uploadImage(UploadImage uploadImage);

  public List<UploadImage> getAllImages();

  public List<UploadImage> getAllImagesByMaterialTest(String materialTestCode);

  public List<UploadImage> getAllImagesByFinishProductTest(String finishProductTestCode);

  public boolean existsByMaterialTestCode(String materialTestCode);

  public boolean existsByFinishProductTestCode(String finishProductTestCode);

}
