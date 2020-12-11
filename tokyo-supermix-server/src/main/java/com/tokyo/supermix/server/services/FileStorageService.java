package com.tokyo.supermix.server.services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
  public void uploadCsv(MultipartFile file);

  public void importMixDesgin(MultipartFile file);

  public void importCustomer(MultipartFile file);

  public void importSupplier(MultipartFile file);

  public void importEmployee(MultipartFile file, HttpServletRequest request);

  public void importProject(MultipartFile file);

  public void importPlantEquipment(MultipartFile file);

  public void importRawMaterial(MultipartFile file, HttpServletRequest request);

  public void importDeliverySample(MultipartFile file);
}
