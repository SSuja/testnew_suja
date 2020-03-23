package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.SieveAcceptedValue;
import com.tokyo.supermix.data.repositories.SieveAcceptedValueRepository;

@Service
public class SieveAcceptedValueServiceImpl implements SieveAcceptedValueService {
  @Autowired
  private SieveAcceptedValueRepository sieveAcceptedValueRepository;

  @Transactional
  public SieveAcceptedValue saveSieveAcceptedValue(SieveAcceptedValue sieveAcceptedValue) {
    return sieveAcceptedValueRepository.save(sieveAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<SieveAcceptedValue> getAllSieveAcceptedValues() {
    return sieveAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isSieveAcceptedValueExist(Long id) {
    return sieveAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public SieveAcceptedValue getSieveAcceptedValueById(Long id) {
    return sieveAcceptedValueRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteSieveAcceptedValue(Long id) {
    sieveAcceptedValueRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isSieveSizeExist(Long sieveSizeId) {
    return sieveAcceptedValueRepository.existsBySieveSizeId(sieveSizeId);
  }
}
