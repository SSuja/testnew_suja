package com.tokyo.supermix.data.dto.report;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public class ReportAttachDto {
  private MultipartFile file;

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }  
}
