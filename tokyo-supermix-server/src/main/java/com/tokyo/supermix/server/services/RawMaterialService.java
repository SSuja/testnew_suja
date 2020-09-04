package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.RawMaterial;

public interface RawMaterialService {
  public void saveRawMaterial(RawMaterial rawMaterial);

  public boolean isRawMaterialNameExist(String name);

  public boolean isRawMaterialExist(Long id);

  public List<RawMaterial> getAllRawMaterials();

  public RawMaterial getRawMaterialById(Long id);

  public boolean isUpdatedNameExist(Long id, String name);

  public void deleteRawMaterial(Long id);

  public Page<RawMaterial> searchRawMaterial(Predicate predicate, int page, int size);

  public List<RawMaterial> getAllActiveRawMaterials();

  public List<RawMaterial> getByMaterialSubCategoryId(Long materialSubCategoryId);

  public List<RawMaterial> getRawMaterialsByPlantCode(String plantCode);

  public List<RawMaterial> getRawMaterialsByMaterialSubCategoryAndPlantCode(
      Long materialSubCategoryId, String plantCode);
}
