package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.SubBusinessUnit;
import com.tokyo.supermix.data.repositories.SubBusinessUnitRepository;

@Service
public class SubBusinessUnitServiceImpl implements SubBusinessUnitService {

  @Autowired
  private SubBusinessUnitRepository subBusinessUnitRepository;

  @Transactional(readOnly = true)
  public List<SubBusinessUnit> getAllSubBusinessUnits() {
    return subBusinessUnitRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isSubBusinessUnitExist(Long id) {
    return subBusinessUnitRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public SubBusinessUnit getSubBusinessUnitById(Long id) {
    return subBusinessUnitRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSubBusinessUnit(Long id) {
    subBusinessUnitRepository.deleteById(id);
  }

  @Transactional
  public void saveSubBusinessUnit(SubBusinessUnit subBusinessUnit) {
    subBusinessUnitRepository.save(subBusinessUnit);
  }

  @Transactional(readOnly = true)
  public boolean isSubBusinessUnitExist(String subBusinessUnit) {
    return subBusinessUnitRepository.existsByName(subBusinessUnit);
  }

  public boolean isUpdatedSubBusinessUnitNameExist(Long id, String subBusinessUnit) {
    if ((!getSubBusinessUnitById(id).getName().equalsIgnoreCase(subBusinessUnit))
        && (isSubBusinessUnitExist(subBusinessUnit))) {
      return true;
    }
    return false;
  }
}
