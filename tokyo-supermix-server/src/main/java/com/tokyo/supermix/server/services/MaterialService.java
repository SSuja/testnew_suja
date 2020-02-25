package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Material;

public interface MaterialService {
  public Material createMaterial(Material material);

  public boolean isMaterialNameExist(String name);

  public boolean isMaterialExist(String code);

  public List<Material> getAllMaterials();

  public Material getMaterialByCode(String code);

  public Material updateMaterial(Material material);

  public boolean isUpdatedNameExist(String code, String name);

  public void deleteMaterial(String code);
}
