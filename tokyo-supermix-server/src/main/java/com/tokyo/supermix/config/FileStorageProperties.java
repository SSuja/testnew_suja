package com.tokyo.supermix.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir1;
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadDir1() {
      return uploadDir1;
    }

    public void setUploadDir1(String uploadDir1) {
      this.uploadDir1 = uploadDir1;
    }  
}
