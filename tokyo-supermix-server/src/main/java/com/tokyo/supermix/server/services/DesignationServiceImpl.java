package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService {

  @Autowired
  private DesignationRepository designationRepository;

  @Transactional(readOnly = true)
  public List<Designation> getAllDesignations() {
    return designationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDesignationExist(Long id) {
    return designationRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Designation getDesignationById(Long id) {
    return designationRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteDesignation(Long id) {
    designationRepository.deleteById(id);
  }

  @Transactional
  public void saveDesignation(Designation designation) {
    designationRepository.save(designation);
  }

  @Transactional(readOnly = true)
  public boolean isDesignationExist(String designation) {
    return designationRepository.existsByName(designation);

  }

  public boolean isUpdatedDesignationNameExist(Long id, String designationName) {
    if ((!getDesignationById(id).getName().equalsIgnoreCase(designationName))
        && (isDesignationExist(designationName))) {
      return true;
    }
    return false;
  }

}
