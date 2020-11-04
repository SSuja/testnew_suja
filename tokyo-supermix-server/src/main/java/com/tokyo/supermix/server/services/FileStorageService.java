package com.tokyo.supermix.server.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.tokyo.supermix.rest.exception.TokyoSupermixFileNotFoundException;
import com.tokyo.supermix.rest.exception.TokyoSupermixFileStorageException;
import org.springframework.core.io.Resource;

public interface FileStorageService {
  public void uploadCsv(MultipartFile file);

  public void importMixDesgin(MultipartFile file);

  public void importCustomer(MultipartFile file);

  public void importSupplier(MultipartFile file);

  public void importEmployee(MultipartFile file, HttpServletRequest request);

  public void importProject(MultipartFile file);

  public void importPlantEquipment(MultipartFile file);

  public void importRawMaterial(MultipartFile file,HttpServletRequest request);
  
  public String storeFile(MultipartFile file)  throws IOException, TokyoSupermixFileStorageException;
  
  public Resource loadFileAsResource(String fileName) throws TokyoSupermixFileNotFoundException;
}
