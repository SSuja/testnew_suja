package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MaterialState;
import com.tokyo.supermix.data.repositories.MaterialStateRepository;

@Service
public class MaterialStateServiceImpl implements MaterialStateService {

  @Autowired
  private MaterialStateRepository materialStateRepository;

  @Transactional
  public MaterialState createMaterialState(MaterialState materialState) {
    return materialStateRepository.save(materialState);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialStateExist(String materialState) {
    return materialStateRepository.existsByMaterialState(materialState);
  }

  @Transactional(readOnly = true)
  public boolean isMaterialStateExist(Long id) {
    return materialStateRepository.existsById(id);
  }

  @Transactional
  public List<MaterialState> getAllMaterialStates() {
    return materialStateRepository.findAll();
  }

  @Transactional
  public MaterialState getMaterialStateById(Long id) {
    return materialStateRepository.findById(id).get();
  }

  @Transactional
  public MaterialState updateMaterialState(MaterialState materialState) {
    return materialStateRepository.save(materialState);
  }

  public boolean isUpdatedMaterialStateExist(Long id, String materialState) {
    if ((!getMaterialStateById(id).getMaterialState().equalsIgnoreCase(materialState))
        && (isMaterialStateExist(materialState))) {
      return true;
    }
    return false;
  }

  @Transactional
  public void deleteMaterialState(Long id) {
    materialStateRepository.deleteById(id);
  }

}
