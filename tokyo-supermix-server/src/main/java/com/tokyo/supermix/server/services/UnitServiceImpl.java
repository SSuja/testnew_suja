package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.data.repositories.UnitRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnitServiceImpl implements UnitService {
  @Autowired
  private UnitRepository unitRepository;

  @Transactional
  public void saveUnit(Unit unit) {
    unitRepository.save(unit);
  }

  @Transactional(readOnly = true)
  public List<Unit> getAllUnits() {    
    return unitRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteUnit(Long id) {
    unitRepository.deleteById(id);

  }

  @Transactional(readOnly = true)
  public Unit getUnitById(Long id) {
    return unitRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isUnitExist(Long id) {
    return unitRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public boolean isUnitExist(String unit) {
    return unitRepository.existsByUnit(unit);
  }

  public boolean isUpdatedUnitExist(Long id, String unit) {
    if ((!getUnitById(id).getUnit().equalsIgnoreCase(unit)) && (isUnitExist(unit))) {
      return true;
    }
    return false;
  }
}
