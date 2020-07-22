package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.notification.EmailNotification;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private EmailNotification emailNotification;

  @Transactional
  public void saveRawMaterial(RawMaterial rawMaterial) {
    if (rawMaterialRepository.save(rawMaterial) != null) {
      emailNotification.sendRawmaterialCreationEmail(rawMaterial);
    }
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialNameExist(String name) {
    return rawMaterialRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialExist(Long id) {
    return rawMaterialRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> getAllRawMaterials() {
    return rawMaterialRepository.findAll();
  }

  @Transactional(readOnly = true)
  public RawMaterial getRawMaterialById(Long id) {
    return rawMaterialRepository.findById(id).get();
  }

  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getRawMaterialById(id).getName().equalsIgnoreCase(name))
        && (isRawMaterialNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRawMaterial(Long id) {
    rawMaterialRepository.deleteById(id);
  }

  @Override
  public Page<RawMaterial> searchRawMaterial(Predicate predicate, int page, int size) {
    return rawMaterialRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.Direction.ASC, "id"));
  }
}
