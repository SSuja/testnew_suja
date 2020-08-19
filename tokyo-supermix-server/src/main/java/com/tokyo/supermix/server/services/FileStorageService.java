package com.tokyo.supermix.server.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  public void uploadCsv(MultipartFile file);

  public void importMixDesgin(MultipartFile file);

  public void importPlantEquipment(MultipartFile file);

}
