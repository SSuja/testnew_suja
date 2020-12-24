package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.UploadImage;

public interface UploadImageRepository extends JpaRepository<UploadImage, Long> {

  public UploadImage findByName(String name);
}
