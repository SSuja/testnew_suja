package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Material;
import com.tokyo.supermix.data.repositories.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {
  @Autowired
  private MaterialRepository materialRepository;

  @Transactional
  public Material createMaterial(Material material) {
    return materialRepository.save(material);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialNameExist(String name) {
    return materialRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialExist(String code) {
    return materialRepository.existsById(code);
  }

  @Transactional
  public List<Material> getAllMaterials() {
    return materialRepository.findAll();
  }

  @Transactional
  public Material getMaterialByCode(String code) {
    return materialRepository.findById(code).get();
  }

  @Transactional
  public Material updateMaterial(Material material) {
    return materialRepository.save(material);
  }

  public boolean isUpdatedNameExist(String code, String name) {
    if ((!getMaterialByCode(code).getName().equalsIgnoreCase(name))
        && (isMaterialNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterial(String code) {
    materialRepository.deleteById(code);
  }
}
