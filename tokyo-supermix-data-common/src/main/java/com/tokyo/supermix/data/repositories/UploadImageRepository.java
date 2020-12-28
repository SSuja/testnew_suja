package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.UploadImage;

public interface UploadImageRepository extends JpaRepository<UploadImage, Long> {

  public UploadImage findByName(String name);

  public boolean existsByMaterialTestCode(String materialTestCode);

  public boolean existsByFinishProductTestCode(String finishProductTestCode);

  public List<UploadImage> findByMaterialTestCode(String materialTestCode);

  public List<UploadImage> findByFinishProductTestCode(String finishProductTestCode);
}
